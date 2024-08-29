package com.example.tamingtemper_androidchallenge.domain.models

import com.example.tamingtemper_androidchallenge.data.models.TemperLevelData
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData

data class TemperLevels(
    var levels: ArrayList<TemperLevel>? = null
) {
    public fun toData(): TemperLevelsData {
        return TemperLevelsData(
            levels = temperLevelsListToData(this.levels),
        )
    }

    public fun fromData(data: TemperLevelsData): TemperLevels? {
        levels = temperLevelsListFromData(data.levels)
        return this
    }

    private fun temperLevelsListToData(activityList: ArrayList<TemperLevel>?): ArrayList<TemperLevelData>? {

        val dataList = ArrayList<TemperLevelData>()
        activityList?.forEach {
            dataList.add(it.toData())
        }

        return dataList
    }

    private fun temperLevelsListFromData(dataList: ArrayList<TemperLevelData>?): ArrayList<TemperLevel>? {

        val activityList = ArrayList<TemperLevel>()
        dataList?.forEach {
            activityList.add(TemperLevel().fromData(it))
        }

        return activityList
    }
}
