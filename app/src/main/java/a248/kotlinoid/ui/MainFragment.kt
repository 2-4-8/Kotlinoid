package a248.kotlinoid.ui

import a248.kotlinoid.MainRecyclerAdapter
import a248.kotlinoid.R
import a248.kotlinoid.VerticalSpaceItemDecorator
import a248.kotlinoid.data.AppDatabase
import a248.kotlinoid.data.ObjectEnity
import a248.kotlinoid.data.getDb
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

class MainFragment: Fragment(){

    lateinit var rv: RecyclerView
    lateinit var progress: ProgressBar

    lateinit var adapter: MainRecyclerAdapter
    lateinit var db: AppDatabase
    var objs: List<ObjectEnity> = listOf()

    companion object Factory {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            //set arguments
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_main, container, false)

        rv = view?.findViewById(R.id.fragment_main_rv) as RecyclerView
        rv.layoutManager = LinearLayoutManager(activity)
        adapter = MainRecyclerAdapter(objs) { deleteObject(it) }
        db = activity.getDb()
        rv.adapter = adapter
        rv.addItemDecoration(VerticalSpaceItemDecorator(1))

        progress = view.findViewById(R.id.fragment_main_progress) as ProgressBar
        progress.visibility = View.GONE

        updateUI()

        return view
    }

    fun updateUI() {
        objectsResultTask(
                {progress.visibility = View.VISIBLE},
                fun() = db.objectDao().getAll()
        ) {result -> kotlin.run {
            objs = result ?: listOf()
            adapter.items = objs
            adapter.notifyDataSetChanged()
            progress.visibility = View.GONE
        }}
    }

    fun insertObjects(vararg data: ObjectEnity) {
        unitResultTask(
                {progress.visibility = View.VISIBLE},
                {db.objectDao().insertAll(*data)},
                {updateUI()}
        )
    }

    fun deleteObject(obj: ObjectEnity) {
        unitResultTask(
                {progress.visibility = View.GONE},
                {db.objectDao().delete(obj)},
                {updateUI()}
        )
    }

}
