package co.kr.datau.shoppingcareprototype

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.datau.shoppingcareprototype.ui.theme.ShoppingCareProtoTypeTheme

class SkeletonActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingCareProtoTypeTheme {
                SkeletonScreen(activity = this)
            }
        }
    }
}

@Composable
fun SkeletonScreen(
    modifier: Modifier = Modifier,
    activity: ComponentActivity
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier
                    .size(36.dp),
                painter = painterResource(R.drawable.main_logo),
                contentDescription = null
            )
            Spacer(modifier.weight(1f))
            Image(
                modifier = modifier.size(24.dp),
                painter = painterResource(R.drawable.main_noti),
                contentDescription = null
            )
            Spacer(modifier.size(10.dp))
            Image(
                modifier = modifier.size(24.dp),
                painter = painterResource(R.drawable.main_menu),
                contentDescription = null
            )
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            SkeletonBox(
                modifier = Modifier
                    .size(width = 100.dp, height = 26.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier.size(10.dp))
            SkeletonBox(
                modifier = Modifier
                    .size(width = 270.dp, height = 26.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier.size(29.dp))

            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Spacer(modifier.size(24.dp))

            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(276.dp)
                    .clip(RoundedCornerShape(30.dp))
            )
            Spacer(modifier.size(33.dp))

            SkeletonBox(
                modifier = Modifier
                    .size(width = 109.dp, height = 25.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier.size(7.dp))

            Row {
                SkeletonBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(123.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                Spacer(modifier.size(8.dp))
                SkeletonBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(123.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
            Spacer(modifier.size(11.dp))
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
        }
    }
}

/**
 * 기본 스켈레톤 박스
 */
@Composable
fun SkeletonBox(
    modifier: Modifier = Modifier,
    brush: Brush = shimmerBrush()
) {
    Box(
        modifier = modifier.background(brush)
    )
}

/**
 * Shimmer 애니메이션 효과를 위한 Brush 생성
 */
@Composable
fun shimmerBrush(): Brush {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim - 1000f, translateAnim - 1000f),
        end = Offset(translateAnim, translateAnim)
    )
}
