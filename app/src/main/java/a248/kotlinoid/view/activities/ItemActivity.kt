package a248.kotlinoid.view.activities

import a248.kotlinoid.R
import a248.kotlinoid.view.fragments.ItemFragment
import a248.kotlinoid.view.fragments.MainFragment
import a248.kotlinoid.view.SupportLifecycleActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity: SupportLifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        setSupportActionBar(this.toolbar)

        val fragmentInstance = ItemFragment.newInstance(
                intent.getIntExtra(MainFragment.EXTRA_ITEM_UUID, 0)
        )

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragmentInstance).commit()
    }

}