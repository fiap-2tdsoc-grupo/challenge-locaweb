package br.com.fiap.challenge.landingpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challenge.R
import br.com.fiap.challenge.filtros.getEmailsImportantes
import br.com.fiap.challenge.listaEmails.Email
import br.com.fiap.challenge.listaEmails.showOnlyFavorites
import br.com.fiap.challenge.navegacao.Screen

data class EmailClass(val nome: String, val role: String, val location: String, val emailIcon: Int, val favorito: Boolean, val isImportant: Boolean)
val emails =
    listOf(
        EmailClass("Joana Ferniz", "Prazo para edição das fotos", "São Paulo - SP", R.drawable.ic_avatar1, false, false),
        EmailClass("Lucas Freire", "Faturamento mês XX", "João Pessoa - PB", R.drawable.ic_avatar2, false,false),
        EmailClass("Paulo Lima", "Doc em anexo", "Amazonas - AM", R.drawable.ic_avatar3, false,false),
        EmailClass("Kar Custon", "Itens automobilísticos", "Pará - PR", R.drawable.ic_avatar4, true,true)
    )

var showOnlyImportants = mutableStateOf(0);
var emailsExibir = mutableStateOf(emails);

@Composable
fun LandingPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(16.dp)
    ) {
        Text(
            text = "Oi, Henrique",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = "Está preparado para um novo modo de ler seus e-mails!",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = "",
            onValueChange = {navController.navigate(Screen.EmailsLista.route)},
            label = { Text("procure um e-mail") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "E-mails em Destaque",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            TextButton(
                onClick = {
                    navController.navigate(Screen.EmailsLista.route)
                }) {
                Text(text = "Ver Tudo",  fontWeight = FontWeight.Bold,  fontSize = 18.sp,)
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { ExbirImportantes(1) }) {
                Text("Mais Importantes")
            }
            Button(onClick = { ExbirImportantes(0) }) {
                Text("Priorize")
            }
            Button(onClick = { ExbirImportantes(0) }) {
                Text("Anterior")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyRow (
                contentPadding = PaddingValues(5.dp)
            ){
                if (showOnlyImportants.value > 0) {
                    emailsExibir.value = getEmailsImportantes();
                }
                items(emailsExibir.value) { email ->
                    EmailCard(email, navController)
                }
            }
        }
    }
}

@Composable
fun EmailCard(email: EmailClass, navController: NavController) {

    Card(
        modifier = Modifier
            .width(180.dp)
            .height(250.dp)
            .padding(5.dp)
    ) {
        Box (Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.background_card_email) ,
                contentDescription = "" ,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                        .padding(16.dp)

            ) {
                Row {
                    Image(
                        painter = painterResource(id = email.emailIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "",

                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(email.nome, fontWeight = FontWeight.Bold)
                Text(email.role, color = Color.Black)
                Button (
                    onClick = { navController.navigate(Screen.MapScreen.route) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black.copy(alpha = 0.2f)
                    )
                ) {
                    Text(email.location, color = Color.LightGray)
                }

            }
        }

    }
}

fun ExbirImportantes(tgExbir: Int) {
    showOnlyImportants.value = tgExbir;
    if (tgExbir < 1)
        emailsExibir.value = emails;
}