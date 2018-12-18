package com.egecius.electriccars.paging

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egecius.electriccars.R
import com.egecius.electriccars.retrofit.RetrofitAdapter
import com.egecius.electriccars.room.Car

class PagedLIstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUi()
    }

    private fun setupUi() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = MyPagedListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        getCarsLiveData().observe(this, Observer { pagedList ->
            Log.i("Eg:PagedLIstActivity:30", "setupUi pagedList $pagedList")
            adapter.submitList(pagedList)
        })
    }

    private fun getCarsLiveData(): LiveData<PagedList<Car>>  {

        val carsRetrofitService = RetrofitAdapter().setupRetrofit()
        val myDataSourceFactory = MyDataSourceFactory(carsRetrofitService)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()

        return LivePagedListBuilder(myDataSourceFactory, config).build()
    }

}
