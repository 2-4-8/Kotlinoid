package a248.kotlinoid.view

import a248.kotlinoid.R
import a248.kotlinoid.view.utils.buildSimpleDialog
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

    val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(this.toolbar)

        val fragmentInstance = MainFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragmentInstance).commit()

        val fab = this.floatingActionButton
        fab.setOnClickListener { buildSimpleDialog { fragmentInstance.insertItem(it) } }
    }
}
