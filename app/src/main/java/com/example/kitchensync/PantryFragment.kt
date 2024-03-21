package com.example.kitchensync

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.network.parseGetRequestBlocking
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements

class PantryFragment : Fragment() {

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
                //val headlineLink = headline.absUrl("href")

                println(headlineTitle)
                fillText += "$headlineTitle\n"
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

    }



}

data class RecipeFoodItem(var foodName: String="", var imageUrl: String="")
