package com.example.teervoetpuntjesapp.ui.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.teervoetpuntjesapp.R
import com.example.teervoetpuntjesapp.ui.navigation.NavigationDestination
import com.example.teervoetpuntjesapp.ui.theme.quicksandFontFamily

object BadgeDetailsDestination : NavigationDestination {
    override val route = "badge_detail"
    override val titleRes = R.string.Badge_details_title
}

@Composable
fun BadgePagina(
    badgeId: Int = 1,
    viewModel: BadgePaginaViewModel = viewModel(factory = BadgePaginaViewModel.Factory),
) {
    val badge by viewModel.badge.collectAsState()

    LaunchedEffect(badge) {
        viewModel.getBadge(badgeId)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(14.dp)
            .verticalScroll(rememberScrollState()).fillMaxSize(),
    ) {
        AsyncImage(
            model = badge.image_url,
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
            Column(
                modifier = Modifier.padding(15.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
            ) {
//                filteredPuntjes.forEach {
//                    if (hasValue(viewModel, it.id)) {
//                        PuntjesKaart(
//                            puntje = it,
//                            isDone = true,
//                            onChecked = { viewModel.behaaldePuntjes.add(it.id) },
//                        )
//                    } else {
//                        PuntjesKaart(
//                            puntje = it,
//                            isDone = false,
//                            onChecked = { viewModel.behaaldePuntjes.add(it.id) },
//                        )
//                    }
            }
            Row {
                Button(
                    onClick = {
//                            viewModel.behaaldePuntjes.clear()
                        // {TODO}
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
                    onClick = { // {TODO}
                    },
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
    AsyncImage(
        model = badge?.image_url,
        contentDescription = "badge icon",
        modifier = Modifier.size(200.dp),
    )
    AsyncImage(
        model = badge?.image_url,
        contentDescription = "badge icon",
        modifier = Modifier.size(200.dp),
    )
}

// fun hasValue(viewModel: AppViewModel, id: Int): Boolean {
//    viewModel.huidigeGebruiker.value?.puntjes?.forEach {
//        if (it.punt_id == id) return true
//    }
//    return false
// }
