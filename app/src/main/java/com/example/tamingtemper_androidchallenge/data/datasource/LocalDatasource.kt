package com.example.tamingtemper_androidchallenge.data.datasource

import android.content.Context
import android.widget.Toast
import androidx.core.net.toUri
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.pspdfkit.document.download.DownloadJob
import com.pspdfkit.document.download.DownloadRequest
import com.pspdfkit.document.download.Progress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class LocalDatasource(
    private val context: Context
) {

    lateinit var request: DownloadRequest

    lateinit var job: DownloadJob

    fun loadJsonFromResource(callback: (File) -> Unit){
        var urlString =
            "https://assets.ctfassets.net/37k4ti9zbz4t/DVQrkzmSp53EXqmFn9z1L/f4270b3b29c508c04493ead947e8651f/Chapter_01__Lesson_02__State_Active.pdf"
        val filename = urlString.split("/").last()
        val outputFile = File(context.filesDir, filename)
        request = DownloadRequest.Builder(context)
            .uri(urlString)
            .outputFile(outputFile)
            .build()

        job = DownloadJob.startDownload(request)

        job.setProgressListener(object : DownloadJob.ProgressListenerAdapter() {
            override fun onProgress(progress: Progress) {
//                progressBar.setProgress((100f * progress.bytesReceived / progress.totalBytes).toInt())

                println("Temper PROGRESS: ${(100f * progress.bytesReceived / progress.totalBytes).toInt()}")
//                Toast.makeText(applicationContext, (100f * progress.bytesReceived / progress.totalBytes).toInt(), Toast.LENGTH_SHORT).show()
            }

            override fun onComplete(output: File) {
                callback(output)
                println("Temper PROGRESS: ${output.toUri().toString()}")
                Toast.makeText(context, output.toUri().toString(), Toast.LENGTH_SHORT).show()
//                PdfActivity.showDocument(context, Uri.fromFile(output), configuration.build())
            }

            override fun onError(exception: Throwable) {
//                handleDownloadError(exception)
            }
        })
    }
}