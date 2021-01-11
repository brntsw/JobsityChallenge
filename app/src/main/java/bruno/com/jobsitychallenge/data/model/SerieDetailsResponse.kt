package bruno.com.jobsitychallenge.data.model

data class SerieDetailsResponse(
    val id: Long,
    val name: String,
    val poster: String,
    val time: String,
    val daysAirs: List<String>,
    val genres: List<String>,
    val summary: String?,
    val episodes: List<EpisodeResponse>
)