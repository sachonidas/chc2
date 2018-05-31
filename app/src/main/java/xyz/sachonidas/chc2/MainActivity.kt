package xyz.sachonidas.chc2

import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import xyz.sachonidas.chc2.R.id.navigation
import xyz.sachonidas.chc2.R.menu.navigation

class MainActivity : AppCompatActivity() {

    private var mTextMessage: TextView? = null
    internal val PREFS_NAME = "MyPrefsFile"
    internal var select = 0
    internal var sp1: Spinner? = null
    internal var etPeso: EditText? = null
    internal var etHoras: EditText? = null
    internal var etCarbo: EditText? = null
    internal var swtich: Switch? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> fragment = CalculatorFragment()
            R.id.navigation_dashboard -> fragment = ItemFragment()
            R.id.navigation_notifications -> fragment = StatsFragment()
        }//mTextMessage.setText(R.string.title_home);
        //mTextMessage.setText(R.string.title_dashboard);
        //mTextMessage.setText(R.string.title_notifications);

        replaceFragment(fragment)
        true
    }

    private fun setInitialFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.main_fragment_placeholder, CalculatorFragment())
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: Fragment?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_fragment_placeholder, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val settings = getSharedPreferences(PREFS_NAME, 0)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time")

            // first time task

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit()
        }

        setInitialFragment()

        val adapter = ArrayAdapter.createFromResource(this,
                R.array.hidratos_array, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        sp1.adapter = adapter

        sp1.visibility = View.VISIBLE
        etCarbo.visibility = View.INVISIBLE

        swtich.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                sp1.visibility = View.VISIBLE
                etCarbo.visibility = View.INVISIBLE
                sp1.requestFocus()
            } else {
                sp1.visibility = View.INVISIBLE
                etCarbo.visibility = View.VISIBLE
                etCarbo.requestFocus()
            }
        }


    }

}
