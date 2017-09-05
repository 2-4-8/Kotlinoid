package a248.kotlinoid.view.fragments

import a248.kotlinoid.view.adapters.MainRecyclerAdapter
import a248.kotlinoid.R
import a248.kotlinoid.view.adapters.VerticalSpaceItemDecorator
import a248.kotlinoid.model.AppDatabase
import a248.kotlinoid.model.ItemEntity
import a248.kotlinoid.model.getDb
import a248.kotlinoid.view.SupportLifecycleFragment
import a248.kotlinoid.view.activities.ItemActivity
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
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment: SupportLifecycleFragment(){

    lateinit var rv: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var adapter: MainRecyclerAdapter
    lateinit var viewModel: ItemViewModel

    companion object Factory {
        const val EXTRA_ITEM_UUID = "extra_uuid"
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = inflater?.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv = this.fragment_main_rv
        rv.layoutManager = LinearLayoutManager(activity)
        adapter = MainRecyclerAdapter(listOf(), {startItemActivity(it)}) { deleteItem(it) }
        rv.adapter = adapter
        rv.addItemDecoration(VerticalSpaceItemDecorator(1))
        progress = this.fragment_main_progress
        progress.visibility  = View.VISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        viewModel.init(activity)
        viewModel.items?.observe(this, Observer<List<ItemEntity>> { updateUI() })
    }

    fun updateUI() {
        adapter.items = viewModel.items?.value ?: listOf()
        adapter.notifyDataSetChanged()
        progress.visibility = View.GONE
    }

    fun insertItem(item: ItemEntity) {
        viewModel.addItem(item)
    }

    fun deleteItem(item: ItemEntity) {
        viewModel.deleteItem(item)
    }

    fun startItemActivity(item: ItemEntity) {
        val intent = Intent(activity, ItemActivity::class.java)
        intent.putExtra(EXTRA_ITEM_UUID, item.uuid)
        activity.startActivity(intent)
    }

}
