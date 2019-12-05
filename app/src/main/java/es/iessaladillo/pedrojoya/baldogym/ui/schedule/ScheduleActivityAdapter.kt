package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.schedule_activity_item.*

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class ScheduleActivityAdapter : RecyclerView.Adapter<ScheduleActivityAdapter.ViewHolder>() {

    private var data: List<TrainingSession> = emptyList()
    private var onItemClickListener: OnItemClickListener? = null

    init {
        setHasStableIds(true)
    }

    class ViewHolder(override val containerView: View, onItemClickListener: OnItemClickListener?) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
        }

        fun bind(trainingSession: TrainingSession) {
            lblTime.text = trainingSession.time
            imgPhoto.setImageResource(trainingSession.photoResId)
            lblName.text = trainingSession.name
            lblTrainer.text = trainingSession.trainer
            lblRoom.text = trainingSession.room
            lblParticipants.text = containerView.context.resources.getQuantityString(
                R.plurals.schedule_item_participants,
                trainingSession.participants,
                trainingSession.participants
            )
            btnJoin.setOnClickListener {
                trainingSession.userJoined = !trainingSession.userJoined
                println(trainingSession.userJoined)
                changeButton(trainingSession)
            }
            changeButton(trainingSession)
        }

        private fun changeButton(trainingSession: TrainingSession) {
            if (trainingSession.userJoined) {
                btnJoin.text =
                    containerView.context.resources.getString(R.string.schedule_item_quit)
            } else {
                btnJoin.text =
                    containerView.context.resources.getString(R.string.schedule_item_join)
            }
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.schedule_activity_item, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long {
        return data[position].id
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun submitList(trainingSessions: List<TrainingSession>) {
        data = trainingSessions
        notifyDataSetChanged()
    }

}