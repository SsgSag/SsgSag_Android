package com.sopt.appjam_sggsag.Info

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.airbnb.lottie.LottieAnimationView
import com.sopt.appjam_sggsag.DB.SharedPreferenceController
import com.sopt.appjam_sggsag.LoginActivity
import com.sopt.appjam_sggsag.MainActivity
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class WelcomeActivity : AppCompatActivity() {

    private var viewPager: ViewPager? = null
    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    private var dotsLayout: LinearLayout? = null
    private var layouts: IntArray? = null
//    private var btnSkip: Button? = null
//    private var btnNext: Button? = null
//    private var prefManager: PrefManager? = null

    //  viewpager change listener
    internal var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            addBottomDots(position)
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Checking for first time launch - before calling setContentView()
//        prefManager = PrefManager(this)
//        if (!prefManager!!.isFirstTimeLaunch()) {
//            launchHomeScreen()
//            finish()
//        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        setContentView(R.layout.activity_welcome)

        viewPager = findViewById<View>(R.id.view_pager) as ViewPager
        dotsLayout = findViewById<View>(R.id.layoutDots) as LinearLayout


        // layouts of welcome sliders
        layouts = intArrayOf(
            R.layout.fragment_first_info,
            R.layout.fragment_second_info,
            R.layout.fragment_third_info,
            R.layout.fragment_fourth_info,
            R.layout.fragment_fifth_info
        )

        // adding bottom dots
        addBottomDots(0)

        // making notification bar transparent
        changeStatusBarColor()

        myViewPagerAdapter = MyViewPagerAdapter()
        viewPager!!.adapter = myViewPagerAdapter
        viewPager!!.addOnPageChangeListener(viewPagerPageChangeListener)

//        btnSkip!!.setOnClickListener { launchHomeScreen() }
//
//        btnNext!!.setOnClickListener {
//            // checking for last page if true launch MainActivity
//            val current = getItem(+1)
//            if (current < layouts!!.size) {
//                // move to next screen
//                viewPager!!.currentItem = current
//            } else {
//                launchHomeScreen()
//            }
//        }

    }

    private fun addBottomDots(currentPage: Int) {
        var dots: Array<ImageView?> = arrayOfNulls(layouts!!.size)

        Log.e("진희야 힘내", "aaaaa $currentPage")

        val lp =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(6, 0, 0, 0)

        dotsLayout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageResource(R.drawable.progress_unactive)
            dots[i]!!.layoutParams=lp
            dotsLayout!!.addView(dots[i])
        }
        dots[currentPage]!!.setImageResource(R.drawable.progress_active)

        if (currentPage == 4) {
            showHide(tv_start_info)
            tv_start_info.setOnClickListener {
                startActivity<LoginActivity>()
            }
        }
    }

    private fun showHide(view:View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.INVISIBLE
        } else{
            View.VISIBLE
        }
    }


    private fun getItem(i: Int): Int {
        return viewPager!!.currentItem + i
    }

    private fun launchHomeScreen() {
//        prefManager!!.setFirstTimeLaunch(false)
        startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        finish()
    }

// Making notification bar transparent

    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * View pager adapter
     */
    inner class MyViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val view = layoutInflater!!.inflate(layouts!![position], container, false)
            container.addView(view)

            return view
        }

        override fun getCount(): Int {
            return layouts!!.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }


        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}
