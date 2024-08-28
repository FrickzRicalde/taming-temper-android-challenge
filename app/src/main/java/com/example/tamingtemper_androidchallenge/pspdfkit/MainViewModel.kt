package com.example.tamingtemper_androidchallenge.pspdfkit

import android.app.Application
import android.content.Context
import com.pspdfkit.document.PdfDocument
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pspdfkit.document.PdfDocumentLoader
import com.pspdfkit.document.download.DownloadJob
import com.pspdfkit.document.download.DownloadRequest
import com.pspdfkit.document.download.source.AssetDownloadSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // The list of PDFs in our `assets` folder.
    private val assetsToLoad = listOf(
        "Annotations.pdf",
        "Aviation.pdf",
        "Calculator.pdf",
        "Classbook.pdf",
        "Construction.pdf",
        "Student.pdf",
        "Teacher.pdf",
        "The-Cosmic-Context-for-Life.pdf"
    )

    private val mutableState = MutableStateFlow(State())
    val state: StateFlow<State> = mutableState

    private fun <T> MutableStateFlow<T>.mutate(mutateFn: T.() -> T) {
        value = value.mutateFn()
    }

    fun loadPdfs() = viewModelScope.launch(Dispatchers.IO) {
        val context = getApplication<Application>().applicationContext

        PdfDocumentLoader
            .openDocumentAsync(context, Uri.parse("//assets.ctfassets.net/37k4ti9zbz4t/DVQrkzmSp53EXqmFn9z1L/f4270b3b29c508c04493ead947e8651f/Chapter_01__Lesson_02__State_Active.pdf"))


        // Mutate the state to indicate that we're now loading.
        mutableState.mutate { copy(loading = true) }

//        val context = getApplication<Application>().applicationContext

        // Each map here is running a suspended function.
        val pdfDocuments = assetsToLoad
            .map { extractPdf(context, it) }
            .map { it.toUri() to loadPdf(context, it.toUri()) }
            .toMap()

        // Stop loading and add the PDFs to the state.
        mutableState.mutate {
            copy(
                loading = false,
                documents = pdfDocuments
            )
        }
    }

    private suspend fun loadPdf(context: Context, uri: Uri) = suspendCoroutine<PdfDocument> { continuation ->
        PdfDocumentLoader
            .openDocumentAsync(context, uri)
            .subscribe(continuation::resume, continuation::resumeWithException)
    }

    private suspend fun extractPdf(context: Context, assetPath: String) = suspendCoroutine<File> { continuation ->
        val outputFile = File(context.filesDir, assetPath)
        val request = DownloadRequest.Builder(context)
            .source(AssetDownloadSource(context, assetPath))
            .outputFile(outputFile)
            .overwriteExisting(true)
            .build()

        val job = DownloadJob.startDownload(request)
        job.setProgressListener(
            object : DownloadJob.ProgressListenerAdapter() {
                override fun onComplete(output: File) {
                    continuation.resume(output)
                }

                override fun onError(exception: Throwable) {
                    super.onError(exception)
                    continuation.resumeWithException(exception)
                }
            }
        )
    }
}