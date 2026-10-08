# 🦸‍♂️ Comics Universe — Android App

Aplicación móvil nativa desarrollada en **Kotlin** y **Jetpack Compose** que muestra una galería interactiva de superhéroes y villanos del universo de cómics. Incluye animaciones en formato **GIF**, visualización detallada mediante modales y una arquitectura preparada para navegación fluida.

Proyecto desarrollado como parte de las actividades prácticas de **Programación para Dispositivos Móviles** — Universidad de Caldas.

---

## 📱 Características principales

- **Galería con GIFs animados:** Carga asíncrona de animaciones GIF usando la librería **Coil** y **OkHttpClient** para garantizar la reproducción continua.
- **Lista reactiva (`LazyColumn`):** Renderizado optimizado con claves únicas (`key = { it.id }`) para un rendimiento eficiente en memoria.
- **Diseño estilo Cómics:** Tarjetas ampliadas e interfaz tematizada con componentes de **Material Design 3**.
- **Vista de Detalle Modal (`Dialog`):** Al tocar cualquier personaje o su imagen, se despliega una tarjeta flotante con la animación en gran tamaño e historia/alias completa.
- **Manejo de errores y red:** Configuración de cabeceras HTTP (`User-Agent`) en OkHttp para evadir bloqueos de servidor (Error 403) al cargar GIFs desde enlaces externos.

<img width="718" height="1599" alt="image" src="https://github.com/user-attachments/assets/fb1a8062-5cbb-4057-b5e7-012c38922470" />
<img width="718" height="1599" alt="image" src="https://github.com/user-attachments/assets/c809be56-df1c-4f39-8e98-b578d52ae2da" />


---

## 🛠️ Tecnologías y Librerías Utilizadas

- **Lenguaje:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
- **Carga de Imágenes y GIFs:** [Coil Compose](https://coil-kt.github.io/coil/) (`coil-compose`, `coil-gif`)
- **Cliente HTTP:** [OkHttp3](https://square.github.io/okhttp/) (Interceptor de cabeceras HTTP)
- **Navegación:** `androidx.navigation:navigation-compose`
- **Mínimo SDK:** API 24 (Android 7.0 Nougat)

---

## 🚀 Instalación y Configuración

### 1. Requisitos previos
- Android Studio Ladybug (o superior).
- JDK 17 o superior.
- Dispositivo físico o emulador con Android 7.0+.

### 2. Dependencias en `app/build.gradle.kts`
Asegúrate de incluir las siguientes dependencias en tu proyecto:

```kotlin
dependencies {
    // Jetpack Compose & Material 3
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.material3:material3")
    
    // Navegación
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // Coil para carga de imágenes y GIFs animados
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("io.coil-kt:coil-gif:2.6.0")

    // OkHttp para interceptores de red
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}
