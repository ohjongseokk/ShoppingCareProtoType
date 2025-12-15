package co.kr.datau.shoppingcareprototype

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import co.kr.datau.shoppingcareprototype.ui.theme.ShoppingCareProtoTypeTheme

class SearchActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingCareProtoTypeTheme {
                SearchScreen(activity = this)
            }
        }
    }
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    activity: ComponentActivity
) {
    val verticalScroll = rememberScrollState()
    var selectedType by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier
                    .size(24.dp)
                    .clickable {
                        activity.finish()
                    },
                painter = painterResource(R.drawable.close),
                contentDescription = null
            )
            Spacer(modifier.size(10.dp))
            Image(
                modifier = modifier.height(17.dp),
                painter = painterResource(R.drawable.search_text),
                contentDescription = null
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier
                    .height(41.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        selectedType = 0
                    },
                painter = painterResource(if (selectedType == 0) R.drawable.name_on else R.drawable.name_off),
                contentDescription = null
            )
            Image(
                modifier = modifier
                    .height(41.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        selectedType = 1
                    },
                painter = painterResource(if (selectedType == 1) R.drawable.date_on else R.drawable.date_off),
                contentDescription = null
            )
        }

        AnimatedContent(
            targetState = selectedType,
            transitionSpec = {
                if (targetState > initialState) {
                    // 오른쪽으로 이동 (왼→오로 들어옴)
                    slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut()
                } else {
                    // 왼쪽으로 이동 (오→왼으로 들어옴)
                    slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut()
                }
            }
        ) { screen ->
            when (screen) {
                0 -> SearchByNameScreen()
                1 -> SearchByDateScreen()
            }
        }

    }
}

@Composable
fun SearchByNameScreen(
    modifier: Modifier = Modifier,
) {
    val activity = LocalActivity.current
    var startSearchBar by remember { mutableStateOf(false) }
    var startKeyword by remember { mutableStateOf(false) }
    val offsetY by animateDpAsState(
        targetValue = if (startSearchBar) 0.dp else 100.dp,
        animationSpec = tween(durationMillis = 600),
        label = "offset_anim"
    )

    LaunchedEffect(Unit) {
        startSearchBar = true
    }

    LaunchedEffect(offsetY.value) {
        if (offsetY.value == 0f) {
            startKeyword = true
        }
    }

    val alpha by animateFloatAsState(
        targetValue = if (startKeyword) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "alpha_anim"
    )

    val defaultDp = 40.dp
    val gapDp = 30.dp

    val offsetX1 by animateDpAsState(
        targetValue = if (startKeyword) 0.dp else defaultDp + gapDp * 1,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )
    val offsetX2 by animateDpAsState(
        targetValue = if (startKeyword) 0.dp else defaultDp + gapDp * 2,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )
    val offsetX3 by animateDpAsState(
        targetValue = if (startKeyword) 0.dp else defaultDp + gapDp * 3,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )
    val offsetX4 by animateDpAsState(
        targetValue = if (startKeyword) 0.dp else defaultDp + gapDp * 4,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )
    val offsetX5 by animateDpAsState(
        targetValue = if (startKeyword) 0.dp else defaultDp + gapDp * 5,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .offset(y = offsetY),
            painter = painterResource(R.drawable.search_search),
            contentDescription = null
        )

        Spacer(modifier.size(17.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Image(
                modifier = modifier
                    .height(45.dp)
                    .alpha(alpha)
                    .offset(x = offsetX1),
                painter = painterResource(R.drawable.search_prev_text),
                contentDescription = null
            )
            Image(
                modifier = modifier
                    .height(45.dp)
                    .alpha(alpha)
                    .offset(x = offsetX2),
                painter = painterResource(R.drawable.search_prev_text),
                contentDescription = null
            )
            Image(
                modifier = modifier
                    .height(45.dp)
                    .alpha(alpha)
                    .offset(x = offsetX3),
                painter = painterResource(R.drawable.search_prev_text),
                contentDescription = null
            )
            Image(
                modifier = modifier
                    .height(45.dp)
                    .alpha(alpha)
                    .offset(x = offsetX4),
                painter = painterResource(R.drawable.search_prev_text),
                contentDescription = null
            )
            Image(
                modifier = modifier
                    .height(45.dp)
                    .alpha(alpha)
                    .offset(x = offsetX5),
                painter = painterResource(R.drawable.search_prev_text),
                contentDescription = null
            )
        }

        Spacer(modifier.weight(1f))

        Image(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    activity?.startActivity(Intent(activity, EmptyActivity::class.java))
                },
            painter = painterResource(R.drawable.search_btn),
            contentDescription = null
        )
    }
}

@Composable
fun SearchByDateScreen(
    modifier: Modifier = Modifier,
) {
    var isShowCalendar by remember { mutableStateOf(false) }
    var isSelectedDate by remember { mutableStateOf(false) }
    val activity = LocalActivity.current

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier.size(42.dp))

        Image(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isShowCalendar = true
                }
                .padding(horizontal = 20.dp),
            painter = painterResource(if (isSelectedDate) R.drawable.ca_01 else R.drawable.search_date_01),
            contentDescription = null
        )

        Spacer(modifier.weight(1f))

        Image(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (isSelectedDate) {
                        activity?.startActivity(Intent(activity, EmptyActivity::class.java))
                    }
                }
                .padding(vertical = 10.dp, horizontal = 20.dp),
            painter = painterResource(if (isSelectedDate) R.drawable.search_btn else R.drawable.search_btn_off),
            contentDescription = null
        )
    }

    if (isShowCalendar) {
        Dialog(
            onDismissRequest = {
                isShowCalendar = false
            }
        ) {
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        isSelectedDate = true
                        isShowCalendar = false
                    },
                painter = painterResource(R.drawable.ca),
                contentDescription = null
            )
        }

    }
}