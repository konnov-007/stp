package apps.stp.converter.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
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
        toolbar.title = getString(R.string.app_name)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(toolbar)

        mConverterHistoryAdapter = ConverterHistoryAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
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

    override fun addNewAdapterItem(item: HistoryItem) = mConverterHistoryAdapter.addItem(item)

    override fun showAlertDialog(title: String, message: String) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(getString(R.string.dialog_button)) { _, _ -> }
            show()
        }
    }

    override fun showErrorMessage() = toast(getString(R.string.error_message))

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionAboutApp -> showAlertDialog(getString(R.string.dialog_about_app_title),
                    getString(R.string.dialog_about_app_message))
            R.id.actionAboutDevelopers -> showAlertDialog(getString(R.string.dialog_about_developers_title),
                    getString(R.string.dialog_about_developers_message))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.dropView()
    }
}
