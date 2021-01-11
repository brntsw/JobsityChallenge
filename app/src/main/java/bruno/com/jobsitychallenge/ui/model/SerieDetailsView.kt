package bruno.com.jobsitychallenge.ui.model

data class SerieDetailsView(
    val id: Long,
    val name: String,
    val poster: String,
    val time: String,
    val daysAirs: List<String>,
    val genres: List<String>,
    val summary: String?,
    val episodesBySeason: HashMap<Int, List<EpisodeView>>
)