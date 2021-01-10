package bruno.com.jobsitychallenge.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import bruno.com.jobsitychallenge.R

class SerieDetailsFragment : Fragment() {

    var serieId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_serie_details, container, false)

        val args: SerieDetailsFragmentArgs by navArgs()
        serieId = args.serieId
        Log.d("Serie ID", "Serie Id is $serieId")

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = SerieDetailsFragment()
    }
}