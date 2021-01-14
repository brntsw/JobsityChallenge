package bruno.com.jobsitychallenge.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EpisodeView(
    val id: Long,
    val name: String,
    val season: Int,
    val summary: String?,
    val imageUrl: String?
) : Parcelable