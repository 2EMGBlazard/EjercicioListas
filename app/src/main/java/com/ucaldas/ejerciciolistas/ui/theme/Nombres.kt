package com.example.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

// 1. Modelo de datos
data class Superhero(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val imagenUrl: String
)

// 2. Lista de 10 Superhéroes con URLs de imágenes
val listaSuperheroes = listOf(
    Superhero(
        id = 1,
        nombre = "Spider-Man",
        descripcion = "Joven héroe de Nueva York con habilidades arácnidas y un gran sentido de la responsabilidad.",
        imagenUrl = "https://images.unsplash.com/photo-1604200213928-ba3cf4fc8436?q=80&w=400&auto=format&fit=crop"
    ),
    Superhero(
        id = 2,
        nombre = "Iron Man",
        descripcion = "Genio, millonario, playboy y filántropo que combate el mal utilizando armaduras de alta tecnología.",
        imagenUrl = "https://images.unsplash.com/photo-1635863138275-d9b33299680b?q=80&w=400&auto=format&fit=crop"
    ),
    Superhero(
        id = 3,
        nombre = "Batman",
        descripcion = "El Caballero de la Noche, protector de Gotham que utiliza la tecnología y las artes marciales.",
        imagenUrl = "https://images.unsplash.com/photo-1509198397868-475647b2a1e5?q=80&w=400&auto=format&fit=crop"
    ),
    Superhero(
        id = 4,
        nombre = "Superman",
        descripcion = "El Último Hijo de Krypton, defensor de la justicia con superfuerza, vuelo y visión de calor.",
        imagenUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?q=80&w=400&auto=format&fit=crop"
    ),
    Superhero(
        id = 5,
        nombre = "Wonder Woman",
        descripcion = "Princesa amazona con habilidades sobrehumanas y armas divinas orientadas a la paz mundial.",
        imagenUrl = "https://images.unsplash.com/photo-1568832359672-e36cf5d74f54?q=80&w=400&auto=format&fit=crop"
    ),
    Superhero(
        id = 6,
        nombre = "Capitán América",
        descripcion = "El supersoldado de la Segunda Guerra Mundial, símbolo de libertad y líder de los Vengadores.",
        imagenUrl = "https://images.unsplash.com/photo-1612036782180-6f0b6cd846fe?q=80&w=400&auto=format&fit=crop"
    ),
    Superhero(
        id = 7,
        nombre = "Thor",
        descripcion = "Dios del Trueno asgardiano que maneja el martillo místico Mjolnir para proteger el cosmos.",
        imagenUrl = "https://images.unsplash.com/photo-1579783902614-a3fb3927b675?q=80&w=400&auto=format&fit=crop"
    ),
    Superhero(
        id = 8,
        nombre = "Flash",
        descripcion = "El hombre más rápido del mundo, capaz de canalizar la Fuerza de la Velocidad para alterar el tiempo.",
        imagenUrl = "https://images.unsplash.com/photo-1559535332-db9971090158?q=80&w=400&auto=format&fit=crop"
    ),
    Superhero(
        id = 9,
        nombre = "Black Panther",
        descripcion = "Rey y protector de Wakanda con un traje mejorado con vibranium y sentidos aumentados.",
        imagenUrl = "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?q=80&w=400&auto=format&fit=crop"
    ),
    Superhero(
        id = 10,
        nombre = "Doctor Strange",
        descripcion = "El Hechicero Supremo encargado de defender la Tierra contra amenazas místicas y de otras dimensiones.",
        imagenUrl = "https://images.unsplash.com/photo-1514539079130-25950c84af65?q=80&w=400&auto=format&fit=crop"
    )
)

// 3. Composable para cada tarjeta de superhéroe
@Composable
fun ItemSuperheroe(hero: Superhero) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Carga la imagen de red de manera asíncrona mediante Coil
            AsyncImage(
                model = hero.imagenUrl,
                contentDescription = "Imagen de ${hero.nombre}",
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = hero.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = hero.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// 4. Composable Principal con el listado
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaSuperheroesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Superhéroes") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(
                items = listaSuperheroes,
                key = { it.id } // Optimiza la recomposición asignando el id único
            ) { hero ->
                ItemSuperheroe(hero = hero)
            }
        }
    }
}