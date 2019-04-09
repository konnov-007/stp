package konnov.commr.vk.wolframcalc.screen

import konnov.commr.vk.wolframcalc.data.ResultPod


sealed class ViewState

class ViewStateEmpty(val message : String?) : ViewState()

class ViewStateSuccess(val result : ArrayList<ResultPod>?) : ViewState()