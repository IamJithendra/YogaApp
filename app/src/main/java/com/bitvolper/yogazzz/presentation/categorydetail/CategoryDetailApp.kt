package com.bitvolper.yogazzz.presentation.categorydetail

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CategoryDetailApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CategoryTopAppBar()
        }
    ) { paddingValues ->
        CategoryDetailScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryTopAppBar() {
    val context = LocalContext.current

    CenterAlignedTopAppBar(
        title = {

        },
        navigationIcon = {
            IconButton(onClick = { (context as Activity).finish() }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
    )
}