package xyz.nfcv.pupil.asmd.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_problem.view.*
import xyz.nfcv.pupil.asmd.R
import xyz.nfcv.pupil.asmd.`fun`.ASMD

class ProblemListAdapter(var context: Context, recyclerView: RecyclerView): RecyclerView.Adapter<ProblemListAdapter.ViewHolder>() {
    var problems = ArrayList<ASMD.Problem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.adapter = this
        recyclerView.layoutManager = manager
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_problem, parent, false))
    }

    override fun getItemCount(): Int {
        return problems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.arg0.text = problems[position].arg0.toString()
        holder.arg1.text = problems[position].arg1.toString()
        holder.operator.text = problems[position].operator.toString()
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val arg0 = v.problem_arg0
        val arg1 = v.problem_arg1
        val operator = v.problem_operator
        val answer = v.problem_answer
    }
}