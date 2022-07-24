package com.example.testexamplecleanarch.presentation.mainFragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testexamplecleanarch.R

import com.example.testexamplecleanarch.data.model.BikeStationsModel
import com.example.testexamplecleanarch.databinding.ListItemStationsBinding
import com.google.android.material.snackbar.Snackbar

class StationsAdapter(
    private val bikestationsList: List<BikeStationsModel.Feature>,
    private val context: Context
) : RecyclerView.Adapter<StationsAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemMallLayoutBinding: ListItemStationsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.list_item_stations,
            parent,
            false
        )
        return Viewholder(itemMallLayoutBinding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val model: BikeStationsModel.Feature = bikestationsList[position]
        holder.listItemStationsBinding.tvHead.text=model.properties.label
        holder.listItemStationsBinding.tvvalone.text=model.properties.bikes
        holder.listItemStationsBinding.tvvaltwo.text=model.properties.free_racks

        holder.listItemStationsBinding.constraintmain.setOnClickListener {
            Bundle().apply {
                val navController = findNavController(it)
                putDouble("lat",model.geometry.coordinates[0])
                putDouble("lng",model.geometry.coordinates[1])
                navController.navigate(R.id.detailsFragment,this)
            }
        }

    }

    override fun getItemCount(): Int {
        return bikestationsList.size
    }

    class Viewholder(val listItemStationsBinding: ListItemStationsBinding) :
        RecyclerView.ViewHolder(listItemStationsBinding.root) {

    }
}