package xyz.nfcv.pupil.asmd.ui.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment

import xyz.nfcv.pupil.asmd.R

class ProblemSettingsFragment : DialogFragment() {

    override fun onStart() {
        super.onStart()
        val dm = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(dm)
        dialog!!.window!!.setLayout(dm.widthPixels, dialog!!.window!!.attributes.height)
        val params: WindowManager.LayoutParams = dialog!!.window!!.attributes
        params.gravity = Gravity.BOTTOM
        dialog!!.window!!.attributes = params
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_problem_settings, container, false)
    }
}
