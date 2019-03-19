package apps.stp.converter.base

interface BasePresenter<T> {
    fun takeView(view: T)
    fun dropView()
}