package com.example.testeableapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.swipeRight
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import org.junit.Rule
import org.junit.Test

class UiTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun roundTip_toggleRoundSwitch_changesTipAmount() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule.onNodeWithTag("inputBill").performTextInput("10")
        composeTestRule.onNodeWithTag("checkboxRoundUp").performClick()

        composeTestRule.onNodeWithTag("textTipResult")
            .assertTextContains("Propina: $2.00")
    }

    @Test
    fun sliderTipPercentage_movesSlider_andUpdatesCalculation() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule.onNodeWithTag("inputBill").performTextInput("100")
        composeTestRule.onNodeWithTag("seekBarPercentage").performTouchInput { swipeRight() }

        composeTestRule.onNodeWithTag("textTipResult")
            .assertTextContains("Propina: $15.00")
    }

    @Test
    fun validateElementsPresence() {
        composeTestRule.setContent { TipCalculatorScreen() }

        composeTestRule.onNodeWithTag("inputBill").performTextInput("100")
        composeTestRule.onNodeWithTag("peopleIncrement").performClick()

        composeTestRule.onNodeWithTag("textTotalPerPerson")
            .assertTextContains("Total por persona: $57.50")
    }
}
