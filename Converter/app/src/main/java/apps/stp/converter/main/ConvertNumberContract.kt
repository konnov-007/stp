package apps.stp.converter.main

import apps.stp.converter.base.BasePresenter
import apps.stp.converter.base.BaseView

interface ConvertNumberContract {
    interface View : BaseView<Presenter> {
        fun initUI()
        fun showErrorMessage()
        fun openAboutDialog()
        fun getConvertedNumber(result: String)
    }

    interface Presenter : BasePresenter<View> {
        fun convertNumber(number: String, initialNumberSystem: Int, resultNumberSystem: Int)
    }
}