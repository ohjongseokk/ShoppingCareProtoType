package co.kr.datau.shoppingcareprototype

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.datau.shoppingcareprototype.ui.theme.ShoppingCareProtoTypeTheme

class MenuActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingCareProtoTypeTheme {
                MenuScreen(activity = this)
            }
        }
    }
}

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    activity: ComponentActivity
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp, start = 10.dp, end = 20.dp),
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
                modifier = modifier.height(26.dp),
                painter = painterResource(R.drawable.menu_text),
                contentDescription = null
            )
        }
        Spacer(modifier = modifier.fillMaxWidth().height(1.dp).background(color = Color(0xFFE6E6E6)))
        Spacer(modifier = modifier.size(30.dp))
        Image(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    activity.startActivity(Intent(activity, EmptyActivity::class.java))
                },
            painter = painterResource(R.drawable.frame_427319294),
            contentDescription = null
        )
    }
}