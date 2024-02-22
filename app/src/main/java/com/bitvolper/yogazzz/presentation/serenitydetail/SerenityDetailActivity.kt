package com.bitvolper.yogazzz.presentation.serenitydetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.bitvolper.yogazzz.base.BaseActivity
import com.bitvolper.yogazzz.presentation.categorydetail.CategoryDetailApp
import com.bitvolper.yogazzz.presentation.theme.YogaAppTheme
import com.bitvolper.yogazzz.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SerenityDetailActivity: BaseActivity() {

    companion object {
        fun startActivity(activity: Activity?) {
            val intent = Intent(activity, SerenityDetailActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()

        homeViewModel.getYogaExercise()
        homeViewModel.getBookmarkYogaExercise()

        setContent {
            YogaAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SerenityDetailApp()
                }
            }
        }
    }
}