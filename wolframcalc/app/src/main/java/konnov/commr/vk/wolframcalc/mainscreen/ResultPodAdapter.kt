package konnov.commr.vk.wolframcalc.mainscreen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import konnov.commr.vk.wolframcalc.R
import konnov.commr.vk.wolframcalc.data.ResultPod
import konnov.commr.vk.wolframcalc.util.getScreenHeight

class ResultPodAdapter(private val resultPods: List<ResultPod>) :
    RecyclerView.Adapter<ResultPodAdapter.ResultPodViewHolder>() {
    private var context: Context? = null
    private var lastAnimatedPosition = -1

    override fun getItemCount(): Int {
       return resultPods.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ResultPodViewHolder {

        context = parent.context

        // Set card layout
        val view = LayoutInflater.from(context).inflate(R.layout.result_pod_card_layout, parent, false)
        return ResultPodViewHolder(view)
    }

    override fun onBindViewHolder(resultPodViewHolder: ResultPodViewHolder, position: Int) {

        resultPodViewHolder.textViewResultPodTitle.setText(resultPods[position].title)
        resultPodViewHolder.textViewResultPodDescription.setText(resultPods[position].description)

        // Set Animation

        setAnimation(resultPodViewHolder.cardView, position)
    }


    // Here is the key method to apply the animation

    private fun setAnimation(viewToAnimate: View, position: Int) {

        viewToAnimate.clearAnimation()

        // If the bound view wasn't previously displayed on screen, it's animated

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position
            viewToAnimate.translationY = getScreenHeight(context).toFloat()
            viewToAnimate.animate()
                .translationY(0f)
                .setInterpolator(DecelerateInterpolator(3f))
                .setDuration(700)
                .start()
        } else
            return
    }

    class ResultPodViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var cardView: CardView
        internal var textViewResultPodTitle: TextView
        internal var textViewResultPodDescription: TextView

        init {
            cardView = itemView.findViewById(R.id.result_pod_card_view) as CardView
            textViewResultPodTitle = itemView.findViewById(R.id.text_result_pod_title) as TextView
            textViewResultPodDescription = itemView.findViewById(R.id.text_result_pod_description) as TextView
        }
    }
}
