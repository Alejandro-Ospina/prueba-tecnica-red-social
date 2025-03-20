# prueba-tecnica-red-social
A continuación, se muestra el backend de la prueba técnica red social.

## Cómo ejecutar los contenedores a través de docker compose
Para ejecutar los microservicios siga los siguientes pasos:

### 1. Clonar el repositorio
clone el repositorio en cualquier directorio de su sistema por medio de:
```
git clone https://github.com/Alejandro-Ospina/prueba-tecnica-red-social.git
```

### 2. Ingrese al directorio creado cuando se clona el repositorio
Dentro del directorio donde se encuentra el repositorio clonado, ejecute el comando:
```
sudo docker compose up --build -d
```

## Notas aclaratorias
* Tener en cuenta que se debe editar el archivo docker compose, y application.properties de cada microservicio de acuerdo a la configuración dada en la base de datos tipo postgre

  ```docker compose.yml
  environment:
      POSTGRES_USER: type-here-username
      POSTGRES_PASSWORD: type-here-pass-db
      POSTGRES_DB: type-here-db-name
  ...
  environment:
      SPRING_DATASOURCE_URL: type-here-postgre-data-base-url
      SPRING_DATASOURCE_USERNAME: type-here-user-name
      SPRING_DATASOURCE_PASSWORD: type-here-pass-db
      TOKEN_SIGNATURE: set-here-signature-pass-minumun-64-characters
  ```

  ```application.properites
  spring.datasource.url=type-here-url-postrge-data-base-url
  spring.datasource.username=type-here-user-name
  spring.datasource.password=type-here-pass-db
  
  token.signature=set-here-signature-pass-minumun-64-characters
  ```
* Debe tener docker instalado el sistema. Para mas información, acceder a este [enlace](https://docs.docker.com/engine/)
* Consultar los siguientes enlaces para leer la documentación en local y poner a prueba cada endpoint (solo cuando ejecute el docker compose)
  ```
  http://localhost:8081/servicio-usuarios.html
  http://localhost:8082/servicio-publicaciones.html
  ```
* Se comparte en el adjunto del correo un archivo json llamado ```redsocial.postman_collection.json``` para exportar en postman y poner a prueba todos los endpoints de cada microservicio.
* Todos los usuarios tienen contraseña ```12345```, para facilitar el logeo y depuración de pruebas. Consultar el archivo ```init.sql``` que contiene la información de usuarios de prueba y publicaciones.

## Conclusiones
Agradezco por el tiempo y la consideración. Pese al tiempo ajustado, desarrollé un backend robusto que cumple con los requerimientos de la prueba técnica. Sin embargo, pido disculpas porque no pude añadir la capa de front. 
Muchas gracias por la atención.
