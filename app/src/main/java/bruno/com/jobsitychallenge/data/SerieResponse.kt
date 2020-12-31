package bruno.com.jobsitychallenge.data

data class SerieResponse(
    val id: Long,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val schedule: ScheduleSerie,
    val genres: List<String>,
    val image: ImageSerie,
    val summary: String
)
