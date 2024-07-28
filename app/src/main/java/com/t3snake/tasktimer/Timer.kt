package com.t3snake.tasktimer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.t3snake.tasktimer.ui.theme.TaskTim3rTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScaffold () {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Task TIM3R")
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainTimer(
                totalTime = 100L * 1000L,
                //handleColor = Color.DarkGray,
                inactiveBarColor = MaterialTheme.colorScheme.secondaryContainer,
                activeBarColor = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
            )
        }

    }
}

@Composable
fun MainTimer(
    totalTime: Long,
    //handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 0.5f,
    //strokeWidth: Dp = 5.dp
) {
    // Size of composable
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    // time remaining / total time * 100
    var percentRemaining by remember {
        mutableFloatStateOf(initialValue)
    }

    var currentTime by remember {
        mutableLongStateOf(totalTime)
    }

    var isTimerRunning by remember{
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if ( (currentTime > 0) and isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            percentRemaining = currentTime / totalTime.toFloat()
        }

        if ( (currentTime <= 0) and isTimerRunning) {
            isTimerRunning = !isTimerRunning
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxHeight()
    ) {
        Canvas(
            modifier = Modifier
                .size(size = 200.dp)
                .onSizeChanged {
                    size = it
                }
        ) {
            // Draw background arc
            drawArc(
                color =  inactiveBarColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = true,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Fill //Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            // Draw the arc representing timer
            drawArc(
                color =  activeBarColor,
                startAngle = -90f,
                sweepAngle = 360f * percentRemaining,
                useCenter = true,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Fill //Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        TimerText(currentTime)

        TimerControl(
            isTimerRunning = isTimerRunning,
            currentTime = currentTime,
            onClick = {
                if ( !isTimerRunning and (currentTime <= 0f)) {
                    currentTime = totalTime
                    isTimerRunning = true
                } else {
                    isTimerRunning = !isTimerRunning
                }
            }
        )


    }
}

@Composable
fun TimerText(
    currentTime: Long,
    modifier: Modifier = Modifier
) {
    val hours = (currentTime/(1000L*3600L)).toInt()
    val curTimeMinusHours = (currentTime - (hours.toLong()*1000L*3600L))
    val minutes = ( curTimeMinusHours/(1000L*60L) ).toInt()
    val curTimeMinusMin = ( curTimeMinusHours - (minutes.toLong()*1000L*60L) )
    val seconds = ( (curTimeMinusMin)/(1000L) ).toInt()

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "Time Left",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$hours hours : $minutes minutes : $seconds seconds",
            fontSize = 23.sp
        )
    }

}

@Composable
fun TimerControl(
    isTimerRunning: Boolean,
    currentTime: Long,
    onClick: () -> Unit
) {
    LargeFloatingActionButton(
        onClick = { onClick() }
    ) {
        if ( !isTimerRunning and (currentTime > 0L) ) {
            Icon(Icons.Filled.PlayArrow, "Floating Play button.")
        } else if ( !isTimerRunning and (currentTime <= 0f)) {
            Icon(Icons.Filled.Refresh, "Floating Restart button.")
        } else {
            val pauseIcon = ImageVector.vectorResource(id = R.drawable.ic_pause_filled)
            Icon(pauseIcon, "Floating action button.")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TimerPreview() {
    TaskTim3rTheme {
        TimerScaffold()
    }
}