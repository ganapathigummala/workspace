package com.gana.workspace.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gana.workspace.R
import com.gana.workspace.databinding.HomefragmentBinding
import com.gana.workspace.view.adapter.FragmentAdapter
import com.gana.workspace.view.adapter.Listenre
import com.gana.workspace.view.model.HomeModel
import com.gana.workspace.view.viewmodel.HomeViewModel

class HomeFragment : Fragment(), Listenre {

    private var _binding: HomefragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FragmentAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.homefragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = FragmentAdapter(requireContext(), emptyList(), this)
        binding.homeRv.layoutManager = LinearLayoutManager(requireContext())
        binding.homeRv.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.homeModels.observe(viewLifecycleOwner) { models ->
            adapter.updateList(models)
        }
    }

    override fun onItemClick(model: HomeModel) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(model.url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
