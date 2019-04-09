package konnov.commr.vk.wolframcalc.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import konnov.commr.vk.wolframcalc.R
import konnov.commr.vk.wolframcalc.data.Repository
import konnov.commr.vk.wolframcalc.data.ResultPod
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val repository = Repository
        val adapter = ArrayAdapter<ResultPod>(this,
            android.R.layout.simple_list_item_1, repository.cachedHistory)
        list_view.adapter = adapter
    }
}
