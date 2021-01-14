package bruno.com.jobsitychallenge.ui.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object FormatTextAdapter {

    @BindingAdapter("format", "arg")
    @JvmStatic
    fun setFormattedTextInt(textView: TextView, format: String, arg: Int) {
        if(arg <= 0) return
        textView.text = String.format(format, arg)
    }

}