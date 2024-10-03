import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sevagrbnv.shambambukliapp.Cell
import ru.sevagrbnv.shambambukliapp.CellType
import ru.sevagrbnv.shambambukliapp.R
import ru.sevagrbnv.shambambukliapp.databinding.ListItemBinding

class CellListAdapter: ListAdapter<Cell, CellListAdapter.CellViewHolder>(
    CellDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val layout = R.layout.list_item
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return CellViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding as ListItemBinding
        when (item.type) {
            CellType.DEAD -> {
                binding.icon.setBackgroundResource(R.drawable.death_shape)
                binding.icon.text = holder.itemView.context.getString(R.string.dead_icon)
                binding.type.text = holder.itemView.context.getString(R.string.Dead)
                binding.comment.text = holder.itemView.context.getString(R.string.dead_comment)
            }
            CellType.LIFE -> {
                binding.icon.setBackgroundResource(R.drawable.life_shape)
                binding.icon.text = holder.itemView.context.getString(R.string.life_icon)
                binding.type.text = holder.itemView.context.getString(R.string.life)
                binding.comment.text = holder.itemView.context.getString(R.string.life_comment)
            }
            CellType.BEING -> {
                binding.icon.setBackgroundResource(R.drawable.being_shape)
                binding.icon.text = holder.itemView.context.getString(R.string.being_icon)
                binding.type.text = holder.itemView.context.getString(R.string.being)
                binding.comment.text = holder.itemView.context.getString(R.string.being_comment)
            }
        }

    }

    class CellViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    class CellDiffCallBack: DiffUtil.ItemCallback<Cell>() {

        override fun areItemsTheSame(oldItem: Cell, newItem: Cell): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cell, newItem: Cell): Boolean {
            return oldItem == newItem
        }
    }
}
