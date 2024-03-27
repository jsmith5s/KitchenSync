package com.example.kitchensync

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.network.parseGetRequestBlocking
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_pantry)
        }

        //use this link later for image searching
        //https://google.com/search?q=space&tbm=isch
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.bottom_recipes -> {
                    replaceFragment(RecipesFragment())
                    true
                }
                R.id.bottom_search -> {
                    replaceFragment(PantryFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(HomeFragment())


        val doc = Ksoup.parseGetRequestBlocking(url = "https://food.ndtv.com/ingredient/vegetables")

        println("title: ${doc.title()}")
        val headlines: Elements = doc.select(".IngrLst-Ar_img")

        headlines.forEach { headline: Element ->
            val headlineTitle = headline.attr("title")
            //val headlineLink = headline.absUrl("href")

            println(headlineTitle)
        }
    }

    private fun replaceFragment (fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            R.id.nav_pantry -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MyPantryFragment()).commit()
            R.id.nav_recipes -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MyRecipesFragment()).commit()
            R.id.nav_diets -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DietsFragment()).commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            onBackPressedDispatcher.onBackPressed()
    }
}