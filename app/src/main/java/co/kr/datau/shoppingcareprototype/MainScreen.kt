package co.kr.datau.shoppingcareprototype

import android.content.Intent
import android.graphics.Shader
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val verticalScroll = rememberScrollState()
    val infiniteTransition = rememberInfiniteTransition()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current

    var boxSize by remember { mutableStateOf(Size.Zero) }
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val fadeAnim = remember { Animatable(0f) }
    val offsetY = remember { Animatable(200f) }
    val widthAnim = remember { Animatable(50f) }
    val targetWidth = (LocalConfiguration.current.screenWidthDp.dp) * 0.37f

    LaunchedEffect(Unit) {
        offsetY.snapTo(200f)
        fadeAnim.snapTo(0f)
        widthAnim.snapTo(50f)

        offsetY.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
        )
        widthAnim.animateTo(
            targetValue = targetWidth.value,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
        fadeAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 300)
        )
    }


    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(verticalScroll)
        ) {
            Image(
                painter = painterResource(R.drawable.topbar),
                contentDescription = null
            )
            Spacer(modifier = modifier.size(30.dp))

            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                painter = painterResource(R.drawable.hello),
                contentDescription = null
            )
            Spacer(modifier = modifier.size(30.dp))

            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.End)
                    .padding(start = 20.dp),
                painter = painterResource(R.drawable.search),
                contentDescription = null
            )
            Spacer(modifier = modifier.size(30.dp))

            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .onSizeChanged {
                        boxSize = Size(
                            width = it.width.toFloat(),
                            height = it.height.toFloat()
                        )
                    }
                    .padding(horizontal = 20.dp)
                    .border(
                        BorderStroke(
                            width = 2.dp,
                            brush = if (boxSize != Size.Zero) {
                                Brush.sweepGradient(
                                    colors = listOf(Color(0xFF42DEE5), Color(0xFFD6FF76)),
                                    center = Offset(boxSize.width / 2f, boxSize.height / 2f)
                                ).rotate(rotation)
                            } else {
                                SolidColor(Color.Transparent)
                            },
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ),
                painter = painterResource(R.drawable.banner_01),
                contentDescription = null
            )
            Spacer(modifier = modifier.size(38.dp))

            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                painter = painterResource(R.drawable.con_01),
                contentDescription = null
            )
            Spacer(modifier = modifier.size(35.dp))

            Column(
                modifier = modifier
                    .background(color = Color(0xFFF3F3F3))
                    .padding(vertical = 30.dp, horizontal = 20.dp)
            ) {
                Image(
                    modifier = modifier
                        .fillMaxWidth(),
                    painter = painterResource(R.drawable.text_my_shopping),
                    contentDescription = null
                )
                Spacer(modifier = modifier.size(10.dp))

                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(context, ReportActivity::class.java)
                            context.startActivity(intent)
                        },
                    painter = painterResource(R.drawable.button_01),
                    contentDescription = null
                )
                Spacer(modifier = modifier.size(10.dp))

                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(context, ReportActivity::class.java)
                            context.startActivity(intent)
                        },
                    painter = painterResource(R.drawable.button_02),
                    contentDescription = null
                )
                Spacer(modifier = modifier.size(40.dp))

                Image(
                    modifier = modifier
                        .fillMaxWidth(),
                    painter = painterResource(R.drawable.frame_01),
                    contentDescription = null
                )
            }
            Spacer(modifier = modifier.size(35.dp))
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                painter = painterResource(R.drawable.banner_02),
                contentDescription = null
            )
            Spacer(modifier = modifier.size(35.dp))

        }

        Box(
            modifier = modifier
                .fillMaxWidth(0.37f)
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 10.dp)
        ) {
            Box(
                modifier = modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = offsetY.value.dp)
                    .width(widthAnim.value.dp)
                    .background(
                        shape = RoundedCornerShape(14.dp),
                        color = Color(0xFF111111).copy(0.08f)
                    )
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = modifier
                        .background(
                            shape = RoundedCornerShape(14.dp),
                            color = Color(0xFF111111)
                        )
                        .padding(vertical = 11.dp, horizontal = 12.dp)
                ) {
                    Image(
                        modifier = modifier
                            .graphicsLayer {
                                alpha = fadeAnim.value
                            },
                        painter = painterResource(R.drawable.txt),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

// rotate 확장 함수
fun Brush.rotate(degrees: Float): Brush {
    return object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val matrix = android.graphics.Matrix().apply {
                setRotate(degrees, size.width / 2f, size.height / 2f)
            }
            val shader = (this@rotate as ShaderBrush).createShader(size)
            shader.setLocalMatrix(matrix)
            return shader
        }
    }
}