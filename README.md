# 🖥️ Bienvenido a SumApplication

***

### :arrow_right: ¿Qué es SumApplication?

SumApplication es un aplicativo que permite llevar a cabo sumas
algebraicas a través de dos dígitos que proporciona el usuario.
Dicho resultado obtenido en la operación es almacenado en una base de
datos PostgreSQL implementada con un contenedor creado con la tecnología Docker,
esto con la finalidad de que la data procesada sea almacenada en un conjunto
de tablas estructuradas.

### 🤷‍♀️ ¿Por qué fue construido SumApplication?

Spring Boot es un módulo que permite realizar aplicaciones de una forma más efectiva
usando Spring Framework, de este modo dicha aplicación fue creada utilizando dicha tecnología
con la finalidad de aprender y practicar los conceptos básicos de SpringBoot.
Aspectos como bien, la infraestructura de capas (controlador, servicio y
repositorio), elaboración de endpoints, aplicación de bases de datos PostgreSQL,
realización de pruebas de unitarias y de integración con módulo JUnit.

### 😉 ¿Como se elaboro SumApplication?

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

## 🧪 Ejecución de Pruebas

SumApplication cuenta con una sección de testing compuesta por pruebas, unitarias y de integración que bien, estas permiten evaluar el comportamiento del aplicativo previamente a su ejecución principal, para el corrido de estas se requiere contar con la siguiente instalación: 

* JDK: `Versión 11 o superior`.
* [Maven Apache](https://maven.apache.org/download.cgi) `Versión 3.8.5 o superior`

Adicionalmente, se necesita la configuración de las [variables de entorno del sistema operativo](https://programmerclick.com/article/27401862932/) con Maven instalado. 

Ahora bien, para correr los distintos test, ejecute el comando a continuación: 

    mvn test 

El comando anterior, tiene la función principal de levantar la aplicación y ejecutar **todos** los testing respectivos
encontrados en la carpeta `.\test` del proyecto.

## 📦 Creación y ejecución de Package con Maven

Para la creación previa de un [package de Maven](https://www.vogella.com/tutorials/ApacheMaven/article.html#:~:text=To%20build%20a%20Maven%20project,as%20parameter%20to%20this%20command.) generativo de un archivo **.jar** ejecutable del aplicativo SumAplicación. sin embargo, se debe contar previamente con lo siguiente: 

* JDK: `Versión 11 o superior`. 

Ahora bien para la creación del package utilizando **Maven**, es necesario la configuración de las [variables de entorno del sistema operativo](https://programmerclick.com/article/27401862932/) con **Maven**, considere que la variable debe ser definida tomando en cuenta el directorio en donde se encuentra instalado el JDK. 

  `Ejemplo: C:\Program Files\Spring5\jdk-17.0.2\bin`
  
Posteriormente, ejecute el siguiente comando de [`Maven Wrapper`](https://github.com/takari/maven-wrapper) en el caso de `Windows SO` para la creación del package. 

    .\mvnw.cmd package 

Completando satisfactoriamente la creación del package, se procede a ejecutarlo. Para ello, es necesario ubicarse en la carpeta en donde fue creado el package de **Maven** del aplicativo, y luego ejecute lo siguiente:

    java -jar .\target\sumApplication-0.0.1-SNAPSHOT.jar

## ⏲️Instalación y ejecución en IDE

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

## 🧑‍💻Tecnologías Utilizadas

* [JDK](https://www.oracle.com/co/java/technologies/javase/javase8-archive-downloads.html): Version 11
* [Maven](https://maven.apache.org/): Version 4
* [SpringBoot](https://spring.io/projects/spring-boot): Version 2.6.5.
* [PostgreSQL](https://www.postgresql.org/): Version 12
* [Docker](https://www.docker.com/): Version 20.10.13
* [JUnit](https://junit.org/junit5/): Version 5.8.2
* [Testcontainers](https://www.testcontainers.org/): Version 1.16.3

## :page_with_curl: Enlaces Útiles
 
* [¿Qué es un JDK](https://www.ibm.com/docs/es/i/7.3?topic=platform-java-development-kit).
* [¿Qué son las pruebas unitarias y de integración?](https://cleventy.com/pruebas-de-unidad-e-integracion-en-un-proyecto-spring-boot/)
* [¿Cómo instalar Docker?](https://www.docker.com/get-started/)

&copy; 2022 Hecho con ❤️ por Ysnaldo J. López H. , Todos los derechos reservados.   