package com.example.tamingtemper_androidchallenge.domain.models

import com.example.tamingtemper_androidchallenge.data.models.TemperFileDetailsData

data class TemperFileDetails(
    var size: Int? = 0,
) {
    fun toData(): TemperFileDetailsData {
        return TemperFileDetailsData(
            size = this.size,
        )
    }

    fun fromData(data: TemperFileDetailsData?) : TemperFileDetails{
        size = data?.size
        return this
    }
}
