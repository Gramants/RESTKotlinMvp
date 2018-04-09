package ste.demo.mvp.kotlin.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_procedure_detail.*
import ste.demo.mvp.kotlin.R
import ste.demo.mvp.kotlin.model.Phases
import ste.demo.mvp.kotlin.model.ProcedureDetails
import ste.demo.mvp.kotlin.presenter.ProcedureDetailsContract
import ste.demo.mvp.kotlin.presenter.ProcedureDetailsPresenter
import java.util.*

class RepositoryDetailActivity : BaseMvpActivity<ProcedureDetailsContract.View,
        ProcedureDetailsContract.Presenter>(),
        ProcedureDetailsContract.View {


    private var mAdapter: PhasesAdapter? = null


    override fun showItemDetail(procedureDetail: List<ProcedureDetails>) {

        progressBar.visibility = View.GONE
        with(procedureDetail.get(0)) {
            toolbar_detail.title = name

            Glide.with(applicationContext)
                    .load(icon)
                    .into(mainimage)

            mAdapter?.addProcedures(phases)
            mAdapter?.notifyDataSetChanged()

        }


    }

    override var mPresenter: ProcedureDetailsContract.Presenter = ProcedureDetailsPresenter()

    companion object {
        private const val NAME = "id"
        fun newIntent(context: Context, name: String): Intent =
                Intent(context, RepositoryDetailActivity::class.java).apply {
                    putExtra(NAME, name)
                }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procedure_detail)

        progressBar.visibility = View.VISIBLE

        mPresenter.getItemById(intent.getStringExtra(NAME))
        setUpRecyclerView()

        btnReload.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            btnReload.visibility = View.GONE
            mPresenter.getItemById(intent.getStringExtra(NAME))
        }

    }

    override fun showReloadButton() {
        progressBar.visibility = View.GONE
        btnReload.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView() {
        mAdapter = PhasesAdapter(ArrayList<Phases>(), {
            Toast.makeText(applicationContext, "Not implemented!",
                    Toast.LENGTH_SHORT).show()
        })


        recyclerViewPhases.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewPhases.adapter = mAdapter


    }

}