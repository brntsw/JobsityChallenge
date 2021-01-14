package bruno.com.jobsitychallenge.ui.episodes

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bruno.com.jobsitychallenge.MainApplication
import bruno.com.jobsitychallenge.R
import bruno.com.jobsitychallenge.ui.adapter.EpisodesAdapter
import com.facebook.shimmer.ShimmerFrameLayout

class EpisodesDialogFragment : DialogFragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerEpisodes: RecyclerView
    private lateinit var shimmer: ShimmerFrameLayout

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.dialog_fragment_episodes, null, false)

        val args: EpisodesDialogFragmentArgs by navArgs()
        val episodes = args.episodes

        toolbar = view.findViewById(R.id.toolbar)
        recyclerEpisodes = view.findViewById(R.id.recycler_episodes)
        shimmer = view.findViewById(R.id.shimmer_view)

        toolbar.title = "Episodes"
        toolbar.setNavigationIcon(R.drawable.clear)
        toolbar.setNavigationOnClickListener { dismiss() }

        recyclerEpisodes.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerEpisodes.adapter = EpisodesAdapter(episodes.toList())

        return initiateAlertDialog(view)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null && dialog.window != null) {
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        }
    }

    private fun initiateAlertDialog(view: View): AlertDialog {
        val builder = AlertDialog.Builder(activity, R.style.Theme_AppCompat_Dialog_Alert)
        builder.setView(view)

        val alert = builder.create()

        alert.setOnShowListener { alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(MainApplication.appContext, android.R.color.black)) }

        return alert
    }

}