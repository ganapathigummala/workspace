package com.gana.workspace.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gana.workspace.MainActivity
import com.gana.workspace.R
import com.gana.workspace.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.apache.commons.lang3.ObjectUtils.isNotEmpty

class LoginActivityV2 : AppCompatActivity() {
    lateinit var activityLoginBinding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initUi()
        onClickEvents()
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUi(currentUser)
        }
    }
    private fun initUi(){
        auth = Firebase.auth
    }

    private fun onClickEvents() {
        activityLoginBinding.button4.setOnClickListener{
            val userName: String = activityLoginBinding.inputUsername.text.toString()
            val password: String = activityLoginBinding.inputPassword.text.toString()
            validateUser(userName,password)
        }

        activityLoginBinding.textView6.setOnClickListener{
            Intent(this, RegisterActivityV2::class.java).also{
                startActivity(it)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }
    }

    private fun validateUser(userName: String,password: String){
        if(!isNotEmpty(userName) && !isNotEmpty(password)){
            activityLoginBinding.txtUsername.error = "Enter Username"
            activityLoginBinding.txtPassword.error = "Enter Password"
            return
        }
        Log.d("dsfsfs",userName)
        Log.d("dsfsfs",password)
        auth.signInWithEmailAndPassword(userName,password)
            .addOnCompleteListener(this){
                task ->
                    if(task.isSuccessful){
                        Snackbar
                            .make(activityLoginBinding.root,"Valid user",Snackbar.LENGTH_SHORT)
                            .show()
                        val user = auth.currentUser
                        updateUi(user)

                    }else{
                        Snackbar
                            .make(activityLoginBinding.root,"Not valid user",Snackbar.LENGTH_SHORT)
                            .show();
                    }
            }
    }

    private fun updateUi(user: FirebaseUser?){
        Snackbar
            .make(activityLoginBinding.root,"Successfully created",Snackbar.LENGTH_SHORT)
            .show();
        Intent(this, MainActivity::class.java).also{
            startActivity(it)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}