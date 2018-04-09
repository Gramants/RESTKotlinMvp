package ste.demo.mvp.kotlin.presenter

import ste.demo.mvp.kotlin.model.Procedures


object ProceduresContract {

    interface View : BaseMvpView {
        fun showAllItems(procedures: MutableList<Procedures>)

    }

    interface Presenter : BaseMvpPresenter<View> {
        fun loadRepositories()

    }


}
