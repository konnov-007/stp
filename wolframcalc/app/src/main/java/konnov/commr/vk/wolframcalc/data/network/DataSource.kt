package konnov.commr.vk.wolframcalc.data.network

import konnov.commr.vk.wolframcalc.data.ResultPod

interface DataSource {

    fun onPodsLoaded(pods: ArrayList<ResultPod>)

    fun onDataNotAvailable()

}