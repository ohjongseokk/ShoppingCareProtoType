package co.kr.datau.shoppingcareprototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.kr.datau.shoppingcareprototype.ui.theme.ShoppingCareProtoTypeTheme

class ReportActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingCareProtoTypeTheme {
                ReportScreen()
            }
        }
    }
}