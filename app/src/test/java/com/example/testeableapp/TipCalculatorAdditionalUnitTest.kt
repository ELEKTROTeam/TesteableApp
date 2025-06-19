package com.example.testeableapp

import com.example.testeableapp.ui.Screens.calculateTip
import org.junit.Test
import kotlin.test.assertEquals

class TipCalculatorAdditionalUnitTest {

    @Test
    fun `Total per person with zero people`() {
        val bill = 100.0
        val tipPercentage = 15
        val roundUp = false
        val numberOfPeople = 0
        val tip = calculateTip(bill, tipPercentage, roundUp)
        val totalPerPerson = if (numberOfPeople > 0) (bill + tip) / numberOfPeople else 0.0
        assertEquals(0.0, totalPerPerson, 0.0001)
    }

    @Test
    fun `Tip calculation with 150 percent`() {
        val bill = 50.0
        val tipPercentage = 150
        val roundUp = false
        val tip = calculateTip(bill, tipPercentage, roundUp)
        assertEquals(75.0, tip, 0.0001)
    }
}