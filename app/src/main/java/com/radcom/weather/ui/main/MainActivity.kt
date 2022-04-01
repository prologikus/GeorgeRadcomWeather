package com.radcom.weather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.color.DynamicColors
import com.radcom.weather.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        refresh.setOnClickListener {
            viewModel.refreshPressed()
        }
    }

    private fun setupObservers() {

        //setup adapter with mainList
        //getting view using Kotlin synthetics
        val mainListAdapter = MainListAdapter()
        mainList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainList.adapter = mainListAdapter

        //every time cities list change:
        viewModel.citiesList.observe(this, Observer {
            mainListAdapter.submitList(it)
        })

        //loading changes:
        viewModel.loading.observe(this, Observer {
            refresh.isEnabled = !it
            loadingOverLay.isVisible = it
        })
    }
}