package xyz.nfcv.pupil.asmd.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.nfcv.pupil.asmd.`fun`.ASMD

class ProblemListAdapter(var context: Context, private val recyclerView: RecyclerView): RecyclerView.Adapter<ProblemListAdapter.ViewHolder>() {
    val problems = ArrayList<ASMD.Problem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemListAdapter.ViewHolder {

    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: ProblemListAdapter.ViewHolder, position: Int) {
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
        override fun onClick(v: View?) {

        }

    }
}