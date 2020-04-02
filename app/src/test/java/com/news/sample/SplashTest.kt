package com.news.sample

import com.news.sample.ui.viewmodel.SplashViewModel
import com.news.sample.util.rx.AppSchedulerProvider
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class SplashTest {

    lateinit var viewModel: SplashViewModel
    lateinit var testScheduler: TestScheduler
    lateinit var testObservable: TestObserver<Long>

    @Before
    fun setUp() {
        viewModel = SplashViewModel(AppSchedulerProvider())
        RxJavaPlugins.reset()

        testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        testObservable = viewModel.overtimeWait().test()
    }

    @Test
    fun testFromSplashToMainOpenFailed() {

        testScheduler.advanceTimeBy(1200L, TimeUnit.MILLISECONDS)
        testObservable.assertComplete()
    }

    @Test
    fun testFromSplashToMainOpenSuccess() {

        testScheduler.advanceTimeBy(1400L, TimeUnit.MILLISECONDS)
        testObservable.assertComplete()
    }

    @After
    fun tearDown() = RxJavaPlugins.reset()
}