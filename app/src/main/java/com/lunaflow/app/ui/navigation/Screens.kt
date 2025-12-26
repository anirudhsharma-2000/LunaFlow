package com.lunaflow.app.ui.navigation

sealed class Screens {
    data object Login : Screens()
    data object Signup : Screens()
    data object PolicyAndAgreement : Screens()
    data object Onboarding : Screens()
    data object CalendarView : Screens()
    data object DetailedLog : Screens()
    data object Insights : Screens()
    data object Settings : Screens()
    data object Notifications : Screens()
    data object ReliefAndCare : Screens()
    data object HelpAndSupport : Screens()
    data object Profile : Screens()
    data object AddWidget : Screens()
}