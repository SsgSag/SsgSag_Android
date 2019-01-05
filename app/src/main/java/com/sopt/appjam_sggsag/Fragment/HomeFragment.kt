package com.sopt.appjam_sggsag.Fragment

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.DefaultItemAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import com.google.gson.JsonParser
import com.sopt.appjam_sggsag.Network.ApplicationController
import com.sopt.appjam_sggsag.R
import com.sopt.appjam_sggsag.Data.PosterData
import com.sopt.appjam_sggsag.Post.PostPosterListResponse
import com.sopt.appjam_sggsag.CardStackAdapter
import com.sopt.appjam_sggsag.SpotDiffCallback
import com.sopt.appjam_sggsag.Network.NetworkService
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.find
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), CardStackListener {
    private val drawerLayout by lazy { drawer_layout }
    private var cardStackView: CardStackView? =null
//    private var LeftButtonView: CardStackView?= null
    private val manager by lazy { CardStackLayoutManager(context, this) }
//    private val adapter by lazy { CardStackAdapter(createPosters()) }
//    private val lbadapter by lazy  {LeftButtonAdpater(createPosters())}
//    private var manager: CardStackLayoutManager? = null
//    private val adapter:CardStackAdapter = CardStackAdapter(createPosters())
    private var homeFragmentView: View? = null

    //For Server Communication
    val WRITE_FRAGMENT_REQUESET_CODE = 1000
    val dataList: ArrayList<PosterData> by lazy {
        ArrayList<PosterData>()
    }
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }
    var inputPosterArr = ArrayList<PosterData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeFragmentView = inflater!!.inflate(R.layout.fragment_home, container, false)
//        getPosterListResponse()
//        setupNavigation()
        return homeFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        getPosterListResponse()
        setupCardStackView()//CardStackAdapter가 처음 쓰이는 부분
        setupButton()
    }
/*
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }
*/

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        //스와이프 시 반응
        Toast.makeText(this.context,"eeeeeeeeeee",Toast.LENGTH_SHORT).show()
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
//        if (manager.topPosition == adapter.itemCount - 5) {
//            paginate()
//        }
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardAppeared: ($position) ${textView.text}")
    }

    override fun onCardDisappeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}")
    }

/*
    private fun setupNavigation() {
        // Toolbar
        val toolbar = findViewById<Toolbar>(R.posterIdx.toolbar)
        setSupportActionBar(toolbar)

        // DrawerLayout
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        actionBarDrawerToggle.syncState()
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        // NavigationView
        val navigationView = findViewById<NavigationView>(R.posterIdx.navigation_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.posterIdx.reload -> reload()
                R.posterIdx.add_spot_to_first -> addFirst(1)
                R.posterIdx.add_spot_to_last -> addLast(1)
                R.posterIdx.remove_spot_from_first -> removeFirst(1)
                R.posterIdx.remove_spot_from_last -> removeLast(1)
                R.posterIdx.replace_first_spot -> replace()
                R.posterIdx.swap_first_for_last -> swap()
            }
            drawerLayout.closeDrawers()
            true
        }
    }
*/

    private fun setupCardStackView() {
        cardStackView=homeFragmentView!!.find(R.id.card_stack_view)
//        LeftButtonView=homeFragmentView!!.find(R.posterIdx.cs_view_for_progress)
//        manager=CardStackLayoutManager(context, this)
//        adapter=CardStackAdapter(createPosters())
        initialize()
    }

    private fun setupButton() {
        val skip: FloatingActionButton = homeFragmentView!!.find(R.id.skip_button)
        skip.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(200)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView?.swipe()
        }

        val like: FloatingActionButton = homeFragmentView!!.find(R.id.like_button)
        like.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(200)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            cardStackView?.swipe()
        }
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)

        cardStackView!!.layoutManager = manager
//        cardStackView!!.adapter = adapter
//        LeftButtonView!!.adapter = lbadapter
        cardStackView!!.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
//        setupProgressView()
//        setupCardTab()
    }

/*
    private fun setupProgressView(){
        var pvReverse: View = homeFragmentView!!.find(R.posterIdx.pv_reverse)
        cardStackView!!.bringToFront()
        pvReverse.setOnClickListener {
            pvReverse.bringToFront()
            Toast.makeText(activity,"눌리는거 맞냐?",Toast.LENGTH_SHORT).show()
        }

        var pvSkip: View =homeFragmentView!!.find(R.posterIdx.pv_skip)
        pvSkip.setOnClickListener {
            Toast.makeText(activity,"아아아아아아앙",Toast.LENGTH_LONG).show()
        }
    }
*/

/*
    private fun setupCardTab(){
        var viewForCardSize : View = homeFragmentView!!.find(R.posterIdx.button_container)
        var widthOfCard : Int= viewForCardSize.width
//        var widthOfCard : Int= viewForCardSize.layoutParams.width
        homeFragmentView!!.setOnTouchListener{v,event->
            when (event?.action){
                MotionEvent.ACTION_BUTTON_PRESS->{
                    var x :  Float =event.getX()
                    if (x<=widthOfCard/2){
                        Toast.makeText(v.context,"이것도 왼쪽일까",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(v.context,"제발 오른쪽",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            v?.onTouchEvent(event)?:true
        }
    }
*/

    /*
    private fun paginate() {
        val old = adapter.getSpots()
        val new = old.plus(createPosters())
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
    */

//    private fun paginate() {
//        val old = adapter.getSpots()
//        val new = old.plus(createPosters())
//        val new = old.plus(createPosters())
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new as ArrayList<PosterData>)
//        result.dispatchUpdatesTo(adapter)
//    }

/*
    private fun reload() {
        val old = adapter.getSpots()
        val new = createPosters()
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
*/
/*
    private fun addFirst(size: Int) {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            for (i in 0 until size) {
                add(manager.topPosition, createSpot())
            }
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
*/
/*
    private fun addLast(size: Int) {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            addAll(List(size) { createSpot() })
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
*/
/*
    private fun removeFirst(size: Int) {
        if (adapter.getSpots().isEmpty()) {
            return
        }

        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            for (i in 0 until size) {
                removeAt(manager.topPosition)
            }
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
*/
/*
    private fun removeLast(size: Int) {
        if (adapter.getSpots().isEmpty()) {
            return
        }

        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            for (i in 0 until size) {
                removeAt(this.size - 1)
            }
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
*/
/*
    private fun replace() {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            removeAt(manager.topPosition)
            add(manager.topPosition, createSpot())
        }
        adapter.setSpots(new)
        adapter.notifyItemChanged(manager.topPosition)
    }
*/
/*
    private fun swap() {
        val old = adapter.getSpots()
        val new = mutableListOf<Spot>().apply {
            addAll(old)
            val first = removeAt(manager.topPosition)
            val last = removeAt(this.size - 1)
            add(manager.topPosition, last)
            add(first)
        }
        val callback = SpotDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }
*/
/*
    private fun createSpot(): Spot {
        return Spot(
            name = "Yasaka Shrine",
            city = "Kyoto",
            photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800"
        )
    }
*/

    private fun getPosterListResponse(){
//        val jsonObject: JSONObject = JSONObject()
//        val gsonObject : JSONObject= JsonParser().parse(jsonObject.toString()) as JSONObject
        Log.e("1111111111111111","1111111111111111111111")
        //바로 아래 라인에서 터진다
        val postPosterListResponse
                =networkService.postPosterResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEb0lUU09QVCIsInVzZXJfaWR4IjoxfQ.5lCvAqnzYP4-2pFx1KTgLVOxYzBQ6ygZvkx5jKCFM08")//,gsonObject)
        Log.e("2222222222222","33333333333333333")
        postPosterListResponse.enqueue(object : Callback<PostPosterListResponse>{
            override fun onFailure(call: Call<PostPosterListResponse>, t: Throwable) {
                Log.e("iiiiiiiiiiiiiiiiiiiiPoster call fail",t.toString())
            }
            override fun onResponse(call: Call<PostPosterListResponse>, response: Response<PostPosterListResponse>) {
                if(response.isSuccessful){
                    Log.e("pleaseeeeeeeeeeeee","can you come to here")
//                    val input : List<PosterData> = response.body()!!.data
//                    input.size
                    val inputPoster0 = response.body()!!.data[0]
                    val inputPoster1 = response.body()!!.data[1]
                    val inputPoster2 = response.body()!!.data[2]
                    val inputPoster3 = response.body()!!.data[3]
                    val inputPoster4 = response.body()!!.data[4]
                    val inputPoster5 = response.body()!!.data[5]
                    val inputPoster6 = response.body()!!.data[6]
                    val inputPoster7 = response.body()!!.data[7]
                    val inputPoster8 = response.body()!!.data[8]
                    val inputPoster9 = response.body()!!.data[9]
                    inputPosterArr.add(inputPoster0)
                    inputPosterArr.add(inputPoster1)
                    inputPosterArr.add(inputPoster2)
                    inputPosterArr.add(inputPoster3)
                    inputPosterArr.add(inputPoster4)
                    inputPosterArr.add(inputPoster5)
                    inputPosterArr.add(inputPoster6)
                    inputPosterArr.add(inputPoster7)
                    inputPosterArr.add(inputPoster8)
                    inputPosterArr.add(inputPoster9)
//                    inputPoster0,
//                    inputPoster1,
//                    inputPoster2,
//                    inputPoster3,
//                    inputPoster4,
//                    inputPoster5,
//                    inputPoster6,
//                    inputPoster7,
//                    inputPoster8,
//                    inputPoster9
                }
            }
        })
    }

//    private fun createPosters(): ArrayList<PosterData> {
//        val posters = ArrayList<PosterData>()
//        Log.e("imageeeee","eeeeeeeeeeeee")
//
//        //1번 CARD
//        posters.add(
//            PosterData(
//                inputPosterArr[0].posterIdx,
//                inputPosterArr[0].categoryIdx,
//                inputPosterArr[0].photoUrl,
//                inputPosterArr[0].posterName,
//                inputPosterArr[0].posterRegDate,
//                inputPosterArr[0].posterStartDate,
//                inputPosterArr[0].posterEndDate,
//                inputPosterArr[0].posterWebsite,
//                inputPosterArr[0].isSeek,
//                inputPosterArr[0].outline,
//                inputPosterArr[0].target,
//                inputPosterArr[0].period,
//                inputPosterArr[0].benefit,
//                inputPosterArr[0].documentDate,
//                inputPosterArr[0].announceDate1,
//                inputPosterArr[0].announceDate2,
//                inputPosterArr[0].finalAnnounceDate,
//                inputPosterArr[0].interviewDate
//            )
//        )
//        //2번 CARD
//        posters.add(
//            PosterData(
//                inputPosterArr[1].posterIdx,
//                inputPosterArr[1].categoryIdx,
//                inputPosterArr[1].photoUrl,
//                inputPosterArr[1].posterName,
//                inputPosterArr[1].posterRegDate,
//                inputPosterArr[1].posterStartDate,
//                inputPosterArr[1].posterEndDate,
//                inputPosterArr[1].posterWebsite,
//                inputPosterArr[1].isSeek,
//                inputPosterArr[1].outline,
//                inputPosterArr[1].target,
//                inputPosterArr[1].period,
//                inputPosterArr[1].benefit,
//                inputPosterArr[1].documentDate,
//                inputPosterArr[1].announceDate1,
//                inputPosterArr[1].announceDate2,
//                inputPosterArr[1].finalAnnounceDate,
//                inputPosterArr[1].interviewDate
//            )
//        )
//
//        //3번 CARD
//        posters.add(
//            PosterData(
//                inputPosterArr[2].posterIdx,
//                inputPosterArr[2].categoryIdx,
//                inputPosterArr[2].photoUrl,
//                inputPosterArr[2].posterName,
//                inputPosterArr[2].posterRegDate,
//                inputPosterArr[2].posterStartDate,
//                inputPosterArr[2].posterEndDate,
//                inputPosterArr[2].posterWebsite,
//                inputPosterArr[2].isSeek,
//                inputPosterArr[2].outline,
//                inputPosterArr[2].target,
//                inputPosterArr[2].period,
//                inputPosterArr[2].benefit,
//                inputPosterArr[2].documentDate,
//                inputPosterArr[2].announceDate1,
//                inputPosterArr[2].announceDate2,
//                inputPosterArr[2].finalAnnounceDate,
//                inputPosterArr[2].interviewDate
//            )
//        )
//
//        //4번 CARD
//        posters.add(
//            PosterData(
//                inputPosterArr[3].posterIdx,
//                inputPosterArr[3].categoryIdx,
//                inputPosterArr[3].photoUrl,
//                inputPosterArr[3].posterName,
//                inputPosterArr[3].posterRegDate,
//                inputPosterArr[3].posterStartDate,
//                inputPosterArr[3].posterEndDate,
//                inputPosterArr[3].posterWebsite,
//                inputPosterArr[3].isSeek,
//                inputPosterArr[3].outline,
//                inputPosterArr[3].target,
//                inputPosterArr[3].period,
//                inputPosterArr[3].benefit,
//                inputPosterArr[3].documentDate,
//                inputPosterArr[3].announceDate1,
//                inputPosterArr[3].announceDate2,
//                inputPosterArr[3].finalAnnounceDate,
//                inputPosterArr[3].interviewDate
//            )
//        )
//
//        //5번 CARD
//        posters.add(
//            PosterData(
//                inputPosterArr[4].posterIdx,
//                inputPosterArr[4].categoryIdx,
//                inputPosterArr[4].photoUrl,
//                inputPosterArr[4].posterName,
//                inputPosterArr[4].posterRegDate,
//                inputPosterArr[4].posterStartDate,
//                inputPosterArr[4].posterEndDate,
//                inputPosterArr[4].posterWebsite,
//                inputPosterArr[4].isSeek,
//                inputPosterArr[4].outline,
//                inputPosterArr[4].target,
//                inputPosterArr[4].period,
//                inputPosterArr[4].benefit,
//                inputPosterArr[4].documentDate,
//                inputPosterArr[4].announceDate1,
//                inputPosterArr[4].announceDate2,
//                inputPosterArr[4].finalAnnounceDate,
//                inputPosterArr[4].interviewDate
//            )
//        )
//
//        //6번 CARD
//        posters.add(
//            PosterData(
//                inputPosterArr[5].posterIdx,
//                inputPosterArr[5].categoryIdx,
//                inputPosterArr[5].photoUrl,
//                inputPosterArr[5].posterName,
//                inputPosterArr[5].posterRegDate,
//                inputPosterArr[5].posterStartDate,
//                inputPosterArr[5].posterEndDate,
//                inputPosterArr[5].posterWebsite,
//                inputPosterArr[5].isSeek,
//                inputPosterArr[5].outline,
//                inputPosterArr[5].target,
//                inputPosterArr[5].period,
//                inputPosterArr[5].benefit,
//                inputPosterArr[5].documentDate,
//                inputPosterArr[5].announceDate1,
//                inputPosterArr[5].announceDate2,
//                inputPosterArr[5].finalAnnounceDate,
//                inputPosterArr[5].interviewDate
//            )
//        )
//
//        //7번 CARD
//        posters.add(
//            PosterData(
//                inputPosterArr[6].posterIdx,
//                inputPosterArr[6].categoryIdx,
//                inputPosterArr[6].photoUrl,
//                inputPosterArr[6].posterName,
//                inputPosterArr[6].posterRegDate,
//                inputPosterArr[6].posterStartDate,
//                inputPosterArr[6].posterEndDate,
//                inputPosterArr[6].posterWebsite,
//                inputPosterArr[6].isSeek,
//                inputPosterArr[6].outline,
//                inputPosterArr[6].target,
//                inputPosterArr[6].period,
//                inputPosterArr[6].benefit,
//                inputPosterArr[6].documentDate,
//                inputPosterArr[6].announceDate1,
//                inputPosterArr[6].announceDate2,
//                inputPosterArr[6].finalAnnounceDate,
//                inputPosterArr[6].interviewDate
//            )
//        )
//
//        //8번 CARD
//        posters.add(
//            PosterData(
//                inputPosterArr[7].posterIdx,
//                inputPosterArr[7].categoryIdx,
//                inputPosterArr[7].photoUrl,
//                inputPosterArr[7].posterName,
//                inputPosterArr[7].posterRegDate,
//                inputPosterArr[7].posterStartDate,
//                inputPosterArr[7].posterEndDate,
//                inputPosterArr[7].posterWebsite,
//                inputPosterArr[7].isSeek,
//                inputPosterArr[7].outline,
//                inputPosterArr[7].target,
//                inputPosterArr[7].period,
//                inputPosterArr[7].benefit,
//                inputPosterArr[7].documentDate,
//                inputPosterArr[7].announceDate1,
//                inputPosterArr[7].announceDate2,
//                inputPosterArr[7].finalAnnounceDate,
//                inputPosterArr[7].interviewDate
//            )
//        )
//
//        //9번 CARD
//        posters.add(
//            PosterData(
//                inputPosterArr[8].posterIdx,
//                inputPosterArr[8].categoryIdx,
//                inputPosterArr[8].photoUrl,
//                inputPosterArr[8].posterName,
//                inputPosterArr[8].posterRegDate,
//                inputPosterArr[8].posterStartDate,
//                inputPosterArr[8].posterEndDate,
//                inputPosterArr[8].posterWebsite,
//                inputPosterArr[8].isSeek,
//                inputPosterArr[8].outline,
//                inputPosterArr[8].target,
//                inputPosterArr[8].period,
//                inputPosterArr[8].benefit,
//                inputPosterArr[8].documentDate,
//                inputPosterArr[8].announceDate1,
//                inputPosterArr[8].announceDate2,
//                inputPosterArr[8].finalAnnounceDate,
//                inputPosterArr[8].interviewDate
//            )
//        )
//
//        //10번 CARD
//        posters.add(
//            PosterData(
//                inputPosterArr[9].posterIdx,
//                inputPosterArr[9].categoryIdx,
//                inputPosterArr[9].photoUrl,
//                inputPosterArr[9].posterName,
//                inputPosterArr[9].posterRegDate,
//                inputPosterArr[9].posterStartDate,
//                inputPosterArr[9].posterEndDate,
//                inputPosterArr[9].posterWebsite,
//                inputPosterArr[9].isSeek,
//                inputPosterArr[9].outline,
//                inputPosterArr[9].target,
//                inputPosterArr[9].period,
//                inputPosterArr[9].benefit,
//                inputPosterArr[9].documentDate,
//                inputPosterArr[9].announceDate1,
//                inputPosterArr[9].announceDate2,
//                inputPosterArr[9].finalAnnounceDate,
//                inputPosterArr[9].interviewDate
//            )
//        )
//        return posters
//    }

/*
    private fun createPosters(): ArrayList<PosterData> {
        val posters = ArrayList<PosterData>()
        Log.e("imageeeee", "eeeeeeeeeeeee")
        //1번 CARD
        posters.add(
            PosterData(
                posterIdx = 1,
                categoryIdx = 1,
                photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                posterName = "hahaha",
                posterRegDate = "hahah",
                posterStartDate = "hahah",
                posterEndDate = "hahah",
                posterWebsite = "hahah",
                isSeek = false,
                outline = "hahah",
                target = "hahah",
                period = "hahah",
                benefit = "hahah",
                documentDate = "hahah",
                announceDate1 = "hahah",
                announceDate2 = "hahah",
                finalAnnounceDate = "hahah",
                interviewDate = "hahah"
            )
        )

        //2번 CARD
        posters.add(
            PosterData(
                posterIdx = 1,
                categoryIdx = 1,
                photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                posterName = "hahaha",
                posterRegDate = "hahah",
                posterStartDate = "hahah",
                posterEndDate = "hahah",
                posterWebsite = "hahah",
                isSeek = false,
                outline = "hahah",
                target = "hahah",
                period = "hahah",
                benefit = "hahah",
                documentDate = "hahah",
                announceDate1 = "hahah",
                announceDate2 = "hahah",
                finalAnnounceDate = "hahah",
                interviewDate = "hahah"            )
        )

        //3번 CARD
        posters.add(
            PosterData(
                posterIdx = 1,
                categoryIdx = 1,
                photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                posterName = "hahaha",
                posterRegDate = "hahah",
                posterStartDate = "hahah",
                posterEndDate = "hahah",
                posterWebsite = "hahah",
                isSeek = false,
                outline = "hahah",
                target = "hahah",
                period = "hahah",
                benefit = "hahah",
                documentDate = "hahah",
                announceDate1 = "hahah",
                announceDate2 = "hahah",
                finalAnnounceDate = "hahah",
                interviewDate = "hahah"            )
        )

        //4번 CARD
        posters.add(
            PosterData(
                posterIdx = 1,
                categoryIdx = 1,
                photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                posterName = "hahaha",
                posterRegDate = "hahah",
                posterStartDate = "hahah",
                posterEndDate = "hahah",
                posterWebsite = "hahah",
                isSeek = false,
                outline = "hahah",
                target = "hahah",
                period = "hahah",
                benefit = "hahah",
                documentDate = "hahah",
                announceDate1 = "hahah",
                announceDate2 = "hahah",
                finalAnnounceDate = "hahah",
                interviewDate = "hahah"            )
        )

        //5번 CARD
        posters.add(
            PosterData(
                posterIdx = 1,
                categoryIdx = 1,
                photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                posterName = "hahaha",
                posterRegDate = "hahah",
                posterStartDate = "hahah",
                posterEndDate = "hahah",
                posterWebsite = "hahah",
                isSeek = false,
                outline = "hahah",
                target = "hahah",
                period = "hahah",
                benefit = "hahah",
                documentDate = "hahah",
                announceDate1 = "hahah",
                announceDate2 = "hahah",
                finalAnnounceDate = "hahah",
                interviewDate = "hahah"            )
        )

        //6번 CARD
        posters.add(
            PosterData(
                posterIdx = 1,
                categoryIdx = 1,
                photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                posterName = "hahaha",
                posterRegDate = "hahah",
                posterStartDate = "hahah",
                posterEndDate = "hahah",
                posterWebsite = "hahah",
                isSeek = false,
                outline = "hahah",
                target = "hahah",
                period = "hahah",
                benefit = "hahah",
                documentDate = "hahah",
                announceDate1 = "hahah",
                announceDate2 = "hahah",
                finalAnnounceDate = "hahah",
                interviewDate = "hahah"            )
        )

        //7번 CARD
        posters.add(
            PosterData(
                posterIdx = 1,
                categoryIdx = 1,
                photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                posterName = "hahaha",
                posterRegDate = "hahah",
                posterStartDate = "hahah",
                posterEndDate = "hahah",
                posterWebsite = "hahah",
                isSeek = false,
                outline = "hahah",
                target = "hahah",
                period = "hahah",
                benefit = "hahah",
                documentDate = "hahah",
                announceDate1 = "hahah",
                announceDate2 = "hahah",
                finalAnnounceDate = "hahah",
                interviewDate = "hahah"            )
        )

        //8번 CARD
        posters.add(
            PosterData(
                posterIdx = 1,
                categoryIdx = 1,
                photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                posterName = "hahaha",
                posterRegDate = "hahah",
                posterStartDate = "hahah",
                posterEndDate = "hahah",
                posterWebsite = "hahah",
                isSeek = false,
                outline = "hahah",
                target = "hahah",
                period = "hahah",
                benefit = "hahah",
                documentDate = "hahah",
                announceDate1 = "hahah",
                announceDate2 = "hahah",
                finalAnnounceDate = "hahah",
                interviewDate = "hahah"            )
        )

        //9번 CARD
        posters.add(
            PosterData(
                posterIdx = 1,
                categoryIdx = 1,
                photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                posterName = "hahaha",
                posterRegDate = "hahah",
                posterStartDate = "hahah",
                posterEndDate = "hahah",
                posterWebsite = "hahah",
                isSeek = false,
                outline = "hahah",
                target = "hahah",
                period = "hahah",
                benefit = "hahah",
                documentDate = "hahah",
                announceDate1 = "hahah",
                announceDate2 = "hahah",
                finalAnnounceDate = "hahah",
                interviewDate = "hahah"
            )
        )

        //10번 CARD
        posters.add(
            PosterData(
                posterIdx = 1,
                categoryIdx = 1,
                photoUrl = "https://source.unsplash.com/Xq1ntWruZQI/600x800",
                posterName = "hahaha",
                posterRegDate = "hahah",
                posterStartDate = "hahah",
                posterEndDate = "hahah",
                posterWebsite = "hahah",
                isSeek = false,
                outline = "hahah",
                target = "hahah",
                period = "hahah",
                benefit = "hahah",
                documentDate = "hahah",
                announceDate1 = "hahah",
                announceDate2 = "hahah",
                finalAnnounceDate = "hahah",
                interviewDate = "hahah"            )
        )
        return posters
    }
*/
}
