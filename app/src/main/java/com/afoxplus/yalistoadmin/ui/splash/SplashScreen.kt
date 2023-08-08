package com.afoxplus.yalistoadmin.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afoxplus.yalistoadmin.R

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateTo: () -> Unit
) {

    val scale: Animatable<Float, AnimationVector1D> = remember { Animatable(0f) }

    val isNavigate by viewModel.isNavigate.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.states()
        scale.animateTo(targetValue = 1f, animationSpec = tween(
            durationMillis = 500,
            easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            }
        ))
    }

    LaunchedEffect(key1 = isNavigate) {
        if (isNavigate) {
            navigateTo()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.yalisto_logo),
            contentDescription = "yalisto_logo",
            modifier = Modifier.scale(scale.value)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navigateTo = { })
}
