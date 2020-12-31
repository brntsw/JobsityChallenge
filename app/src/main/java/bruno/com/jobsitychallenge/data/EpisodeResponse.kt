package bruno.com.jobsitychallenge.data

data class EpisodeResponse(
    val id: Long,
    val url: String,
    val name: String,
    val number: Int,
    val season: Int,
    val summary : String,
    val image: ImageSerie
)
