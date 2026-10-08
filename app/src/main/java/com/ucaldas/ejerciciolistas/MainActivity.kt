package com.ucaldas.ejerciciolistas

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import okhttp3.OkHttpClient
import androidx.navigation.NavType
import androidx.navigation.navArgument

// ==========================================
// 1. CLASE PRINCIPAL (MainActivity)
// ==========================================
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListaSuperheroesApp()
                }
            }
        }
    }
}

// ==========================================
// 2. MODELO DE DATOS
// ==========================================
data class Superhero(
    val id: Int,
    val nombre: String,
    val alias: String,
    val descripcion: String,
    val detalleCompleto: String,
    val gifUrl: String
)

val listaSuperheroes = listOf(
    Superhero(
        id = 1,
        nombre = "Spider-Man",
        alias = "Peter Parker",
        descripcion = "Héroe arácnido con agilidad sobrehumana y sentido de peligro.",
        detalleCompleto = "Tras ser picado por una araña radiactiva, Peter Parker aprendió que 'un gran poder conlleva una gran responsabilidad'. Defiende Nueva York con sus lanzatelarañas.",
        gifUrl = "https://www.gifsanimados.org/data/media/1555/spider-man-imagen-animada-0017.gif"
    ),
    Superhero(
        id = 2,
        nombre = "Iron Man",
        alias = "Tony Stark",
        descripcion = "Genio millonario blindado con tecnología de vanguardia.",
        detalleCompleto = "Magnate industrial y genio inventor que creó una armadura de alta tecnología para salvar su vida y posteriormente proteger al mundo como el Vengador de Hierro.",
        gifUrl = "https://media.tenor.com/lHgg3LUUgyAAAAAM/iron-man-helmet.gif"
    ),
    Superhero(
        id = 3,
        nombre = "Batman",
        alias = "Bruce Wayne",
        descripcion = "El Caballero de la Noche, maestro de la estrategia y las artes marciales.",
        detalleCompleto = "Tras presenciar la tragedia de sus padres, Bruce Wayne entrenó su mente y cuerpo al límite humano. Utiliza la tecnología y el miedo para proteger Gotham City.",
        gifUrl = "https://media.tenor.com/BhWF4uoOccYAAAAM/michael-keaton-batman-keaton.gif"
    ),
    Superhero(
        id = 4,
        nombre = "Linterna Verde",
        alias = "Hal Jordan",
        descripcion = "Protector intergaláctico armado con un anillo de voluntad pura.",
        detalleCompleto = "Piloto de pruebas elegido por un anillo de poder alienígena para unirse a los Green Lantern Corps, usando su fuerza de voluntad para materializar cualquier cosa que imagine.",
        gifUrl = "https://media.tenor.com/lOuesCd5Q98AAAAM/green-lantern-hal-jordan.gif"
    ),
    Superhero(
        id = 5,
        nombre = "Flash",
        alias = "Barry Allen",
        descripcion = "El velocista carmesí capaz de romper la barrera del tiempo.",
        detalleCompleto = "Un científico forense que obtuvo supervelocidad tras ser alcanzado por un rayo y químicos. Conectado a la Speed Force, es el hombre más rápido del mundo.",
        gifUrl = "https://media.tenor.com/fVaTxfa_Tg0AAAA1/the-flash-grant-gustin.webp"
    ),
    Superhero(
        id = 6,
        nombre = "Mujer Maravilla",
        alias = "Diana Prince",
        descripcion = "Princesa amazona con la fuerza de los dioses y el Lasso de la Verdad.",
        detalleCompleto = "Entrenada como una guerrera formidable en Themyscira, Diana viaja al mundo del hombre portando artefactos divinos para luchar por la paz, la justicia y la igualdad.",
        gifUrl = "https://media.tenor.com/6-VYR6ei9rYAAAAM/dc-dceu.gif"
    ),
    Superhero(
        id = 7,
        nombre = "Falcon",
        alias = "Sam Wilson",
        descripcion = "Héroe alado con equipamiento táctico de vuelo avanzado.",
        detalleCompleto = "Exmilitar entrenado en combate aéreo que utiliza alas mecánicas de alta tecnología para surcar los cielos y combatir el crimen junto al Capitán América.",
        gifUrl = "https://media.tenor.com/QET4uGDFvBgAAAAM/sam-wilson-falcon.gif"
    ),
    Superhero(
        id = 8,
        nombre = "Capitán América",
        alias = "Steve Rogers",
        descripcion = "El supersoldado y líder patriótico de los Vengadores.",
        detalleCompleto = "Sometido al experimento del Suero del Supersoldado en la Segunda Guerra Mundial, Steve Rogers posee la cima del potencial humano y un escudo indestructible de Vibranium.",
        gifUrl = "https://media.tenor.com/VcrD5Fub-u0AAAAM/caption.gif"
    ),
    Superhero(
        id = 9,
        nombre = "Ant-Man",
        alias = "Scott Lang",
        descripcion = "Héroe capaz de alterar su tamaño y comunicarse con insectos.",
        detalleCompleto = "Usando el traje diseñado por Hank Pym con Partículas Pym, Scott puede reducirse al tamaño de una hormiga aumentando su densidad, o crecer a alturas gigantescas.",
        gifUrl = "https://media.tenor.com/R_d6MmcVTc8AAAAM/cmadeit-antman.gif"
    ),
    Superhero(
        id = 10,
        nombre = "Superman",
        alias = "Clark Kent / Kal-El",
        descripcion = "El Hombre de Acero con poder solar e invulnerabilidad.",
        detalleCompleto = "Enviado a la Tierra desde el moribundo planeta Krypton, Clark Kent usa sus increíbles poderes bajo la guía de sus padres adoptivos para ser el mayor símbolo de esperanza.",
        gifUrl = "https://media.tenor.com/RsR7M34EAEgAAAAM/superman-flying.gif"
    ),
    Superhero(
        id = 11,
        nombre = "Thor",
        alias = "Thor Odinson",
        descripcion = "Dios del Trueno asgardiano y portador del martillo místico.",
        detalleCompleto = "Príncipe de Asgard y protector de los Nueve Reinos. Thor domina el rayo y las tormentas mediante su martillo místico Mjolnir.",
        gifUrl = "https://media.tenor.com/Mu00cW3t5AwAAAAM/show-off-thor.gif"
    ),
    Superhero(
        id = 12,
        nombre = "Aquaman",
        alias = "Arthur Curry",
        descripcion = "Rey de la Atlántida y soberano de los siete mares.",
        detalleCompleto = "Hijo de un farero y una reina atlante, Arthur domina la fuerza marina, la telepatía acuática y sostiene el Tridente de Atlan para proteger océanos y tierra.",
        gifUrl = "https://media.tenor.com/dpAWCvPkmL0AAAAM/jason-momoa.gif"
    ),
    Superhero(
        id = 13,
        nombre = "Hulk",
        alias = "Bruce Banner",
        descripcion = "Gigante esmeralda impulsado por fuerza e ira ilimitadas.",
        detalleCompleto = "Tras sufrir un accidente con radiación gamma, el brillante científico Bruce Banner se transforma en una colosal masa verde incontrolable cada vez que se enfurece.",
        gifUrl = "https://media.tenor.com/Ol3UqzSM8iAAAAAM/warr.gif"
    ),
    Superhero(
        id = 14,
        nombre = "Loki",
        alias = "Loki Laufeyson",
        descripcion = "Dios del Engaño, maestro de las ilusiones y la magia.",
        detalleCompleto = "Hermano adoptivo de Thor e hijo biológico de un Gigante de Hielo. Destaca por su ingenio astuto, habilidades de metamorfosis y dominio del engaño místico.",
        gifUrl = "https://media.tenor.com/exkyEuBB4AwAAAAM/loki.gif"
    ),
    Superhero(
        id = 15,
        nombre = "Wolverine",
        alias = "Logan / James Howlett",
        descripcion = "Mutante con garras de adamantium y factor de curación veloz.",
        detalleCompleto = "Posee garras retráctiles, sentidos animales aguzados y un esqueleto recubierto del indestructible metal Adamantium, sumado a un factor regenerativo curativo.",
        gifUrl = "https://media.tenor.com/BsoLVT9d1qYAAAAM/ready-to-fight-wolverine.gif"
    ),
    Superhero(
        id = 16,
        nombre = "Deadpool",
        alias = "Wade Wilson",
        descripcion = "Mercenario bocazas impredecible con factor regenerativo.",
        detalleCompleto = "Sometido al programa Weapon X, obtuvo un factor de curación acelerado que lo vuelve casi inmortal. Destaca por romper la cuarta pared y su humor irreverente.",
        gifUrl = "https://media.tenor.com/A9XkUbxuAdwAAAAM/deadpool-marvel.gif"
    )
)

// ==========================================
// 3. COMPONENTES DE INTERFAZ
// ==========================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaSuperheroesApp() {
    var heroeSeleccionado by remember { mutableStateOf<Superhero?>(null) }
    val context = LocalContext.current

    // Configuramos OkHttpClient para enviar User-Agent y evitar que el servidor bloquee el GIF (Error 403)
    val okHttpClient = remember {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    // Creamos el ImageLoader de Coil con OkHttp y soporte para GIFs
    val gifImageLoader = remember {
        ImageLoader.Builder(context)
            .okHttpClient(okHttpClient)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🦸‍♂️ Comics Universe", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(
                items = listaSuperheroes,
                key = { hero -> hero.id }
            ) { superhero ->
                TarjetaSuperheroeGrande(
                    hero = superhero,
                    imageLoader = gifImageLoader,
                    onHeroeClick = { heroeSeleccionado = superhero }
                )
            }
        }

        // Diálogo con el GIF ampliado al hacer click
        heroeSeleccionado?.let { hero ->
            DetalleHeroeDialog(
                hero = hero,
                imageLoader = gifImageLoader,
                onDismiss = { heroeSeleccionado = null }
            )
        }
    }
}

@Composable
fun TarjetaSuperheroeGrande(
    hero: Superhero,
    imageLoader: ImageLoader,
    onHeroeClick: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onHeroeClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(hero.gifUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "GIF de ${hero.nombre}",
                imageLoader = imageLoader,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = hero.nombre,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "(${hero.alias})",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = hero.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun DetalleHeroeDialog(
    hero: Superhero,
    imageLoader: ImageLoader,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(hero.gifUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "GIF ampliado de ${hero.nombre}",
                    imageLoader = imageLoader,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = hero.nombre,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Identidad Secreta: ${hero.alias}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = hero.detalleCompleto,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cerrar")
                }
            }
        }
    }
}