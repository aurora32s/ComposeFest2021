package com.example.compose.rally

import androidx.compose.material.Text
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.toUpperCase
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.overview.OverviewBody
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    // this rule lets you set the Compose content under test
    // and interact with it.

    @Test
    fun myTest () {
        composeTestRule.setContent {
            Text("You can set any compose content!")
        }
    }
    // with Compose, we can test a component in isolation.
    // You can choose what Compose UI content to use in the test.
    // This is done with the 'setContent' method of the 'ComposeTestRule'
    @Test
    fun rallyTopAppBarText () {
        composeTestRule.setContent {
            RallyTheme {
                val allScreens = RallyScreen.values().toList()
                RallyTopAppBar( // pass the fake data
                    allScreens = allScreens,
                    onTabSelected = { /*TODO*/ },
                    currentScreen = RallyScreen.Accounts)
            }
        }
        // you can see what's going on by adding a sleep().
//        Thread.sleep(5000)
        // composeTestRule{.finder}{.assertion}{.action}
//        composeTestRule
//            .onNodeWithContentDescription(RallyScreen.Accounts.name)
//            .assertIsSelected() // what is selected property?

        // print the Semantics tree
        // use the unmerged tree
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
//        composeTestRule
//            .onNodeWithContentDescription(RallyScreen.Accounts.name)
//            .assertExists()

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase())
                and
                hasParent(hasContentDescription(RallyScreen.Accounts.name)),
                useUnmergedTree = true
            )
            .assertExists()
    }

    // any test that you write must be properly synchronized with the subject under the tree.
    // without synchronization, tests could look for elements before they're displayed or they
    // could wait unnecessarily.
    @Test
    fun overviewScreen_alertsDisplayed () {
        composeTestRule.setContent {
            OverviewBody()
        }

        // never finish
        // the compose is permanently busy so there is no way to synchronize
        // the app with the test.
        // In Compose, the animation APIs were designed with testability in mind,
        // so the problem can be fixed by using the correct API.
        // ex. infiniteElevationAnimation.animateValue
        composeTestRule
            .onNodeWithText("Alerts")
            .assertIsDisplayed()
    }
}