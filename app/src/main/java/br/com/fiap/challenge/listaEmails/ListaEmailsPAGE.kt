package br.com.fiap.challenge.listaEmails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challenge.R
import br.com.fiap.challenge.filtros.getEmailsByName
import br.com.fiap.challenge.filtros.getEmailsFavoritos
import br.com.fiap.challenge.navegacao.Screen

class ListaEmailsPAGE {
}

data class Email(val sender: String, val subject: String, val message: String, val imageRes: Int, val favorito: Boolean)
val emails =
    listOf(
        Email("Joana Ferniz", "Prazo para edição das fotos", "Em até 2 dias estará pronto!", R.drawable.ic_avatar1, false),
        Email("Lucas Freire", "Faturamento mês XX", "Bom Dia! Faturamento do mês XX...", R.drawable.ic_avatar2, false),
        Email("Paulo Lima", "Doc em anexo", "Ok, Muito Obrigada. Atenciosamente...", R.drawable.ic_avatar3, false),
        Email("Kar Custon", "Itens automobilísticos", "Oi, poderia enviar o boleto no num. XXX", R.drawable.ic_avatar4, true)
    )
var showOnlyFavorites = mutableStateOf(0);

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailListScreen(navController: NavController) {
    var listEmailByName by remember { mutableStateOf(emails) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Caixa de Entrada (9)") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6200EE)),
                navigationIcon = {
                    NavigationIcon(
                        icon = Icons.Default.ArrowBack,
                        description = "Back"
                    ) {
                        // Ação de voltar
                    }
                     },
                actions = {
                    TopBarAction(
                        icon = Icons.Default.Search,
                        description = "Search"
                    ) {
                        // Ação de pesquisa
                    }
                    TopBarAction(
                        icon = Icons.Default.Settings,
                        description = "Settings"
                    ) {
                        // Ação de configurações
                    }
                }
            )
        },
        content = { padding ->
            LazyColumn(
                contentPadding = padding
            ) {
                item {
                    SearchBar(onSearchChanged = { newText ->
                        listEmailByName = getEmailsByName(newText)
                    })
                }
                if (showOnlyFavorites.value > 0) {
                    listEmailByName = getEmailsFavoritos();
                }
                items(listEmailByName) { email ->
                    EmailItem(email)
                }
            }

        },
        bottomBar = {
            BottomIconsRow(navController)
        }
    )
}

@Composable
fun TopBarAction(
    icon: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = description, tint = Color.White)
    }
}

@Composable
fun NavigationIcon(
    icon: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = description, tint = Color.White)
    }
}

@Composable
fun EmailItem(email: Email) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = email.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(90.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(email.sender, fontWeight = FontWeight.Bold)
            Text(email.subject, fontSize = 14.sp, color = Color.Gray)
            Text(email.message, fontSize = 12.sp, color = Color.Gray)
        }

    }
    Divider(color = Color.LightGray, thickness = 1.dp)

}

@Composable
fun SearchBar(onSearchChanged: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    var listEmailByName by remember {
        mutableStateOf(getEmailsByName(text))
    }
    Divider(color = Color.LightGray, thickness = 1.dp)
    TextField(
        value = text,
        onValueChange = { text = it
            onSearchChanged(it)},
        placeholder = { Text("Pesquisar") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Ícone de pesquisa") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .size(55.dp),
        singleLine = true
    )


}

@Composable
fun BottomIconsRow(navController: NavController) {

var listaEmailsFavoritos by remember {
    mutableStateOf(getEmailsFavoritos())
}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(
            onClick = {
                    navController.navigate(Screen.EmailsLista.route)
                ExbirFavoritos(0);
            }
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Voltar"
            )
        }

        IconButton(
            onClick = {
                ExbirFavoritos(1);
            }
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favoritos"
            )
        }


        IconButton(
            onClick = {
                navController.navigate(Screen.Calendario.route)
            }
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Pesquisar"
            )
        }


        IconButton(
            onClick = {

            }
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = "Profile"
            )
        }
    }
}


@Composable
fun PreviewEmailListScreen(navController: NavController) {
        EmailListScreen(navController)
}

fun ExbirFavoritos(tgExbir: Int) {
    showOnlyFavorites.value = tgExbir;
}