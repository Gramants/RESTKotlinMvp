package ste.demo.mvp.kotlin.presenter

import rx.functions.Action1
import ste.demo.mvp.kotlin.network.ApiManager
import ste.demo.mvp.kotlin.network.GeneralErrorHandler


class ProceduresPresenter : BaseMvpPresenterImpl<ProceduresContract.View>(), ProceduresContract.Presenter {


    override fun loadRepositories() {
        ApiManager.getAllItems()
                .doOnError { mView?.showMessage(it.toString()) }
                .subscribe(Action1 { mView?.showAllItems(it) },
                        GeneralErrorHandler(mView, true, { throwable, errorBody, isNetwork -> mView?.showError(throwable.message) }))
    }
}