package a248.kotlinoid.view.fragments

import a248.kotlinoid.R
import a248.kotlinoid.view.SupportLifecycleFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_item.*

class ItemFragment: SupportLifecycleFragment() {

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_DESC = "arg_desc"
        fun newInstance(title: String, desc: String): ItemFragment {
            val instance = ItemFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESC, desc)
            instance.arguments = args
            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = inflater?.inflate(R.layout.fragment_item, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.fragment_item_title.text = arguments.getString(ARG_TITLE)
        this.fragment_item_desc.text = arguments.getString(ARG_DESC)
    }
}