package bruno.com.jobsitychallenge.extension

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun Activity?.setToolbar(toolbar: Toolbar) {
    this?.let {
        if(this is AppCompatActivity) {
            this.setSupportActionBar(toolbar)
        }
    }
}