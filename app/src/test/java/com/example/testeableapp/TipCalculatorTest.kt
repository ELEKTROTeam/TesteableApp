package com.example.testeableapp

import org.junit.Assert.assertEquals
import org.junit.Test
import com.example.testeableapp.ui.Screens.calculateTip


class TipCalculatorTest {

    @Test
    fun `Calculate 20% tip`() {
        // Arrange
        val amount = 100.0
        val tipPercent = 20
        val roundUp = false

        // Act
        val tip = calculateTip(amount, tipPercent, roundUp)

        // Assert
        assertEquals(20.0, tip, 0.001)
    }

    @Test
    fun `Calculate 15% tip and round up`() {
        // Arrange
        val amount = 99.99
        val tipPercent = 15
        val roundUp = true

        // Act
        val tip = calculateTip(amount, tipPercent, roundUp)

        // Assert
        assertEquals(15.0, tip, 0.001)
    }

    @Test
    fun `Calculate negative amount, expected 0`() {
        // Arrange
        val amount = -50.0
        val tipPercent = 20
        val roundUp = false

        // Act
        val tip = calculateTip(amount, tipPercent, roundUp)

        // Assert
        assertEquals(0.0, tip, 0.001)

    }

    @Test
    fun `Calculate total per person (include tip)`() {
        // Arrange
        val amount = 120.0
        val tipPercent = 10
        val roundUp = false
        val numberOfPeople = 3

        // Act
        val tip = calculateTip(amount, tipPercent, roundUp)
        val totalPerPerson = if (numberOfPeople > 0) (amount + tip) / numberOfPeople else 0.0

        // Assert
        assertEquals(44.0, totalPerPerson, 0.001)
    }
}
