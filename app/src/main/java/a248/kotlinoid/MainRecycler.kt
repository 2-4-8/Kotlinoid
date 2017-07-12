package a248.kotlinoid

import a248.kotlinoid.data.ObjectEnity
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_item.view.*

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

class MainRecyclerAdapter(var items: List<ObjectEnity>, val deleter: (ObjectEnity) -> Unit):
        RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: ObjectEnity, deleter: (ObjectEnity) -> Unit) = with(itemView) {
            Log.d("==MainRecycler", "Binding ViewHolder")
            item_title.text = "${item.title} (#${item.uuid})"
            item_desc.text = item.desc
            item_delete.setOnClickListener { deleter(item) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items.get(position), deleter)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("==MainRecycler", "Creating ViewHolder")
        return ViewHolder(parent.inflate(R.layout.recycler_item))
    }

    override fun getItemCount(): Int = items.size
}

class VerticalSpaceItemDecorator(val height: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        outRect.bottom = height
    }
}