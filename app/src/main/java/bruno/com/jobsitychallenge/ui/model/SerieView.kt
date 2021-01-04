package bruno.com.jobsitychallenge.ui.model

import android.graphics.Bitmap
import bruno.com.jobsitychallenge.data.model.ImageSerie
import bruno.com.jobsitychallenge.data.model.ScheduleSerie

data class SerieView(
    val id: Long,
    val name: String,
    val schedule: ScheduleSerie,
    val imagePath: String
)