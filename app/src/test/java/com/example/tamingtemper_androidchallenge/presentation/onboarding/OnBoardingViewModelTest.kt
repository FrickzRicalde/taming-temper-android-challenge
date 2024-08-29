package com.example.tamingtemper_androidchallenge.presentation.onboarding

import com.example.tamingtemper_androidchallenge.data.models.TemperActivityData
import com.example.tamingtemper_androidchallenge.data.models.TemperIconData
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelData
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.example.tamingtemper_androidchallenge.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.util.ArrayList

@OptIn(ExperimentalCoroutinesApi::class)
class OnBoardingViewModelTest {

    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: OnBoardingViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var temperLevelsData: TemperLevelsData

    @Before
    fun setUp() {
        userRepository = mock(UserRepository::class.java)
        viewModel = OnBoardingViewModel(userRepository)

        val icon = TemperIconData(title = "Icon Unlocked")
        val lockedIcon = TemperIconData(title = "Icon Locked")

        val actList = ArrayList<TemperActivityData>()
        actList.add(
            TemperActivityData(
                id = "",
                challengeId = "Ch1",
                type = "",
                title = "Act 1",
                titleB = "",
                description = "",
                descriptionB = "",
                state = "",
                icon = icon,
                lockedIcon = lockedIcon,

            )
        )

        val levelList = ArrayList<TemperLevelData>()
        levelList.add(TemperLevelData(
            level = "1",
            title = "Stage Impact",
            description = "description",
            state = "AVAILABLE",
            activities = actList
        ))


        temperLevelsData = TemperLevelsData(levelList)
    }

    @Test
    fun `assert viewModel loadTemperLevels`() = runTest(UnconfinedTestDispatcher()) {

        Mockito.`when`(
            userRepository.loadTemperLevels()
        ).thenReturn(
            flow {
                emit(temperLevelsData)
            }
        )

        Mockito.`when`(
            userRepository.loadTemperLevelsFromResource()
        ).thenReturn(
            TemperLevels().fromData(temperLevelsData)
        )

        Mockito.`when`(
            runBlocking {
                userRepository.saveTemperLevels(temperLevelsData)
            }

        ).thenReturn(
            Unit
        )

        viewModel.loadTemperLevels()

        advanceTimeBy(1000)

        launch {
            viewModel.temperLevels.collect{ value ->

                val temperLevels: TemperLevelsData = value?.toData()!!
                val levelList = temperLevels.levels
                val temperLevel = levelList?.get(0)
                val actList = temperLevel?.activities
                val act = actList?.get(0)
                val icon = act?.icon
                val lockedIcon = act?.lockedIcon


                assertNotNull(temperLevels)
                assertNotNull(levelList)
                assertNotNull(temperLevel)
                assertNotNull(actList)
                assertNotNull(act)
                assertNotNull(icon)
                assertNotNull(lockedIcon)

                assertEquals("AVAILABLE", temperLevel?.state)
                assertEquals("1", temperLevel?.level)

                assertEquals("Act 1", act?.title)
                assertEquals("Ch1", act?.challengeId)

                assertEquals("Icon Unlocked", icon?.title)
                assertEquals("Icon Locked", lockedIcon?.title)

                this.coroutineContext.job.cancel()
            }
        }

    }

    @Test
    fun `assert viewModel saveTemperLevels`() = runTest(UnconfinedTestDispatcher()) {

        Mockito.`when`(
            userRepository.loadTemperLevels()
        ).thenReturn(
            flow {
                emit(temperLevelsData)
            }
        )

        Mockito.`when`(
            userRepository.loadTemperLevelsFromResource()
        ).thenReturn(
            TemperLevels().fromData(temperLevelsData)
        )

        Mockito.`when`(
            runBlocking {
                userRepository.saveTemperLevels(temperLevelsData)
            }

        ).thenReturn(
            Unit
        )

        viewModel.loadTemperLevels()

        advanceTimeBy(1000)

        launch {
            viewModel.temperLevels.collect{ value ->

                val temperLevels: TemperLevelsData = value?.toData()!!
                val levelList = temperLevels.levels
                val temperLevel = levelList?.get(0)
                val actList = temperLevel?.activities
                val act = actList?.get(0)
                val icon = act?.icon
                val lockedIcon = act?.lockedIcon


                assertNotNull(temperLevels)
                assertNotNull(levelList)
                assertNotNull(temperLevel)
                assertNotNull(actList)
                assertNotNull(act)
                assertNotNull(icon)
                assertNotNull(lockedIcon)

                assertEquals("AVAILABLE", temperLevel?.state)
                assertEquals("1", temperLevel?.level)

                assertEquals("Act 1", act?.title)
                assertEquals("Ch1", act?.challengeId)

                assertEquals("Icon Unlocked", icon?.title)
                assertEquals("Icon Locked", lockedIcon?.title)

                this.coroutineContext.job.cancel()
            }
        }

    }

}

@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestDispatcher = StandardTestDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}