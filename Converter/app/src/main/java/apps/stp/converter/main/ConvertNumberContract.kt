package apps.stp.converter.main

import apps.stp.converter.base.BasePresenter
import apps.stp.converter.base.BaseView
import apps.stp.converter.data.HistoryItem

interface ConvertNumberContract {
    interface View : BaseView<Presenter> {
        fun initUI()
        fun showErrorMessage()
        fun openAboutDialog()
        fun getConvertedNumber(result: String)
        fun addNewAdapterItem(item: HistoryItem)
    }

    interface Presenter : BasePresenter<View> {
        fun convertNumber(inputNumber: String, inputNumberSystem: String, resultNumberSystem: String)
    }
}