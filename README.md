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

- FirebaseAuth:

  El primer “encontronazo” vino con el proceso de logueo de la aplicación , ya que una vez registrado no me permitia introducir la contraseña , lo que entraba en bucle infinito. Para solucionarlo y gracias a foros encontrados a traves de la red, desactive la protección de enumeracion de correo electronico. Ya con esto permitía logar en la app. 

- Libreria Retrofit:

   Es la primera vez que la usaba y el mapeo de las diferentes clases ha requerido de mucho tiempo de ver manuales y videos.  La principal dificultad la encontré en crear los tipos de datos correctos para mapear, ya que tenia que conocer bien donde se encontraba el dato especifico que queria encontrar. En algunos casos (como el tipo de pokemon) se encontraba anidado dentro de un array slot[] lo que supueso varios intentos.

   La logica para el desarrollo  fueron separar en dos clases la obtención de datos:  JsonRespuesta(listado de pokemons)  y  JsonRespuestaDetalle(pokemon especifico con el detalle y el numero de pagina de la url)  y generar las clases necesarias.
Una vez obtenida la lista de pokemons, a traves de un listener, inyecto todos los datos del pokemon Caputrado a la base de datos, siguiente la ruta users/pokemonCapturados.

- FirebaseFirestore.

   Trabajar con Firestore ha sido desafiante ya que me costó bastante descargar los datos de cada uno de los pokemons capturados y meterlos en una lista de PokemonCapturado. Tras varios fracasos y mucho debug, la opción que funcionaba fue la de crear mapas de tipo <String, Object>    e ir poco a poco separando los datos en el tipo correcto. 
	
	Este punto fue decisivo para el desarrollo de la aplicación, ya que a partir de esta pantalla la aplicación comenzaba a ser funcional y parecia que lo peor había pasado. En esta fase y tras algunas pruebas, decidí incorporar el icono con el logo según el tipo de pokemons capturado.  
	
	La pantalla de Detalle no me causó grandes inconvenientes ya que la implementaciín fue parecida a la realizada en la Tarea1. SI me quedé con las ganas de poder buscar alguna opcion al pixelado de la imagen cuando se aumentaba el tamaño y poder añadirle alguna que otra caracteristica. Pero volver hacia atrás para incorporar alguna caracteristica adicional implicaría modificar el mapeo de las calses y llevaría más tiempo. 

  
- Pantalla Config.

   Esta pantalla parecía que iba a ser más cómoda de lo que realmene llegó a ser ya que en la opción de deslogueo no conseguía que volveria al inicio de FirebaseUIActivity tras el cuadro de dialogo, ya que perdía el contexto (y las pilas) cuando desde la clase DialogLogOutFragment  transferia la actividad. Finalmente y gracias a tutoriales  logré que con la linea intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)  se lanzara la actividad como una nueva actividad de tarea

- Conclusion final: la tarea me ha parecido bastante compleja comparandola con la primera y segunda tarea (aunque el tiempo para poder realizarla era casi de 2 meses), el trabajar con firestore , (plataforma que desconocia completamente) ha sido una gran sorpresa y seguroq eu volveré a usarla en el futuro por su flexibilidad y sencillez

## Capturas de pantalla



  

 
