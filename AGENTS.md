# AGENTS.md - Contexto y Guía del Proyecto

Este documento proporciona una visión general técnica, la arquitectura y las pautas para agentes de IA y desarrolladores que trabajen en este proyecto.

## 📌 Descripción y Contexto del Proyecto
`6-cinema-mcp-server` es una aplicación Java construida con **Spring Boot** e integrada con **Spring AI MCP Server WebMVC** (`spring-ai-starter-mcp-server-webmvc`) para exponer herramientas y recursos mediante el protocolo Model Context Protocol (MCP).

### 🎬 Dominio de Negocio (Cartelera de Cine)
El servidor gestiona la información de un complejo de cine:
- **Cartelera de Películas:** Título, género, duración y sinopsis de las películas activas.
- **Horarios y Funciones:** Programación de funciones por sala y bloques de horarios (ej. matiné 14:00, tarde 17:30, estelar 21:00).
- **Público Recomendado / Clasificación:** Calificación por edades y público objetivo (ej. *TE - Todo Espectador*, *+13 - Mayores de 13 años*, *+16 - Mayores de 16 años*, *+18 - Adultos*).

---

## 🛠️ Tecnologías y Dependencias
- **Java:** 25
- **Spring Boot:** 4.1.0
- **Spring AI:** 2.0.0 (`spring-ai-starter-mcp-server-webmvc`)
- **Gestor de dependencias:** Maven (`mvnw`)

---

## 🎯 Skills Disponibles

El proyecto cuenta con las skills copiadas en `.agents/skills/`:

### 1. `spring-boot-best-practices`
- **Ubicación:** `.agents/skills/spring-boot-best-practices/SKILL.md`
- **Descripción:** Guía para la creación, refactorización y extensión de aplicaciones Spring Boot siguiendo arquitectura en capas limpia, mejores prácticas de desarrollo y estándares de Java moderno.
- **Trigger / Cuándo invocar:** Debe invocarse **SIEMPRE** que el usuario solicite crear una API de Spring Boot, un monolito Spring Web, o cuando se pida crear, agregar, refactorizar o modificar un `Entity` (model), `Repository`, `Service`, `Controller`, `DTO` o `Mapper`.

---

## 🏗️ Arquitectura y Estructura del Código

La aplicación sigue una arquitectura limpia de Spring Boot:

```
src/main/java/com/andres/course/agy/springboot/cinemamcpserver/app/
└── Application.java
```

---

## ⚙️ Configuración (`application.properties`)

```properties
spring.application.name=6-cinema-mcp-server
server.port=8081

spring.ai.mcp.server.name=cinema-mcp-server
spring.ai.mcp.server.version=1.0.0
spring.ai.mcp.server.type=SYNC
spring.ai.mcp.server.protocol=STREAMABLE
spring.ai.mcp.server.streamable-http.mcp-endpoint=/mcp
```

---

## 🚀 Comandos de Construcción y Verificación

- **Compilar el proyecto:**
  ```bash
  ./mvnw clean compile
  ```
- **Ejecutar la aplicación:**
  ```bash
  ./mvnw spring-boot:run
  ```
