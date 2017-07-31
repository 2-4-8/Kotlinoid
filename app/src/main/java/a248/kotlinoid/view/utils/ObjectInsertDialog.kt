package a248.kotlinoid.view.utils

import a248.kotlinoid.R
import a248.kotlinoid.model.ItemEntity
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import android.widget.EditText
import kotlinx.android.synthetic.main.dialog_insert.view.*

fun FragmentActivity.buildSimpleDialog(onCreated: (ItemEntity) -> Unit) {
    val builder = AlertDialog.Builder(this)
    val inflater = this.layoutInflater
    val dialogView = inflater.inflate(R.layout.dialog_insert, null)
    builder.setView(dialogView)

    var etTitle: EditText? = null
    var etDesc: EditText? = null

    with (dialogView) {
        etTitle = dialog_et_title
        etDesc = dialog_et_desc
    }

    builder.setTitle("New Object")
    builder.setPositiveButton("Ok") {dialog, which ->
        run {
            val obj = ItemEntity()
            obj.title = etTitle?.text?.toString() ?: "No title"
            obj.desc = etDesc?.text?.toString() ?: "No desc"
            onCreated(obj)
        }
    }
    builder.setNegativeButton("Cancel", {dialog, which ->  })
    builder.create().show()
}