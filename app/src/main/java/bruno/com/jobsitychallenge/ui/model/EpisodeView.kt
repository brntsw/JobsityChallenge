package bruno.com.jobsitychallenge.ui.model

data class EpisodeView(
    val id: Long,
    val name: String,
    val season: Int,
    val summary: String?,
    val imageUrl: String?
)