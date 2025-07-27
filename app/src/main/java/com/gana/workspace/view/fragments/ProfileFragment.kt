package com.gana.workspace.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gana.workspace.databinding.ProfileFragmentBinding
import com.gana.workspace.view.LoginActivityV2
import com.gana.workspace.view.java.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private var binding: ProfileFragmentBinding? = null
//    private val binding get() = binding!!

    override fun onCreateView( inflater: LayoutInflater,  container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ProfileFragment", "onViewCreated called")
        initUi()
        onClickListener();
        // Example: binding.textViewProfile.text = "Welcome, User!"
    }

    private fun initUi() {
        val user = Firebase.auth.currentUser
        binding?.email?.setText(user?.email ?: "No email")
    }

    private fun onClickListener() {
        binding?.logout?.setOnClickListener(View.OnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(requireContext(), LoginActivityV2::class.java))
            requireActivity().finish()
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d("ProfileFragment", "onStart called")
    }

    override fun onResume() {
        super.onResume()

        Log.d("ProfileFragment", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ProfileFragment", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ProfileFragment", "onStop called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null 
        Log.d("ProfileFragment", "onDestroyView called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ProfileFragment", "onDestroy called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("ProfileFragment", "onDetach called")
    }
}
