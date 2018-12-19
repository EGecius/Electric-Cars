package com.egecius.electriccars.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egecius.electriccars.R
import com.egecius.electriccars.room.Car

class PagedListActivity : AppCompatActivity(), PagedListActivityView {

    private lateinit var presenter: PagedListActivityPresenter
    private lateinit var adapter: MyPagedListAdapter

    override fun showCars(cars: PagedList<Car>?) {
        adapter.submitList(cars)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()

        presenter = ViewModelProviders.of(this).get(PagedListActivityPresenter::class.java)
        presenter.startPresenting(this, this)
    }

    private fun setupUi() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        adapter = MyPagedListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

interface PagedListActivityView {

    fun showCars(cars: PagedList<Car>?)

}
