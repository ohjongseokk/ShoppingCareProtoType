package co.kr.datau.shoppingcareprototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.datau.shoppingcareprototype.ui.theme.ShoppingCareProtoTypeTheme

class CalendarNoteActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingCareProtoTypeTheme {
                CalendarNoteScreen(activity = this)
            }
        }
    }
}

@Composable
fun CalendarNoteScreen(
    modifier: Modifier = Modifier,
    activity: ComponentActivity
) {
    val verticalScroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier
                    .size(24.dp)
                    .clickable {
                        activity.finish()
                    },
                painter = painterResource(R.drawable.note_back),
                contentDescription = null
            )
            Image(
                modifier = modifier
                    .width(63.dp)
                    .padding(start = 10.dp),
                painter = painterResource(R.drawable.note_text),
                contentDescription = null
            )
            Spacer(modifier = modifier.weight(1f))


        }
        Image(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    activity.finish()
                },
            painter = painterResource(R.drawable.note01),
            contentDescription = null
        )
        Image(
            modifier = modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.note02),
            contentDescription = null
        )

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
}