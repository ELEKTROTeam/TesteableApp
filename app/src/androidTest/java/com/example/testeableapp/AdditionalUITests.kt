package com.example.testeableapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import org.junit.Rule
import org.junit.Test

class AdditionalUITests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun invalidPutCharge(){
        val expectedTip = "Propina: $0.00"

        composeTestRule.setContent {
            TipCalculatorScreen()
        }

        composeTestRule
            .onNodeWithTag("inputBill")
            .assertExists()
            .performTextInput("..")


        composeTestRule.onNodeWithTag("textTipResult")
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals(expectedTip)

        composeTestRule.onNodeWithText("Total por persona: $0.00")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun changeNumberOfPeople(){
        composeTestRule.setContent {
            TipCalculatorScreen()
        }
        composeTestRule.onNodeWithTag("peopleIncrement").performClick()
        composeTestRule.onNodeWithTag("peopleIncrement").performClick()
        composeTestRule.onNodeWithTag("peopleCount").assertExists().assertTextEquals("3")
        composeTestRule.onNodeWithTag("peopleDecrease").performClick()
        composeTestRule.onNodeWithTag("peopleDecrease").performClick()
        composeTestRule.onNodeWithTag("peopleDecrease").performClick()
        composeTestRule.onNodeWithTag("peopleCount").assertExists().assertTextEquals("1")
    }
}