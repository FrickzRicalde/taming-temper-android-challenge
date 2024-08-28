package com.example.tamingtemper_androidchallenge.domain.models

import com.example.tamingtemper_androidchallenge.data.models.TemperActivityData
import com.example.tamingtemper_androidchallenge.data.models.TemperIconData

data class TemperActivity(
    var id: String? = "",
    var challengeId: String? = "",
    var type: String? = "",
    var title: String? = "",
    var titleB: String? = "",
    var description: String? = "",
    var descriptionB: String? = "",
    var state: String? = "",
    var icon: TemperIcon? = null,
    var lockedIcon: TemperIcon? = null,
) {
    fun toData(): TemperActivityData {
        return TemperActivityData(
            id = this.id,
            challengeId = this.challengeId,
            type = this.type,
            title = this.title,
            titleB = this.titleB,
            description = this.description,
            descriptionB = this.descriptionB,
            state = this.state,
            icon = this.icon?.toData(),
            lockedIcon = this.lockedIcon?.toData(),
            )
    }

    fun fromData(data: TemperActivityData): TemperActivity {
        id = data.id
        challengeId = data.challengeId
        type = data.type
        title = data.title
        titleB = data.titleB
        description = data.description
        descriptionB = data.descriptionB
        state = data.state
        icon = TemperIcon().fromData(data.icon)
        lockedIcon = TemperIcon().fromData(data.lockedIcon)

        return this
    }
}
