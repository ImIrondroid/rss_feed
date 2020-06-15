package com.news.sample

class MainTest {

    /*lateinit var viewModel: MainViewModel

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
    fun tearDown() = RxJavaPlugins.reset()*/
}