package co.kr.datau.shoppingcareprototype

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.LocalActivity
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("ConfigurationScreenWidthHeight")
@OptIn(UnstableApi::class)
@Composable
fun ReportScreen(
    modifier: Modifier = Modifier
) {
    val verticalScroll = rememberScrollState()
    val context = LocalContext.current
    val activity = LocalActivity.current
    val density = LocalDensity.current
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(R.raw.back.getResourceUri(context)))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }


//    val rotationAnim = rememberInfiniteTransition().animateFloat(
//        initialValue = 0f,
//        targetValue = 360f,
//        animationSpec = infiniteRepeatable(
//            animation = keyframes {
//                durationMillis = 3000
//                0f at 0
//                360f at 2000
//                360f at 3000
//            },
//            repeatMode = RepeatMode.Restart
//        )
//    )

    val rotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            rotation.animateTo(
                targetValue = rotation.value + 360f,
                animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
            )

            delay(1000)
        }
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color(0xFF1B1B1B)
            )
    ) {
        AndroidView(
            modifier = modifier
                .fillMaxWidth()
                .height(screenHeight),
            factory = { context ->
                PlayerView(context).apply {
                    this.player = exoPlayer
                    this.overlayFrameLayout
                    this.useController = false
                    this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
            }
        )

        Column {
            Image(
                modifier = modifier
                    .clickable {
                        activity?.finish()
                    },
                painter = painterResource(R.drawable.top),
                contentDescription = null
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScroll)
            ) {
                Column(
                    modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = modifier.size(70.dp))
                    Image(
                        modifier = modifier.fillMaxWidth(),
                        painter = painterResource(R.drawable.tit),
                        contentDescription = null
                    )


                    Spacer(modifier = modifier.size(33.dp))

                    Image(
                        modifier = modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                rotationY = rotation.value
                                cameraDistance = 12 * density.density
                            },
                        painter = painterResource(R.drawable.card),
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.size(14.dp))

                    Image(
                        modifier = modifier
                            .fillMaxWidth(),
                        painter = painterResource(R.drawable.con01),
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.size(62.dp))
                }

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            color = Color(0xFF1B1B1B)
                        )
                        .padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = modifier.size(90.dp))
                    Image(
                        modifier = modifier
                            .fillMaxWidth(),
                        painter = painterResource(R.drawable.tit_sub_01),
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.size(36.dp))

                    ScrollAppearImage(R.drawable.rank1)
                    Spacer(modifier = modifier.size(36.dp))
                    ScrollAppearImage(R.drawable.rank2)
                    Spacer(modifier = modifier.size(36.dp))
                    ScrollAppearImage(R.drawable.rank3)
                    Spacer(modifier = modifier.size(80.dp))

                    Image(
                        modifier = modifier
                            .fillMaxWidth(),
                        painter = painterResource(R.drawable.tit_sub_2),
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.size(30.dp))
                    Image(
                        modifier = modifier
                            .fillMaxWidth(),
                        painter = painterResource(R.drawable.fram1),
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.size(50.dp))
                }
            }
        }
    }
}

fun Int.getResourceUri(context: Context): Uri = Uri.parse("android.resource://${context.packageName}/$this")


@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ScrollAppearImage(
    painter: Int,
    modifier: Modifier = Modifier,
    verticalOffset: Float = 70f
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    var visible by remember { mutableStateOf(false) }

    val scaleAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0.8f,
        animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing)
    )

    val alphaAnim = animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    val offsetYAnim = animateFloatAsState(
        targetValue = if (visible) 0f else verticalOffset,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    Image(
        painter = painterResource(id = painter),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer(
                scaleX = scaleAnim.value,
                scaleY = scaleAnim.value,
                alpha = alphaAnim.value,
                translationY = with(density) { offsetYAnim.value.dp.toPx() }
            )
            .onGloballyPositioned { coordinates ->
                val position = coordinates.positionInWindow().y
                val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }
                val triggerPosition = screenHeightPx * 0.9f
                if (!visible && position < triggerPosition) {
                    visible = true
                }
            }
    )
}