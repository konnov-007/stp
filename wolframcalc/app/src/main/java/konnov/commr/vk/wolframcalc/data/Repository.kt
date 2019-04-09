package konnov.commr.vk.wolframcalc.data

import konnov.commr.vk.wolframcalc.data.network.DataSource
import konnov.commr.vk.wolframcalc.data.network.RemoteDataSource

object Repository : DataSource {

    lateinit var source: DataSource

    fun initRepo(dataSource: DataSource) {
        source = dataSource
    }

    override fun onPodsLoaded(pods: ArrayList<ResultPod>) {
        source.onPodsLoaded(pods)
    }

    override fun onDataNotAvailable() {
        source.onDataNotAvailable()
    }

    val mRemoteDataSource = RemoteDataSource

    fun getQueryResult(query: String) {
        mRemoteDataSource.getQueryResult(query, this)
    }

}