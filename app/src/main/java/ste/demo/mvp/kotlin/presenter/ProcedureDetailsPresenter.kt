package ste.demo.mvp.kotlin.presenter

import rx.functions.Action1
import ste.demo.mvp.kotlin.network.ApiManager
import ste.demo.mvp.kotlin.network.GeneralErrorHandler


class ProcedureDetailsPresenter : BaseMvpPresenterImpl<ProcedureDetailsContract.View>(),
        ProcedureDetailsContract.Presenter {

    override fun getItemById(name: String) {
        ApiManager.getItemById(name)
                .doOnError { mView?.showMessage(it.toString()) }
                .subscribe(Action1 { mView?.showItemDetail(it) },
                        GeneralErrorHandler(mView, true,
                                { throwable, errorBody, isNetwork -> mView?.showReloadButton() }))
    }


}