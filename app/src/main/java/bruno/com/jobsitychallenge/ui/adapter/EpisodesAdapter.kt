package bruno.com.jobsitychallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import bruno.com.jobsitychallenge.R
import bruno.com.jobsitychallenge.databinding.ItemEpisodeBinding
import bruno.com.jobsitychallenge.ui.model.EpisodeView

class EpisodesAdapter(val episodes: List<EpisodeView>) : RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemEpisodeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_episode, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(episodes[position])

    override fun getItemCount(): Int = episodes.size

    class ViewHolder(val binding: ItemEpisodeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: EpisodeView) {
            binding.root.setOnClickListener { v->
                //TODO open the EpisodeDetailsFragment
            }
            binding.episode = episode
            binding.executePendingBindings()
        }
    }

}