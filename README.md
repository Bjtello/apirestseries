# 📺 Servicio Web REST: Gestión de Catálogo de Series

![Java](https://img.shields.io/badge/Java-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x%20/%204.x-brightgreen?logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data-JPA-blue?logo=spring&logoColor=white)
![H2 Database](https://img.shields.io/badge/Database-H2%20In--Memory-yellow)
![Maven](https://img.shields.io/badge/Build-Maven-red?logo=apachemaven&logoColor=white)
![License](https://img.shields.io/badge/Status-Completed-success)

Backend desarrollado como parte de la asignatura de **Programación Back-End** de la **Pontificia Universidad Católica del Ecuador**. Este microservicio expone una API REST para la administración integral de operaciones CRUD, de un catálogo multimedia de series de televisión, gestionando el ciclo de vida de los datos mediante persistencia relacional en memoria.

---

## 📑 Tabla de Contenidos
- [Características del Sistema](#-características-del-sistema)
- [Pila Tecnológica](#-pila-tecnológica)
- [Diseño y Arquitectura](#-diseño-y-arquitectura)
- [Catálogo de Endpoints](#-catálogo-de-endpoints)
- [Ejemplos de Carga Útil (Payloads)](#-ejemplos-de-carga-útil-payloads)
- [Guía de Puesta en Marcha](#-guía-de-puesta-en-marcha)
- [Inspección de Datos en H2](#-inspección-de-datos-en-h2)
- [Suite de Pruebas con Postman](#-suite-de-pruebas-con-postman)

---

## 🚀 Características del Sistema
* **Controlador REST desacoplado:** Gestión estructurada de rutas semánticas bajo `/api/series`.
* **Respuestas HTTP estandarizadas:** Uso estricto de `ResponseEntity` con códigos de respuesta adecuados (`200 OK`, `201 Created`, `204 No Content`, `404 Not Found`).
* **Abstracción de Persistencia:** Manejo declarativo de consultas y operaciones de base de datos a través de Spring Data JPA y `JpaRepository`.
* **Manejo funcional de opcionales:** Control defensivo de registros no encontrados empleando `java.util.Optional`.
* **Código conciso:** Implementación de Project Lombok para eliminar código boilerplate (getters, setters, constructores).

---

## 🛠 Pila Tecnológica
| Componente | Detalle / Versión | Propósito |
| :--- | :--- | :--- |
| **Lenguaje** | Java 27  | Plataforma de ejecución principal |
| **Framework** | Spring Boot | Núcleo de inyección de dependencias y configuración |
| **Persistencia** | Spring Data JPA / Hibernate | Mapeo objeto-relacional (ORM) y CRUD automatizado |
| **Motor de BD** | H2 Database Engine | Motor relacional ligero en memoria volátil |
| **Herramientas** | Project Lombok | Reducción de código repetitivo mediante anotaciones |
| **Gestor de Compilación** | Apache Maven Wrapper | Resolución de dependencias y construcción del proyecto |
| **Testing de API** | Postman | Ejecución de requests y verificación de endpoints |

---

## 🏗 Diseño y Arquitectura

El proyecto adopta un patrón en capas enfocado en la separación de responsabilidades:

```text
[ Cliente HTTP / Postman ]
          │  (Petición JSON)
          ▼
   ┌───────────────┐
   │ SerieController│  ── Controla rutas, serializa/deserializa y emite HttpStatus
   └───────┬───────┘
           │  (Llamada a método)
           ▼
   ┌───────────────┐
   │  SerieService │  ── Aplica reglas de negocio y orquesta la persistencia
   └───────┬───────┘
           │  (Acceso ORM)
           ▼
   ┌───────────────┐
   │SerieRepository│  ── Interfaz JPA conectada al motor relacional
   └───────┬───────┘
           │  (SQL automático)
           ▼
   ┌───────────────┐
   │  H2 Database  │  ── Base de datos en memoria (tabla 'series')
   └───────────────┘
```

### Organización de Paquetes (`ec.edu.puce.seriesapi`)
* `models`: Entidad `Serie` mapeada a la tabla `series` con campos: `id`, `titulo`, `plataforma` y `calificacion`.
* `repository`: Interfaz `SerieRepository` que extiende de `JpaRepository<Serie, Long>`.
* `service`: `SerieService`, mediador encargado del procesamiento seguro de entidades.
* `controller`: `SerieController`, expone los servicios web vía protocolo HTTP.

---

## 📡 Catálogo de Endpoints

Todos los servicios están disponibles bajo la URL base: `http://localhost:8080/api/series`

| Verbo | Ruta Relativa | Código Éxito | Código Error | Acción Realizada |
| :---: | :--- | :---: | :---: | :--- |
| `GET` | `/` | `200 OK` | - | Obtiene la lista con todas las series dadas de alta. |
| `GET` | `/{id}` | `200 OK` | `404 Not Found` | Localiza y devuelve una serie según su identificador numérico. |
| `POST` | `/` | `201 Created` | `400 Bad Request` | Registra una nueva serie en la base de datos. |
| `PUT` | `/{id}` | `200 OK` | `404 Not Found` | Reemplaza y actualiza la información de una serie existente. |
| `DELETE`| `/{id}` | `204 No Content` | `404 Not Found` | Da de baja una serie registrada por su ID. |

---

## 📦 Ejemplos de Carga Útil (Payloads)

### Registro de Series (POST)
```json
{
  "titulo": "La Casa de Papel",
  "plataforma": "Netflix",
  "calificacion": 8.5
}
```

```json
{
  "titulo": "Los Simuladores",
  "plataforma": "Telefe",
  "calificacion": 9.3
}
```

### Modificación de Serie Existente (PUT `/api/series/1`)
```json
{
  "titulo": "La Casa de Papel (Temporada Final)",
  "plataforma": "Netflix",
  "calificacion": 9.0
}
```

---

## 💻 Guía de Puesta en Marcha

### Prerrequisitos
* Tener instalado el **Java Development Kit (JDK) 21** o superior.
* Git configurado en tu equipo.

### Pasos de Instalación y Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/Bjtello/apirestseries.git
   cd apirestseries
   ```

2. **Compilar y verificar la aplicación:**
   * En sistemas Unix / macOS:
     ```bash
     ./mvnw clean test
     ```
   * En sistemas Windows (PowerShell / CMD):
     ```powershell
     .\mvnw.cmd clean test
     ```

3. **Arrancar el servidor de desarrollo:**
   * Mediante Maven Wrapper:
     ```bash
     ./mvnw spring-boot:run
     ```
   * O ejecutando la clase principal `ec.edu.puce.seriesapi.SeriesApiApplication` desde su entorno preferido (IntelliJ IDEA, Eclipse, VS Code).

4. **Verificación rápida con cURL:**
   ```bash
   curl -X GET http://localhost:8080/api/series
   ```

---

## 🗄 Inspección de Datos en H2

Spring Boot levanta de forma predeterminada una consola interactiva para examinar la base de datos en memoria en tiempo real:

* **Dirección de la consola:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
* **Driver Class:** `org.h2.Driver`
* **JDBC URL:** `jdbc:h2:mem:seriesdb`
* **Usuario (Username):** `sa`
* **Contraseña (Password):** *(dejar el campo vacío)*

> **Consulta SQL de muestra:**
> ```sql
> SELECT id, titulo, plataforma, calificacion FROM series;
> ```

---

## 📮 Suite de Pruebas con Postman

Se incluye en el proyecto la colección preconfigurada para probar de forma inmediata cada uno de los métodos expuestos:

1. Ubique el archivo en: `postman/api-rest-series.postman_collection.json`.
2. Abra Postman y haga clic en **Import**.
3. Seleccione el archivo JSON para importar la colección **API REST Series - Pruebas CRUD**.
4. La colección cuenta con las 9 pruebas ordenadas secuencialmente, evaluando tanto respuestas exitosas como validaciones defensivas ante registros inexistentes.
