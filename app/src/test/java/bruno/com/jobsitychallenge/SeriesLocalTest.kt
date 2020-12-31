package bruno.com.jobsitychallenge

import android.util.Log
import bruno.com.jobsitychallenge.data.EpisodeResponse
import bruno.com.jobsitychallenge.data.IRequest
import bruno.com.jobsitychallenge.data.SerieResponse
import bruno.com.jobsitychallenge.data.SerieSearchResponse
import bruno.com.jobsitychallenge.local.SeriesLocalImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertThrows

@ExperimentalCoroutinesApi
class SeriesLocalTest {

    var seriesLocal: SeriesLocalImpl? = null

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        seriesLocal = SeriesLocalImpl()
    }

    @Test
    fun `Test if the return from the local JSON Series is bringing the correct data`() = runBlockingTest {

        var resultSuccess = false

        seriesLocal?.getSeries(object : IRequest<List<SerieResponse>> {
            override fun onSuccess(result: List<SerieResponse>) {
                Assert.assertEquals(250, result[0].id)
                Assert.assertEquals(251, result[1].id)
                resultSuccess = true
            }

            override fun onError(message: String) {
                Log.e("Error", message)
                resultSuccess = false
            }
        })

        Assert.assertEquals(true, resultSuccess)
    }

    @Test
    fun `Test if the return from the local JSON Details is bringing the correct data`() = runBlockingTest {
        var resultSuccess = false

        seriesLocal?.getSerieDetails(250, object : IRequest<SerieResponse>{
            override fun onSuccess(result: SerieResponse) {
                Assert.assertEquals(250, result.id)
                resultSuccess = true
            }

            override fun onError(message: String) {
                Log.e("Error", message)
                resultSuccess = false
            }

        })

        Assert.assertEquals(true, resultSuccess)
    }

    @Test
    fun `Test if the return from the local JSON Serie Search brings the correct data`() = runBlockingTest {
        var resultSuccess = false

        seriesLocal?.searchSerieByName("supernatural", object : IRequest<List<SerieSearchResponse>>{
            override fun onSuccess(result: List<SerieSearchResponse>) {
                Assert.assertEquals(19, result[0].show.id)
                Assert.assertEquals(13457, result[1].show.id)

                resultSuccess = true
            }

            override fun onError(message: String) {
                Log.e("Error", message)
                resultSuccess = false
            }
        })

        Assert.assertEquals(true, resultSuccess)
    }

    @Test
    fun `Test if the return from the local JSON Episodes from a show is bringing the correct data`() = runBlockingTest {
        var resultSuccess = false

        seriesLocal?.getEpisodes(250, object : IRequest<List<EpisodeResponse>>{
            override fun onSuccess(result: List<EpisodeResponse>) {
                Assert.assertEquals(20849, result[0].id)
                Assert.assertEquals(20850, result[1].id)
            }

            override fun onError(message: String) {
                Log.e("Error", message)
                resultSuccess = false
            }
        })

        Assert.assertEquals(true, resultSuccess)
    }

}