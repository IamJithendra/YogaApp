package com.bitvolper.yogazzz.presentation.accountsetup

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.bitvolper.yogazzz.R
import com.bitvolper.yogazzz.presentation.theme.YogaAppTheme
import com.bitvolper.yogazzz.utility.AccountSetupContinueComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectLegRaiseDurationScreen(modifier: Modifier = Modifier,
                                 paddingValues: PaddingValues = PaddingValues(),
                                 onSkipClick: () -> Unit = {  },
                                 onContinueClick: () -> Unit = { }) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(
            top = 16.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "How Many Leg Raises Can You Do at One Time?", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.SemiBold)

        Text(text = "Let's talk about lower body strength", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = modifier.weight(1f))

        Image(painter = painterResource(id = R.drawable.ic_leg_raise), contentDescription = null, modifier = modifier.size(300.dp), contentScale = ContentScale.Crop)

        Spacer(modifier = modifier.weight(1f))

        Slider(value = 2f,
            onValueChange = { },
            track = { sliderPositions ->
                SliderDefaults.Track(
                    modifier = Modifier
                        .scale(scaleX = 1f, scaleY = 3f)
                        .clip(RoundedCornerShape(100)),
                    sliderPositions = sliderPositions,
                    colors = SliderDefaults.colors(
                        activeTrackColor = Color.Transparent,
                        activeTickColor = MaterialTheme.colorScheme.outline,
                        inactiveTickColor = MaterialTheme.colorScheme.outline,
                    )
                )
            },
            thumb = { sliderPositions ->
                SliderDefaults.Thumb(
                    interactionSource = remember { MutableInteractionSource() },
                    thumbSize = DpSize(30.dp, 30.dp)
                )
            },
            steps = 3,
            valueRange = 0f..2f,
        )

        AccountSetupContinueComposable(
            onSkipClick = onSkipClick, 
            onContinueClick = onContinueClick
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SelectLegRaiseDurationLightThemePreview() {
    YogaAppTheme(darkTheme = false) {
        SelectLegRaiseDurationScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SelectLegRaiseDurationDarkThemePreview() {
    YogaAppTheme(darkTheme = true) {
        SelectLegRaiseDurationScreen()
    }
}