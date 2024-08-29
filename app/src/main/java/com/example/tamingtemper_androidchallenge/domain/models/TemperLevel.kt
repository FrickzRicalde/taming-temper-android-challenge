package com.example.tamingtemper_androidchallenge.domain.models

import com.example.tamingtemper_androidchallenge.data.models.TemperActivityData
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelData

data class TemperLevel(
    var level: String? = "",
    var title: String? = "",
    var description: String? = "",
    var state: String? = "",
    var activities: ArrayList<TemperAct>? = null
) {
    public fun toData(): TemperLevelData {
        return TemperLevelData(
            level = this.level,
            title = this.title,
            description = this.description,
            state = this.state,
            activities = temperActivityListToData(this.activities),
        )
    }

    public fun fromData(data: TemperLevelData) : TemperLevel{
        level = data.level
        title = data.title
        description = data.description
        state = data.state
        activities = temperActivityListFromData(data.activities)

        return this
    }

    private fun temperActivityListToData(activityList: ArrayList<TemperAct>?): ArrayList<TemperActivityData>? {

        val dataList = ArrayList<TemperActivityData>()
        activityList?.forEach {
            dataList.add(it.toData())
        }

        return dataList
    }

    private fun temperActivityListFromData(dataList: ArrayList<TemperActivityData>?): ArrayList<TemperAct>? {

        val activityList = ArrayList<TemperAct>()
        dataList?.forEach {
            activityList.add(TemperAct().fromData(it))
        }

        return activityList
    }
}
