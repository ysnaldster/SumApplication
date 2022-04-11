#Bienvenido a SumApplication
###¿Qué es SumApplication? 
SumApplication es un aplicativo que permite llevar a cabo sumas 
algebraicas a través de dos dígitos que proporciona el usuario. 
Dicho resultado obtenido en la operación es almacenado en una base de 
datos PostgreSQL implementada con un contenedor creado con la tecnología Docker, 
esto con la finalidad de que la data procesada sea almacenada en un conjunto 
de tablas estructuradas.

###¿Por qué fue construido SumApplication? 
Spring Boot es un módulo que permite realizar aplicaciones de una forma más efectiva 
usando Spring Framework, de este modo dicha aplicación fue creada utilizando dicha tecnología 
con la finalidad de aprender y practicar los conceptos básicos de SpringBoot. 
Aspectos como bien, la infraestructura de capas (controlador, servicio y 
repositorio), elaboración de endpoints, aplicación de bases de datos PostgreSQL, 
realización de pruebas de unitarias y de integración con módulo JUnit. 

###¿Como se elaboro SumApplication? 
Se optó por la aplicación de la herramienta SpringBoot, con fin de llevar a cabo un 
aplicativo que cumpla con la tarea de realizar un esquema completo cliente - servidor.
A través de la creación de endpoints interactivos que permitan sumar dos números ingresados
por el lado de cliente; para luego ser procesados del lado del servidor, sumándolos e insertándolos
en una base de datos con el fin de llevar a cabo un registro de cada operación realizada. 

***
##Lista de Contenido 
| N° item | Contenido                                      |
|---------|------------------------------------------------| 
| 1       | ¿Cómo instalar y ejecutar SumApplication?      |
| 2       | ¿Qué tecnologías y herramientas se utilizaron? |
| 3       | Estructura de archivos                         |
| 4       | Pruebas unitarias                              | 
| 5       | Pruebas de integración                         | 
| 6       | Enlaces de recursos                            | 

##¿Cómo instalar y ejecutar SumApplication?
Como se indicó en la sección de introducción dicho proyecto se elaboro utilizando la 
tecnología de SpringBook, el cual es conocido como un famoso framework del lenguaje 
de programación java [Spring Boot](https://spring.io/projects/spring-boot#overview). Por ende,
es importante tener instalado previamente lo siguiente: 

1. JDK en versión igual o superior a la 11. Leer más. [¿Qué es un JDK](https://www.ibm.com/docs/es/i/7.3?topic=platform-java-development-kit).
2. Editor de código o IDE de preferencia. [Se recomienda IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/)

Ahora bien, para ejecutar el proyecto siga los pasos dictados a continuación. 

1. Aperture el proyecto en su IDE. 
2. Inicialice un contenedor Docker previamente con una imagen PostgreSQL de su preferencia. 
3. Corra el proyecto con la opción RUN de su IDE.
4. Utilice el navegador para enviar los datos empleando el endpoint preferido. 

##¿Qué tecnologías y herramientas se utilizaron?

A continuación, se especifican la lista de tecnologías utilizadas para la elaboracion 
de SumApplication: 
* HTTP.
* PostgreSQL.
* JDBC Template (NamedParameterJdbcTemplate).
* JUnit (Pruebas unitarias y de integración).
* Docker.
* Test Containers (Pruebas unitarias y de integración).
* Gestos de PostgreSQL PGAdmin 
(Opcional para consultar el almacenamiento de información).

## Pruebas Unitarias 
Se realizó un test respectivo para probar la utilización del servicio de suma. Bien
puede ser ejecutada a través del comando RUN del IDE de preferencia. 

##Pruebas de Integración 
Utilizando la tecnología de JVM se implementó el uso de TestContainers para generar 
un contenedor docker con una imagen de PostgreSQl (Versión 12). Con fin, de realizar 
las pruebas ante una base de datos real.

&copy; 2022 Ysnaldo J. López H. , Todos los derechos reservados.   