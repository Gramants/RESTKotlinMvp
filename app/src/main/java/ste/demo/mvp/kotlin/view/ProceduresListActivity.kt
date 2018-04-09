package ste.demo.mvp.kotlin.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_procedures_list.*
import ste.demo.mvp.kotlin.R
import ste.demo.mvp.kotlin.model.Procedures
import ste.demo.mvp.kotlin.presenter.ProceduresContract
import ste.demo.mvp.kotlin.presenter.ProceduresPresenter
import java.util.*


class ProceduresListActivity : BaseMvpActivity<ProceduresContract.View,
        ProceduresPresenter>(),
        ProceduresContract.View {


    private var mAdapter: ProceduresAdapter? = null
    override var mPresenter: ProceduresPresenter = ProceduresPresenter()


    override fun showAllItems(procedures: MutableList<Procedures>) {
        mAdapter?.addProcedures(procedures)
        mAdapter?.notifyDataSetChanged()
        hideProgress()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procedures_list)
        setUpRecyclerView()
        mPresenter.loadRepositories()

        toolbar.title = getString(R.string.title_activity_repositories)
        showProgress()
        fab.setOnClickListener {
            showProgress()
            mPresenter.loadRepositories()
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = ProceduresAdapter(ArrayList<Procedures>(), {
            startActivity(RepositoryDetailActivity.newIntent(this, it.id))
        })


        recyclerViewRepositories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewRepositories.adapter = mAdapter


    }

    private fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun showError(error: String?) {
        super.showError(error)
        progress_bar.visibility = View.GONE
    }
}

