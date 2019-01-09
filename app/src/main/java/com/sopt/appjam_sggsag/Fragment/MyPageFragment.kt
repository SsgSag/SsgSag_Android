package com.sopt.appjam_sggsag.Fragment

import android.app.Activity
import android.content.CursorLoader
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sopt.appjam_sggsag.Career.CareerActivity
import com.sopt.appjam_sggsag.MainActivity
import com.sopt.appjam_sggsag.MyPage.InterestArea
import com.sopt.appjam_sggsag.MyPage.JobActivity
import com.sopt.appjam_sggsag.MyPage.InterestArea
import com.sopt.appjam_sggsag.R
import com.sopt.appjam_sggsag.SignUp.SignUp2
import kotlinx.android.synthetic.main.activity_sign_up2.*
import kotlinx.android.synthetic.main.fragment_my_page.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class MyPageFragment : Fragment() {
    private var myPageFragment: View? = null
    val My_READ_STORAGE_REQUEST_CODE = 7777
    val REQUEST_CODE_SELECT_IMAGE: Int = 1004

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myPageFragment = inflater!!.inflate(R.layout.fragment_my_page, container, false)

        setOnBtnClickListener()
        onClickCamera()

        return myPageFragment
    }

    companion object {
        private var instance: MyPageFragment? = null
        @Synchronized
        fun getInstance(): MyPageFragment {
            if (instance == null) {
                instance = MyPageFragment()
            }
            return instance!!
        }
    }

    private fun setOnBtnClickListener() {

        val btn_preference: TextView = myPageFragment!!.find(R.id.btn_my_page_preference)
        btn_preference.setOnClickListener {
            startActivity<InterestArea>()
        }
        val btn_job: TextView = myPageFragment!!.find(R.id.btn_my_page_job)
        btn_job.setOnClickListener {
            startActivity<JobActivity>()
        }
        val btn_career: TextView = myPageFragment!!.find(R.id.btn_my_page_career)
        btn_career.setOnClickListener {
            startActivity<CareerActivity>()
        }
        val btn_notice: RelativeLayout = myPageFragment!!.find(R.id.btn_my_page_notice)
        btn_notice.setOnClickListener {
            toast("공지사항")
        }
        val btn_push_setting: RelativeLayout = myPageFragment!!.find(R.id.btn_my_page_push_setting)
        btn_push_setting.setOnClickListener {
            toast("푸시 알림 설정")
        }
        val btn_account: RelativeLayout = myPageFragment!!.find(R.id.btn_my_page_account_management)
        btn_account.setOnClickListener {
            toast("계정관리")
        }
    }

    private fun onClickCamera() {
        val btn_profile_setting: ImageView = myPageFragment!!.find(R.id.iv_camera_my_page)
        btn_profile_setting.setOnClickListener {
            requestReadExternalStoragePermission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //REQUEST_CODE_SELECT_IMAGE를 통해 앨범에서 보낸 요청에 대한 Callback인지를 체크!!!
        if (requestCode == REQUEST_CODE_SELECT_IMAGE) {
            //앨범 사진 선택에 대한 Callback이 RESULT_OK인지 체크!!
            if (resultCode == Activity.RESULT_OK) {
                //data.data에는 앨범에서 선택한 사진의 Uri가 들어있습니다!! 그러니까 제대로 선택됐는지 null인지 아닌지를 체크!!!
                if (data != null) {
                    val selectedImageUri: Uri = data.data
                    //Uri를 getRealPathFromURI라는 메소드를 통해 절대 경로를 알아내고, 인스턴스 변수인 imageURI에 String으로 넣어줍니다!
                    SignUp2.getSignUp2.imageURI = getRealPathFromURI(selectedImageUri)

                    //Glide를 통해 imageView에 우리가 선택한 이미지를 띄워 줍시다!(무엇을 선택했는지는 알아야 좋겠죠?!)
                    Glide.with(this)
                        .load(selectedImageUri)
                        .thumbnail(0.1f)
                        .into(iv_profile_my_page)

                }
            }
        }
    }

    fun getRealPathFromURI(content: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader: CursorLoader = CursorLoader(context, content, proj, null, null, null)
        val cursor: Cursor = loader.loadInBackground()
        val column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result = cursor.getString(column_idx)
        cursor.close()
        return result
    }

    private fun requestReadExternalStoragePermission() {
        //첫번째 if문을 통해 과거에 이미 권한 메시지에 대한 OK를 했는지 아닌지에 대해 물어봅니다!
        if (ContextCompat.checkSelfPermission(
                context!!,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity!!,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                //사용자에게 권한을 왜 허용해야되는지에 메시지를 주기 위한 대한 로직을 추가하려면 이 블락에서 하면됩니다!!
                //하지만 우리는 그냥 비워놓습니다!! 딱히 줄말 없으면 비워놔도 무관해요!!! 굳이 뭐 안넣어도됩니다!
            } else {
                //아래 코드는 권한을 요청하는 메시지를 띄우는 기능을 합니다! 요청에 대한 결과는 callback으로 onRequestPermissionsResult 메소드에서 받습니다!!!
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    My_READ_STORAGE_REQUEST_CODE
                )
            }
        } else {
            //첫번째 if문의 else로써, 기존에 이미 권한 메시지를 통해 권한을 허용했다면 아래와 같은 곧바로 앨범을 여는 메소드를 호출해주면됩니다!!
            showAlbum()
        }

//        if (ActivityCompat.checkSelfPermission(
//                context!!,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                context!!,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            requestPermissions(
//                activity,
//                arrayOf(
//                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
//                    android.Manifest.permission.ACCESS_FINE_LOCATION
//                ),
//                REQUEST_LOCATION
//            )
//        } else {
//            Log.e("DB", "PERMISSION GRANTED")
//        }

    }

    private fun showAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
    }
}