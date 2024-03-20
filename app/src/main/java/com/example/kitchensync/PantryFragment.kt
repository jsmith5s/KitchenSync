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

data class Country(var name: String="", val population: String="")

data class ScrapingResult(val countries: MutableList<Country> = mutableListOf(), var count:Int = 0)

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

    fun main(){
        // Set your website URL
        val website_url = "https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_population"
        val countries = skrape(HttpFetcher) {
            request {
                // Tell skrape{it} which URL to fetch data from
                url = website_url
            }


            extractIt<ScrapingResult> {results ->
                htmlDocument{
                    // Main function where you'll parse web data
                    val countryRows = table(".wikitable") {
                        tr{
                            findAll{this}
                        }
                    }

                    countryRows
                        .drop(2)  // Remove the first two elements; these are just the table header and subheader
                        .map{
                            // Define variables to hold name and population
                            var name: String =""
                            var population: String=""
                            it.a{
                                findFirst(){   // Find the first <a> tag
                                    name = text    // Extract its text (this is the name of the country)
                                    println("Name - $text ")
                                }
                            }
                            it.td{
                                findSecond(){    // Find the second <td> tag
                                    population = text   // Extract its text (this is the population of the country)
                                    println("Population - $text \n")
                                }
                            }
                            results.countries.add(Country(name,population))   // Create a country and add it to the results object
                            results.count = results.countries.size  // Get the number of countries and add it to the results object
                        }
                }
            }
        }
    }
}