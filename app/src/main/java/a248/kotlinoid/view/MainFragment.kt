package a248.kotlinoid.view

import a248.kotlinoid.view.adapters.MainRecyclerAdapter
import a248.kotlinoid.R
import a248.kotlinoid.view.adapters.VerticalSpaceItemDecorator
import a248.kotlinoid.model.AppDatabase
import a248.kotlinoid.model.ItemEntity
import a248.kotlinoid.model.getDb
import a248.kotlinoid.viewmodel.ItemViewModel
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

const val EXTRA_TITLE = "item_title"
const val EXTRA_DESC = "item_desc"

class MainFragment: SupportLifecycleFragment(){

    lateinit var rv: RecyclerView
    lateinit var progress: ProgressBar

    lateinit var adapter: MainRecyclerAdapter
    lateinit var db: AppDatabase
    var items: List<ItemEntity> = listOf()
    lateinit var viewModel: ItemViewModel

    companion object Factory {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        viewModel.init(activity.getDb().itemDao())
        viewModel.items?.observe(this, Observer<List<ItemEntity>> {
            Log.d("==MainFragment", "updating UI from LiveData observer")
            updateUI()
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_main, container, false)

        rv = view?.findViewById(R.id.fragment_main_rv) as RecyclerView
        rv.layoutManager = LinearLayoutManager(activity)
        adapter = MainRecyclerAdapter(items, {startItemActivity(it)}) { deleteItem(it) }
        db = activity.getDb()
        rv.adapter = adapter
        rv.addItemDecoration(VerticalSpaceItemDecorator(1))

        progress = view.findViewById(R.id.fragment_main_progress) as ProgressBar
        progress.visibility = View.GONE

        return view
    }

    fun updateUI() {
        Log.d("==MainFragment", "updating UI")
        items = viewModel.items?.value ?: listOf()
        adapter.items = items
        adapter.notifyDataSetChanged()
        progress.visibility = View.GONE
    }

    fun insertItem(data: ItemEntity) {
        viewModel.addItem(data)
    }

    fun deleteItem(obj: ItemEntity) {
        viewModel.deleteItem(obj)
    }

    fun startItemActivity(obj: ItemEntity) {
        val intent = Intent(activity, ItemActivity::class.java)
        intent.putExtra(EXTRA_TITLE, obj.title)
        intent.putExtra(EXTRA_DESC, obj.desc)
        activity.startActivity(intent)
    }

}
