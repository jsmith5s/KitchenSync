package com.example.kitchensync

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(private val trendingList : List<TrendingFood>) :
    RecyclerView.Adapter<HomeAdapter.FoodViewHolder>(){
    class FoodViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val foodImageView : ImageView = itemView.findViewById(R.id.imageView)
        val foodNameTV : TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_items, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = trendingList[position]
        holder.foodImageView.setImageResource(food.foodImage)
        holder.foodNameTV.text = food.foodName
    }

}