package com.gana.workspace

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.gana.workspace.databinding.ActivityMainBinding
import com.gana.workspace.view.fragments.HomeFragment
import com.gana.workspace.view.fragments.LikesFragment
import com.gana.workspace.view.fragments.ProfileFragment
import com.gana.workspace.view.fragments.ShopsFragment
import com.gana.workspace.view.java.RegistrationActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupEdgeToEdge()
        initViews()
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }
    }

    private fun initViews() {
        binding.home.setOnClickListener {
            loadFragment(HomeFragment())
        }

        binding.shops.setOnClickListener {

            loadFragment(ShopsFragment())
        }
        binding.likes.setOnClickListener {

            loadFragment(LikesFragment())
        }
        binding.profile.setOnClickListener {

            loadFragment(ProfileFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fcv, fragment)
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
