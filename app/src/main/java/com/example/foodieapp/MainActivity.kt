package com.example.foodieapp

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodieapp.data.FoodhubSession
import com.example.foodieapp.ui.features.auth.AuthScreen
import com.example.foodieapp.ui.features.auth.signin.SignInScreen
import com.example.foodieapp.ui.features.auth.signup.SignUpScreen
import com.example.foodieapp.ui.features.home.HomeScreen
import com.example.foodieapp.ui.theme.FoodieAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.foodieapp.ui.features.navigation.AuthScreen
import com.example.foodieapp.ui.features.navigation.SignUp
import com.example.foodieapp.ui.features.navigation.Login
import com.example.foodieapp.ui.features.navigation.Home
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var showSplashScreen = true
    @Inject
    lateinit var session: FoodhubSession
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                showSplashScreen
            }
            setOnExitAnimationListener{screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.5f,
                    0f
                )
                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.5f,
                    0f
                )
                zoomX.duration = 500
                zoomY.duration = 500
                zoomX.interpolator = OvershootInterpolator()
                zoomY.interpolator = OvershootInterpolator()
                zoomX.doOnEnd {
                    screen.remove()
                }
                zoomX.doOnEnd {
                    screen.remove()
                }
                zoomX.start()
                zoomY.start()
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodieAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = if(session.getToken() != null) Home else AuthScreen,
                    enterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(300)) + fadeIn(animationSpec = tween(300))
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(300)) + fadeOut(animationSpec = tween(300))
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(300)) + fadeIn(animationSpec = tween(300))
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(300)) + fadeOut(animationSpec = tween(300))
                    }
                ){
                    composable<SignUp> {
                        SignUpScreen(navController = navController)
                    }
                    composable<AuthScreen> {
                        AuthScreen(navController = navController)
                    }
                    composable<Login> {
                        SignInScreen(navController = navController)
                    }
                    composable<Home> {
                        HomeScreen(navController = navController)
                    }
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            showSplashScreen = false
        }
    }
}
