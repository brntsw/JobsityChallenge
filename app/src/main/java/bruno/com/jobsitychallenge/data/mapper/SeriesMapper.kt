package bruno.com.jobsitychallenge.data.mapper

import android.graphics.Bitmap
import bruno.com.jobsitychallenge.data.model.SerieResponse
import bruno.com.jobsitychallenge.ui.model.SerieView

class SeriesMapper : Mapper<SerieResponse, SerieView> {
    override fun mapFromRepoToView(entity: SerieResponse): SerieView {
        return SerieView(
            entity.id,
            entity.name,
            entity.schedule,
            entity.image.medium
        )
    }
}