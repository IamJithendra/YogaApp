package com.bitvolper.yogazzz.presentation.home.discover.flexiblity_strength

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.bitvolper.yogazzz.base.BaseActivity
import com.bitvolper.yogazzz.presentation.home.discover.adjust_yoga.AdjustYogaApp
import com.bitvolper.yogazzz.presentation.theme.YogaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlexibilityStrengthActivity: BaseActivity() {

    companion object {
        fun startActivity(activity: Activity?) {
            val intent = Intent(activity, FlexibilityStrengthActivity::class.java)
            activity?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()
        setContent {
            YogaAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlexibilityStrengthApp()
                }
            }
        }
    }
}