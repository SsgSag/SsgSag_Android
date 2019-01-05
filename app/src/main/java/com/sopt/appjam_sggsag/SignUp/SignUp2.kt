package com.sopt.appjam_sggsag.SignUp

import android.app.Activity
import android.content.CursorLoader
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sopt.appjam_sggsag.R
import kotlinx.android.synthetic.main.activity_sign_up2.*
import org.jetbrains.anko.startActivity

class SignUp2 : AppCompatActivity() {
    object getSignUp2{
        lateinit var name :String
        lateinit var birth: String
        lateinit var email: String
        lateinit var imageURI: String
        var btn: Int = 0
    }

    val REQUEST_CODE_SELECT_IMAGE: Int = 1004
    val My_READ_STORAGE_REQUEST_CODE = 7777

    override fun onBackPressed() {
        startActivity<SignUp1>()
        finish()
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        onClickCamera()

        iv_back_2.setOnClickListener {
            startActivity<SignUp1>()
            finish()
        }

        iv_female.setOnClickListener {
            iv_female.setImageResource(R.drawable.bt_female_active)
            iv_male.setImageResource(R.drawable.bt_male_unactive)
            getSignUp2.btn = 1
            Toast.makeText(this, getSignUp2.btn.toString(), Toast.LENGTH_SHORT).show()
            goToNext()
        }
        iv_male.setOnClickListener {
            iv_male.setImageResource(R.drawable.bt_male_active)
            iv_female.setImageResource(R.drawable.bt_female_unactive)
            getSignUp2.btn = 2
            Toast.makeText(this, getSignUp2.btn.toString(), Toast.LENGTH_SHORT).show()
            goToNext()
        }

        cb_service_privacy.setOnClickListener {
            if (cb_service_privacy.isChecked)
                goToNext()
            else
                changeInput()
        }

        et_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                changeInput()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        et_birth.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                changeInput()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                changeInput()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    private fun changeInput() {
        getEditText()
        if (getSignUp2.name.isEmpty() || getSignUp2.birth.isEmpty() || getSignUp2.email.isEmpty() || getSignUp2.btn == 0 || !cb_service_privacy.isChecked)
            iv_next_2.setImageResource(R.drawable.bt_next_unactive)
        else
            goToNext()
    }

    private fun onClickCamera() {
        btn_camera.setOnClickListener {
            requestReadExternalStoragePermission()
        }
    }

    private fun requestReadExternalStoragePermission() {
        //첫번째 if문을 통해 과거에 이미 권한 메시지에 대한 OK를 했는지 아닌지에 대해 물어봅니다!
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                //사용자에게 권한을 왜 허용해야되는지에 메시지를 주기 위한 대한 로직을 추가하려면 이 블락에서 하면됩니다!!
                //하지만 우리는 그냥 비워놓습니다!! 딱히 줄말 없으면 비워놔도 무관해요!!! 굳이 뭐 안넣어도됩니다!
            } else {
                //아래 코드는 권한을 요청하는 메시지를 띄우는 기능을 합니다! 요청에 대한 결과는 callback으로 onRequestPermissionsResult 메소드에서 받습니다!!!
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    My_READ_STORAGE_REQUEST_CODE
                )
            }
        } else {
            //첫번째 if문의 else로써, 기존에 이미 권한 메시지를 통해 권한을 허용했다면 아래와 같은 곧바로 앨범을 여는 메소드를 호출해주면됩니다!!
            showAlbum()
        }
    }

    fun getRealPathFromURI(content: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader: CursorLoader = CursorLoader(this, content, proj, null, null, null)
        val cursor: Cursor = loader.loadInBackground()
        val column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result = cursor.getString(column_idx)
        cursor.close()
        return result
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
                    getSignUp2.imageURI = getRealPathFromURI(selectedImageUri)

                    //Glide를 통해 imageView에 우리가 선택한 이미지를 띄워 줍시다!(무엇을 선택했는지는 알아야 좋겠죠?!)
                    Glide.with(this@SignUp2)
                        .load(selectedImageUri)
                        .thumbnail(0.1f)
                        .into(iv_profile)

                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //onActivityResult와 같은 개념입니다. requestCode로 어떤 권한에 대한 callback인지를 체크합니다.
        if (requestCode == My_READ_STORAGE_REQUEST_CODE) {
            //결과에 대해 허용을 눌렀는지 체크하는 조건문이구요!
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //이곳은 외부저장소 접근을 허용했을 때에 대한 로직을 쓰시면됩니다. 우리는 앨범을 여는 메소드를 호출해주면되겠죠?
                showAlbum()
            } else {
                //이곳은 외부저장소 접근 거부를 했을때에 대한 로직을 넣어주시면 됩니다.
                finish()
            }
        }
    }

    private fun showAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
    }

    private fun getEditText() {
        getSignUp2.name = et_name.text.toString()
        getSignUp2.birth = et_birth.text.toString()
        getSignUp2.email = et_email.text.toString()
    }

    private fun goToNext() {
        getEditText()
        if (getSignUp2.btn > 0 && getSignUp2.name.isNotEmpty() && getSignUp2.birth.isNotEmpty() && getSignUp2.email.isNotEmpty() && cb_service_privacy.isChecked) {
            iv_next_2.setImageResource(R.drawable.bt_next_active)
            iv_next_2.setOnClickListener {
                startActivity<SignUp3>()
                finish()
            }
        }
    }
}
