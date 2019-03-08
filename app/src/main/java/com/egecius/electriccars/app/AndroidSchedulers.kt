package com.egecius.electriccars.app

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidSchedulers : com.egecius.electriccars.app.Schedulers {

    override fun getExecutionScheduler(): Scheduler {
        return Schedulers.io()
    }

    override fun getPostExecutionScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
