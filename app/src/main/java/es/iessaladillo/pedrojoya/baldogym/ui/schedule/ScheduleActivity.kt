package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.ui.trainingsession.TRAINING_DESCRIPTION
import es.iessaladillo.pedrojoya.baldogym.ui.trainingsession.TrainingSessionActivity
import es.iessaladillo.pedrojoya.baldogym.utils.observeEvent
import kotlinx.android.synthetic.main.schedule_activity.*

const val TRAINING_TIME = "TRAINING_TIME"
const val TRAINING_PHOTOID = "TRAINING_PHOTOID"
const val TRAINING_NAME = "TRAINING_NAME"
const val TRAINING_TRAINER = "TRAINING_TRAINER"
const val TRAINING_ROOM = "TRAINING_ROOM"
const val TRAINING_PARTICIPANTS = "TRAINING_PARTICIPANTS"
const val TRAINING_WEEKDAY = "TRAINING_WEEKDAY"
const val TRAINING_USERJOINED = "TRAINING_USERJOINED"


class ScheduleActivity : AppCompatActivity() {

    private val viewModel: ScheduleActivityViewModel by viewModels {
        ScheduleActivityViewModelFactory(LocalRepository, application)
    }
    private val listAdapter: ScheduleActivityAdapter = ScheduleActivityAdapter().apply {
        setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val trainingSession = viewModel.trainingSessions.value?.get(position)
                val intent = Intent(this@ScheduleActivity, TrainingSessionActivity::class.java)
                    .putExtra(TRAINING_TIME, trainingSession?.time)
                    .putExtra(TRAINING_PHOTOID, trainingSession?.photoResId)
                    .putExtra(TRAINING_NAME, trainingSession?.name)
                    .putExtra(TRAINING_TRAINER, trainingSession?.trainer)
                    .putExtra(TRAINING_ROOM, trainingSession?.room)
                    .putExtra(TRAINING_PARTICIPANTS, trainingSession?.participants)
                    .putExtra(TRAINING_DESCRIPTION, trainingSession?.description)
                    .putExtra(TRAINING_WEEKDAY, trainingSession?.weekDay.toString())
                    .putExtra(TRAINING_USERJOINED, trainingSession?.userJoined)
                startActivity(intent)
            }
        })
    }
    private var lblDay: TextView? = null
    private var lblMonday: TextView? = null
    private var lblTuesday: TextView? = null
    private var lblWednesday: TextView? = null
    private var lblThursday: TextView? = null
    private var lblFriday: TextView? = null
    private var lblSaturday: TextView? = null
    private var lblSunday: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setupViews()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupViews() {
        lblDay = ActivityCompat.requireViewById(this, R.id.lblWeekDay)
        lblMonday = ActivityCompat.requireViewById(this, R.id.lblMonday)
        lblTuesday = ActivityCompat.requireViewById(this, R.id.lblTuesday)
        lblWednesday = ActivityCompat.requireViewById(this, R.id.lblWednesday)
        lblThursday = ActivityCompat.requireViewById(this, R.id.lblThursday)
        lblFriday = ActivityCompat.requireViewById(this, R.id.lblFriday)
        lblSaturday = ActivityCompat.requireViewById(this, R.id.lblSaturday)
        lblSunday = ActivityCompat.requireViewById(this, R.id.lblSunday)
        setListeners()
    }

    private fun setListeners() {
        lblMonday!!.setOnClickListener {
            viewModel.filterMonday()
        }
        lblTuesday!!.setOnClickListener {
            viewModel.filterTuesday()
        }
        lblWednesday!!.setOnClickListener {
            viewModel.filterWednesday()
        }
        lblThursday!!.setOnClickListener {
            viewModel.filterThursday()
        }
        lblFriday!!.setOnClickListener {
            viewModel.filterFriday()
        }
        lblSaturday!!.setOnClickListener {
            viewModel.filteSaturday()
        }
        lblSunday!!.setOnClickListener {
            viewModel.filterSunday()
        }
    }

    private fun setupObservers() {
        viewModel.trainingSessions.observe(this) {
            showTrainingSessions(it)
        }
        viewModel.currentFilter.observe(this) {
            lblDay?.text = it.toString()
        }
        viewModel.joined.observeEvent(this) {
            joinTrainingSession(it)
        }
    }

    private fun joinTrainingSession(trainingSession: TrainingSession) {
        viewModel.join(trainingSession)
    }

    private fun showTrainingSessions(trainingSessions: List<TrainingSession>) {
        lstSchedule.post {
            listAdapter.submitList(trainingSessions)
        }
    }

    private fun setupRecyclerView() {
        lstSchedule.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ScheduleActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }


}
