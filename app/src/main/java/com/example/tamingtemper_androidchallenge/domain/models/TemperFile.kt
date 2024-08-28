package com.example.tamingtemper_androidchallenge.domain.models

import com.example.tamingtemper_androidchallenge.data.models.TemperFileData

data class TemperFile(
    var url: String? = "",
    var details: TemperFileDetails? = null,
    var fileName: String? = "",
    var contentType: String? = "",
) {
    fun toData(): TemperFileData {
        return TemperFileData(
            url = this.url,
            details = this.details?.toData(),
            fileName = this.fileName,
            contentType = this.contentType,
        )
    }

    fun fromData(data: TemperFileData?) : TemperFile? {
        if (data == null){
            return null
        }
        url = data.url
        details = TemperFileDetails().fromData(data.details)
        fileName = data.fileName
        contentType = data.contentType
        return this
    }
}
