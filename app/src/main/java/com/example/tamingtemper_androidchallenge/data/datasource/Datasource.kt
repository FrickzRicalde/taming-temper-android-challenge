package com.example.tamingtemper_androidchallenge.data.datasource

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.net.toUri
import com.pspdfkit.document.PdfDocumentLoader
import com.pspdfkit.document.download.DownloadJob
import com.pspdfkit.document.download.DownloadRequest
import com.pspdfkit.document.download.Progress
import java.io.File

class Datasource(
    private val context: Context
) {

    lateinit var request: DownloadRequest

    lateinit var job: DownloadJob

    fun downloadImage(url: String, success: (Boolean)->Unit){
        val filename = url.split("/").last()
        val outputFile = File(context.filesDir, filename)
        request = DownloadRequest.Builder(context)
            .uri(url)
            .outputFile(outputFile)
            .build()

        job = DownloadJob.startDownload(request)

        job.setProgressListener(object : DownloadJob.ProgressListenerAdapter() {
            override fun onProgress(progress: Progress) {
            }

            override fun onComplete(output: File) {
                success(true)
            }

            override fun onError(exception: Throwable) {
                success(false)
            }
        })
    }

    fun loadImageFromAppDir(url: String?, imageBitmap: (ImageBitmap)->Unit){
        val filename = url?.split("/")?.last()

        val file = File(context.filesDir, filename)

        var x = PdfDocumentLoader
            .openDocumentAsync(
                context,
                file.toUri()

            )

        val document = x.blockingGet()

        val pageImageSize = document!!.getPageSize(0).toRect()

        val bitmap = document.renderPageToBitmap(
            context,
            0,
            pageImageSize.width().toInt(),
            pageImageSize.height().toInt()
        ).asImageBitmap()

        imageBitmap(bitmap)
    }
}