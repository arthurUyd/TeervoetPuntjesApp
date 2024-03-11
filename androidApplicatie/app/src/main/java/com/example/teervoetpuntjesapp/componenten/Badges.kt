package com.example.teervoetpuntjesapp.componenten

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.data.AppViewModel
import com.example.teervoetpuntjesapp.ui.theme.quicksandFontFamily

@Composable
fun BadgeLijst(
    badges: List<Badge>,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier.padding(all = 14.dp).fillMaxSize(),
    ) {
        items(badges) {
            BadgeCard(badge = it, modifier = Modifier.padding(6.dp), onClick = {
                navController.navigate("puntjes/{badge}")
            })
        }
    }
}

@Composable
fun BadgeCard(
    badge: Badge,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp).background(MaterialTheme.colorScheme.primaryContainer).padding(8.dp)
                .clickable { onClick() },
        ) {
            AsyncImage(
                model = badge.image_url,
                contentDescription = "badge icon",
                modifier = Modifier.size(100.dp),
            )
            Text(
                text = badge.titel,
                fontFamily = quicksandFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}
