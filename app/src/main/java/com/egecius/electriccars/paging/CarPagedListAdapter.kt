package com.egecius.electriccars.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.egecius.electriccars.R
import com.egecius.electriccars.mainactivity.CarRecyclerViewAdapter
import com.egecius.electriccars.mainactivity.OnCarClickListener
import com.egecius.electriccars.room.Car
import com.squareup.picasso.Picasso

class CarPagedListAdapter(private val onCarClickListener: OnCarClickListener) : PagedListAdapter<Car, CarRecyclerViewAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CarRecyclerViewAdapter.MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.car_list_item, viewGroup, false)
        return CarRecyclerViewAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarRecyclerViewAdapter.MyViewHolder, position: Int) {
        val item: Car? = getItem(position)
        holder.cardView.setOnClickListener {
            if (item != null) {
                onCarClickListener.onClick(item, holder.image)
            }
        }
        holder.title.text = item?.name
        val imgUrl = item?.img
        Picasso.get().load(imgUrl).into(holder.image)
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
