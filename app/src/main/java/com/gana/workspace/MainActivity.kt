package com.gana.workspace

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.gana.workspace.databinding.ActivityMainBinding
import com.gana.workspace.view.LoginActivity
import com.gana.workspace.view.RegistrationActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initViews()
        setupEdgeToEdge()
    }

    private fun initViews() {
        binding.button.setOnClickListener {
            navigateToLoginActivity()
        }
    }

    private fun navigateToLoginActivity() {
        Intent(this, RegistrationActivity::class.java).also {
            startActivity(it)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    private fun setupEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            WindowInsetsCompat.CONSUMED
        }
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }
}