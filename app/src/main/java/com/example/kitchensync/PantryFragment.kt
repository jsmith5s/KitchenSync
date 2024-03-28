package com.example.kitchensync

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.network.parseGetRequestBlocking
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements
import com.squareup.picasso.Picasso
import java.net.URL
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class PantryFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var ingList : ArrayList<RecipeFoodItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pantry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.changeButton)
        var webName:String = "vegetables"

        button.setOnClickListener {
            if (webName == "vegetables")
                webName = "meat"
            else
                webName = "vegetables"


            var temp:TextView = view.findViewById(R.id.tempText)
            var fillText:String = ""

            val doc = Ksoup.parseGetRequestBlocking(url = "https://food.ndtv.com/ingredient/$webName")

            println("title: ${doc.title()}")
            val headlines: Elements = doc.select(".IngrLst-Ar_img")

            headlines.forEach { headline: Element ->
                val headlineTitle = headline.attr("title")
                val headlineLink = headline.absUrl("src")

                println(headlineTitle)
                fillText += "$headlineTitle\n"
                ingList.add(RecipeFoodItem(headlineTitle, headlineLink))
            }
            temp.text = fillText

        }

        /*fun switchURL() {
            var temp:TextView = view.findViewById(R.id.tempText)
            var fillText:String = ""

            val doc = Ksoup.parseGetRequestBlocking(url = "https://food.ndtv.com/ingredient/meat")

            println("title: ${doc.title()}")
            val headlines: Elements = doc.select(".IngrLst-Ar_img")

            headlines.forEach { headline: Element ->
                val headlineTitle = headline.attr("title")
                //val headlineLink = headline.absUrl("href")

                println(headlineTitle)
                fillText += "$headlineTitle\n"
            }
            temp.text = fillText
        }*/

        val recyclerPantry:RecyclerView = view.findViewById(R.id.recyclerPantry)
        //searchView = view.findViewById(R.id.searchView)
        recyclerPantry.setHasFixedSize(true)
        recyclerPantry.layoutManager = LinearLayoutManager(context)
        recyclerPantry.adapter = IngredientsAdapter(ingList)
        //recyclerPantry.adapter = adapter

        //var driver : WebDriver = ChromeDriver()
        //driver.get("https://food.ndtv.com/ingredient/vegetables")
        //println(driver.title)
    }


}

data class RecipeFoodItem(var foodName: String="", var imageUrl: String="")
class IngredientsAdapter(private val ingredientsList : ArrayList<RecipeFoodItem>) :
    RecyclerView.Adapter<IngredientsAdapter.IngViewHolder>(){
    class IngViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val ingredientView : ImageView = itemView.findViewById(R.id.imageView)
        val ingredientName : TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_items, parent, false)
        return IngViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngViewHolder, position: Int) {
        val food = ingredientsList[position]
        //val url = URL(food.imageUrl)
        //val imageData = url.readBytes()
        //holder.ingredientView.setImageBitmap(Bitmap.createBitmap(imageData))
        //Picasso.get().load(food.imageUrl).into(holder.ingredientView)
        holder.ingredientName.text = food.foodName
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

}
