package com.tukorea.waiter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tukorea.waiter.databinding.ActivitySignUpPageBinding


class SignUp_Page : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    private lateinit var binding: ActivitySignUpPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivitySignUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db: FirebaseFirestore = Firebase.firestore
        db = FirebaseFirestore.getInstance()

        binding.signinbutton.setOnClickListener {
            // 입력칸들 중 하나라도 비어있다면 -> 모든 정보를 입력해주세요
            if(binding.signName.text.toString().equals("")
                ||binding.signmail.text.toString().equals("")
                ||binding.signPW.text.toString().equals("")
                ||binding.signPWcheck.text.toString().equals("")
                ||binding.signName.text.toString().equals("")
                ||binding.signnumber.text.toString().equals("")
                ||(binding.customer.isChecked === false &&binding.owner.isChecked === false)
            ){
                Toast.makeText(this, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
            }

            // 비밀번호와 비밀번호 확인이 일치하지 않으면 -> 두 비밀번호가 일치하지 않습니다
            else if(!binding.signPW.text.toString().equals(binding.signPWcheck.text.toString())){
                Toast.makeText(this, "두 비밀번호가 일치하지 않습니다!\n", Toast.LENGTH_SHORT).show()
            }
            // 위 두 경우가 아니면 Firebase auth에 Email과 Password를 담아 회원을 생성해 줌
            else{
                Firebase.auth.createUserWithEmailAndPassword(
                    binding.signmail.text.toString(),
                    binding.signPW.text.toString()
                ).addOnCompleteListener(this){
                    if (it.isSuccessful) {
                        val userinfo = hashMapOf(
                            "signName" to binding.signName.text.toString(),
                            "phoneNum" to binding.signnumber.text.toString(),
                            "type" to "type"
                        )

                        val useruid = Firebase.auth.currentUser?.uid //회원가입한 회원 uid
                        //손님 회원가입
                        if (binding.customer.isChecked) {
                            db.collection("user")
                               .document("${useruid}")
                               .set(userinfo)

                            //유저 타입 user로 (default값 owner)
                            db.collection("login")
                                .document("${useruid}")
                                .set(userinfo)

                            db.collection("login")
                                .document("${useruid}")
                                .update("type","user")

                            Log.d("usercheck","${Firebase.auth.currentUser?.uid}")

                        }

                        //주인 회원가입
                        else if(binding.owner.isChecked) {
                            Log.d("logincheck","owner")
                            Log.d("usercheck","${useruid}")
                            db.collection("owner")
                                .document("${useruid}")
                                .set(userinfo)

//                            db.collection("owner")
//                                .document("${useruid}").collection("x_y")
//                                .document("storeName").set("blank")
//
//                            db.collection("owner")
//                                .document("${useruid}").collection("x_y")
//                                .document("storeName").collection("reservation_list")
//
//                            db.collection("login")
//                                .document("${useruid}")
//                                .set(userinfo)
//
//                            db.collection("login")
//                                .document("${useruid}")
//                                .update("type","owner")

                            db.collection("login")
                                .document("${useruid}")
                                .update("type","owner")
                        }


                        Toast.makeText(this, "회원가입 완료! 로그인 해주세요", Toast.LENGTH_LONG).show()
                        finish() //회원가입 완료되면 페이지 종료
                    }
                    // 위에서 말한 모든 경우에 해당하지 않고 회원가입 실패
                    else{
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}