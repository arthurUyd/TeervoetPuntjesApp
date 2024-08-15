package com.example.teervoetpuntjesapp.ui.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.R
import com.example.teervoetpuntjesapp.componenten.PuntjesKaart
import com.example.teervoetpuntjesapp.ui.login.LoginFormViewModel
import com.example.teervoetpuntjesapp.ui.navigation.NavigationDestination
import com.example.teervoetpuntjesapp.ui.shared.ErrorScreen
import com.example.teervoetpuntjesapp.ui.shared.LoadingScreen
import com.example.teervoetpuntjesapp.ui.theme.quicksandFontFamily

object BadgeDetailsDestination : NavigationDestination {
    override val route = "badge_detail"
    override val titleRes = R.string.Badge_details_title
}

@Composable
fun BadgeScreen(
    id: Int,
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit,
) {
    var viewModel: BadgePaginaViewModel = viewModel(factory = BadgePaginaViewModel.Factory)
    var userviewModel: LoginFormViewModel = viewModel(factory = LoginFormViewModel.Factory)

    val behaaldebadges = userviewModel.badges

    val badgeUiState = viewModel.badgeUiState

    LaunchedEffect(id) {
        viewModel.getBadge(id)
        userviewModel.getBadges()
    }

    when (badgeUiState) {
        is BadgeUiState.Success -> {
            val badge = badgeUiState.badge
            val puntjes = badgeUiState.puntjes
            BadgeDetail(badge = badge, puntjes = puntjes, onBackButtonClicked = { onBackButtonClicked() }, behaaldeBadges = behaaldebadges)
        }
        is BadgeUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is BadgeUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun BadgeDetail(
    badge: Badge,
    onBackButtonClicked: () -> Unit,
    puntjes: List<Puntje>,
    behaaldeBadges: List<Int>,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(14.dp)
            .verticalScroll(rememberScrollState()).fillMaxSize(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(badge.image_url)
                .crossfade(true)
                .build(),
            contentDescription = "logo van de badge",
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
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
                puntjes.forEach { puntje ->
                    if (behaaldeBadges.contains(puntje.id)) {
                        PuntjesKaart(
                            puntje = puntje,
                            isDone = true,
                            onChecked = { },
                        )
                    } else {
                        PuntjesKaart(
                            puntje = puntje,
                            isDone = false,
                            onChecked = { },
                        )
                    }
                }
                Row {
                    Button(
                        onClick = {
                            onBackButtonClicked()
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
                        onClick = {
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
    }
}
