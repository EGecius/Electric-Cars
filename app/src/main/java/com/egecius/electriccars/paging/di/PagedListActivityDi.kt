package com.egecius.electriccars.paging.di

import androidx.lifecycle.ViewModelProviders
import com.egecius.electriccars.paging.PagedListActivity
import com.egecius.electriccars.paging.PagedListActivityPresenter
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [PagedListActivityModule::class])
interface PagedListActivityComponent {

    fun injectInto(pagedListActivity: PagedListActivity)
}

@Module
class PagedListActivityModule(private val activity: PagedListActivity) {

    @Provides
    fun providesPresenter(): PagedListActivityPresenter {
        return ViewModelProviders.of(activity).get(PagedListActivityPresenter::class.java)
    }
}
