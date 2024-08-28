package com.example.tamingtemper_androidchallenge.domain.models

import com.example.tamingtemper_androidchallenge.data.models.TemperFileData
import com.example.tamingtemper_androidchallenge.data.models.TemperIconData

data class TemperIcon(
    var id: String? = "",
    var file: TemperFile? = null,
    var title: String? = "",
    var description: String? = "",
) {
    fun toData(): TemperIconData {
        return TemperIconData(
            id = this.id,
            file = this.file?.toData(),
            title = this.title,
            description = this.description,
        )
    }

    fun fromData(data: TemperIconData?) : TemperIcon?{
        if (data == null) {
            return null
        }
        id = data.id
        file = TemperFile().fromData(data.file)
        title = data.title
        description = data.description
        return this
    }
}