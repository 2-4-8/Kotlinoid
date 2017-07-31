package a248.kotlinoid.view.adapters

import a248.kotlinoid.R
import a248.kotlinoid.model.ItemEntity
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_item.view.*

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

class MainRecyclerAdapter(
        var items: List<ItemEntity>,
        val onClick: (ItemEntity) -> Unit,
        val deleter: (ItemEntity) -> Unit)
    : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: ItemEntity,
                 onClick: (ItemEntity) -> Unit,
                 deleter: (ItemEntity) -> Unit) = with(itemView) {
            item_title.text = "${item.title} (#${item.uuid})"
            item_desc.text = item.desc
            item_delete.setOnClickListener { deleter(item) }
            itemView.setOnClickListener { onClick(item) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], onClick, deleter)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.recycler_item))

    override fun getItemCount(): Int = items.size
}

class VerticalSpaceItemDecorator(val height: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        outRect.bottom = height
    }
}

class MainRecyclerAnimator: SimpleItemAnimator() {
    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateMove(holder: RecyclerView.ViewHolder?,
                             fromX: Int, fromY: Int,
                             toX: Int, toY: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateChange(oldHolder: RecyclerView.ViewHolder?,
                               newHolder: RecyclerView.ViewHolder?,
                               fromLeft: Int, fromTop: Int,
                               toLeft: Int, toTop: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun runPendingAnimations() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isRunning(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun endAnimation(item: RecyclerView.ViewHolder?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun endAnimations() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}