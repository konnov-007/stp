package konnov.commr.vk.wolframcalc.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import konnov.commr.vk.wolframcalc.R
import konnov.commr.vk.wolframcalc.data.ResultPod
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel : ViewModel

    private lateinit var resultPodAdapter: ResultPodAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(linearLayoutManager)

        mViewModel = createViewModel()
        mViewModel.mLiveData.observe(this, Observer <ViewState> { response -> updateViewState(response) })

        calculate_btn.setOnClickListener {
            mViewModel.loadData(input_et.text.toString())
        }
    }


    private fun updateViewState(state: ViewState) {
        when (state) {
            is ViewStateSuccess -> showResult(state.result!!)
            is ViewStateEmpty -> showError(state.message)
        }
    }

    private fun createViewModel() = ViewModelProviders.of(this).get(ViewModel::class.java)

    private fun showError(message : String?){
        Snackbar.make(findViewById(android.R.id.content), message!!, Snackbar.LENGTH_LONG).show()
    }

    private fun showResult(resultPods: ArrayList<ResultPod>) {
        resultPodAdapter = ResultPodAdapter(resultPods)
        recyclerView.adapter = resultPodAdapter
    }

}
