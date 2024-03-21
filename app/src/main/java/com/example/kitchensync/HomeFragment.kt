package com.example.kitchensync

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // getting the trendingList
        val trendingList = listOf(
            TrendingFood(R.drawable.berrytart, "Berry Tart"),
            TrendingFood(R.drawable.pancakes, "Pancakes"),
            TrendingFood(R.drawable.crepe, "Crêpes"),
            TrendingFood(R.drawable.chickensalad, "Chicken Salad"),
            TrendingFood(R.drawable.orangechicken, "Orange Chicken"),
            TrendingFood(R.drawable.raspberrymouse, "Raspberry Mousse"),
            TrendingFood(R.drawable.creme, "Crème Brûlée"),
            TrendingFood(R.drawable.foodsample, "Hot Food")
        )

        val recyclerView:RecyclerView=view.findViewById(R.id.trend)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = HomeAdapter(trendingList)
        val snap: SnapHelper = LinearSnapHelper()
        snap.attachToRecyclerView(recyclerView)


    }


}