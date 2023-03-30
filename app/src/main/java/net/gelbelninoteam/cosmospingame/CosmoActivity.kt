package net.gelbelninoteam.cosmospingame

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import kotlinx.coroutines.launch
import net.gelbelninoteam.cosmospingame.ui.components.CosmoImageButton
import net.gelbelninoteam.cosmospingame.ui.components.InfoBoard
import net.gelbelninoteam.cosmospingame.ui.theme.CosmoSpinGameTheme

class CosmoActivity : ComponentActivity() {

    private val controlButtonModifier = Modifier
        .width(60.dp)
        .height(60.dp)
    private val controlButtonShape = RoundedCornerShape(40)

    private var score = 1000
    private var bit = 50

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        @Suppress("DEPRECATION")
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        sharedPreferences =
            getSharedPreferences(getString(R.string.score_pref), Context.MODE_PRIVATE)
        score = sharedPreferences.getInt(getString(R.string.score_key), 1000)

        setContent {
            CosmoSpinGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    val multiplicationList = intArrayOf(2, 0, 5, 0, 2, 0, 2, 0, 2, 0, 2, 0)
                    val coroutineScope = rememberCoroutineScope()
                    val rotation = remember { Animatable(0f) }

                    var mutableScore by remember { mutableStateOf(score) }
                    var mutableBit by remember { mutableStateOf(bit) }
                    var mutableWin by remember { mutableStateOf("0") }
                    var spinStart by remember { mutableStateOf(true) }
                    var multiplication: Int

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            InfoBoard(
                                mainText = stringResource(id = R.string.score),
                                textInfo = mutableScore.toString()
                            )
                            InfoBoard(
                                mainText = stringResource(id = R.string.last_sum),
                                textInfo = mutableWin
                            )
                        }

                        Box {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxWidth()
                                ) {

                                }
                                Image(
                                    painter = painterResource(id = R.drawable.cosmo_wheel),
                                    contentDescription = "",
                                    modifier = Modifier.rotate(rotation.value)
                                )

                                Box(
                                    contentAlignment = Alignment.BottomCenter,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.arrow_upward),
                                        contentDescription = "",
                                    )
                                }
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CosmoImageButton(
                                onClick = {
                                    if (mutableBit > 50) {
                                        mutableBit -= 50
                                        mutableScore += 50
                                        bit = mutableBit
                                        score = mutableScore
                                    }
                                },
                                modifier = controlButtonModifier,
                                shape = controlButtonShape,
                                imageId = R.drawable.ic_minus,
                                enabled = spinStart
                            )
                            InfoBoard(
                                mainText = stringResource(id = R.string.bit),
                                textInfo = mutableBit.toString()
                            )
                            CosmoImageButton(
                                onClick = {
                                    if (mutableScore >= 50) {
                                        mutableBit += 50
                                        mutableScore -= 50
                                        bit = mutableBit
                                        score = mutableScore
                                    }
                                },
                                modifier = controlButtonModifier,
                                shape = controlButtonShape,
                                imageId = R.drawable.ic_plus,
                                enabled = spinStart
                            )
                        }

                        CosmoImageButton(
                            onClick = {
                                coroutineScope.launch {
                                    spinStart = false

                                    multiplication =
                                        (multiplicationList.indices).random()

                                    rotation.animateTo(
                                        targetValue = 0f,
                                        animationSpec = tween(durationMillis = 0)
                                    )
                                    rotation.animateTo(
                                        targetValue = (multiplication * 30) + 3600f,
                                        animationSpec = tween(
                                            durationMillis = 1500,
                                            easing = LinearOutSlowInEasing
                                        )
                                    )

                                    spinStart = true

                                    if (multiplicationList[multiplication] != 0) {
                                        mutableScore += mutableBit * multiplicationList[multiplication]
                                        mutableWin =
                                            "+ ${mutableBit * multiplicationList[multiplication]}"
                                        score = mutableScore
                                    } else {
                                        if (mutableScore - mutableBit <= 0) {
                                            if (mutableScore == 0) {
                                                mutableBit = 50
                                            } else {
                                                mutableScore = 0
                                            }
                                        } else {
                                            mutableScore -= mutableBit
                                        }
                                        mutableWin =
                                            "- $mutableBit"
                                        score = mutableScore
                                        bit = mutableBit
                                    }
                                }
                            },
                            modifier = Modifier
                                .height(100.dp)
                                .width(100.dp),
                            shape = RoundedCornerShape(40),
                            imageId = R.drawable.ic_start_spin,
                            enabled = spinStart
                        )
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()

        if (bit > 50) {
            score += bit - 50
        }

        sharedPreferences.edit {
            putInt(getString(R.string.score_key), score)
        }
    }
}