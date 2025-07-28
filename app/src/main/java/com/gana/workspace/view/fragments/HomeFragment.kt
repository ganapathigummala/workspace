package com.gana.workspace.view.fragments

import FragmentAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gana.workspace.R
import com.gana.workspace.databinding.HomefragmentBinding

class HomeFragment : Fragment() , FragmentAdapter.Listener{

    lateinit var adapter: FragmentAdapter
    lateinit var homeRv: RecyclerView
     var _binding: HomefragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.homefragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
        observeViewModel()    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    // Called to clean up resources
    override fun onDestroyView() {
        super.onDestroyView()
        // Cleanup references to views to avoid memory leaks
    }

    // Called when the fragment is no longer in use
    override fun onDestroy() {
        super.onDestroy()
    }

    // Called when the fragment is detached from its activity
    override fun onDetach() {
        super.onDetach()
    }
    private fun setupUI(view: View) {
        homeRv = binding.homeRv
        homeRv.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = FragmentAdapter(requireContext(), this@HomeFragment)
        }
    }



    private fun observeViewModel() {
        // Connect to your ViewModel's LiveData or StateFlow
    }

    override fun onclick() {
        TODO("Not yet implemented")
    }


}
