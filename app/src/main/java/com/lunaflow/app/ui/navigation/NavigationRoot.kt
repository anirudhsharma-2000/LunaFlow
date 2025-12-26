package com.lunaflow.app.ui.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.lunaflow.app.ui.calendar.CalendarScreen

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val initialScreen = Screens.CalendarView
    val backstack = remember { mutableStateListOf<Screens>(initialScreen) }
    val context = LocalContext.current
    BackHandler(enabled = backstack.size == 1) {
        (context as? Activity?)?.finish()
    }
    NavDisplay(
        backStack = backstack,
        entryProvider = entryProvider {
            entry<Screens.Login> { DummyScreen(modifier, it.toString()) }
            entry<Screens.Signup> { DummyScreen(modifier, it.toString()) }
            entry<Screens.PolicyAndAgreement> { DummyScreen(modifier, it.toString()) }
            entry<Screens.Onboarding> { DummyScreen(modifier, it.toString()) }
            entry<Screens.CalendarView> { CalendarScreen(modifier, backstack) }
            entry<Screens.DetailedLog> { DummyScreen(modifier, it.toString()) }
            entry<Screens.Insights> { DummyScreen(modifier, it.toString()) }
            entry<Screens.Settings> { DummyScreen(modifier, it.toString()) }
            entry<Screens.Notifications> { DummyScreen(modifier, it.toString()) }
            entry<Screens.ReliefAndCare> { DummyScreen(modifier, it.toString()) }
            entry<Screens.HelpAndSupport> { DummyScreen(modifier, it.toString()) }
            entry<Screens.Profile> { DummyScreen(modifier, it.toString()) }
            entry<Screens.AddWidget> { DummyScreen(modifier, it.toString()) }
        }
    )
}

@Composable
fun DummyScreen(modifier: Modifier = Modifier, text: String) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text)
    }
}