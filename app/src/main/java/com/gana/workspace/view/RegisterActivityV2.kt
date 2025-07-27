package com.gana.workspace.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gana.workspace.MainActivity
import com.gana.workspace.R
import com.gana.workspace.databinding.ActivityRegistrationBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivityV2 : AppCompatActivity(){
    lateinit var auth:FirebaseAuth
    lateinit var activityRegistrationBinding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegistrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration)
        initUi()
        onClickEvents()
    }

    private fun onClickEvents() {
        activityRegistrationBinding.button4.setOnClickListener{
            val userName: String = activityRegistrationBinding.inputUsername.text.toString()
            val password: String = activityRegistrationBinding.inputPassword.text.toString()
            createUser(userName,password)
        }
    }

    private fun initUi(){
        auth = Firebase.auth
        activityRegistrationBinding?.textView6?.setOnClickListener(View.OnClickListener {
            Intent(this,LoginActivityV2::class.java).also{
            startActivity(it)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        } })
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }
    }

    private fun createUser(userName: String,password: String){
        auth.createUserWithEmailAndPassword(userName,password)
            .addOnCompleteListener(this){
                task ->
                    if(task.isSuccessful){
                        val user = auth.currentUser
                        updateUi(user)
                    }else{
                        Snackbar
                            .make(activityRegistrationBinding.root,"Not valid user",Snackbar.LENGTH_SHORT)
                            .show();
                    }
            }
    }

    private fun reload(){

    }

    private fun updateUi(user: FirebaseUser?){
        Snackbar
            .make(activityRegistrationBinding.root,"Successfully created",Snackbar.LENGTH_SHORT)
            .show();
        Intent(this,MainActivity::class.java).also{
            startActivity(it)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}