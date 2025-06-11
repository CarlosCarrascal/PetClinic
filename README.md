# Laboratorio 11 - Spring Boot con CI/CD

Este proyecto es una aplicación Spring Boot que implementa operaciones CRUD básicas para usuarios y perfiles, con una integración completa de CI/CD utilizando Jenkins.

## Requisitos

- JDK 17
- Maven 3.6+
- MySQL 8.0+ (para entorno de producción)
- Jenkins (para CI/CD)

## Estructura del Proyecto

- `/src/main/java`: Código fuente principal
- `/src/main/resources`: Recursos de la aplicación
- `/src/test/java`: Pruebas unitarias y de integración
- `/src/test/resources`: Recursos para pruebas

## Configuración de Base de Datos

### Producción
La aplicación está configurada para conectarse a MySQL. Edita `src/main/resources/application.properties` para configurar la conexión.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lab11
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### Pruebas
Para las pruebas, se utiliza H2 (base de datos en memoria). La configuración ya está en `src/test/resources/application-test.properties`.

## Ejecución Local

1. Clona el repositorio
2. Configura la base de datos MySQL
3. Ejecuta la aplicación:

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: http://localhost:8090

## Pruebas

El proyecto incluye:
- Pruebas unitarias para servicios
- Pruebas unitarias para repositorios
- Pruebas de controladores
- Pruebas de integración

Para ejecutar las pruebas:

```bash
mvn test
```

## CI/CD con Jenkins

El proyecto incluye un `Jenkinsfile` para la configuración de un pipeline de CI/CD que:
1. Compila el código
2. Ejecuta las pruebas
3. Genera el paquete final
4. Archiva los artefactos

### Configuración de Jenkins

1. Instalar Jenkins
2. Configurar las herramientas en Jenkins:
   - JDK 17
   - Maven 3.9.9
3. Crear un nuevo Pipeline apuntando al repositorio Git
4. Seleccionar "Pipeline script from SCM"
5. Apuntar al archivo Jenkinsfile en el repositorio

## Endpoints

- `/userForm` - Formulario para crear usuarios
- `/saveUser` - Guardar un usuario
- `/profileForm` - Formulario para crear perfiles
- `/saveProfile` - Guardar un perfil 