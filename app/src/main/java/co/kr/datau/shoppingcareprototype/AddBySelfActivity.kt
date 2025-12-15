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
import androidx.compose.foundation.layout.height
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

class AddBySelfActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingCareProtoTypeTheme {
                AddBySelfScreen(activity = this)
            }
        }
    }
}

@Composable
fun AddBySelfScreen(
    modifier: Modifier = Modifier,
    activity: ComponentActivity
) {
    val verticalScroll = rememberScrollState()

    Column(
        modifier = modifier.fillMaxSize()
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
            Spacer(modifier.size(10.dp))
            Image(
                modifier = modifier
                    .height(26.dp),
                painter = painterResource(R.drawable.add_text),
                contentDescription = null
            )
        }
        Spacer(modifier = modifier.fillMaxWidth().height(1.dp).background(color = Color(0xFFE6E6E6)))

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(verticalScroll)
        ) {
            Spacer(modifier = modifier.size(30.dp))
            Image(
                modifier = modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.group_25),
                contentDescription = null
            )

            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 28.dp)
                    .clickable {
                        activity.finish()
                    },
                painter = painterResource(R.drawable.app_btn_fill),
                contentDescription = null
            )
        }
    }
}