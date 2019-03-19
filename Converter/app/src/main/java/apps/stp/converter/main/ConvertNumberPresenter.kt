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

    override fun convertNumber(inputNumber: String, inputNumberSystem: String, resultNumberSystem: String, accuracy : String) {
        if (mConvertNumberModel.numberIsCorrect(inputNumber)) {
            try {
                val resultNumber : String;
                if(accuracy.isEmpty()) {
                    resultNumber =
                        mConvertNumberModel.convertNumber(inputNumber, inputNumberSystem, resultNumberSystem)
                } else {
                    resultNumber =
                        mConvertNumberModel.convertNumber(inputNumber, inputNumberSystem, resultNumberSystem, accuracy)
                }
                    mView?.getConvertedNumber(resultNumber)
                mView?.addNewAdapterItem(HistoryItem(inputNumber, resultNumber, inputNumberSystem, resultNumberSystem))
            } catch (exception: Exception) {
                mView?.showErrorMessage()
            }
        } else mView?.showErrorMessage()
    }
}