package konnov.commr.vk.wolframcalc.screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import konnov.commr.vk.wolframcalc.data.Repository
import konnov.commr.vk.wolframcalc.data.ResultPod
import konnov.commr.vk.wolframcalc.data.network.DataSource

class ViewModel (app: Application) : AndroidViewModel(app), DataSource {
    override fun onPodsLoaded(pods: ArrayList<ResultPod>) {
        mLiveData.postValue(ViewStateSuccess(pods))
    }

    override fun onDataNotAvailable() {
        mLiveData.value = ViewStateEmpty("Нет данных")
    }

    val mRepository = Repository

    val mLiveData = MutableLiveData<ViewState>()
    get() {
        if (field.value == null) {
            field.value = ViewStateEmpty("Ошибка")
        }
        return field
    }

    fun loadData(query : String?) {
        if(query!!.isEmpty()) {
            mLiveData.value = ViewStateEmpty("Введите текст")
        } else {
            mRepository.initRepo(this)
            mRepository.getQueryResult(query)
        }
    }

}