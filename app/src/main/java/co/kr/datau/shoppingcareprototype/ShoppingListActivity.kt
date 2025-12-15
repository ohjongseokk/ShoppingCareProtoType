package co.kr.datau.shoppingcareprototype

import android.content.Intent
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
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    modifier: Modifier = Modifier,
    selectedType: Int = 0
) {
    val activity = LocalActivity.current
    val context = LocalContext.current
    var selectedType by remember { mutableStateOf(selectedType) }

    var isOpenBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

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
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                isOpenBottomSheet = true
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

    if (isOpenBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { isOpenBottomSheet = false },
            sheetState = sheetState,
            dragHandle = null,
            containerColor = Color(0xFFFFFFFF)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp, start = 20.dp, end = 20.dp, bottom = 22.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = modifier
                        .size(width = 40.dp, height = 4.dp)
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(100.dp)
                        )
                )
                Spacer(modifier = modifier.size(18.dp))

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = Color(0xFFF8F8F8)
                        )
                        .padding(horizontal = 30.dp, vertical = 14.dp)
                ) {
                    Image(
                        modifier = modifier.fillMaxWidth(),
                        painter = painterResource(R.drawable.frame_427319561),
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.size(10.dp))

                    Spacer(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color(0xFFDEDEDE))
                    )

                    Spacer(modifier = modifier.size(10.dp))
                    Image(
                        modifier = modifier
                            .fillMaxWidth()
                            .clickable {
                                isOpenBottomSheet = false
                                context.startActivity(Intent(context, AddBySelfActivity::class.java))
                            },
                        painter = painterResource(R.drawable.frame_427319564),
                        contentDescription = null
                    )
                }
                Spacer(modifier = modifier.size(10.dp))

                Image(
                    modifier = modifier.fillMaxWidth(),
                    painter = painterResource(R.drawable.frame_427319565),
                    contentDescription = null
                )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShoppingListListScreen(
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val stickyHeaderIndexes = remember { mutableListOf<Int>() }
    var currentIndex = 0

    val currentStickyHeaderIndex by remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo
                .filter { it.index in stickyHeaderIndexes }
                .filter { it.offset <= 0 } // 상단에 닿은 것들
                .maxByOrNull { it.index }  // 가장 아래(header 교체 시)
                ?.index
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F5F5))
    ) {
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            LazyColumn(
                state = listState
            ) {
                repeat(3) {
                    val myHeaderIndex = currentIndex

                    stickyHeader {
                        val isSticky = currentStickyHeaderIndex == myHeaderIndex

                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(
//                                    color = if (isSticky) Color.White else Color.Transparent
                                    color = if (isSticky) Color.White else Color.Transparent
                                )
                                .padding(horizontal = 30.dp, vertical = 14.dp)
                        ) {
                            Image(
                                modifier = modifier.height(24.dp),
                                painter = painterResource(
                                    id = if (it == 0) R.drawable.date_01 else if (it == 1) R.drawable.date_02 else R.drawable.date_03
                                ),
                                contentDescription = null
                            )
                        }
                    }
                    stickyHeaderIndexes.add(myHeaderIndex)
                    currentIndex++

                    items(4) { idx ->
                        if (idx == 0) {
                            Spacer(modifier.size(20.dp))
                        }

                        Image(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            painter = painterResource(R.drawable.note_item),
                            contentDescription = null
                        )

                        if (idx != 3) {
                            Spacer(modifier.size(10.dp))
                        } else {
                            Spacer(modifier.size(20.dp))
                        }
                    }
                    currentIndex += 4
                }
            }
        }
    }
}
