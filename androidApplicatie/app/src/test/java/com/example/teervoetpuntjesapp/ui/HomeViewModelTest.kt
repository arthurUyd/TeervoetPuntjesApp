package com.example.teervoetpuntjesapp.ui

import app.cash.turbine.test
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.data.TestBadgeRepository
import com.example.teervoetpuntjesapp.ui.home.BadgeUiState
import com.example.teervoetpuntjesapp.ui.home.HomeScreenViewModel
import com.example.teervoetpuntjesapp.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val badgeRepository = TestBadgeRepository()
    private lateinit var viewmodel: HomeScreenViewModel

    @Before
    fun setup() {
        viewmodel = HomeScreenViewModel(badgeRepository)
    }

    @Test
    fun `als homescreen state is geïnitializeerd is state loading`() = runTest {
        viewmodel.uiState.test {
            assertEquals(BadgeUiState.Loading, awaitItem().badges)
        }
    }

    @Test
    fun `als homescreen state is geïnitializeerd is correcte state`() = runTest {
        viewmodel.uiState.test {
            val initialstate = awaitItem()
            assertEquals(BadgeUiState.Loading, initialstate.badges)
            assertFalse(initialstate.isRefreshing)
        }
    }

    @Test
    fun `als badgeUiState op succes staat is het hetzelfde als repo`() = runTest {
        viewmodel.uiState.test {
            badgeRepository.sendBadges(testInputBadges)

            assertTrue(awaitItem().badges is BadgeUiState.Loading)

            assertEquals(
                testInputBadges,
                (awaitItem().badges as BadgeUiState.Success).badges,
            )
        }
    }
}

private val testInputBadges = listOf(
    Badge(
        1,
        "badge1",
        "badge1",
    ),
    Badge(
        2,
        "badge2",
        "badge2",
    ),
)
