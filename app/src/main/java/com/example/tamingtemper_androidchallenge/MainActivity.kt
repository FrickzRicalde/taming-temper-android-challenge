package com.example.tamingtemper_androidchallenge

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.example.tamingtemper_androidchallenge.presentation.onboarding.OnBoardingViewModel
import com.example.tamingtemper_androidchallenge.presentation.onboarding.OnboardingScreen
import com.example.tamingtemper_androidchallenge.ui.theme.TamingTemperAndroidChallengeTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pspdfkit.document.PdfDocument
import com.pspdfkit.document.download.DownloadJob
import com.pspdfkit.document.download.DownloadRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tamingtemper_androidchallenge.presentation.onboarding.OnBoardingEvent
import com.pspdfkit.document.PdfDocumentLoader
import com.pspdfkit.ui.PdfActivity

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var temperLevelsUsecases: TemperLevelsUseCases

//    val viewModel: OnBoardingViewModel by viewModels()


    private lateinit var document: PdfDocument

    lateinit var request: DownloadRequest

    lateinit var job: DownloadJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //Working Collect
//        @Inject
//        lateinit var temperLevelsUsecases: TemperLevelsUseCases
//        lifecycleScope.launch {
//            temperLevelsUsecases.loadTemperLevels().collect{
//                println("TEMPER HILT: $it")
//            }
//        }
        //Working Collect END



//        val fileContent = this::class.java.classLoader.getResource("response.json").readText()
//
//        val gameofthronesCharacters: List<GameOfThronesDto> = Gson().fromJson(fileContent, object : TypeToken<List<GameOfThronesDto>>() {}.type)
//
//        println("-----> JSON Data <-----")
//        gameofthronesCharacters.forEach{ println(it) }
//
//        gameOfThronesService.createCharactersFromJson(gameofthronesCharacters)

        //Get App resource
//        val fileContent = this::class.java.classLoader?.getResource("response.json")!!.readText()
//
//        val temperLevels: TemperLevels = Gson().fromJson(fileContent, object : TypeToken<TemperLevels>() {}.type)
        //Get App resource END



//        val filePath = "resources/response.json" // Replace with your JSON file path
//        val file2 = File(filePath)
////        val fileRes = File("src/main/res/json/response.json")
//
//        val jsonString = file2.readText()
//        val json = Gson().fromJson(jsonString, TemperResponse::class.java)



//        println("Temper JSON: $temperLevels")

        var urlString =
            "https://assets.ctfassets.net/37k4ti9zbz4t/DVQrkzmSp53EXqmFn9z1L/f4270b3b29c508c04493ead947e8651f/Chapter_01__Lesson_02__State_Active.pdf"

//        download(urlString)

//        urlString = urlString.replace("\\", "%5C")
//        val url = URL(urlString)
//        val imageData = url.readBytes()

//        println("imageData: $imageData")

        //WORKING

//        val folder = File(Environment.getExternalStorageDirectory().toString() + "/Download/Test")
//
//        if (!folder.exists()) {
//            folder.mkdirs()
//        }
//
        val filename = urlString.split("/").last()
//
//        val file =
//            File(Environment.getExternalStorageDirectory().toString() + "/Download/Test/$filename")
//
//
//        var x = PdfDocumentLoader
//            .openDocumentAsync(
//                applicationContext,
//                file.toUri()
//
//            )
//
//
//        document = x.blockingGet()
//
//        println("document: $document")

        //WORKING END


        //WORKING 2
//        val outputFile = File(applicationContext.filesDir, filename)
//        request = DownloadRequest.Builder(applicationContext)
//            .uri(urlString)
//            .outputFile(outputFile)
//            .build()
//
//        job = DownloadJob.startDownload(request)
//
//        job.setProgressListener(object : DownloadJob.ProgressListenerAdapter() {
//            override fun onProgress(progress: Progress) {
////                progressBar.setProgress((100f * progress.bytesReceived / progress.totalBytes).toInt())
//
//                println("Temper PROGRESS: ${(100f * progress.bytesReceived / progress.totalBytes).toInt()}")
////                Toast.makeText(applicationContext, (100f * progress.bytesReceived / progress.totalBytes).toInt(), Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onComplete(output: File) {
//                println("Temper PROGRESS: ${output.toUri().toString()}")
//                Toast.makeText(applicationContext, output.toUri().toString(), Toast.LENGTH_SHORT).show()
////                PdfActivity.showDocument(context, Uri.fromFile(output), configuration.build())
//            }
//
//            override fun onError(exception: Throwable) {
////                handleDownloadError(exception)
//            }
//        })

        //WORKING 2 END

//        lifecycleScope.launch {
//            viewModel.onEvent(
//                OnBoardingEvent.SaveTemperLevels("")
//            )
//        }

        enableEdgeToEdge()
        setContent {
            TamingTemperAndroidChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    val temperLevels by viewModel.temperLevels.collectAsState()
                    viewModel.loadTemperLevels()


//                    viewModel.onEvent(
////                        OnBoardingEvent.SaveTemperLevels(temperLevels.toData())
//                        OnBoardingEvent.LoadTemperLevels()
//                    )

//                    val temperLevels = TemperLevels()


                    Box(modifier = Modifier.padding(innerPadding)) {
                        OnboardingScreen(temperLevels)
                    }
//                    Text(text = "", modifier = Modifier.padding(innerPadding))
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding),
//                        document = document,
//                        context = applicationContext,
//
//                        )
                }
            }
        }
    }

    private fun download(url: String) {
        val folder = File(Environment.getExternalStorageDirectory().toString() + "/Download/Test")

        if (!folder.exists()) {
            folder.mkdirs()
        }

        Toast.makeText(this, "Download Started", Toast.LENGTH_SHORT).show()
        val filename = url.split("/").last()

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(url))
        request.setAllowedNetworkTypes(
            DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
        )
        request.setTitle(filename)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "Test/$filename"
        )
        downloadManager.enqueue(request)


    }
}


@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
fun Greeting(name: String, modifier: Modifier = Modifier, document: PdfDocument, context: Context) {

//    val urlString = ""
//    val filename = urlString.split("/").last()
//
//        val file =
//            File(Environment.getExternalStorageDirectory().toString() + "/Download/Test/$filename")
//
//
//        var x = PdfDocumentLoader
//            .openDocumentAsync(
//                context,
//                file.toUri()
//
//            )
//    x.subscribe { it ->
//
//    }


//        document = x.blockingGet()

    val thumbnailImage = remember(document) {
        val pageImageSize = document.getPageSize(0).toRect()

        document.renderPageToBitmap(
            context,
            0,
            pageImageSize.width().toInt(),
            pageImageSize.height().toInt()
        ).asImageBitmap()
    }

    Image(
        bitmap = thumbnailImage,
        contentScale = ContentScale.Crop,
        contentDescription = "Preview for the document ${document.title}",
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
    )

//    AsyncImage(
//        modifier = Modifier.fillMaxSize().background(Color.Red),
//        model = "//assets.ctfassets.net/37k4ti9zbz4t/DVQrkzmSp53EXqmFn9z1L/f4270b3b29c508c04493ead947e8651f/Chapter_01__Lesson_02__State_Active.pdf",
//        contentDescription = "Translated description of what the image contains"
//    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TamingTemperAndroidChallengeTheme {
//        Greeting("Android")
//    }
//}
