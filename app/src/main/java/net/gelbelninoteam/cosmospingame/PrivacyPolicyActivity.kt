package net.gelbelninoteam.cosmospingame

import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import net.gelbelninoteam.cosmospingame.ui.theme.CosmoSpinGameTheme

class PrivacyPolicyActivity : ComponentActivity() {

    private val policyUrl = "https://www.google.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        @Suppress("DEPRECATION")
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContent {
            CosmoSpinGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    AndroidView(factory = {
                        WebView(it).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            webViewClient = WebViewClient()
                            loadUrl(policyUrl)
                        }
                    }, update = {
                        it.loadUrl(policyUrl)
                    })
                }
            }
        }
    }
}