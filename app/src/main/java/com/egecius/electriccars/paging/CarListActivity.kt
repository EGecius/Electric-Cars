package com.egecius.electriccars.paging

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egecius.electriccars.R
import com.egecius.electriccars.paging.di.DaggerPagedListActivityComponent
import com.egecius.electriccars.paging.di.PagedListActivityModule
import com.egecius.electriccars.room.Car
import javax.inject.Inject

class CarListActivity : AppCompatActivity(), PagedListActivityPresenter.View {

    private lateinit var adapter: MyPagedListAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var presenter: PagedListActivityPresenter

    override fun showCars(cars: PagedList<Car>) {
        adapter.submitList(cars)
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        injectDependencies()

        presenter.startPresenting(this, this)
    }

    private fun setupUi() {
        progressBar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.recycler_view)
        adapter = MyPagedListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun injectDependencies() {
        DaggerPagedListActivityComponent.builder()
            .pagedListActivityModule(PagedListActivityModule((this)))
            .build().injectInto(this)
    }
}