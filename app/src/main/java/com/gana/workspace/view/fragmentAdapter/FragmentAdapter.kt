import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gana.workspace.R
import com.gana.workspace.databinding.HomeRvCardBinding
import com.gana.workspace.view.fragments.HomeFragment

class FragmentAdapter(homeFragment: Context, homeFragment1: HomeFragment) : RecyclerView.Adapter<FragmentAdapter.Holder>() {

    private val itemList = listOf("Item 1", "Item 2", "Item 3")

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
        val item = itemList[position]
        holder.binding.sometxt.text = item
    }

    override fun getItemCount(): Int = itemList.size

    class Holder(val binding: HomeRvCardBinding) : RecyclerView.ViewHolder(binding.root)

    interface Listener{
        fun onclick()
    }
}
