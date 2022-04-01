package com.radcom.weather.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.radcom.weather.R
import com.radcom.weather.data.uiObjects.CityWeatherModel
import kotlinx.android.synthetic.main.item_city_weather.view.*


class MainListAdapter() :
    ListAdapter<CityWeatherModel, MainListAdapter.ViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<CityWeatherModel>() {

        override fun areItemsTheSame(oldItem: CityWeatherModel, newItem: CityWeatherModel) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CityWeatherModel, newItem: CityWeatherModel) =
            oldItem.temperature == newItem.temperature
                    && oldItem.windSpeed == newItem.windSpeed
                    && oldItem.maxTemperature == newItem.maxTemperature
                    && oldItem.minTemperature == newItem.minTemperature
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city_weather, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.currentList[position]
        holder.bind(item, position)
    }

    class ViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(model: CityWeatherModel, index: Int) {
            this.itemView.degrees.text = model.temperature.toString()
            this.itemView.minTemp.text = model.minTemperature.toString()
            this.itemView.maxTemp.text = model.maxTemperature.toString()
            this.itemView.windSpeed.text = model.windSpeed.toString()
            this.itemView.city.text = model.name
            this.itemView.coords.text = "${model.coordinates.first}, ${model.coordinates.second}"

            this.itemView.locationButton.setOnClickListener {
                //or passed to viewModel
                val uri =
                    "http://maps.google.com/maps?q=loc:" + model.coordinates.first + "," + model.coordinates.second
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))

                this.itemView.context.startActivity(intent)

            }

        }
    }
}