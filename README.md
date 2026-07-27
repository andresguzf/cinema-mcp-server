# 🎬 Cinema MCP Server (`6-cinema-mcp-server`)

Servidor **MCP (Model Context Protocol)** desarrollado con **Spring Boot** y **Spring AI WebMVC**, diseñado para expone información sobre la cartelera de cine, horarios de funciones y clasificación de público recomendado a clientes de IA.

---

## 📌 Dominio de Negocio (Cartelera de Cine)

El servidor gestiona y expone la información clave de un complejo de cine:

- **Cartelera de Películas:** Título, género, duración y sinopsis de películas activas.
- **Horarios y Funciones:** Programación de funciones por sala y bloques de horarios (Matiné 14:00, Tarde 17:30, Estelar 21:00).
- **Público Recomendado / Clasificación:** Calificación por edades (ej. *TE - Todo Espectador*, *+13 - Mayores de 13 años*, *+16 - Mayores de 16 años*, *+18 - Adultos*).

---

## 🛠️ Tecnologías y Dependencias

- **Java:** 25
- **Spring Boot:** 4.1.0
- **Spring AI:** 2.0.0 (`spring-ai-starter-mcp-server-webmvc`)
- **Protocolo MCP:** Streamable HTTP (`STREAMABLE`)
- **Gestor de dependencias:** Maven (`mvnw`)

---

## ⚙️ Configuración (`application.properties`)

La aplicación está configurada para ejecutarse en el puerto **8081** exponiendo el protocolo **Streamable HTTP** en el endpoint `/mcp`:

```properties
spring.application.name=6-cinema-mcp-server
server.port=8081

# Spring AI MCP Server WebMVC Configuration
spring.ai.mcp.server.name=cinema-mcp-server
spring.ai.mcp.server.version=1.0.0
spring.ai.mcp.server.type=SYNC
spring.ai.mcp.server.protocol=STREAMABLE
spring.ai.mcp.server.streamable-http.mcp-endpoint=/mcp
```

---

## 🏗️ Estructura del Proyecto

```
6-cinema-mcp-server/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/com/andres/course/agy/springboot/cinemamcpserver/app/
│   │   │   └── Application.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── AGENTS.md
├── pom.xml
└── README.md
```

---

## 🚀 Comandos de Construcción y Ejecución

### Compilar el Proyecto
```bash
./mvnw clean compile
```

### Ejecutar la Aplicación
```bash
./mvnw spring-boot:run
```

Una vez iniciada la aplicación, el servidor MCP estará disponible en `http://localhost:8081/mcp`.
