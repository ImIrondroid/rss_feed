package com.news.sample.util.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun computation(): Scheduler
    fun mainThread(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
}