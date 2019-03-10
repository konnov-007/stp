package apps.stp.converter.main

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import apps.stp.converter.R
import apps.stp.converter.data.HistoryItem
import apps.stp.converter.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class ConvertNumberActivity : AppCompatActivity(), ConvertNumberContract.View {
    private lateinit var mPresenter: ConvertNumberContract.Presenter
    private lateinit var mConverterHistoryAdapter: ConverterHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = ConvertNumberPresenter()
        initUI()
    }

    override fun initUI() {
        toolbar.title = "Конвертер"
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(toolbar)

        mConverterHistoryAdapter = ConverterHistoryAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this@ConvertNumberActivity)
        recyclerView.adapter = mConverterHistoryAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val numbersList = resources.getStringArray(R.array.number_system_list).toCollection(ArrayList())
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, numbersList)
        adapter.setDropDownViewResource(R.layout.spinner_textview)
        spinnerInputNumber.adapter = adapter
        spinnerResultNumber.adapter = adapter

        buttonConvert.setOnClickListener {
            mPresenter.convertNumber(editTextInputNumber.text.toString(),
                    spinnerInputNumber.selectedItem.toString(),
                    spinnerResultNumber.selectedItem.toString())
        }
    }

    override fun getConvertedNumber(result: String) = editTextResultNumber.setText(result)

    override fun openAboutDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewAdapterItem(item: HistoryItem) = mConverterHistoryAdapter.addItem(item)

    override fun showErrorMessage() = toast("Ошибка! Введите корректные данные!")

    override fun onResume() {
        super.onResume()
        mPresenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.dropView()
    }
}
