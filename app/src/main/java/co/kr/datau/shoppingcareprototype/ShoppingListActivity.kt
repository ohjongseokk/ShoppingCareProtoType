package co.kr.datau.shoppingcareprototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.datau.shoppingcareprototype.ui.theme.ShoppingCareProtoTypeTheme

class ShoppingListActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingCareProtoTypeTheme {
                ShoppingListScreen(
                    selectedType = intent?.getIntExtra("selectedType", 0) ?: 0
                )
            }
        }
    }
}

@Composable
fun ShoppingListScreen(
    modifier: Modifier = Modifier,
    selectedType: Int = 0
) {
    val activity = LocalActivity.current
    var selectedType by remember { mutableStateOf(selectedType) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
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
                        activity?.finish()
                    },
                painter = painterResource(R.drawable.close),
                contentDescription = null
            )
            Spacer(modifier.size(10.dp))
            Image(
                modifier = modifier.height(26.dp),
                painter = painterResource(R.drawable.shopping_text),
                contentDescription = null
            )
            Spacer(modifier.weight(1f))

            Image(
                modifier = modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        selectedType = 0
                    },
                painter = painterResource(if (selectedType == 0) R.drawable.calendar_on else R.drawable.calendar_off),
                contentDescription = null
            )
            Spacer(
                modifier = modifier
                    .padding(horizontal = 8.dp)
                    .size(height = 18.dp, width = 1.dp)
                    .background(color = Color(0xFFBFBFBF))
            )
            Image(
                modifier = modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        selectedType = 1
                    },
                painter = painterResource(if (selectedType == 1) R.drawable.list_on else R.drawable.list_off),
                contentDescription = null
            )
        }
        Image(
            modifier = modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.note02),
            contentDescription = null
        )


        Box(
            modifier = modifier.fillMaxSize()
        ) {
            if (selectedType == 0) {
                ShoppingListCalendarScreen()
            } else {
                ShoppingListListScreen()
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
//                        .offset(y = offsetY.value.dp)
//                        .width(widthAnim.value.dp)
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
                            .clickable {
//                                isOpenBottomSheet = true
                            }
                            .padding(vertical = 11.dp, horizontal = 12.dp)
                    ) {
                        Image(
//                            modifier = modifier
//                                .graphicsLayer {
//                                    alpha = fadeAnim.value
//                                },
                            painter = painterResource(R.drawable.txt),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShoppingListCalendarScreen(
    modifier: Modifier = Modifier
) {
    val verticalScroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(verticalScroll)
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 20.dp, end = 20.dp, bottom = 4.dp),
            painter = painterResource(R.drawable.frame_427319739),
            contentDescription = null
        )

        Image(
            modifier = modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.group_27),
            contentDescription = null
        )

    }
}

@Composable
fun ShoppingListListScreen(
    modifier: Modifier = Modifier
) {
    val verticalScroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(verticalScroll)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(horizontal = 30.dp, vertical = 14.dp)
        ) {
            Image(
                modifier = modifier.height(24.dp),
                painter = painterResource(R.drawable.gr_22),
                contentDescription = null
            )
        }

        Column(
            modifier = modifier
                .background(color = Color(0xFFF5F5F5))
        ) {
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 28.dp),
                painter = painterResource(R.drawable.gr_28),
                contentDescription = null
            )
        }

    }
}
