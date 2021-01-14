package bruno.com.jobsitychallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import bruno.com.jobsitychallenge.R
import bruno.com.jobsitychallenge.databinding.ItemSeasonBinding
import bruno.com.jobsitychallenge.ui.details.SerieDetailsFragmentDirections
import bruno.com.jobsitychallenge.ui.model.EpisodeView

class SeasonsAdapter(private val episodesBySeason: HashMap<Int, List<EpisodeView>>) : RecyclerView.Adapter<SeasonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSeasonBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_season, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        for((key, value) in episodesBySeason) {
            if(key == position)
                holder.bind(position + 1, value)
        }
    }

    override fun getItemCount(): Int = episodesBySeason.size

    class ViewHolder(val binding: ItemSeasonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(season: Int, episodes: List<EpisodeView>) {
            binding.root.setOnClickListener { v->
                v.findNavController().navigate(SerieDetailsFragmentDirections.actionSerieDetailsFragmentToEpisodesDialogFragment(episodes.toTypedArray()))
            }
            binding.seasonNumber = season
            binding.executePendingBindings()
        }
    }

}