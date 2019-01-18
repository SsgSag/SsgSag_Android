package com.sopt.appjam_sggsag.Fragment

import android.media.Image
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.sopt.appjam_sggsag.Adapter.CardStackAdapter
import com.sopt.appjam_sggsag.Data.DetailPosterData
import com.sopt.appjam_sggsag.MyApplication
import android.widget.TextView
import com.sopt.appjam_sggsag.DB.SharedPreferenceController
import com.sopt.appjam_sggsag.Data.PosterData
import com.sopt.appjam_sggsag.MyApplication
import com.sopt.appjam_sggsag.Network.NetworkService
import com.sopt.appjam_sggsag.Post.PostPosterListResponse
import com.sopt.appjam_sggsag.R
import com.sopt.appjam_sggsag.Spot
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), CardStackListener {

    //  private val drawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawer_layout) }
    //  private val cardStackView by lazy { findViewById<CardStackView>(R.id.card_stack_view) }
    val manager by lazy { CardStackLayoutManager(activity!!, this) }
    val adapter = CardStackAdapter(createSpots())
import org.jetbrains.anko.support.v4.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), CardStackListener {

    val manager by lazy { CardStackLayoutManager(activity!!, this) }
    val adapter = CardStackAdapter(createSpots())
    var num: Int = adapter.itemCount
    lateinit var bt_x_animation : LottieAnimationView
    lateinit var bt_o_animation : LottieAnimationView
    lateinit var lastSplash : LottieAnimationView
    var posterlist: List<PosterData>? = null

    val networkService: NetworkService by lazy {
        MyApplication.instance.networkService
    }


    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        if (direction == Direction.Right) {
            Log.e("aaaa", "오른쪽")
        } else if (direction == Direction.Left) {
            Log.e("aaaa", "왼쪽")
        if (direction == Direction.Right) {
            num--
            Log.e("aaaa", "오른쪽")
        } else if (direction == Direction.Left) {
            num--
            Log.e("aaaa", "왼쪽")
        }
        changeCardNum()
        if (num==0){
            lastSplash.playAnimation()
        }
    }

    override fun onCardRewound() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardCanceled() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardAppeared(view: View?, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val homeFragment: View = inflater!!.inflate(R.layout.fragment_home, container, false)

        bt_x_animation = homeFragment.findViewById(R.id.lt_bt_x)
        bt_o_animation = homeFragment.findViewById(R.id.lt_bt_o)

        lastSplash = homeFragment.findViewById(R.id.lt_empty_hifive)
        //calendarFragment.frag_calendar_view.addDecorators(sundayDecorator, onedayDecorator)
        //  setOnClickListener()
        return homeFragment
    }

    private fun changeCardNum() {
        when (num) {
            20 -> iv_num.setImageResource(R.drawable.ic_num_20)
            19 -> iv_num.setImageResource(R.drawable.ic_num_19)
            18 -> iv_num.setImageResource(R.drawable.ic_num_18)
            17 -> iv_num.setImageResource(R.drawable.ic_num_17)
            16 -> iv_num.setImageResource(R.drawable.ic_num_16)
            15 -> iv_num.setImageResource(R.drawable.ic_num_15)
            14 -> iv_num.setImageResource(R.drawable.ic_num_14)
            13 -> iv_num.setImageResource(R.drawable.ic_num_13)
            12 -> iv_num.setImageResource(R.drawable.ic_num_12)
            11 -> iv_num.setImageResource(R.drawable.ic_num_11)
            10 -> iv_num.setImageResource(R.drawable.ic_num_10)
            9 -> iv_num.setImageResource(R.drawable.ic_num_9)
            8 -> iv_num.setImageResource(R.drawable.ic_num_8)
            7 -> iv_num.setImageResource(R.drawable.ic_num_7)
            6 -> iv_num.setImageResource(R.drawable.ic_num_6)
            5 -> iv_num.setImageResource(R.drawable.ic_num_5)
            4 -> iv_num.setImageResource(R.drawable.ic_num_4)
            3 -> iv_num.setImageResource(R.drawable.ic_num_3)
            2 -> iv_num.setImageResource(R.drawable.ic_num_2)
            1 -> iv_num.setImageResource(R.drawable.ic_num_1)
            0 -> button_container.visibility = View.INVISIBLE
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupCardStackView()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        btn_x.setOnClickListener {
        lt_bt_x.setOnClickListener {
            bt_x_animation.playAnimation()
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(200)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            card_stack_view.swipe()
        }

        lt_bt_o.setOnClickListener {
            bt_o_animation.playAnimation()
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(200)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            card_stack_view.swipe()
        }
    }

<<<<<<< HEAD
/*
    override fun onCardDragging(direction: Direction, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction) {
        if (manager.topPosition == adapter.itemCount - 5) {
            paginate()
        }
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View, position: Int) {
    }

    override fun onCardDisappeared(view: View, position: Int) {
    }
*/


=======
>>>>>>> cc424f97351f7c8af93f42ee1b9e5307700caacd
    private fun setupCardStackView() {
        initialize()
    }

<<<<<<< HEAD

=======
>>>>>>> cc424f97351f7c8af93f42ee1b9e5307700caacd
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
        card_stack_view.layoutManager = manager
        card_stack_view.adapter = adapter
        /*
        card_stack_view.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
        */
    }

    /*
        private fun paginate() {
            val old = adapter.getSpots()
            val new = old.plus(createSpots())
            val callback = SpotDiffCallback(old, new)
            val result = DiffUtil.calculateDiff(callback)
            adapter.setSpots(new)
            result.dispatchUpdatesTo(adapter)
        }

        private fun reload() {
            val old = adapter.getSpots()
            val new = createSpots()
            val callback = SpotDiffCallback(old, new)
            val result = DiffUtil.calculateDiff(callback)
            adapter.setSpots(new)
            result.dispatchUpdatesTo(adapter)
        }

        private fun addFirst(size: Int) {
            val old = adapter.getSpots()
<<<<<<< HEAD
            val new = mutableListOf<DetailPosterData>().apply {
=======
            val new = mutableListOf<Spot>().apply {
>>>>>>> cc424f97351f7c8af93f42ee1b9e5307700caacd
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

        private fun addLast(size: Int) {
            val old = adapter.getSpots()
<<<<<<< HEAD
            val new = mutableListOf<DetailPosterData>().apply {
=======
            val new = mutableListOf<Spot>().apply {
>>>>>>> cc424f97351f7c8af93f42ee1b9e5307700caacd
                addAll(old)
                addAll(List(size) { createSpot() })
            }
            val callback = SpotDiffCallback(old, new)
            val result = DiffUtil.calculateDiff(callback)
            adapter.setSpots(new)
            result.dispatchUpdatesTo(adapter)
        }

        private fun removeFirst(size: Int) {
            if (adapter.getSpots().isEmpty()) {
                return
            }

            val old = adapter.getSpots()
<<<<<<< HEAD
            val new = mutableListOf<DetailPosterData>().apply {
=======
            val new = mutableListOf<Spot>().apply {
>>>>>>> cc424f97351f7c8af93f42ee1b9e5307700caacd
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

        private fun removeLast(size: Int) {
            if (adapter.getSpots().isEmpty()) {
                return
            }

            val old = adapter.getSpots()
<<<<<<< HEAD
            val new = mutableListOf<DetailPosterData>().apply {
=======
            val new = mutableListOf<Spot>().apply {
>>>>>>> cc424f97351f7c8af93f42ee1b9e5307700caacd
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

        private fun replace() {
            val old = adapter.getSpots()
<<<<<<< HEAD
            val new = mutableListOf<DetailPosterData>().apply {
=======
            val new = mutableListOf<Spot>().apply {
>>>>>>> cc424f97351f7c8af93f42ee1b9e5307700caacd
                addAll(old)
                removeAt(manager.topPosition)
                add(manager.topPosition, createSpot())
            }
            adapter.setSpots(new)
            adapter.notifyItemChanged(manager.topPosition)
        }

        private fun swap() {
            val old = adapter.getSpots()
<<<<<<< HEAD
            val new = mutableListOf<DetailPosterData>().apply {
=======
            val new = mutableListOf<Spot>().apply {
>>>>>>> cc424f97351f7c8af93f42ee1b9e5307700caacd
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

<<<<<<< HEAD
        private fun createSpot(): DetailPosterData {
            return DetailPosterData(
=======
        private fun createSpot(): Spot {
            return Spot(
>>>>>>> cc424f97351f7c8af93f42ee1b9e5307700caacd
                name = "Yasaka Shrine",
                city = "Kyoto",
                url = "https://source.unsplash.com/Xq1ntWruZQI/600x800"
            )
        }
    */
<<<<<<< HEAD
    private fun createSpots(): List<DetailPosterData> {
        val posters = ArrayList<DetailPosterData>()
//        Log.e(inputPosterData!!.posters[0].photoUrl, inputPosterData!!.posters[0].photoUrl.toString())
//        Log.e("posterIdx",inputPosterData!!.posters[0].posterIdx.toString())
        Log.e("입력 전전전", "eeeeeeeeeeeee")
//        Log.e("inputPosterData",inputPosterData.toString())
        Log.e("inputPosterData!!.posters[0]", MyApplication.inputPosterData!!.posters[0].toString())
        Log.e("poster 확인", posters.toString())
//        Log.e("inputPosterData!!.posters[0].posterIdx",inputPosterData!!.posters[0].posterIdx.toString())
        Log.e("-------------------", "----------------------")

        //0번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = MyApplication.inputPosterData!!.posters[0].posterIdx,
                categoryIdx = MyApplication.inputPosterData!!.posters[0].categoryIdx,
                photoUrl = MyApplication.inputPosterData!!.posters[0].photoUrl,
                posterName = MyApplication.inputPosterData!!.posters[0].posterName,
                posterRegDate = MyApplication.inputPosterData!!.posters[0].posterRegDate,
                posterStartDate = MyApplication.inputPosterData!!.posters[0].posterStartDate,
                posterEndDate = MyApplication.inputPosterData!!.posters[0].posterEndDate,
                posterWebSite = MyApplication.inputPosterData!!.posters[0].posterWebSite,
                isSeek = MyApplication.inputPosterData!!.posters[0].isSeek,
                outline = MyApplication.inputPosterData!!.posters[0].outline,
                target = MyApplication.inputPosterData!!.posters[0].target,
                period = MyApplication.inputPosterData!!.posters[0].period,
                benefit = MyApplication.inputPosterData!!.posters[0].benefit,
                announceDate1 = MyApplication.inputPosterData!!.posters[0].announceDate1,
                announceDate2 = MyApplication.inputPosterData!!.posters[0].announceDate2,
                finalAnnounceDate = MyApplication.inputPosterData!!.posters[0].finalAnnounceDate,
                interviewDate = MyApplication.inputPosterData!!.posters[0].interviewDate,
                documentDate = MyApplication.inputPosterData!!.posters[0].documentDate
            )
        )

        Log.e("posters[0] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[1]",inputPosterData!!.posters[1].toString())
        Log.e("inputPosterData!!.posters[1].posterIdx", MyApplication.inputPosterData!!.posters[1].posterIdx.toString())
        Log.e("poster 확인", posters.toString())
        Log.e("-------------------", "----------------------")
        //1번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = MyApplication.inputPosterData!!.posters[1].posterIdx,
                categoryIdx = MyApplication.inputPosterData!!.posters[1].categoryIdx,
                photoUrl = MyApplication.inputPosterData!!.posters[1].photoUrl,
                posterName = MyApplication.inputPosterData!!.posters[1].posterName,
                posterRegDate = MyApplication.inputPosterData!!.posters[1].posterRegDate,
                posterStartDate = MyApplication.inputPosterData!!.posters[1].posterStartDate,
                posterEndDate = MyApplication.inputPosterData!!.posters[1].posterEndDate,
                posterWebSite = MyApplication.inputPosterData!!.posters[1].posterWebSite,
                isSeek = MyApplication.inputPosterData!!.posters[1].isSeek,
                outline = MyApplication.inputPosterData!!.posters[1].outline,
                target = MyApplication.inputPosterData!!.posters[1].target,
                period = MyApplication.inputPosterData!!.posters[1].period,
                benefit = MyApplication.inputPosterData!!.posters[1].benefit,
                announceDate1 = MyApplication.inputPosterData!!.posters[1].announceDate1,
                announceDate2 = MyApplication.inputPosterData!!.posters[1].announceDate2,
                finalAnnounceDate = MyApplication.inputPosterData!!.posters[1].finalAnnounceDate,
                interviewDate = MyApplication.inputPosterData!!.posters[1].interviewDate,
                documentDate = MyApplication.inputPosterData!!.posters[1].documentDate
            )
        )

        Log.e("posters[1] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[2]",inputPosterData!!.posters[2].toString())
        Log.e("inputPosterData!!.posters[2].posterIdx", MyApplication.inputPosterData!!.posters[2].posterIdx.toString())
        Log.e("poster 확인", posters.toString())
        Log.e("-------------------", "----------------------")

        //2번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = MyApplication.inputPosterData!!.posters[2].posterIdx,
                categoryIdx = MyApplication.inputPosterData!!.posters[2].categoryIdx,
                photoUrl = MyApplication.inputPosterData!!.posters[2].photoUrl,
                posterName = MyApplication.inputPosterData!!.posters[2].posterName,
                posterRegDate = MyApplication.inputPosterData!!.posters[2].posterRegDate,
                posterStartDate = MyApplication.inputPosterData!!.posters[2].posterStartDate,
                posterEndDate = MyApplication.inputPosterData!!.posters[2].posterEndDate,
                posterWebSite = MyApplication.inputPosterData!!.posters[2].posterWebSite,
                isSeek = MyApplication.inputPosterData!!.posters[2].isSeek,
                outline = MyApplication.inputPosterData!!.posters[2].outline,
                target = MyApplication.inputPosterData!!.posters[2].target,
                period = MyApplication.inputPosterData!!.posters[2].period,
                benefit = MyApplication.inputPosterData!!.posters[2].benefit,
                announceDate1 = MyApplication.inputPosterData!!.posters[2].announceDate1,
                announceDate2 = MyApplication.inputPosterData!!.posters[2].announceDate2,
                finalAnnounceDate = MyApplication.inputPosterData!!.posters[2].finalAnnounceDate,
                interviewDate = MyApplication.inputPosterData!!.posters[2].interviewDate,
                documentDate = MyApplication.inputPosterData!!.posters[2].documentDate
            )
        )
=======
      /*
    private fun createSpots(): List<DetailPosterData> {
        val posters = ArrayList<DetailPosterData>()

//        Log.e("입력 전전전", "eeeeeeeeeeeee")
//        Log.e("inputPosterData!!.posters[0]",inputPosterData!!.posters[0].toString())
//        Log.e("poster 확인",posters.toString())

        //1번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = 44,
                categoryIdx = 0,
                photoUrl = "https://s3.ap-northeast-2.amazonaws.com/project-hs/ef4f0d8509094bb6942ed34e906f8262.png",
                posterName = "2019 에스원아이디어 공모전",
                posterRegDate = "2019-01-10",
                posterStartDate = "2018-11-19",
                posterEndDate = "2019-01-14",
                posterWebSite = null,
                isSeek = 0,
                outline = "언제나 안심 <에스원> 에서 모두가 안심할 수 있는 첨단 미래를 만들기 위한 <2019 에스원 아이디어 공모전>을 개최합니다.",
                target = "국내 거주 대학(원)생 또는 일반인\n팀 지원 시 3인 이내 구성\n",
                period = "19.03.02 ~19.07.03까지",
                benefit = "대상(1명/팀): 500만원 / 최우수상(2명/팀): 각 300만원\n우수상(3명/팀): 각 100만원 / 장려상(4명/팀): 각 50만원\n",
                documentDate = "2018-12-25",
                announceDate1 = null,
                announceDate2 = null,
                finalAnnounceDate = "2019-01-18",
                interviewDate = "2019-01-17"
            )
        )

//        Log.e("posters[0] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[1]",inputPosterData!!.posters[1].toString())
//        Log.e("inputPosterData!!.posters[1].posterIdx",inputPosterData!!.posters[1].posterIdx.toString())
//        Log.e("poster 확인",posters.toString())

        //2번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = 42,
                categoryIdx = 0,
                photoUrl = "https://s3.ap-northeast-2.amazonaws.com/project-hs/17d42d6449e745f5b64e8a8e255e3868.png",
                posterName = "2019 경기도 블로그 기자단(경기소셜락커) 모집",
                posterRegDate = "2019-01-10",
                posterStartDate = "2018-12-21",
                posterEndDate = "2019-01-14",
                posterWebSite = null,
                isSeek = 0,
                outline = "경기도의 정책 소식을 도민들이 알기 쉽게 전달하고, 주변의 따뜻한 이야기와 다채로운 축제,\n\n      특별한 경기도의 행사 현장을 생생하게 전달하는 경기도민 기자단, 경기소셜락커! \n\\n      2018년도 경기소셜락커의 눈부신 활약에 바통을 이어 받을 2019년도 경기소셜락커를 모집합니다.",
                target = null,
                period = "19.03.02~19.07.03",
                benefit = "대상(1명/팀): 500만원 / 최우수상(2명/팀): 각 300만원\n우수상(3명/팀): 각 100만원 / 장려상(4명/팀): 각 50만원\n",
                documentDate = "2018-12-25",
                announceDate1 = null,
                announceDate2 = null,
                finalAnnounceDate = "2019-01-17",
                interviewDate = "2019-01-17"
            )
        )

//        Log.e("posters[1] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[1]",inputPosterData!!.posters[1].toString())
//        Log.e("inputPosterData!!.posters[2].posterIdx",inputPosterData!!.posters[2].posterIdx.toString())
//        Log.e("poster 확인",posters.toString())

        //3번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = 31,
                categoryIdx = 5,
                photoUrl = "https://s3.ap-northeast-2.amazonaws.com/project-hs/88aa0c0607aa461dbeaf018699fa446f.jpg",
                posterName = "커넥츠 다이어트 서포터즈 모집",
                posterRegDate = "2019-01-09",
                posterStartDate = "2019-01-13",
                posterEndDate = "2019-01-16",
                posterWebSite = null,
                isSeek = 0,
                outline = "커넥츠에서 ‘2019 커넥츠 다이어트 체험단’을 모집합니다.\n커넥츠(Conects)는 대한민국 최대 에듀테크 기업 ST Unitas에서 운영하는 소셜러닝플랫폼(Social learning platform)입니다. 커넥츠에서는 다양한 분야의 전문가, 멘토, 유저의 노하우와 경험담을 공유할 수 있습니다.",
                target = "다이어트가 하고 싶은 사람",
                period = "19.03.02 ~19.07.31",
                benefit = null,
                documentDate = "2018-12-25",
                announceDate1 = null,
                announceDate2 = null,
                finalAnnounceDate = "2019-01-22",
                interviewDate = null
            )
        )

        //4번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = 24,
                categoryIdx = 1,
                photoUrl = "https://s3.ap-northeast-2.amazonaws.com/project-hs/9c616f39daed458d80cbf107191f6729.png",
                posterName = " 멀티캠퍼스 오픽리더스클럽 16기 모집",
                posterRegDate = "2019-01-09",
                posterStartDate = "2019-01-04",
                posterEndDate = "2019-01-24",
                posterWebSite = null,
                isSeek = 0,
                outline = "안녕하세요 멀티캠퍼스입니다!\n멀티캠퍼스에서 이달 4일부터 24일까지 OPIc Leader’s Club(OLC)​ 16기를 모집합니다~!\n마케터를 꿈꾸는 대학생 재(휴)학생은 망설이지말고 지원하세요\n",
                target = "마케터를 꿈꾸는 대학생 재(휴)학생 30명",
                period = "2019.02.08 (금) ~ 2019.06.28 (금) 5개월",
                benefit = null,
                announceDate1 = null,
                announceDate2 = null,
                finalAnnounceDate = "2019-01-29",
                interviewDate = "2019-01-27",
                documentDate = "2018-12-25"
            )
        )

        //5번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = 28,
                categoryIdx = 5,
                photoUrl = "https://s3.ap-northeast-2.amazonaws.com/project-hs/70591639f719427e83d13b894f67d216.jpg",
                posterName = "클리마투스 컬리지 1월 강연",
                posterRegDate = "2019-01-09",
                posterStartDate = "2019-01-24",
                posterEndDate = "2019-01-24",
                posterWebSite = null,
                isSeek = 0,
                outline = "경희사이버대학교 정지훈 교수 특강",
                target = "대학생",
                period = "당일",
                benefit = null,
                announceDate1 = null,
                announceDate2 = null,
                finalAnnounceDate = "2019-01-28",
                interviewDate = null,
                documentDate = "2018-12-25"
            )
        )

        //6번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = 25,
                categoryIdx = 1,
                photoUrl = "https://s3.ap-northeast-2.amazonaws.com/project-hs/529c981c196b4d4480b8f319250ef2f6.png",
                posterName = "삼성전자 리포터즈/글로벌리포터즈 3기",
                posterRegDate = "2019-01-09",
                posterStartDate = "2019-01-07",
                posterEndDate = "2019-01-27",
                posterWebSite = null,
                isSeek = 0,
                outline = "영삼성에서 신규 리포터즈/글로벌리포터즈 3기를 모집합니다\n갤럭시 최신기종과 웰컴기프트팩!!\n콘텐츠 제작 전문성을 키워주는 특강 / 임직원 피드백\n우수자에게 주어지는 해외취재 기회!!",
                target = "대학생",
                period = "19.02.22 ~19.08.31",
                benefit = null,
                announceDate1 = "2019-01-01",
                announceDate2 = "2019-01-19",
                finalAnnounceDate = "2019-01-29",
                interviewDate = "2019-01-27",
                documentDate = "2018-12-25"
            )
        )

        //7번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = 26,
                categoryIdx = 5,
                photoUrl = "https://s3.ap-northeast-2.amazonaws.com/project-hs/e06865ef16d245f6b7f6197d1d0f3213.jpg",
                posterName = "모비아카데미 모바일 게임 마케팅, 크리에이티브&미디어 가이드",
                posterRegDate = "2019-01-09",
                posterStartDate = "2019-01-30",
                posterEndDate = "2019-01-30",
                posterWebSite = null,
                isSeek = 0,
                outline = "현업 마케터가 실무를 겪으며 깨달은 노하우를 모비아카데미에서 공개합니다.",
                target = "대학생",
                period = "19.02.22 ~19.08.31",
                benefit = null,
                announceDate1 = null,
                announceDate2 = null,
                finalAnnounceDate = "2019-01-29",
                interviewDate = null,
                documentDate = "2018-12-25"
            )
        )

        //8번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = 45,
                categoryIdx = 0,
                photoUrl = "https://s3.ap-northeast-2.amazonaws.com/project-hs/e0586bb255b44883b0cc7aa957d7b0f2.jpg",
                posterName = "2019 서울로 식물 정원 공모전",
                posterRegDate = "2019-01-10",
                posterStartDate = "2018-11-19",
                posterEndDate = "2019-01-31",
                posterWebSite = null,
                isSeek = 0,
                outline = "클로란 식물재단 Botany for Change 학생공모전에 여러분을 초대합니다.",
                target = "국내외 대학 또는 대학원 재학생(휴학생 포함)으로 구성된 2인 이상 3인 이하의 팀",
                period = null,
                benefit = "대상: 1작품 / 상금 3백만원 및 PFDC본사 및 프랑스 남부 클로란 식물재단 본사 방문\n우수상: 2작품 / 상금 1백만원, 상장 및 상품\n입선: 3작품 / 상장 및 상품\n",
                announceDate1 = "2018-12-29",
                announceDate2 = null,
                finalAnnounceDate = "2019-02-25",
                interviewDate = null,
                documentDate = "2018-12-25"
            )
        )

        //9번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = 30,
                categoryIdx = 1,
                photoUrl = "https://s3.ap-northeast-2.amazonaws.com/project-hs/86ae35ced4744e5480b3c8a513c1095f.jpg",
                posterName = "예술의전당 컬처리더 2기 모집",
                posterRegDate = "2019-01-09",
                posterStartDate = "2019-01-15",
                posterEndDate = "2019-02-02",
                posterWebSite = null,
                isSeek = 0,
                outline = "- 예술의전당 공연/전시 관람 후 SNS 콘텐츠 제작\n\n- 월 2회 토요세션 참석(필수)",
                target = "- 사진 촬영과 글쓰기에 관심 많은 분으로 기혼자에 한함\n * 영상 촬영 편집 가능한 지원자 우대",
                period = "19.03.02 ~19.07.31",
                benefit = null,
                announceDate1 = null,
                announceDate2 = null,
                finalAnnounceDate = "2019-02-12",
                interviewDate = null,
                documentDate = "2018-12-25"
            )
        )

        //10번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = 23,
                categoryIdx = 1,
                photoUrl = "https://s3.ap-northeast-2.amazonaws.com/project-hs/3c23303125a34ddf893694508ecc1e30.png",
                posterName = "2019 KT&G 아시아 대학생 창업교류전(ASVF) 서포터즈 모집",
                posterRegDate = "2019-01-08",
                posterStartDate = "2018-10-14",
                posterEndDate = "2019-02-08",
                posterWebSite = null,
                isSeek = 0,
                outline = "2019년 19회째를 맞는 2019 KT&G 아시아 대학생 창업교류전은\n국내에서 유일한 창업아이템교류 국제 행사로 각국의 아시아 학생들이 모여 서로의 아이템을 발표하고\n공동 관심사에 대한 토론을 통해 서로의 문화와 경제, 사회 전반에 대한\n폭 넓은 교류를 가질 수 있는 지식의 장을 제공합니다.",
                target = "일반인, 대학생, 대학원생 ",
                period = "2019.4.2 ~ 4.7 (",
                benefit = null,
                announceDate1 = "2018-12-28",
                announceDate2 = null,
                finalAnnounceDate = "2019-02-20",
                interviewDate = null,
                documentDate = "2018-12-25"
            )
        )
        return posters
    }
*/
    private fun createSpots(): List<Spot> {
        val spots = ArrayList<Spot>()
        spots.add(
            Spot(
                name = "2019 에스원아이디어 공모전",
                category = "#기획/아이디어",
                start_date = "2019-01-8",
                end_date = "2019-01-14",
                url = "https://s3.ap-northeast-2.amazonaws.com/project-hs/ef4f0d8509094bb6942ed34e906f8262.png"
            )
        )
        spots.add(
            Spot(
                name = "2019 경기도 블로그 기자단(경기소셜락커) 모집",
                category = "#기획/아이디어",
                start_date = "2019-01-10",
                end_date = "2019-01-19",
                url = "https://s3.ap-northeast-2.amazonaws.com/project-hs/17d42d6449e745f5b64e8a8e255e3868.png"
            )
        )
        spots.add(
            Spot(
                name = "커넥츠 다이어트 서포터즈 모집",
                category = "#브랜딩/마켓팅",
                start_date = "2019-01-13",
                end_date = "2019-01-16",
                url = "https://s3.ap-northeast-2.amazonaws.com/project-hs/88aa0c0607aa461dbeaf018699fa446f.jpg"
            )
        )
        spots.add(
            Spot(
                name = " 멀티캠퍼스 오픽리더스클럽 16기 모집",
                category = "#기획/아이디어",
                start_date = "2019-01-4",
                end_date = "2019-01-24",
                url = "https://s3.ap-northeast-2.amazonaws.com/project-hs/9c616f39daed458d80cbf107191f6729.png"
            )
        )
        spots.add(
            Spot(
                name = "클리마투스 컬리지 1월 강연",
                category = "#브랜딩/마켓팅",
                start_date = "2019-01-24",
                end_date = "2019-01-24",
                url = "https://s3.ap-northeast-2.amazonaws.com/project-hs/70591639f719427e83d13b894f67d216.jpg"
            )
        )
        spots.add(
            Spot(
                name = "삼성전자 리포터즈/글로벌리포터즈 3기",
                category = "#기획/아이디어",
                start_date = "2019-01-07",
                end_date = "2019-01-27",
                url = "https://s3.ap-northeast-2.amazonaws.com/project-hs/529c981c196b4d4480b8f319250ef2f6.png"
            )
        )
        spots.add(
            Spot(
                name = "모비아카데미 모바일 게임 마케팅, 크리에이티브&미디어 가이드",
                category = "#브랜딩/마켓팅",
                start_date = "2019-01-30",
                end_date = "2019-01-30",
                url = "https://s3.ap-northeast-2.amazonaws.com/project-hs/e06865ef16d245f6b7f6197d1d0f3213.jpg"
            )
        )
        spots.add(
            Spot(
                name = "2019 서울로 식물 정원 공모전",
                category = "#기획/아이디어",
                start_date = "2019-01-19",
                end_date = "2019-01-31",
                url = "https://s3.ap-northeast-2.amazonaws.com/project-hs/e0586bb255b44883b0cc7aa957d7b0f2.jpg"
            )
        )
        spots.add(
            Spot(
                name = "예술의전당 컬처리더 2기 모집",
                category = "#금융/경제",
                start_date = "2019-01-19",
                end_date = "2019-01-31",
                url = "https://source.unsplash.com/CdVAUADdqEc/600x800"
            )
        )
        spots.add(
            Spot(
                name = "2019 KT&G 아시아 대학생 창업교류전(ASVF) 서포터즈 모집",
                category ="#금융/경제",
                start_date = "2019-01-14",
                end_date = "2019-02-08",
                url = "https://s3.ap-northeast-2.amazonaws.com/project-hs/3c23303125a34ddf893694508ecc1e30.png"
            )
        )
        return spots
    }
>>>>>>> cc424f97351f7c8af93f42ee1b9e5307700caacd

        Log.e("posters[2] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[3]",inputPosterData!!.posters[3].toString())
        Log.e("inputPosterData!!.posters[3].posterIdx", MyApplication.inputPosterData!!.posters[3].posterIdx.toString())
        Log.e("poster 확인", posters.toString())
        Log.e("-------------------", "----------------------")

        //3번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = MyApplication.inputPosterData!!.posters[3].posterIdx,
                categoryIdx = MyApplication.inputPosterData!!.posters[3].categoryIdx,
                photoUrl = MyApplication.inputPosterData!!.posters[3].photoUrl,
                posterName = MyApplication.inputPosterData!!.posters[3].posterName,
                posterRegDate = MyApplication.inputPosterData!!.posters[3].posterRegDate,
                posterStartDate = MyApplication.inputPosterData!!.posters[3].posterStartDate,
                posterEndDate = MyApplication.inputPosterData!!.posters[3].posterEndDate,
                posterWebSite = MyApplication.inputPosterData!!.posters[3].posterWebSite,
                isSeek = MyApplication.inputPosterData!!.posters[3].isSeek,
                outline = MyApplication.inputPosterData!!.posters[3].outline,
                target = MyApplication.inputPosterData!!.posters[3].target,
                period = MyApplication.inputPosterData!!.posters[3].period,
                benefit = MyApplication.inputPosterData!!.posters[3].benefit,
                announceDate1 = MyApplication.inputPosterData!!.posters[3].announceDate1,
                announceDate2 = MyApplication.inputPosterData!!.posters[3].announceDate2,
                finalAnnounceDate = MyApplication.inputPosterData!!.posters[3].finalAnnounceDate,
                interviewDate = MyApplication.inputPosterData!!.posters[3].interviewDate,
                documentDate = MyApplication.inputPosterData!!.posters[3].documentDate
            )
        )

        Log.e("posters[3] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[4]",inputPosterData!!.posters[4].toString())
        Log.e("inputPosterData!!.posters[4].posterIdx", MyApplication.inputPosterData!!.posters[4].posterIdx.toString())
        Log.e("poster 확인", posters.toString())
        Log.e("-------------------", "----------------------")

        //4번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = MyApplication.inputPosterData!!.posters[4].posterIdx,
                categoryIdx = MyApplication.inputPosterData!!.posters[4].categoryIdx,
                photoUrl = MyApplication.inputPosterData!!.posters[4].photoUrl,
                posterName = MyApplication.inputPosterData!!.posters[4].posterName,
                posterRegDate = MyApplication.inputPosterData!!.posters[4].posterRegDate,
                posterStartDate = MyApplication.inputPosterData!!.posters[4].posterStartDate,
                posterEndDate = MyApplication.inputPosterData!!.posters[4].posterEndDate,
                posterWebSite = MyApplication.inputPosterData!!.posters[4].posterWebSite,
                isSeek = MyApplication.inputPosterData!!.posters[4].isSeek,
                outline = MyApplication.inputPosterData!!.posters[4].outline,
                target = MyApplication.inputPosterData!!.posters[4].target,
                period = MyApplication.inputPosterData!!.posters[4].period,
                benefit = MyApplication.inputPosterData!!.posters[4].benefit,
                announceDate1 = MyApplication.inputPosterData!!.posters[4].announceDate1,
                announceDate2 = MyApplication.inputPosterData!!.posters[4].announceDate2,
                finalAnnounceDate = MyApplication.inputPosterData!!.posters[4].finalAnnounceDate,
                interviewDate = MyApplication.inputPosterData!!.posters[4].interviewDate,
                documentDate = MyApplication.inputPosterData!!.posters[4].documentDate
            )
        )

        Log.e("posters[4] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[5]",inputPosterData!!.posters[5].toString())
        Log.e("inputPosterData!!.posters[5].posterIdx", MyApplication.inputPosterData!!.posters[5].posterIdx.toString())
        Log.e("poster 확인", posters.toString())
        Log.e("-------------------", "----------------------")
        //5번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = MyApplication.inputPosterData!!.posters[5].posterIdx,
                categoryIdx = MyApplication.inputPosterData!!.posters[5].categoryIdx,
                photoUrl = MyApplication.inputPosterData!!.posters[5].photoUrl,
                posterName = MyApplication.inputPosterData!!.posters[5].posterName,
                posterRegDate = MyApplication.inputPosterData!!.posters[5].posterRegDate,
                posterStartDate = MyApplication.inputPosterData!!.posters[5].posterStartDate,
                posterEndDate = MyApplication.inputPosterData!!.posters[5].posterEndDate,
                posterWebSite = MyApplication.inputPosterData!!.posters[5].posterWebSite,
                isSeek = MyApplication.inputPosterData!!.posters[5].isSeek,
                outline = MyApplication.inputPosterData!!.posters[5].outline,
                target = MyApplication.inputPosterData!!.posters[5].target,
                period = MyApplication.inputPosterData!!.posters[5].period,
                benefit = MyApplication.inputPosterData!!.posters[5].benefit,
                announceDate1 = MyApplication.inputPosterData!!.posters[5].announceDate1,
                announceDate2 = MyApplication.inputPosterData!!.posters[5].announceDate2,
                finalAnnounceDate = MyApplication.inputPosterData!!.posters[5].finalAnnounceDate,
                interviewDate = MyApplication.inputPosterData!!.posters[5].interviewDate,
                documentDate = MyApplication.inputPosterData!!.posters[5].documentDate
            )
        )

        Log.e("posters[5] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[6]",inputPosterData!!.posters[6].toString())
        Log.e("inputPosterData!!.posters[6].posterIdx", MyApplication.inputPosterData!!.posters[6].posterIdx.toString())
        Log.e("poster 확인", posters.toString())
        Log.e("-------------------", "----------------------")
        //6번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = MyApplication.inputPosterData!!.posters[6].posterIdx,
                categoryIdx = MyApplication.inputPosterData!!.posters[6].categoryIdx,
                photoUrl = MyApplication.inputPosterData!!.posters[6].photoUrl,
                posterName = MyApplication.inputPosterData!!.posters[6].posterName,
                posterRegDate = MyApplication.inputPosterData!!.posters[6].posterRegDate,
                posterStartDate = MyApplication.inputPosterData!!.posters[6].posterStartDate,
                posterEndDate = MyApplication.inputPosterData!!.posters[6].posterEndDate,
                posterWebSite = MyApplication.inputPosterData!!.posters[6].posterWebSite,
                isSeek = MyApplication.inputPosterData!!.posters[6].isSeek,
                outline = MyApplication.inputPosterData!!.posters[6].outline,
                target = MyApplication.inputPosterData!!.posters[6].target,
                period = MyApplication.inputPosterData!!.posters[6].period,
                benefit = MyApplication.inputPosterData!!.posters[6].benefit,
                announceDate1 = MyApplication.inputPosterData!!.posters[6].announceDate1,
                announceDate2 = MyApplication.inputPosterData!!.posters[6].announceDate2,
                finalAnnounceDate = MyApplication.inputPosterData!!.posters[6].finalAnnounceDate,
                interviewDate = MyApplication.inputPosterData!!.posters[6].interviewDate,
                documentDate = MyApplication.inputPosterData!!.posters[6].documentDate
            )
        )
        Log.e("posters[6] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[7]",inputPosterData!!.posters[7].toString())
        Log.e("inputPosterData!!.posters[7].posterIdx", MyApplication.inputPosterData!!.posters[7].posterIdx.toString())
        Log.e("poster 확인", posters.toString())
        Log.e("-------------------", "----------------------")
        //7번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = MyApplication.inputPosterData!!.posters[7].posterIdx,
                categoryIdx = MyApplication.inputPosterData!!.posters[7].categoryIdx,
                photoUrl = MyApplication.inputPosterData!!.posters[7].photoUrl,
                posterName = MyApplication.inputPosterData!!.posters[7].posterName,
                posterRegDate = MyApplication.inputPosterData!!.posters[7].posterRegDate,
                posterStartDate = MyApplication.inputPosterData!!.posters[7].posterStartDate,
                posterEndDate = MyApplication.inputPosterData!!.posters[7].posterEndDate,
                posterWebSite = MyApplication.inputPosterData!!.posters[7].posterWebSite,
                isSeek = MyApplication.inputPosterData!!.posters[7].isSeek,
                outline = MyApplication.inputPosterData!!.posters[7].outline,
                target = MyApplication.inputPosterData!!.posters[7].target,
                period = MyApplication.inputPosterData!!.posters[7].period,
                benefit = MyApplication.inputPosterData!!.posters[7].benefit,
                announceDate1 = MyApplication.inputPosterData!!.posters[7].announceDate1,
                announceDate2 = MyApplication.inputPosterData!!.posters[7].announceDate2,
                finalAnnounceDate = MyApplication.inputPosterData!!.posters[7].finalAnnounceDate,
                interviewDate = MyApplication.inputPosterData!!.posters[7].interviewDate,
                documentDate = MyApplication.inputPosterData!!.posters[7].documentDate
            )
        )
        Log.e("posters[7] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[8]",inputPosterData!!.posters[8].toString())
        Log.e("inputPosterData!!.posters[8].posterIdx", MyApplication.inputPosterData!!.posters[8].posterIdx.toString())
        Log.e("poster 확인", posters.toString())
        Log.e("-------------------", "----------------------")
        //8번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = MyApplication.inputPosterData!!.posters[8].posterIdx,
                categoryIdx = MyApplication.inputPosterData!!.posters[8].categoryIdx,
                photoUrl = MyApplication.inputPosterData!!.posters[8].photoUrl,
                posterName = MyApplication.inputPosterData!!.posters[8].posterName,
                posterRegDate = MyApplication.inputPosterData!!.posters[8].posterRegDate,
                posterStartDate = MyApplication.inputPosterData!!.posters[8].posterStartDate,
                posterEndDate = MyApplication.inputPosterData!!.posters[8].posterEndDate,
                posterWebSite = MyApplication.inputPosterData!!.posters[8].posterWebSite,
                isSeek = MyApplication.inputPosterData!!.posters[8].isSeek,
                outline = MyApplication.inputPosterData!!.posters[8].outline,
                target = MyApplication.inputPosterData!!.posters[8].target,
                period = MyApplication.inputPosterData!!.posters[8].period,
                benefit = MyApplication.inputPosterData!!.posters[8].benefit,
                announceDate1 = MyApplication.inputPosterData!!.posters[8].announceDate1,
                announceDate2 = MyApplication.inputPosterData!!.posters[8].announceDate2,
                finalAnnounceDate = MyApplication.inputPosterData!!.posters[8].finalAnnounceDate,
                interviewDate = MyApplication.inputPosterData!!.posters[8].interviewDate,
                documentDate = MyApplication.inputPosterData!!.posters[8].documentDate
            )
        )
        Log.e("posters[8] 입력 완료", " ")
//        Log.e("inputPosterData!!.posters[9]",inputPosterData!!.posters[9].toString())
        Log.e("inputPosterData!!.posters[9].posterIdx", MyApplication.inputPosterData!!.posters[9].posterIdx.toString())
        Log.e("poster 확인", posters.toString())
        Log.e("-------------------", "----------------------")
        //9번 CARD
        posters.add(
            DetailPosterData(
                posterIdx = MyApplication.inputPosterData!!.posters[9].posterIdx,
                categoryIdx = MyApplication.inputPosterData!!.posters[9].categoryIdx,
                photoUrl = MyApplication.inputPosterData!!.posters[9].photoUrl,
                posterName = MyApplication.inputPosterData!!.posters[9].posterName,
                posterRegDate = MyApplication.inputPosterData!!.posters[9].posterRegDate,
                posterStartDate = MyApplication.inputPosterData!!.posters[9].posterStartDate,
                posterEndDate = MyApplication.inputPosterData!!.posters[9].posterEndDate,
                posterWebSite = MyApplication.inputPosterData!!.posters[9].posterWebSite,
                isSeek = MyApplication.inputPosterData!!.posters[9].isSeek,
                outline = MyApplication.inputPosterData!!.posters[9].outline,
                target = MyApplication.inputPosterData!!.posters[9].target,
                period = MyApplication.inputPosterData!!.posters[9].period,
                benefit = MyApplication.inputPosterData!!.posters[9].benefit,
                announceDate1 = MyApplication.inputPosterData!!.posters[9].announceDate1,
                announceDate2 = MyApplication.inputPosterData!!.posters[9].announceDate2,
                finalAnnounceDate = MyApplication.inputPosterData!!.posters[9].finalAnnounceDate,
                interviewDate = MyApplication.inputPosterData!!.posters[9].interviewDate,
                documentDate = MyApplication.inputPosterData!!.posters[9].documentDate
            )
        )
        Log.e("posters[9] 입력 완료", " ")
        Log.e("inputPosterData!!.posters[9]", MyApplication.inputPosterData!!.posters[9].toString())
        Log.e("inputPosterData!!.posters[9].posterIdx", MyApplication.inputPosterData!!.posters[9].posterIdx.toString())
        Log.e("poster 확인", posters.toString())
        Log.e("-------------------", "----------------------")
        return posters
    }
}
