package net.gelbelninoteam.cosmospingame

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.gelbelninoteam.cosmospingame.ui.components.CosmoImageButton
import net.gelbelninoteam.cosmospingame.ui.theme.CosmoSpinGameTheme
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    private val buttonModifier = Modifier
        .width(100.dp)
        .height(100.dp)
    private val buttonShape = RoundedCornerShape(50)

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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CosmoImageButton(
                            onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        CosmoActivity::class.java
                                    )
                                )
                            },
                            modifier = buttonModifier,
                            shape = buttonShape,
                            imageId = R.drawable.ic_play
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        CosmoImageButton(
                            onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        PrivacyPolicyActivity::class.java
                                    )
                                )
                            },
                            modifier = buttonModifier,
                            shape = buttonShape,
                            imageId = R.drawable.ic_policy
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        CosmoImageButton(
                            onClick = {
                                finish()
                                exitProcess(0)
                            },
                            modifier = buttonModifier,
                            shape = buttonShape,
                            imageId = R.drawable.ic_exit
                        )
                    }
                }
            }
        }
    }
}