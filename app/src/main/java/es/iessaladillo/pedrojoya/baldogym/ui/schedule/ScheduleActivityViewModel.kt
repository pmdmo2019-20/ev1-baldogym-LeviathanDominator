package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay
import es.iessaladillo.pedrojoya.baldogym.utils.Event

class ScheduleActivityViewModel(
    private val repository: Repository
) : ViewModel() {

    private val mutableTrainingSessions: MutableLiveData<List<TrainingSession>> = MutableLiveData()
    val trainingSessions: LiveData<List<TrainingSession>>
        get() = mutableTrainingSessions

    private val _currentFilter: MutableLiveData<WeekDay> = MutableLiveData()
    val currentFilter: LiveData<WeekDay>
        get() = _currentFilter

    private val _joined: MutableLiveData<Event<TrainingSession>> = MutableLiveData()
    val joined: LiveData<Event<TrainingSession>>
        get() = _joined

    init {
        _currentFilter.value = getCurrentWeekDay()
        queryTrainingSessions(_currentFilter.value!!)
    }

    private fun queryTrainingSessions(filter: WeekDay) {
        _currentFilter.value = filter
        when (filter) {
            WeekDay.MONDAY -> mutableTrainingSessions.value =
                repository.queryMondayTrainingSessions()
            WeekDay.TUESDAY -> mutableTrainingSessions.value =
                repository.queryTuesdayTrainingSessions()
            WeekDay.WEDNESDAY -> mutableTrainingSessions.value =
                repository.queryWednesdayTrainingSessions()
            WeekDay.THURSDAY -> mutableTrainingSessions.value =
                repository.queryThursdayTrainingSessions()
            WeekDay.FRIDAY -> mutableTrainingSessions.value =
                repository.queryFridayTrainingSessions()
            WeekDay.SATURDAY -> mutableTrainingSessions.value =
                repository.querySaturdayTrainingSessions()
            WeekDay.SUNDAY -> mutableTrainingSessions.value =
                repository.querySundayTrainingSessions()
        }
    }

    fun filterMonday() {
        _currentFilter.value = WeekDay.MONDAY
        queryTrainingSessions(_currentFilter.value!!)
    }

    fun filterTuesday() {
        _currentFilter.value = WeekDay.TUESDAY
        queryTrainingSessions(_currentFilter.value!!)
    }

    fun filterWednesday() {
        _currentFilter.value = WeekDay.WEDNESDAY
        queryTrainingSessions(_currentFilter.value!!)
    }

    fun filterThursday() {
        _currentFilter.value = WeekDay.THURSDAY
        queryTrainingSessions(_currentFilter.value!!)
    }

    fun filterFriday() {
        _currentFilter.value = WeekDay.FRIDAY
        queryTrainingSessions(_currentFilter.value!!)
    }

    fun filteSaturday() {
        _currentFilter.value = WeekDay.SATURDAY
        queryTrainingSessions(_currentFilter.value!!)
    }

    fun filterSunday() {
        _currentFilter.value = WeekDay.SUNDAY
        queryTrainingSessions(_currentFilter.value!!)
    }

    fun join(trainingSession: TrainingSession) {
        val session = trainingSession.copy()
        session.userJoined = !session.userJoined
        _joined.value = Event(trainingSession)
    }

}