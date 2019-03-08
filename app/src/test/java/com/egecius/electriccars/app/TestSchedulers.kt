package com.egecius.electriccars.app

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulers : com.egecius.electriccars.app.Schedulers {
    override fun getExecutionScheduler(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun getPostExecutionScheduler(): Scheduler {
        return Schedulers.trampoline()
    }
}
