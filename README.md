
# LAB4 - CINEMA-PART II
### Descripción

En este repositorio se se encontrará la solución al laboratorio 4
de arsw, el objetivo es comprender el funcionamiento de spring y el
diseño de api rest, el cual usa los verbos del protocolo Http
GET,POST,PUT,DELETE y spring utiliza los respectivos @Requestmapping
para realizarlos.

### Pre requisitos

Para correr el proyecto debe tener instalados los siguientes programas
en su computador:

- Java SE Development Kit 8
- Java SE Runtime Environment 8
- Maven para poder construir el proyecto.
- Git para clonar el repositorio. 

### Instrucciones de instalación

Desde la terminal ejecutar los siguientes comandos:
```
 git clone https://github.com/AlejandroBohal/LAB4-CINEMAII-ARSW
 cd "LAB4-CINEMAII-ARSW" 
```
compilar el proyecto con el siguiente comando:
```
 mvn package
```
Para correr el servidor local de spring con maven utilizaremos 
el siguiente comando en el directorio raíz
```
mvn springboot:run
```
### Pruebas

Para correr las pruebas usar el siguiente comando.
```
mvn test;
```

## Parte I

- Integre al proyecto base suministrado los 
Beans desarrollados en el Ejercicio Anterior.
 Sólo copie las clases, NO los archivos 
 de configuración. Rectifique que se 
 tenga correctamente configurado el 
 esquema de inyección de dependencias
  con las anotaciones @Service y @Autowired.
  
![](https://media.discordapp.net/attachments/352624122301513730/752726281753329684/unknown.png?width=569&height=475)

- Modifique el bean de persistecia 'InMemoryCinemaPersistence' para que por defecto se inicialice con al menos otras 2 salas de cine, y al menos 2 funciones asociadas a cada una.

![](https://media.discordapp.net/attachments/352624122301513730/752726736684187728/unknown.png?width=891&height=475)

- Configure su aplicación para que ofrezca el recurso "/cinema", de manera que cuando se le haga una petición GET, retorne -en formato jSON- el conjunto de todos los cines. 
Para esto:
  - Modifique la clase CinemaAPIController teniendo en cuenta el ejemplo de controlador REST hecho con SpringMVC/SpringBoot. (ver code 1)
  - Haga que en esta misma clase se inyecte el bean de tipo CinemaServices (al cual, a su vez, se le inyectarán sus dependencias de persistencia y de filtrado de películas).
  - De ser necesario modifique el método getAllCinemas(), de manera que utilice la persistencia previamente inyectada y retorne todos los cines registrados.

![](https://media.discordapp.net/attachments/352624122301513730/752727847134822450/unknown.png?width=1026&height=461)

- Verifique el funcionamiento de a aplicación lanzando la aplicación con maven (ver code 2). Y luego enviando una petición GET a:  http://localhost:8080/cinemas. Rectifique que, como respuesta, se obtenga un objeto jSON con una lista que contenga el detalle de los cines suministados por defecto.

![](https://media.discordapp.net/attachments/352624122301513730/752729223277314148/unknown.png?width=604&height=475)

- Modifique el controlador para que ahora, acepte peticiones GET al recurso /cinemas/{name}, el cual retorne usando una representación jSON todas las funciones del cine cuyo nombre sea {name}. Si no existe dicho cine, se debe responder con el código de error HTTP 404. Para esto, revise en la documentación de Spring, sección 22.3.2, el uso de @PathVariable. De nuevo, verifique que al hacer una petición GET -por ejemplo- a recurso http://localhost:8080/cinemas/cinemaY , se obtenga en formato jSON el conjunto de funciones asociadas al cine 'cinemaY' (ajuste esto a los nombres de cine usados en el punto 2).

![](https://media.discordapp.net/attachments/352624122301513730/752729814116466778/unknown.png?width=733&height=475)

![](https://media.discordapp.net/attachments/352624122301513730/752729898451337226/unknown.png)

- Modifique el controlador para que ahora, acepte peticiones GET al recurso /cinemas/{name}/{date}, el cual retorne usando una representación jSON una lista de funciones asociadas al cine cuyo nombre es {name} y cuya fecha sea {date}, para mayor facilidad se seguirá manejando el formato "yyyy-MM-dd". De nuevo, si no existen dichas funciones, se debe responder con el código de error HTTP 404.

![](https://media.discordapp.net/attachments/352624122301513730/752730763417485382/unknown.png?width=1026&height=367)
![](https://media.discordapp.net/attachments/352624122301513730/752731299902652556/unknown.png)

- Modifique el controlador para que ahora, acepte peticiones GET al recurso /cinemas/{name}/{date}/{moviename}, el cual retorne usando una representación jSON sólo UNA función, en este caso es necesario detallar además de la fecha, la hora exacta de la función de la forma "yyyy-MM-dd HH:mm". Si no existe dicha función, se debe responder con el código de error HTTP 404.

![](https://media.discordapp.net/attachments/352624122301513730/752732849962221628/unknown.png?width=1026&height=385)
![](https://media.discordapp.net/attachments/352624122301513730/752732463884795974/unknown.png)

## Parte II

- Agregue el manejo de peticiones POST (creación de nuevas funciones), de manera que un cliente http pueda registrar una nueva función a un determinado cine haciendo una petición POST al recurso ‘/cinemas/{name}’, y enviando como contenido de la petición todo el detalle de dicho recurso a través de un documento jSON. Para esto, tenga en cuenta el siguiente ejemplo, que considera -por consistencia con el protocolo HTTP- el manejo de códigos de estados HTTP (en caso de éxito o error): (ver code 3)

![](https://media.discordapp.net/attachments/352624122301513730/752736526235861112/unknown.png?width=1026&height=367)
![](https://media.discordapp.net/attachments/352624122301513730/752736249625968640/unknown.png?width=421&height=475)

- Para probar que el recurso ‘cinemas’ acepta e interpreta correctamente las peticiones POST, use el comando curl de Unix. Este comando tiene como parámetro el tipo de contenido manejado (en este caso jSON), y el ‘cuerpo del mensaje’ que irá con la petición, lo cual en este caso debe ser un documento jSON equivalente a la clase Cliente (donde en lugar de {ObjetoJSON}, se usará un objeto jSON correspondiente a una nueva función: (ver code 4) Con lo anterior, registre un nueva función (para 'diseñar' un objeto jSON, puede usar esta herramienta): Nota: puede basarse en el formato jSON mostrado en el navegador al consultar una función con el método GET.

![](https://media.discordapp.net/attachments/352624122301513730/752739441688182794/unknown.png?width=1026&height=250)

- Teniendo en cuenta el nombre del cine, la fecha y hora de la función y el nombre de la película, verifique que el mismo se pueda obtener mediante una petición GET al recurso '/cinemas/{name}/{date}/{moviename}' correspondiente.

![](https://media.discordapp.net/attachments/352624122301513730/752741617361879121/unknown.png?width=539&height=475)

- Agregue soporte al verbo PUT para los recursos de la forma '/cinemas/{name}', de manera que sea posible actualizar una función determinada, el servidor se encarga de encontrar la función correspondiente y actualizarla o crearla.

![](https://media.discordapp.net/attachments/352624122301513730/752743396938088478/unknown.png?width=1026&height=367)
![](https://cdn.discordapp.com/attachments/352624122301513730/752742917399117824/unknown.png)

## Parte III

La solución de la parte 3 se encuentra aquí:

https://github.com/AlejandroBohal/LAB4-CINEMAII-ARSW/