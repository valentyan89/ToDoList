package com.example.todolist

import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsToggleable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UITest {

    @get:Rule
    val uiTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun appIsLaunched() {
        uiTestRule.onRoot().assertExists()
    }

    @Test
    fun CheckBoxButton(){
        uiTestRule.onNodeWithTag("CheckBox").performClick()
        uiTestRule.onNodeWithTag("CheckBox").assertIsOff()
    }
}