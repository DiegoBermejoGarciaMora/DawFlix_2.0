# INSTRUCCIONES PARA EJECUTAR EL PROGRAMA

## ESTRUCTURA DE CARPETAS

- `src`: Alberga la clase “DAWFlix.java” que ejecuta el programa y el resto de carpetas
- `Model`: Contiene carpetas de las clases y el “ComparatorByName” para ordenar
- `Interface`: Interfaz reproducible que contiene la función reproducir para los contenidos
- `Exceptions`: Excepciones que pueda llegar a lanzar el programa
- `DAO`: Base de datos de contenidos y usuarios (base, administradores y baneados)

## EJECUCIÓN
1. *MENU DE INICIO DE SESION O CREACION DE CUENTA*
La clase principal “DAWFlix.java” ejecutará el programa. Saldrá el menú de inicio de sesión o de registro, con la opción de cerrar el programa si no se desea hacer ninguna de las opciones.
>OPCIÓN 1. Iniciar Sesion: El programa pedirá un email (sin el @gmail / @hotmail) y una contraseña, creando un objeto con el que comparar en los arrays de usuarios y administradores. Si el usuario está baneado, saldrá un mensaje de error. Para iniciar el programa, los usuarios "principales" son: email: user1, contraseña: user1 como usuario base, email: admin, contraseña: admin como usuario administrador y email: userBan, contraseña: userBan para el usuario baneado. También existe un usuario base email: a, contraseña: a para un inicio de sesion rapido.

>OPCION 2. Crear Cuenta: El programa pedirá un nombre de usuario, un email (sin el @gmail / @hotmail) y una contraseña. Para confirmar la contraseña, se deberá escribir de manera correcta 2 veces, si las contraseñas no coinciden, saldrá un mensaje de error y volverá a pedir la primera contraseña. Ahora deberemos escoger el tipo de suscripción que queramos, cada una tiene un precio y duración. Una vez creada la cuenta, podremos iniciar sesion como en el apartado anterior. Si el usuario ya existe o está en la lista de baneados, saldra un mensaje de error.

>OPCION 3. Salir: El programa se cerrará

2. *MENU PRINCIPAL*
Si se ha iniciado sesión con una cuenta administradora, habrán 5 opciones más: Añadir Contenido, Borrar Contenido, Mostrar Usuarios Baneados, Banear Usuario y Desbanear Usuario. También tendrá las opciones base de Mostrar Todos los Contenidos, Seleccionar Contenidos y Visualizar Cuenta.
>OPCION 1. Muestra todos los contenidos ordenados alfabéticamente agrupados por tipo de contenido (Peliculas, Serie y Musica en este orden).

>OPCION 2. Seleccionar Contenido: Pedirá el titulo del contenido que se vaya a seleccionar. Si el contenido no existe, saldra un mensaje de error. Una vez encontrado el contenido, saldrán 3 opciones más.
    >OPCION 2.1. Reproducir: Reproduce el contenido y lo añade a la lista "reciente" del usuario
    >OPCION 2.2. Añadir a Favoritos: Añade el contenido a la lista "favoritos" del usuario, si ya está la lista, saldrá un mensaje de error.
    >OPCION 2.3. Atras: Vuelve al menú principal

>OPCION 3. Visualizar Cuenta: Muestra el nombre de usuario y los favoritos y recientes.

>OPCION 4. Opcion Administradora. Añadir Contenido: Pide elegir el tipo de contenido (Peliculas, Serie o Musica) o salir. Según el tipo de contenido, pedirá unos atributos u otros.

>OPCION 5. Opcion Administradora. Borrar Contenido: Pide elegir el tipo de contenido (Peliculas, Serie o Musica) o salir. Solo habrá que escribir el titulo del contenido. El programa comprueba si el contenido existe o no.

>OPCION 6. Opcion Administradora. Mostrar Usuarios Baneados: Muestra una lista con los usuarios baneados, estos no podrán iniciar sesion.

>OPCION 7. Opcion Administradora. Banear Usuario: Pide escribir el email del usuario. Comprueba si el email existe o no. Cuando se cierre sesion y se intente iniciar sesion con ese usuario, saldra un mensaje de error.

>OPCION 8. Opcion Administradora. Desbanear Usuario: Pide escribir el email de un usuario. Comprueba si el email esta baneado o no. Cuando se cierre sesion y se intente iniciar sesión con ese usuario, el programa se lo permitirá.

>OPCION 0. Cerrar Sesion: Se cierra sesión y vuelve al menu de inicio de sesion o creacion de cuenta.
Para poder salir del programa, es obligatorio cerrar sesión.

## Author
- [@DiegoBermejoGarciaMora](https://github.com/DiegoBermejoGarciaMora)

## Link Video
- [https://drive.google.com/file/d/1on7OAz2l3FpFfW68nQjbd9Gyq_tw9zlG/view?usp=sharing]
