package com.news.sample

import com.news.sample.ui.viewmodel.MainViewModel
import com.news.sample.util.rx.AppSchedulerProvider
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class RxTest {

    private val viewModel = MainViewModel(AppSchedulerProvider())
    lateinit var testScheduler: TestScheduler
    lateinit var testObservable: TestObserver<Long>

    @Before
    fun setUp() {
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
    fun testForFailedOpen() {

        testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        testObservable = viewModel.overtimeWait().test()

        testScheduler.advanceTimeBy(2500L, TimeUnit.MILLISECONDS)
        testObservable.assertComplete()
    }

    @Test
    fun testForSuccessfulOpen() {

        testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        testObservable = viewModel.overtimeWait().test()

        testScheduler.advanceTimeBy(3500L, TimeUnit.MILLISECONDS)
        testObservable.assertComplete()
    }

    @After
    fun tearDown() = RxJavaPlugins.reset()
}