package a248.kotlinoid.view

import a248.kotlinoid.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val ARG_TITLE = "arg_title"
private const val ARG_DESC = "arg_desc"

class ItemFragment: Fragment() {

    companion object {
        fun newInstance(title: String, desc: String): ItemFragment {
            val instance = ItemFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESC, desc)
            instance.arguments = args
            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(R.layout.fragment_item, container, false)

        val tv_title = view?.findViewById(R.id.fragment_item_title) as TextView
        tv_title.text = arguments.getString(ARG_TITLE)
        val tv_desc = view.findViewById(R.id.fragment_item_desc) as TextView
        tv_desc.text = arguments.getString(ARG_DESC)

        return view
    }
}