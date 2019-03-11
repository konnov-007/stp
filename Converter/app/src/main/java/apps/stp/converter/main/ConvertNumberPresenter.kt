package apps.stp.converter.main

import apps.stp.converter.data.HistoryItem

class ConvertNumberPresenter : ConvertNumberContract.Presenter {

    private var mView: ConvertNumberContract.View? = null
    private val mConvertNumberModel = ConvertNumberModel()

    override fun takeView(view: ConvertNumberContract.View) {
        mView = view
    }

    override fun dropView() {
        mView = null
    }

    override fun convertNumber(inputNumber: String, inputNumberSystem: String, resultNumberSystem: String) {
        if (inputNumber.length <= 20) {
            try {
                val resultNumber = mConvertNumberModel.convertNumber(inputNumber, inputNumberSystem, resultNumberSystem)
                mView?.getConvertedNumber(resultNumber)
                mView?.addNewAdapterItem(HistoryItem(inputNumber, resultNumber, inputNumberSystem, resultNumberSystem))
            } catch (exception: Exception) {
                mView?.showErrorMessage()
            }
        } else mView?.showErrorMessage()
    }
}