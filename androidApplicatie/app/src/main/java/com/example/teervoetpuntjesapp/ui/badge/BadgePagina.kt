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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.teervoetpuntjesapp.ui.login.LoginResult
import com.example.teervoetpuntjesapp.ui.navigation.NavigationDestination
import com.example.teervoetpuntjesapp.ui.shared.ErrorScreen
import com.example.teervoetpuntjesapp.ui.shared.LoadingScreen
import com.example.teervoetpuntjesapp.ui.theme.quicksandFontFamily

/**
 * Klasse die de bestemming voor de Badgepagina voorstelt.
 */
object BadgeDetailsDestination : NavigationDestination {
    override val route = "badge_detail"
    override val titleRes = R.string.Badge_details_title
}
/**
 * Composable functie voor het weergeven van het detailscherm van een badge.
 *
 * Deze functie toont informatie over een specifieke badge, inclusief de afbeelding, titel en
 * een lijst van bijbehorende puntjes. De gebruiker kan terugkeren naar het vorige scherm
 * of de badge ondertekenen (indien ingelogd).
 *
 * @param id Het identificatienummer van de badge.
 * @param modifier Optionele modifier om de styling van de composable aan te passen.
 * @param onBackButtonClicked Functie die wordt aangeroepen wanneer de gebruiker op de terug-knop klikt.
 * @param onOndertekenClicked Functie die wordt aangeroepen wanneer de gebruiker op de onderteken-knop klikt.
 */
@Composable
fun BadgeScreen(
    id: Int,
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit,
    onOndertekenClicked: () -> Unit,
) {
    var viewModel: BadgePaginaViewModel = viewModel(factory = BadgePaginaViewModel.Factory)
    var userviewModel: LoginFormViewModel = viewModel(factory = LoginFormViewModel.Factory)
    val loginResult by userviewModel.loginResult.collectAsState()
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
            BadgeDetail(
                badge = badge,
                puntjes = puntjes,
                onBackButtonClicked = { onBackButtonClicked() },
                behaaldeBadges = behaaldebadges,
                loginResult = loginResult,
                onOndertekenClicked = { onOndertekenClicked() },
                onPuntjeClicked = { userviewModel.PuntjeClicked(it) },
            )
        }
        is BadgeUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is BadgeUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

/**
 * Composable functie voor het weergeven van de details van een badge.
 *
 * Deze functie toont de afbeelding, titel en een lijst van bijbehorende puntjes. De puntjes worden
 * weergegeven als PuntjesKaart composables. De gebruiker kan terugkeren naar het vorige scherm
 * of de badge ondertekenen (indien ingelogd).
 *
 * @param badge De Badge data class die de details van de badge bevat.
 * @param onBackButtonClicked Functie die wordt aangeroepen wanneer de gebruiker op de terug-knop klikt.
 * @param puntjes Lijst van Puntje data classes die de bijbehorende puntjes voorstellen.
 * @param behaaldeBadges Lijst van identificatienummers van de puntjes die de gebruiker al behaald heeft.
 * @param loginResult Resultaat om te weten of de gebruiker is ingelogd.
 * @param onOndertekenClicked Functie die wordt aangeroepen wanneer de gebruiker op de onderteken-knop klikt.
 * @param onPuntjeClicked Functie die wordt aangeroepen wanneer de gebruiker op een puntje klikt.
 */
@Composable
fun BadgeDetail(
    badge: Badge,
    onBackButtonClicked: () -> Unit,
    puntjes: List<Puntje>,
    behaaldeBadges: List<Int>,
    loginResult: LoginResult,
    onOndertekenClicked: () -> Unit,
    onPuntjeClicked: (Int) -> Unit,
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
                            onChecked = { onPuntjeClicked(puntje.id) },
                        )
                    } else {
                        PuntjesKaart(
                            puntje = puntje,
                            isDone = false,
                            onChecked = { onPuntjeClicked(puntje.id) },
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
                    if (loginResult is LoginResult.Success) {
                        Button(
                            onClick = {
                                onOndertekenClicked()
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
}
