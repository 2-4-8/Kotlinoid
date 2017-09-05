package a248.kotlinoid.view.adapters

import a248.kotlinoid.R
import a248.kotlinoid.databinding.RecyclerItemBinding
import a248.kotlinoid.model.ItemEntity
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_item.view.*

class MainRecyclerAdapter(
        var items: List<ItemEntity>,
        val onClick: (ItemEntity) -> Unit,
        val deleter: (ItemEntity) -> Unit) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemEntity, onClick: (ItemEntity) -> Unit, deleter: (ItemEntity) -> Unit) {
            binding.item = item
            binding.itemDelete.setOnClickListener{ deleter(item) }
            binding.root.setOnClickListener{ onClick(item) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], onClick, deleter)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = items.size
}

class VerticalSpaceItemDecorator(val height: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        outRect.bottom = height
    }
}