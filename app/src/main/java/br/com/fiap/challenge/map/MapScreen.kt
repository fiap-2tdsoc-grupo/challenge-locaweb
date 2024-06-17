package br.com.fiap.challenge.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challenge.R
import br.com.fiap.challenge.navegacao.Screen

@Composable
fun MapScreen(navController: NavController) {
    var searchValue by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "Encontre em tempo\n \uD835\uDDFF\uD835\uDDF2\uD835\uDDEE\uD835\uDDF9, onde seu e-\nmail foi enviado!",
            fontSize = 28.sp,
            color = Color.Black,
            textAlign = TextAlign.Start,
            lineHeight = 36.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.map),
            contentDescription = "Map image",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = searchValue,
            onValueChange = { searchValue = it },
            label = { Text("Pesquisar") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier = Modifier
                    .width(110.dp)
                    .height(140.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                ) {
                    Image(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.ic_avatar1),
                        contentDescription = "Random person",
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Joana Femiz",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier.padding(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.card_gray_darker),
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Localizando...",  fontSize = 8.sp)
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .width(110.dp)
                    .height(140.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                ) {
                    Image(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.ic_avatar2),
                        contentDescription = "Random person",
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Paulo Lima",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier.padding(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.card_gray_darker),
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Localizando...",  fontSize = 8.sp)
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .width(110.dp)
                    .height(140.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                ) {
                    Image(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.ic_avatar3),
                        contentDescription = "Random person",
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Lucas Lima",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier.padding(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.card_gray_darker),
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Localizando...",  fontSize = 8.sp)
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .width(110.dp)
                    .height(140.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                ) {
                    Image(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.ic_avatar1),
                        contentDescription = "Random person",
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Joana Femiz",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier.padding(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.card_gray_darker),
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Localizando...",  fontSize = 8.sp)
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
           Button(
               onClick = { navController.popBackStack() },
               modifier = Modifier
                   .width(110.dp)
                   .height(40.dp),
               colors = ButtonDefaults.buttonColors(
                   containerColor = colorResource(id = R.color.card_gray),
               )
           ) {
               Row(
                   modifier = Modifier.fillMaxSize(),
                   horizontalArrangement = Arrangement.Center,
                   verticalAlignment = Alignment.CenterVertically,
               ) {
                   Text(
                       text = "Voltar",
                       fontSize = 15.sp,
                       color = Color.Black
                   )
               }
           }
        }
    }
}