package com.egecius.electriccars.paging

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.egecius.electriccars.mainactivity.CarRecyclerViewAdapter
import com.egecius.electriccars.room.Car

class MyPagedListAdapter private constructor() :
    PagedListAdapter<Car, CarRecyclerViewAdapter.MyViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarRecyclerViewAdapter.MyViewHolder {
        return null
    }

    override fun onBindViewHolder(holder: CarRecyclerViewAdapter.MyViewHolder, position: Int) {

    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Car>() {
            override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem == newItem
            }
        }
    }
}
