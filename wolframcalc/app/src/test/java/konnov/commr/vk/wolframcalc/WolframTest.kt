package konnov.commr.vk.wolframcalc

import konnov.commr.vk.wolframcalc.data.ResultPod
import konnov.commr.vk.wolframcalc.data.source.Repository
import konnov.commr.vk.wolframcalc.data.source.WolframDataSource
import konnov.commr.vk.wolframcalc.data.source.local.LocalDataSource
import konnov.commr.vk.wolframcalc.data.source.remote.RemoteWolframDataSource
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WolframTest {

    @Mock
    private lateinit var localDataSource: LocalDataSource
    private lateinit var repository: Repository

    @Before
    fun setUpRepo() {
        MockitoAnnotations.initMocks(this)
        repository = Repository(RemoteWolframDataSource, localDataSource)
    }

    @Test
    fun checkCalculationIsCorrect() {
        val query = "2+2"
        val expectedResult = "4"
        var answerIsCorrect = false

        repository.getQueryResult(query, object : WolframDataSource.GetResultCallback {
            override fun onPodsLoaded(pods: List<ResultPod>) {
                for (result in pods) {
                    if (result.description.contains(expectedResult)) {
                        println("result: " + result.description)
                        answerIsCorrect = true
                        break
                    }
                }
            }

            override fun onDataNotAvailable() {
                print("onDataNotAvailable")
            }
        })
        Thread.sleep(5000) //waiting until it calculates
        assertTrue(answerIsCorrect)
    }
}