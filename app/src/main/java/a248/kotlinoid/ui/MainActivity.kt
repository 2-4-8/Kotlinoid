package a248.kotlinoid.ui

import a248.kotlinoid.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(this.toolbar)

        val fragmentInstance = MainFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragmentInstance).commit()

        val fab = this.floatingActionButton
        fab.setOnClickListener { buildSimpleDialog { fragmentInstance.insertObjects(it) } }
    }
}
