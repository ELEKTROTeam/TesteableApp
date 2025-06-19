package com.example.testeableapp.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun TipCalculatorScreen() {
    var billAmount by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf(15) }
    var roundUp by remember { mutableStateOf(false) }
    var numberOfPeople by remember { mutableStateOf(1) }

    val bill = billAmount.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(bill, tipPercentage, roundUp)
    val totalPerPerson = if (numberOfPeople > 0) (bill + tip) / numberOfPeople else 0.0

    Column(Modifier.padding(32.dp)) {
        OutlinedTextField(
            value = billAmount,
            onValueChange = { billAmount = it },
            label = { Text("Monto de la cuenta") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().testTag("inputBill")
        )

        Slider(
            value = tipPercentage.toFloat(),
            onValueChange = { tipPercentage = it.toInt() },
            valueRange = 0f..50f,
            steps = 49,
            modifier = Modifier.fillMaxWidth().testTag("seekBarPercentage")
        )
        Text(modifier = Modifier.testTag("people"),text = "Número de personas: $numberOfPeople")
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(modifier = Modifier.testTag("peopleDecrease"),onClick = { if (numberOfPeople > 1) numberOfPeople-- }) {
                Text("-")
            }
      Text(numberOfPeople.toString(), modifier = Modifier.testTag("peopleCount"))
            Button(onClick = { numberOfPeople++ }, modifier = Modifier.testTag("peopleIncrement")) {
                Text("+")
            }
        }

        Row(Modifier.fillMaxWidth()) {
            Checkbox(
                checked = roundUp,
                onCheckedChange = { roundUp = it },
                modifier = Modifier.testTag("checkboxRoundUp")
            )
            Text("Redondear propina")
        }

        Text(
            "Propina: $${"%.2f".format(tip)}",
            modifier = Modifier.testTag("textTipResult")
        )
        Text(
            "Total por persona: $${"%.2f".format(totalPerPerson)}",
            modifier = Modifier.testTag("textTotalPerPerson")
        )
    }
}


fun calculateTip(amount: Double, tipPercent: Int, roundUp: Boolean): Double {
    var tip = amount * tipPercent / 100
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    return tip
}