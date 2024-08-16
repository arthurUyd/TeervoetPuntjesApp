package com.example.teervoetpuntjesapp.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.teervoetpuntjesapp.R
/**
 * Composable functie voor het weergeven van een laadscherm tijdens het laden van gegevens.
 *
 * @param modifier Optionele Modifier om de stijl van de composable aan te passen. (standaard: Modifier)
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = Modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading),
    )
}

/**
 * Composable functie voor het weergeven van een fout scherm bij netwerk- of andere fouten.
 *
 * @param modifier Optionele Modifier om de stijl van de composable aan te passen. (standaard: Modifier)
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painterResource(id = R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.connection_error_image),
        )
    }
}
