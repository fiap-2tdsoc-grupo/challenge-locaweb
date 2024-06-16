package br.com.fiap.challenge.calendario

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.challenge.ui.theme.CalendarComposeTheme
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class Calendario {
}

@Composable
fun CalendarScreen() {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { currentDate = currentDate.minusMonths(1) }) {
                BasicText("<")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${currentDate.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentDate.year}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { currentDate = currentDate.plusMonths(1) }) {
                BasicText(">")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        CalendarView(currentDate)
    }
}

@Composable
fun CalendarView(currentDate: LocalDate) {
    val daysOfWeek = listOf("Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb")
    val firstDayOfMonth = currentDate.withDayOfMonth(1)
    val lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth())
    val dayOfWeek = firstDayOfMonth.dayOfWeek.value % 7

    Column {
        Row {
            for (day in daysOfWeek) {
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        var currentWeekDay = 0
        for (i in 1..dayOfWeek) {
            if (i == 1) {
                Row {
                    for (j in 1 until dayOfWeek) {
                        Text(
                            text = "",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        currentWeekDay++
                    }
                }
            }
        }

        var day = 1
        while (day <= lastDayOfMonth.dayOfMonth) {
            Row {
                for (i in 0..6) {
                    if (day > lastDayOfMonth.dayOfMonth) {
                        Text(
                            text = "",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    } else if (i >= currentWeekDay || day > 1) {
                        val date = LocalDate.of(currentDate.year, currentDate.month, day)
                        Text(
                            text = day.toString(),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { println("Data selecionada: $date") },
                            textAlign = TextAlign.Center
                        )
                        day++
                    } else {
                        Text(
                            text = "",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                currentWeekDay = 0
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
CalendarComposeTheme {
    CalendarScreen()
}

}