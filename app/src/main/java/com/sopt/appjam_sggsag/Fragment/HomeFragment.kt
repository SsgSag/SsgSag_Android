package com.sopt.appjam_sggsag.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import com.sopt.appjam_sggsag.Adapter.CardStackAdapter
import com.sopt.appjam_sggsag.DB.SharedPreferenceController
import com.sopt.appjam_sggsag.Data.PosterData
import com.sopt.appjam_sggsag.MyApplication
import com.sopt.appjam_sggsag.Network.NetworkService
import com.sopt.appjam_sggsag.Post.PostPosterListResponse
import com.sopt.appjam_sggsag.R
import com.sopt.appjam_sggsag.Spot
import com.yuyakaido.android.cardstackview.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), CardStackListener {

    val manager by lazy { CardStackLayoutManager(activity!!, this) }
    val adapter = CardStackAdapter(createSpots())
    var num: Int = adapter.itemCount

    var posterlist: List<PosterData>? = null

    val networkService: NetworkService by lazy {
        MyApplication.instance.networkService
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

        if (direction == Direction.Right) {
            num--
            Log.e("aaaa", "오른쪽")
        } else if (direction == Direction.Left) {
            num--
            Log.e("aaaa", "왼쪽")
        }
        changeCardNum()
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
    }

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

}
