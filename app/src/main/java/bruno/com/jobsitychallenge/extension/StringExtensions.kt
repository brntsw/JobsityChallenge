package bruno.com.jobsitychallenge.extension

import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned

fun String?.toHtml() : Spanned {
    this?.let {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        else
            Html.fromHtml(this)
    } ?: run {
        return SpannableString("")
    }
}