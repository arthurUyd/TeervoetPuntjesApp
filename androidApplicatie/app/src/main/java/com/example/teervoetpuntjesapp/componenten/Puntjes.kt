package com.example.teervoetpuntjesapp.componenten

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.ui.theme.quicksandFontFamily
/**
 * Een composable die een kaart weergeeft voor een puntje, met een checkbox om het als voltooid te markeren.
 *
 * @param puntje Het Puntje-object.
 * @param isDone Een Boolean waarde die aangeeft of het puntje al voltooid is.
 * @param onChecked Een callback-functie die wordt aangeroepen wanneer het checkboxje wordt aangevinkt of uitgevinkt.
 */
@Composable
fun PuntjesKaart(
    puntje: Puntje,
    isDone: Boolean,
    onChecked: () -> Unit,
) {
    var checkedState = remember { mutableStateOf(isDone) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onChecked()
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.secondaryContainer,
                uncheckedColor = MaterialTheme.colorScheme.secondaryContainer,
            ),

        )
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = puntje.titel,
                fontFamily = quicksandFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(top = 2.dp, start = 5.dp),
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
    }
}
