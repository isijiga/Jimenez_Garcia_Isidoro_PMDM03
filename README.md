## Introducción

**Pokemon App** es una aplicación creada para la tarea 3 de la asignatura de “Programación Multimedia y dispositivos moviles” del curso de Desarrollo Aplicaciones Multimedia para el curso 2024/2025.  
	La app genera una lista de 150 pokemon aleatorios  y simula su captura tras tocar algun nombre. El pokemon capturado es agregado a una lista unica para el usuario autentificado. 
Puede crear un usuario nuevo o usar el usuario:
 pruebas@pruebas / pass: pruebas

 ## Características principales
 
- Pantalla de Autentiticación.Cada usuario puede acceder a su cuenta personal de forma segura a traves de dos vias.


  - Correo Electronico.  El usuario solo tiene que acceder con su nombre y apellidos y contraseña. Establece una contraseña unica que permitirá acceder en cual quier momento.

  
  - Cuenta de google: A traves de la autetificación por Google, se usará la cuenta personal de google para confirmar el acceso, de esta manera no será necesario rellenar ningun formulario de acceso.  	

  
- Pantalla de Pokedex:


 Desde la pantalla de Pokedex se visualizará una lista de 150 pokemon diferentes, para seleccionarlo, pulsa sobre el nombre y se visualizará una notificación si se ha guardado correctamente. 

 - Pantalla Pokemon Capturados:  En esta pantalla se muestran los pokemons capturados, dependiendo del tipo, el icono cambiará. Si se quiere expandir más información sobre un pokemon concreto, es suficiente con seleccionarlo y llevará a una pantalla de detalles, donde se indica: imagen,  el tipo, el peso, la altura y el número de colección. 

- Pantalla Ajustes:  Permite modificar algunos aspectos de la app. Estos son: 
 
  - Borrar pokemons. Si está activado el switch se mostrará un icono al lado de cada pokemon que permitirá eliminarlo de la lista.

 
  - Cambio de idioma. Al pulsar sobre el switch se conmutará entre castellano e ingles. En todo caso, no implicará reinciar la aplicación.


  - Cerrar Sesion: Esta opción permite al usuario deslogarse de la app y volverá a la pantalla de acceso. 


  - Acerca de: Se muestra en pantalla emergente tanto autor como version de la aplicación.
 
## Tecnologías utilizadas:
Para la creación de la aplicación se han implementado las siguientes tecnologías / librerias: 

- Firebase
 
    "com.firebaseui:firebase-ui-auth:8.0.2"


    "com.google.firebase:firebase-auth:23.1.0"


    "com.google.firebase:firebase-firestore:23.1.0"


    "com.google.android.gms:play-services-auth:19.5.0"


- Navegación

  
    "androidx.navigation:navigation-fragment:2.8.3"


    "androidx.navigation:navigation-ui:2.8.3"


    "androidx.recyclerview:recyclerview:1.3.2"


    "androidx.cardview:cardview:1.0.0"


- Mapeo de clases y descarga de fuente de datos 


    "com.squareup.retrofit2:retrofit:2.9.0"


    "com.squareup.retrofit2:converter-gson:2.9.0"


- Tratamiento de imagen:

  
    "com.squareup.picasso:picasso:2.8"`

## Instrucciones de uso:

Desde Android Studio pulsa sobre Archivo, Get from version Control → e indica la url https://github.com/isijiga/Jimenez_Garcia_Isidoro_PMDM03.git y el directorio de destino


En caso de que aparezca error relacionado con caracteres especiales, añadir en el ´gradle.properties´ la siguiente linea: 


`android.overridePathCheck=true'`

## Conclusiones del desarrollador: 

## FirebaseAuth

El primer desafío surgió durante el proceso de inicio de sesión de la aplicación. Una vez registrado, no me permitía introducir la contraseña, lo que provocaba un bucle infinito. Gracias a foros encontrados en la red, desactivé la protección de enumeración de correo electrónico, lo que resolvió el problema y permitió el acceso a la aplicación.

## Librería Retrofit

Esta fue mi primera experiencia utilizando Retrofit, y el mapeo de las diferentes clases requirió mucho tiempo de consulta de manuales y videos. La principal dificultad fue crear los tipos de datos correctos para el mapeo, ya que necesitaba conocer bien la ubicación específica de los datos que quería obtener. En algunos casos, como el tipo de Pokémon, los datos estaban anidados dentro de un array `slot[]`, lo que requirió varios intentos.

La lógica de desarrollo consistió en separar la obtención de datos en dos clases: `JsonRespuesta` (listado de Pokémon) y `JsonRespuestaDetalle` (Pokémon específico con detalles y número de página de la URL), y generar las clases necesarias. Una vez obtenida la lista de Pokémon, a través de un listener, inyecté todos los datos del Pokémon capturado en la base de datos, siguiendo la ruta `users/pokemonCapturados`.

## FirebaseFirestore

Trabajar con Firestore fue desafiante, ya que me costó bastante descargar los datos de cada uno de los Pokémon capturados y meterlos en una lista de `PokemonCapturado`. Tras varios fracasos y mucho debugging, la opción que funcionó fue crear mapas de tipo `<String, Object>` e ir separando los datos en el tipo correcto.

Este punto fue decisivo para el desarrollo de la aplicación, ya que a partir de esta pantalla la aplicación comenzaba a ser funcional y parecía que lo peor había pasado. En esta fase y tras algunas pruebas, decidí incorporar el icono con el logo según el tipo de Pokémon capturado.

La pantalla de Detalle no me causó grandes inconvenientes, ya que la implementación fue similar a la realizada en la Tarea 1. Me quedé con las ganas de encontrar una solución para el pixelado de la imagen al aumentar su tamaño y añadirle alguna característica adicional. Sin embargo, volver atrás para incorporar características adicionales implicaría modificar el mapeo de las clases y llevaría más tiempo.

## Pantalla de Configuración

Esta pantalla parecía que iba a ser más sencilla de lo que realmente resultó ser. En la opción de deslogueo, no conseguía que la aplicación volviera al inicio de `FirebaseUIActivity` tras el cuadro de diálogo, ya que perdía el contexto y las pilas cuando transfería la actividad desde la clase `DialogLogOutFragment`. Finalmente, gracias a tutoriales, logré que con la línea `intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)` se lanzara la actividad como una nueva actividad de tarea.

## Conclusión Final

La tarea me pareció bastante compleja en comparación con la primera y segunda tarea (aunque el tiempo para realizarla era casi de dos meses). Trabajar con Firestore, una plataforma que desconocía completamente, fue una gran sorpresa y estoy seguro de que volveré a usarla en el futuro por su flexibilidad y sencillez.
## Capturas de pantalla

![imagen](https://github.com/user-attachments/assets/43e31243-d698-4e98-a9a7-b73305952e4c)
![imagen](https://github.com/user-attachments/assets/d1be164e-6f6e-457b-b64b-dc7af60fcfbe)
![imagen](https://github.com/user-attachments/assets/38bc23cc-261c-4e09-bdb4-c9c8b23a1c87)
![imagen](https://github.com/user-attachments/assets/aeaaa7be-530d-4756-a8aa-107eeb3b6f7f)



  

 
