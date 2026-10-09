# 🦸‍♂️ Comics Universe — Android App (Héroes y Villanos)

Aplicación móvil nativa desarrollada en **Kotlin** y **Jetpack Compose** que muestra una galería interactiva de superhéroes y villanos del universo de cómics. Incluye animaciones en formato **GIF**, navegación por pestañas con gesto de deslizamiento (*Swipe*), soporte para orientación horizontal y persistencia de estado ante la rotación de pantalla.

Proyecto desarrollado como parte de las actividades prácticas de **Programación para Dispositivos Móviles** — Universidad de Caldas.

---

## 📱 Características principales

- **Navegación con Swipe y Pestañas (`TabRow` + `HorizontalPager`):** Transición fluida entre las secciones de **Héroes** y **Villanos** deslizando el dedo lateralmente o seleccionando la pestaña superior.
- **Galería con GIFs animados:** Carga asíncrona de animaciones GIF usando **Coil** y **OkHttpClient** con interceptores para evadir bloqueos HTTP (Error 403).
- **Detalle Modal Dinámico e Inmersivo (`Dialog`):** Al presionar cualquier tarjeta, se despliega una ventana flotante con el GIF ampliado, alias e historia completa.
- **Persistencia ante Rotación:** Uso de `rememberSaveable` para conservar el personaje seleccionado abierto al girar el dispositivo, evitando que el modal se cierre.
- **Diseño Adaptativo:** Ajuste automático de la interfaz en modo horizontal (*Landscape*) mediante scroll interno (`verticalScroll`) y redistribución de tamaños de imagen.

<img width="718" height="1599" alt="image" src="https://github.com/user-attachments/assets/4293a9c1-3a8c-4805-b1b7-0b7fe5354fdd" />
<img width="718" height="1599" alt="image" src="https://github.com/user-attachments/assets/88ae46a2-4693-4309-a938-5d9c12544189" />
<img width="718" height="1599" alt="image" src="https://github.com/user-attachments/assets/7f5faabd-cdfd-42e7-a479-6a8bd283cdb2" />
<img width="718" height="1599" alt="image" src="https://github.com/user-attachments/assets/2911904e-5d18-4fe6-8c0b-34b3c61b5cfc" />


---

## 🏗️ Estructura del Proyecto

El código está organizado siguiendo principios de arquitectura limpia y separación de responsabilidades:

```text
com.ucaldas.ejerciciolistas/
│
├── Superhero.kt       # Data Class (Modelo de datos)
├── Nombres.kt         # Colecciones de datos (listaSuperheroes y listaVillanos)
├── Componentes.kt     # Composables UI (TarjetaSuperheroeGrande y DetalleHeroeDialog)
└── MainActivity.kt    # Controlador principal (Layout, Scaffold, Pager y Red)
