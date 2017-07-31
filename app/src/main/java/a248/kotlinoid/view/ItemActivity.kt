package a248.kotlinoid.view

import a248.kotlinoid.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        setSupportActionBar(this.toolbar)

        val fragmentInstance = ItemFragment.newInstance(
                intent.getStringExtra(EXTRA_TITLE),
                intent.getStringExtra(EXTRA_DESC))

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragmentInstance).commit()
    }
}