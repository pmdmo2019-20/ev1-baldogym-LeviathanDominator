package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

interface Repository {

    fun queryWeekSchedule(): List<TrainingSession>
    fun queryMondayTrainingSessions(): List<TrainingSession>
    fun queryTuesdayTrainingSessions(): List<TrainingSession>
    fun queryWednesdayTrainingSessions(): List<TrainingSession>
    fun queryThursdayTrainingSessions(): List<TrainingSession>
    fun queryFridayTrainingSessions(): List<TrainingSession>
    fun querySaturdayTrainingSessions(): List<TrainingSession>
    fun querySundayTrainingSessions(): List<TrainingSession>

}