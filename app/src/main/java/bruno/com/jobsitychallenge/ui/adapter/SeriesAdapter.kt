package bruno.com.jobsitychallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import bruno.com.jobsitychallenge.R
import bruno.com.jobsitychallenge.databinding.ItemSerieBinding
import bruno.com.jobsitychallenge.ui.list.SerieItemClickListener
import bruno.com.jobsitychallenge.ui.list.SeriesListFragmentDirections
import bruno.com.jobsitychallenge.ui.model.SerieView

class SeriesAdapter(private val series: List<SerieView>) : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSerieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_serie, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(series[position])

    override fun getItemViewType(position: Int): Int = position

    override fun getItemCount(): Int = series.size

    class ViewHolder(val binding: ItemSerieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(serieView: SerieView) {
            binding.root.setOnClickListener { v ->
                v.findNavController().navigate(SeriesListFragmentDirections.actionSeriesListFragmentToSerieDetailsFragment(serieView.id))
            }
            binding.serie = serieView
            binding.executePendingBindings()
        }
    }

}