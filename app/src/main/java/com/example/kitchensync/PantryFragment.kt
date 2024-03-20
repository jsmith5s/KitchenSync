package com.example.kitchensync

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extractIt
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.*

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

    /*fun main(){
        // Set your website URL
        val website_url = "https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_population"
        val countries : List<String> = skrape(HttpFetcher) {
            request {
                // Tell skrape{it} which URL to fetch data from
                url = website_url
            }


            extractIt<ScrapingResult> { results ->
                htmlDocument{
                    // Main function where you'll parse web data
                }
            }
        }
    }*/
}