package ste.demo.mvp.kotlin.presenter

import ste.demo.mvp.kotlin.model.ProcedureDetails


object ProcedureDetailsContract {

    interface View : BaseMvpView {
        fun showItemDetail(procedureDetail: List<ProcedureDetails>)
        fun showReloadButton()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun getItemById(name: String)
    }


}