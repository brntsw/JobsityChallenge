package bruno.com.jobsitychallenge.ui.adapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import bruno.com.jobsitychallenge.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageBindingAdapter {
    @BindingAdapter("imageBitmap")
    @JvmStatic
    fun loadImage(imgView: ImageView, urlPath: String) {
        val imgUri = urlPath.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply {
                RequestOptions()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.broken_image)
            }.into(imgView)
    }
}