package com.t3snake.tasktimer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.t3snake.tasktimer.ui.theme.TaskTim3rTheme

@Composable
fun RoutineCard() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick = {
            /*TODO*/
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Elevated",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.65f),
                textAlign = TextAlign.Left,
            )

            FilledTonalButton(
                onClick = { /*TODO*/ }
            ) {
                Text("Config")
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RoutinesPreview() {
    TaskTim3rTheme {
        RoutineCard()
    }
}