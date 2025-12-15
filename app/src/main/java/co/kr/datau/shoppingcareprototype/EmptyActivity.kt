package co.kr.datau.shoppingcareprototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.kr.datau.shoppingcareprototype.ui.theme.ShoppingCareProtoTypeTheme

class EmptyActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingCareProtoTypeTheme {
                EmptyScreen(activity = this)
            }
        }
    }
}

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    activity: ComponentActivity
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = modifier
                .align(Alignment.Center),
            painter = painterResource(R.drawable.empty),
            contentDescription = null
        )

        Image(
            modifier = modifier
                .padding(vertical = 30.dp, horizontal = 20.dp)
                .align(Alignment.BottomCenter)
                .clickable {
                    activity.finish()
                },
            painter = painterResource(R.drawable.frame_26),
            contentDescription = null
        )
    }
}