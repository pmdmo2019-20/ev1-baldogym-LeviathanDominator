package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import es.iessaladillo.pedrojoya.baldogym.R

const val TRAINING_TIME = "TRAINING_TIME"
const val TRAINING_PHOTOID = "TRAINING_PHOTOID"
const val TRAINING_NAME = "TRAINING_NAME"
const val TRAINING_TRAINER = "TRAINING_TRAINER"
const val TRAINING_ROOM = "TRAINING_ROOM"
const val TRAINING_PARTICIPANTS = "TRAINING_PARTICIPANTS"
const val TRAINING_DESCRIPTION = "TRAINING_DESCRIPTION"
const val TRAINING_WEEKDAY = "TRAINING_WEEKDAY"
const val TRAINING_USERJOINED = "TRAINING_USERJOINED"

class TrainingSessionActivity : AppCompatActivity() {

    private var lblTime: TextView? = null
    private var imgPhoto: ImageView? = null
    private var lblName: TextView? = null
    private var lblTrainer: TextView? = null
    private var lblRoom: TextView? = null
    private var lblParticipants: TextView? = null
    private var lblWeekDay: TextView? = null
    private var lblDescription: TextView? = null
    private var btnJoin: Button? = null
    private var joined: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_session_activity)
        setupViews()
        readIntent()
        println(lblWeekDay.toString())
    }

    private fun setupViews() {
        lblTime = ActivityCompat.requireViewById(this, R.id.lblTime)
        imgPhoto = ActivityCompat.requireViewById(this, R.id.imgPhoto)
        lblName = ActivityCompat.requireViewById(this, R.id.lblName)
        lblTrainer = ActivityCompat.requireViewById(this, R.id.lblTrainer)
        lblRoom = ActivityCompat.requireViewById(this, R.id.lblRoom)
        lblParticipants = ActivityCompat.requireViewById(this, R.id.lblParticipants)
        lblWeekDay = ActivityCompat.requireViewById(this, R.id.lblWeekDay)
        lblDescription = ActivityCompat.requireViewById(this, R.id.lblDescription)
        btnJoin = ActivityCompat.requireViewById(this, R.id.btnJoin)
    }

    private fun readIntent() {
        joined = intent.getBooleanExtra(TRAINING_USERJOINED, false)
        lblTime?.text = intent.getStringExtra(TRAINING_TIME)
        imgPhoto?.setImageResource(intent.getIntExtra(TRAINING_PHOTOID, 0))
        lblName?.text = intent.getStringExtra(TRAINING_NAME)
        lblTrainer?.text = intent.getStringExtra(TRAINING_TRAINER)
        lblRoom?.text = intent.getStringExtra(TRAINING_ROOM)
        lblParticipants?.text = resources.getQuantityString(
            R.plurals.schedule_item_participants,
            intent.getIntExtra(TRAINING_PARTICIPANTS, 0),
            intent.getIntExtra(TRAINING_PARTICIPANTS, 0)
        )
        lblWeekDay?.text = intent.getStringExtra(TRAINING_WEEKDAY)
        lblDescription?.text = intent.getStringExtra(TRAINING_DESCRIPTION)
        if (joined) {
            btnJoin?.text = resources.getString(R.string.schedule_item_quit)
        } else {
            btnJoin?.text = resources.getString(R.string.schedule_item_join)
        }
        btnJoin?.setOnClickListener {
            joined = !joined
            changeButton()
        }
    }

    private fun changeButton() {
        if (joined) {
            btnJoin?.text =
                this@TrainingSessionActivity.resources.getString(R.string.schedule_item_quit)
        } else {
            btnJoin?.text =
                this@TrainingSessionActivity.resources.getString(R.string.schedule_item_join)
        }
    }

}
