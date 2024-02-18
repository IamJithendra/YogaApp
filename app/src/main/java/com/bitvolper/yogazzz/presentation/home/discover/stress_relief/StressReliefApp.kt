package com.bitvolper.yogazzz.presentation.home.discover.stress_relief

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.bitvolper.yogazzz.presentation.viewmodel.DiscoverViewModel

@Composable
fun StressReliefApp(modifier: Modifier = Modifier, discoverViewModel: DiscoverViewModel = hiltViewModel()) {

    val stressReliefUIState by discoverViewModel.stressRelief.collectAsState()

    Scaffold(
        topBar = { StressReliefTopAppBar() }
    ) { paddingValues ->
        StressReliefScreen(paddingValues = paddingValues, stressReliefUIState = stressReliefUIState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StressReliefTopAppBar() {
    val context = LocalContext.current

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Stress Relief",
                fontWeight = FontWeight.SemiBold)
        },
        navigationIcon = {
            IconButton(onClick = { (context as Activity).finish() }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }
        }
    )
}

