package xyz.nfcv.pupil.asmd.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home_nav.*
import xyz.nfcv.pupil.asmd.R

class HomeNavFragment : Fragment(), RadioGroup.OnCheckedChangeListener {
    var onPageSelectedListener: OnPageSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPageSelectedListener) {
            onPageSelectedListener = context
        } else {
            throw IllegalArgumentException("$context should implements OnPageSelectedListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_nav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nav_group.setOnCheckedChangeListener(this)
    }

    enum class Pages {
        TEST, ANALYSIS
    }

    interface OnPageSelectedListener {
        fun onPageChanged(page: Pages)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when(checkedId) {
            R.id.sheet_test -> onPageSelectedListener?.onPageChanged(Pages.TEST)
            R.id.sheet_analysis -> onPageSelectedListener?.onPageChanged(Pages.ANALYSIS)
        }
    }
}