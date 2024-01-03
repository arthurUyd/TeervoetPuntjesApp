package com.example.teervoetpuntjesapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.componenten.PuntjesKaart
import com.example.teervoetpuntjesapp.data.AppViewModel
import com.example.teervoetpuntjesapp.ui.theme.quicksandFontFamily

@Composable
fun BadgePagina(
    badge: Badge?,
    viewModel: AppViewModel,
    navController: NavController,
) {
    val puntjes by viewModel.puntjes
    LaunchedEffect(puntjes) {
        viewModel.getPuntjes()
    }
    var filteredPuntjes = puntjes.filter { it.badge_id == badge?.id ?: null }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(14.dp),
    ) {
        AsyncImage(
            model = badge?.image_url,
            contentDescription = "badge icon",
            modifier = Modifier.size(200.dp),
        )
        badge?.titel?.let {
            Text(
                text = it,
                fontFamily = quicksandFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.fillMaxWidth(),
        ) {
            LazyColumn(
                modifier = Modifier.padding(15.dp).background(MaterialTheme.colorScheme.primaryContainer),
            ) {
                items(filteredPuntjes) {
                    if (hasValue(viewModel, it.id)) {
                        PuntjesKaart(puntje = it, isDone = true, onChecked = { viewModel.behaaldePuntjes.add(it.id) })
                    } else {
                        PuntjesKaart(puntje = it, isDone = false, onChecked = { viewModel.behaaldePuntjes.add(it.id) })
                    }
                }
                item {
                    Row {
                        Button(
                            onClick = { 
                                viewModel.behaaldePuntjes.clear()
                                navController.navigate("home") 
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            ),
                        ) {
                            Text(
                                text = "Terug",
                                fontFamily = quicksandFontFamily,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = { navController.navigate("leidingLogin") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            ),
                        ) {
                            Text(
                                text = "Onderteken",
                                fontFamily = quicksandFontFamily,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }
        }
    }
}

fun hasValue(viewModel: AppViewModel, id: Int): Boolean {
    viewModel.huidigeGebruiker.value?.puntjes?.forEach {
        if (it.punt_id == id) return true
    }
    return false
}
