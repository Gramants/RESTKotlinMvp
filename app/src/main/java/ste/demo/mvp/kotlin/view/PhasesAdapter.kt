package ste.demo.mvp.kotlin.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.*
import ste.demo.mvp.kotlin.R
import ste.demo.mvp.kotlin.model.Phases
import com.hendraanggrian.pikasso.circle
import com.hendraanggrian.pikasso.picasso



class PhasesAdapter(private val repositories: MutableList<Phases>,
                    private val onClick: (Phases) -> Unit)
    : RecyclerView.Adapter<PhasesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(repositories[position])
    }


    override fun getItemCount(): Int = repositories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false).let {
            ViewHolder(it, onClick)
        }
    }

    class ViewHolder(override val containerView: View, private val onClick: (Phases) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(phases: Phases) {
            with(phases) {
                text_view_title.text = name

                        picasso
                        .load(icon)
                        .circle()
                        .error(R.drawable.errorimage)
                        .into(procedure_icon)

                containerView.setOnClickListener { onClick(this) }
            }
        }
    }


    fun addProcedures(newPhases: List<Phases>) {
        repositories.addAll(newPhases)
    }
}