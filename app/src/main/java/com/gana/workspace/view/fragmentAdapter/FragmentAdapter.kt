package com.gana.workspace.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gana.workspace.R
import com.gana.workspace.databinding.HomeRvCardBinding
import com.gana.workspace.view.model.HomeModel

class FragmentAdapter(
    private val context: Context,
    private var aiModels: List<HomeModel>,
    private val listener: Listenre
) : RecyclerView.Adapter<FragmentAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: HomeRvCardBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.home_rv_card,
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = aiModels[position]
        holder.binding.sometxt.text = item.name

        holder.binding.root.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = aiModels.size

    fun updateList(newList: List<HomeModel>) {
        aiModels = newList
        notifyDataSetChanged()
    }

    class Holder(val binding: HomeRvCardBinding) : RecyclerView.ViewHolder(binding.root)
}

interface Listenre {
    fun onItemClick(model: HomeModel)
}
