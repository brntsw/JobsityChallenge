package bruno.com.jobsitychallenge.ui.list

import bruno.com.jobsitychallenge.ui.model.SerieView

interface SerieItemClickListener {
    fun onSerieClicked(serie: SerieView)
}