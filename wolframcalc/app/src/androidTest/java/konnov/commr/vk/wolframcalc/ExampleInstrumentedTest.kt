package konnov.commr.vk.wolframcalc

import androidx.test.runner.AndroidJUnit4
import konnov.commr.vk.wolframcalc.data.Repository
import konnov.commr.vk.wolframcalc.data.ResultPod
import konnov.commr.vk.wolframcalc.data.network.DataSource

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : DataSource {
    @Test
    @Override
    override fun onPodsLoaded(pods: ArrayList<ResultPod>) {
        var correct = false
        for (pod in pods) {
            if(pod.title.contains("1.38")) {
                correct = true
            }
        }
        assertTrue(correct)
    }

    override fun onDataNotAvailable() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    fun calculationIsCorrect() {
        val repository = Repository
        repository.initRepo(this)
        val query = "log(4)"
        repository.getQueryResult(query)

    }
}