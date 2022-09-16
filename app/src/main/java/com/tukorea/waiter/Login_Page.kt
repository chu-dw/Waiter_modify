package com.tukorea.waiter

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tukorea.waiter.databinding.ActivityLoginPageBinding
import java.security.MessageDigest
import kotlin.math.log


class Login_Page : AppCompatActivity() {

    private var auth : FirebaseAuth? = null
    private lateinit var binding: ActivityLoginPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        auth = Firebase.auth
        var db = Firebase.firestore

        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //회원가입 버튼 클릭
        binding.signinButton.setOnClickListener {
            val sign_intent = Intent(this,SignUp_Page::class.java)
            startActivity(sign_intent)
        }

        //로그인 버튼 클릭
        binding.loginbutton.setOnClickListener {
            val userid = binding.userid.text.toString()
            val password = binding.userpassword.text.toString()

            Log.d("로그인 확인","${password}")

            val map_intent = Intent(this,Map_page::class.java)
            val owner_intent = Intent(this,StoreList_page::class.java)

            if(binding.userid.text.toString().equals("") || binding.userpassword.toString().equals("")){
                Toast.makeText(this, "로그인에 필요한 정보를 모두 입력해 주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                if(db.equals("user")){
                    Log.d("컬렉션","user")
                }
                Log.d("컬렉션","${db.getNamedQuery(dataDir.toString())}")
                userlogin(userid,password, map_intent)

            }
        }
    }

    private fun userlogin(userEmail: String, password: String, intent:Intent) {
        Firebase.auth.signInWithEmailAndPassword(userEmail,password).addOnCompleteListener(this) {
            Log.d("로그인 확인","${password}")
            if(it.isSuccessful) {
                Log.d("로그인","되냐?")
                startActivity(intent)
            }
            else {
                Log.w("왜 안되는데", "signInWithEmail", it.exception)
                Toast.makeText(this, "아이디 혹은 비밀번호 오류로 인한 로그인 실패!", Toast.LENGTH_SHORT).show()

            }
        }
    }

}