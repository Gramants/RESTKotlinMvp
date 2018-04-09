package ste.demo.mvp.kotlin.presenter


interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()
}