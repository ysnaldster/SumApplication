
# Bienvenido a SumApplication
***
### ¿Qué es SumApplication? 
SumApplication es un aplicativo que permite llevar a cabo sumas 
algebraicas a través de dos dígitos que proporciona el usuario. 
Dicho resultado obtenido en la operación es almacenado en una base de 
datos PostgreSQL implementada con un contenedor creado con la tecnología Docker, 
esto con la finalidad de que la data procesada sea almacenada en un conjunto 
de tablas estructuradas.

### ¿Por qué fue construido SumApplication? 
Spring Boot es un módulo que permite realizar aplicaciones de una forma más efectiva 
usando Spring Framework, de este modo dicha aplicación fue creada utilizando dicha tecnología 
con la finalidad de aprender y practicar los conceptos básicos de SpringBoot. 
Aspectos como bien, la infraestructura de capas (controlador, servicio y 
repositorio), elaboración de endpoints, aplicación de bases de datos PostgreSQL, 
realización de pruebas de unitarias y de integración con módulo JUnit. 

### ¿Como se elaboro SumApplication? 
Se optó por la aplicación de la herramienta SpringBoot, con fin de llevar a cabo un 
aplicativo que cumpla con la tarea de realizar un esquema completo cliente - servidor.
A través de la creación de endpoints interactivos que permitan sumar dos números ingresados
por el lado de cliente; para luego ser procesados del lado del servidor, sumándolos e insertándolos
en una base de datos con el fin de llevar a cabo un registro de cada operación realizada. 

## Tabla de Contenido
1. Instalación y ejecución
2. Tecnologías Utilizadas
3. Pruebas Unitarias 
4. Pruebas de Integración 
5. Enlaces Útiles

## Arranque de SumApplication
Para inicializar SumApplication debe de contarse con los siguientes requerimientos previos: 

* JDK: Versión 11. 
* Apache Maven: Versión > 3.8.5.
* Docker: Version 20.10.13 o superior. 


Por otro lado, el proyecto cuenta con un archivo [.jar](https://docs.oracle.com/javase/8/docs/technotes/guides/jar/jarGuide.html) 
ejecutable por terminal. Para ello, debe **desplazarse a la ubicación en donde se encuentra guardado el proyecto,** 
y luego emplear el comando expuesto a continuación:

    java -jar .\target\sumApplication-0.0.1-SNAPSHOT.jar

De igual forma, SumApplication cuenta con una sección de testing compuesta por pruebas, unitarias y de integración que bien,
permiten evaluar el comportamiento del aplicativo previamente a su ejecución principal, estos pueden ser
ejecutados a través del siguiente comando:

    mvn test 

Dicho comando, tiene la función principal de levantar la aplicación y ejecutar **todos** los testing respectivos
encontrados en la carpeta `.\test` del proyecto.

## Instalación y ejecución en IDE
De acuerdo a la sección anterior, el presente proyecto se elaboró utilizando la
tecnología de SpringBook, el cual es conocido como un famoso framework del lenguaje
de programación java [Spring Boot](https://spring.io/projects/spring-boot#overview). Por ende,
es importante tener instalado previamente lo siguiente:

* JDK en versión 11, [si lo desea puede revisar la 
documentación oficial de Java](https://www.java.com/es/download/help/windows_manual_download.html). 
* Editor de código o IDE de preferencia. [Se recomienda IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/)

De igual forma, [la documentación oficial de Spring Boot proporciona una guia de instalación 
detallada](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/getting-started.html#getting-started.first-application)
.Se recomienda revisarla previamente.

Ahora bien, para ejecutar el proyecto siga los pasos dictados a continuación. 

* Aperture el proyecto en su IDE. 
* Inicialice un contenedor Docker previamente con una imagen PostgreSQL de su preferencia. 
* Corra el proyecto con la opción RUN de su IDE.
* Utilice el navegador o un software de preferencia ([Se recomienda Postman](https://www.postman.com/))
para enviar los datos empleando el endpoint preferido. 

## Tecnologías Utilizadas

* [JDK](https://www.oracle.com/co/java/technologies/javase/javase8-archive-downloads.html): Version 11
* [Maven](https://maven.apache.org/): Version 4
* [SpringBoot](https://spring.io/projects/spring-boot): Version 2.6.5.
* [PostgreSQL](https://www.postgresql.org/): Version 12
* [Docker](https://www.docker.com/): Version 20.10.13
* [JUnit](https://junit.org/junit5/): Version 5.8.2
* [Testcontainers](https://www.testcontainers.org/): Version 1.16.3


## Pruebas Unitarias 
>"Estos test son responsables de probar una unidad funcional y aislada de código"
> 
> *Cleventy*

Como se especifica anteriormente este tipo de test se encarga de probar el comportamiento
de una unidad de código, que bien a efectos de la aplicación en Spring Boot puede ser un
determinado servicio. Ahora bien, para el presente proyecto se realizo una prueba unitaria 
encargada de corroborar que el servicio de suma este retornando la operación algebráica, dicho 
test se encuentra ubicación en la siguiente ruta. 
> ./src/java/service/**SumServiceTest**  

## Pruebas de Integración 
>"Eson responsables de probar la integración/comunicación entre diferentes unidads funcionales. 
> Estos tests están bajo la carpeta integration. Para todos estos tests se levanta un
> API local y se pasa por todas las capas necesarias para el test, probando de esta 
> manera, a su vez, controlador, servicios, repositorios y cualquier otra lógica 
> implicada."
>
> *Cleventy*

Utilizando la tecnología de JVM se implementó el uso de TestContainers para generar 
un contenedor docker con una imagen de PostgreSQl (Versión 12). Con fin, de realizar 
las pruebas ante una base de datos real. En el caso particular de SumApplication se 
elaboraron dos pruebas que permiten los siguientes aspectos.

* Validar el resultado optenido del endpoint 
* Revisar el funcionamiento del repositorio e integración de capas del aplicativo. 

Dichas pruebas se encuentran ubicadas en las rutas expuestas a continuación. 
> ./src/java/controller/**SumControllerSaveDataTests**
> 
> ./src/java/controller/**SumControllerTests**

## Enlaces Útiles
* [¿Qué es un JDK](https://www.ibm.com/docs/es/i/7.3?topic=platform-java-development-kit).
* [¿Qué son las pruebas unitarias y de integración?](https://cleventy.com/pruebas-de-unidad-e-integracion-en-un-proyecto-spring-boot/)
* [¿Cómo instalar Docker?](https://www.docker.com/get-started/)


&copy; 2022 Ysnaldo J. López H. , Todos los derechos reservados.   