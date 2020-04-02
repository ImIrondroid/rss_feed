package com.news.sample

import com.news.sample.ui.viewmodel.MainViewModel
import com.news.sample.util.rx.AppSchedulerProvider
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class MainTest {

    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(AppSchedulerProvider())
        RxJavaPlugins.reset()
    }

    @Test
    fun testForConnectingWithGoogle() {

        viewModel.getLinks().test()
            .assertResult()
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun testForMainOpenFailed() {

        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        val testObservable = viewModel.overtimeWait().test()

        testScheduler.advanceTimeBy(2900L, TimeUnit.MILLISECONDS)
        testObservable.assertComplete()
    }

    @Test
    fun testForMainOpenSuccess() {

        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        val testObservable = viewModel.overtimeWait().test()

        testScheduler.advanceTimeBy(3100L, TimeUnit.MILLISECONDS)
        testObservable.assertComplete()
    }

    @After
    fun tearDown() = RxJavaPlugins.reset()
}