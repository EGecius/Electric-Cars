package com.egecius.electriccars.app

import io.reactivex.Scheduler

interface Schedulers {
    fun getExecutionScheduler(): Scheduler
    fun getPostExecutionScheduler(): Scheduler
}
