package com.egecius.electriccars.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egecius.electriccars.R
import com.egecius.electriccars.paging.di.DaggerPagedListActivityComponent
import com.egecius.electriccars.paging.di.PagedListActivityModule
import com.egecius.electriccars.room.Car
import javax.inject.Inject

class PagedListActivity : AppCompatActivity(), PagedListActivityView {

    private lateinit var adapter: MyPagedListAdapter

    @Inject
    lateinit var presenter: PagedListActivityPresenter

    override fun showCars(cars: PagedList<Car>?) {
        adapter.submitList(cars)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        injectDependencies()

        presenter.startPresenting(this, this)
    }

    private fun injectDependencies() {
        DaggerPagedListActivityComponent.builder()
            .pagedListActivityModule(PagedListActivityModule((this)))
            .build().injectInto(this)
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
