package apps.stp.converter.main

class ConvertNumberPresenter : ConvertNumberContract.Presenter {

    private var mView: ConvertNumberContract.View? = null

    override fun takeView(view: ConvertNumberContract.View) {
        mView = view
    }

    override fun dropView() {
        mView = null
    }

    override fun convertNumber(number: String, initialNumberSystem: Int, resultNumberSystem: Int) {
        try {
            mView?.getConvertedNumber(Integer.toString(Integer.parseInt(number, initialNumberSystem), resultNumberSystem))
        } catch (exception: Exception) {
            mView?.showErrorMessage()
        }
    }
}