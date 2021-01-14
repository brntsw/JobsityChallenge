package bruno.com.jobsitychallenge.data.mapper

import bruno.com.jobsitychallenge.data.model.EpisodeResponse
import bruno.com.jobsitychallenge.data.model.SerieDetailsResponse
import bruno.com.jobsitychallenge.ui.model.EpisodeView
import bruno.com.jobsitychallenge.ui.model.SerieDetailsView

class SerieDetailsMapper : Mapper<SerieDetailsResponse, SerieDetailsView> {

    override fun mapFromRepoToView(entity: SerieDetailsResponse): SerieDetailsView {

        return SerieDetailsView(
            entity.id,
            entity.name,
            entity.poster,
            entity.time,
            entity.daysAirs,
            entity.genres,
            entity.summary,
            getHashMapOfEpisodesBySeason(entity.episodes)
        )

    }

    private fun getHashMapOfEpisodesBySeason(episodes: List<EpisodeResponse>) : HashMap<Int, List<EpisodeView>> {
        val hashMap = HashMap<Int, List<EpisodeView>>()

        var lastSeason = 1

        episodes.forEach { episode ->
            if(episode.season > lastSeason)
                lastSeason = episode.season
        }

        for(season in 0 until lastSeason) {
            val episodesOfSeason = mutableListOf<EpisodeView>()

            episodes.forEach { episode ->
                if((season + 1) == episode.season) {
                    episodesOfSeason.add(EpisodeView(
                        episode.id,
                        episode.name,
                        episode.season,
                        episode.summary,
                        episode.image?.medium
                    ))
                }
            }

            hashMap[season] = episodesOfSeason.toList()
        }

        return hashMap
    }
}