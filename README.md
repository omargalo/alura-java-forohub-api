# alura-java-forohub-api

## API REST en Java + Spring + MySQL

Este proyecto es una API RESTful desarrollada con Java y Spring Boot que utiliza MySQL como base de datos. El objetivo es gestionar un foro de discusión, permitiendo realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre los tópicos del foro.

### Requisitos

- Java 21
- Spring Boot 3.3.0
- Maven 4.0.0
- MySQL
 
### Configuración

1. Clonar el repositorio:
   ```sh
   git clone https://github.com/omargalo/alura-java-forohub-api.git
   cd alura-java-forohub-api
   ```
   1. Configurar la base de datos en application.properties:
       ```properties
      spring.application.name=forohub.api
      spring.datasource.url = jdbc:mysql://${AZMYSQL_HOST}
      spring.datasource.username = ${AZMYSQL_USER}
      spring.datasource.password = ${AZMYSQL_PASSWORD}
      api.security.secret=${JWT_SECRET}
      spring.flyway.enabled=true
      spring.flyway.locations=classpath:db/migration
      ```
La API está documentada con Swagger. Para acceder a la documentación, levanta la aplicación y navega a http://localhost:8080/swagger-ui.html.

###  Endpoints Principales:

- Registro de Usuario

    - Método: POST
    - URI: /api/auth/register
    - Request Body:
    ```json
    {
      "nombre": "Nombre de Usuario",
      "email": "usuario@test.com",
      "password": "contraseñaSegura"
    }
    ```

- Login de Usuario

    - Método: POST
    - URI: /api/auth/login
    - Request Body:
    ```json
    {
      "username": "usuario@example.com",
      "password": "contraseñaSegura"
    }
    ```

- Crear Tópico

    - Método: POST
    - URI: /api/topicos
    - Request Body:
    ```json
    {
      "titulo": "Nuevo Tópico",
      "mensaje": "Mensaje del nuevo tópico",
      "autorId": 1,
      "cursoId": 1,
      "status": "Activo"
    }
    ```

- Listar Tópicos:

    - Método: GET
    - URI: /api/topicos
    - Parámetros opcionales:
      - nombreCurso, anio, page, size

- Obtener Tópico por ID:

    - Método: GET
    - URI: /api/topicos/{id}

- Actualizar Tópico

    - Método: PUT
    - URI: /api/topicos/{id}
    - Request Body:
    ```json
    {
      "titulo": "Título Actualizado",
      "mensaje": "Mensaje actualizado",
      "status": "Activo",
      "cursoId": 1
    }
    ```
- Eliminar Tópico

    - Método: DELETE
    - URI: /api/topicos/{id}
