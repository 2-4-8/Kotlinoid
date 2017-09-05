package a248.kotlinoid.view.fragments

import a248.kotlinoid.R
import a248.kotlinoid.databinding.FragmentItemBinding
import a248.kotlinoid.view.SupportLifecycleFragment
import a248.kotlinoid.viewmodel.ItemViewModel
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_item.*

class ItemFragment: SupportLifecycleFragment() {

    lateinit var binding: FragmentItemBinding
    lateinit var viewModel: ItemViewModel

    companion object {
        private const val ARG_ITEM_UUID = "arg_item_uuid"
        fun newInstance(uuid: Int): ItemFragment {
            val instance = ItemFragment()
            val args = Bundle()
            args.putInt(ARG_ITEM_UUID, uuid)
            instance.arguments = args
            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        viewModel.init(activity)
        viewModel.getItem(arguments.getInt(ARG_ITEM_UUID)).subscribe { t -> binding.item = t }
    }
}