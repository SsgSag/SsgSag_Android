package com.sopt.appjam_sggsag.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import com.sopt.appjam_sggsag.Adapter.CardStackAdapter
import com.sopt.appjam_sggsag.Data.DetailPosterData
import com.sopt.appjam_sggsag.MyApplication
import com.sopt.appjam_sggsag.R
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), CardStackListener {

    //  private val drawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawer_layout) }
    //  private val cardStackView by lazy { findViewById<CardStackView>(R.id.card_stack_view) }
    val manager by lazy { CardStackLayoutManager(activity!!, this) }
    val adapter = CardStackAdapter(createSpots())

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        if (direction == Direction.Right) {
            Log.e("aaaa", "오른쪽")
        } else if (direction == Direction.Left) {
            Log.e("aaaa", "왼쪽")
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

        //calendarFragment.frag_calendar_view.addDecorators(sundayDecorator, onedayDecorator)
        //  setOnClickListener()
        return homeFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupCardStackView()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        btn_x.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(200)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            card_stack_view.swipe()
        }
        btn_o.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(200)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            card_stack_view.swipe()
        }
    }

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


    private fun setupCardStackView() {
        initialize()
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
            val new = mutableListOf<DetailPosterData>().apply {
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
            val new = mutableListOf<DetailPosterData>().apply {
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
            val new = mutableListOf<DetailPosterData>().apply {
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
            val new = mutableListOf<DetailPosterData>().apply {
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
            val new = mutableListOf<DetailPosterData>().apply {
                addAll(old)
                removeAt(manager.topPosition)
                add(manager.topPosition, createSpot())
            }
            adapter.setSpots(new)
            adapter.notifyItemChanged(manager.topPosition)
        }

        private fun swap() {
            val old = adapter.getSpots()
            val new = mutableListOf<DetailPosterData>().apply {
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

        private fun createSpot(): DetailPosterData {
            return DetailPosterData(
                name = "Yasaka Shrine",
                city = "Kyoto",
                url = "https://source.unsplash.com/Xq1ntWruZQI/600x800"
            )
        }
    */
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
