package konnov.commr.vk.wolframcalc.historyscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import konnov.commr.vk.wolframcalc.R
import konnov.commr.vk.wolframcalc.data.ResultPod
import konnov.commr.vk.wolframcalc.mainscreen.ResultPodAdapter
import konnov.commr.vk.wolframcalc.util.ViewState
import konnov.commr.vk.wolframcalc.util.ViewStateSuccess
import konnov.commr.vk.wolframcalc.util.obtainViewModel
import kotlinx.android.synthetic.main.activity_history.*
import java.util.ArrayList

class HistoryActivity : AppCompatActivity() {

    private lateinit var mHistoryViewModel: HistoryViewModel

    private lateinit var resultPodAdapter: ResultPodAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(history_toolbar)

        history_rv.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        history_rv.layoutManager = linearLayoutManager

        mHistoryViewModel = obtainViewModel()
        mHistoryViewModel.mLiveData.observe(this, Observer <ViewState> { response -> updateViewState(response) })
    }

    override fun onResume() {
        super.onResume()
        mHistoryViewModel.getHistory()
    }


    private fun updateViewState(state: ViewState) {
        when (state) {
            is ViewStateSuccess -> showHistory(state.result!!)
        }
    }

    private fun showHistory(resultPods: ArrayList<ResultPod>) {
        resultPodAdapter = ResultPodAdapter(resultPods)
        history_rv.adapter = resultPodAdapter
    }

    private fun obtainViewModel(): HistoryViewModel = obtainViewModel(HistoryViewModel::class.java)

}
