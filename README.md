# Tech Challenge

## 📌 Requisitos

Antes de iniciar, asegúrate de tener instalado lo siguiente en tu sistema:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Git
- RabbitMQ Docker Image
  * puede ser obtenido con el comando:
  ```bash
  docker pull rabbitmq
  ```
  * para probar la imagen:
  ```bash
  docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management 
  ```

---

## 🚀 Instalación y Despliegue

### 1️⃣ Clonar el Repositorio
```bash
git clone https://github.com/Pisanchez2/microtechtest.git
cd microtechtest
```

### 2️⃣ Configurar Variables de Entorno
Crea un archivo `.env` en la raíz del proyecto:

```bash
touch .env
nano .env
```

Agrega las siguientes variables y guarda (modificar de acuerdo a la configuracion local):
```env
# App Configuration
APP_NAME=microtechtest

# MySQL Database Configuration
SPRING_DATASOURCE_DOCKER_URL=jdbc:mysql://mysql_db:3306/<DBNAME>?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/<DBNAME>?useSSL=false&serverTimezone=UTC
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
```

### 3️⃣ Levantar los Contenedores con Docker Compose
```bash
docker compose up --build -d
```

Este comando:
- Levanta un contenedor MySQL con la base de datos `techChallengeDB`
- Inicia la aplicación Spring Boot en otro contenedor

---

## ✅ Verificaciones Post-Despliegue

### Comprobar que los Contenedores Están Corriendo
```bash
docker ps
```

### Ver Logs de los Contenedores
```bash
docker logs mysql_db
docker logs cliente_service
docker logs cuenta_service
docker logs rabbitmq_broker
```

### Probar Conexión a la Base de Datos
```bash
docker exec -it mysql_db mysql -uuser -ppassword -e "SHOW DATABASES; USE techChallengeDB; SHOW TABLES;"
```

### Acceder a la Aplicación desde el Navegador
Para gestion de cliente-persona
```plaintext
http://localhost:8081
```
Para gestion de cuenta-movimientos
```plaintext
http://localhost:8082
```

---

## 🔧 Detener y Eliminar Contenedores

Para detener los contenedores sin eliminar volúmenes:
```bash
docker compose down
```

Para eliminar los volúmenes (datos persistentes de MySQL):
```bash
docker compose down -v
```