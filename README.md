# franchise-api

API REST para gestionar una lista de franquicias: cada franquicia tiene un nombre y un
listado de sucursales, cada sucursal tiene un nombre y un listado de productos, y cada
producto tiene un nombre y una cantidad de stock.

Desarrollada con Java 21 y Spring Boot 3.2.5 siguiendo una arquitectura hexagonal
(puertos y adaptadores), y persistida en Supabase (Postgres).

## Tecnologias principales

- Java 21, Spring Boot 3.2.5, Maven
- Spring Data JPA + Postgres (Supabase)
- Springdoc OpenAPI y Swagger UI
- JUnit 5, Mockito y AssertJ
- ArchUnit para validar la arquitectura
- JaCoCo para medir la cobertura

## Requisitos

- JDK 21
- Maven 3.9 o una version posterior (o usa el `mvn` de tu IDE)
- Un proyecto de Supabase (o cualquier Postgres accesible)

## Configuracion

Copia `.env.example` a `.env` y completa los valores de tu base de datos. `.env` esta en
`.gitignore`: nunca subas credenciales reales al repositorio.

| Variable | Valor predeterminado | Descripcion |
|---|---|---|
| `SERVER_PORT` | `8080` | Puerto en el que se ejecuta la API. |
| `CORS_ORIGIN` | `http://localhost:4200` | Origenes autorizados, separados por comas, sin ruta ni barra final. |
| `DB_URL` | *(localhost, no funcional)* | Cadena JDBC de conexion a Postgres/Supabase. |
| `DB_USERNAME` | *(vacio)* | Usuario de la base de datos. |
| `DB_PASSWORD` | *(vacio)* | Password de la base de datos. |

La aplicacion importa `.env` si existe. Tambien puedes exportar las variables antes de ejecutar:

```bash
# bash
set -a; source .env; set +a
mvn spring-boot:run
```

```powershell
# PowerShell
Get-Content .env | ForEach-Object {
  if ($_ -match '^([^#=]+)=(.*)$') { [System.Environment]::SetEnvironmentVariable($matches[1], $matches[2]) }
}
mvn spring-boot:run
```

Las tablas se crean/actualizan automaticamente al arrancar
(`spring.jpa.hibernate.ddl-auto=update`). Si prefieres provisionarlas manualmente en el
editor SQL de Supabase (o quieres el `CHECK (stock >= 0)` y los indices que Hibernate no
genera por si solo), usa los scripts de [`docs/sql`](./docs/sql): el diagrama
entidad-relacion, `schema.sql` (`CREATE TABLE`) y `seed.sql` (datos de ejemplo).

## Documentacion interactiva

Con la aplicacion en ejecucion:

- Swagger UI: http://localhost:8080/swagger-ui.html
- Contrato OpenAPI: http://localhost:8080/api-docs

## Endpoints

Todas las respuestas exitosas usan el formato `{ success, message, data, timestamp }`. Los
errores usan `{ success: false, message, timestamp }`.

| Metodo | Ruta | Descripcion |
|---|---|---|
| `POST` | `/api/franchises` | Crea una franquicia. |
| `GET` | `/api/franchises` | Lista todas las franquicias. |
| `GET` | `/api/franchises/{id}` | Consulta una franquicia con sus sucursales y productos. |
| `PATCH` | `/api/franchises/{id}/name` | Actualiza el nombre de una franquicia. |
| `GET` | `/api/franchises/{id}/top-stock-products` | Producto con mas stock por cada sucursal de la franquicia. |
| `POST` | `/api/franchises/{franchiseId}/branches` | Agrega una sucursal a una franquicia. |
| `PATCH` | `/api/branches/{id}/name` | Actualiza el nombre de una sucursal. |
| `POST` | `/api/branches/{branchId}/products` | Agrega un producto a una sucursal. |
| `DELETE` | `/api/branches/{branchId}/products/{productId}` | Elimina un producto de una sucursal. |
| `PATCH` | `/api/products/{id}/stock` | Actualiza el stock de un producto (valor absoluto, `>= 0`). |
| `PATCH` | `/api/products/{id}/name` | Actualiza el nombre de un producto. |

### Producto con mas stock por sucursal (`GET /api/franchises/{id}/top-stock-products`)

Para la franquicia indicada, devuelve un listado con el producto de mayor stock **de cada
una de sus sucursales** (una sucursal sin productos no aparece en el resultado). Cada
elemento indica a que sucursal pertenece:

```json
{
  "success": true,
  "message": "Top stock products per branch retrieved successfully.",
  "data": [
    { "branchId": "...", "branchName": "Downtown", "productId": "...", "productName": "Soda 400ml", "stock": 60 },
    { "branchId": "...", "branchName": "Uptown", "productId": "...", "productName": "Milkshake", "stock": 30 }
  ],
  "timestamp": "2026-09-23T15:00:00Z"
}
```

## Pruebas y cobertura

```bash
mvn verify
```

Ejecuta las pruebas unitarias (dominio y casos de uso), las reglas de ArchUnit y el chequeo de
cobertura de JaCoCo. El reporte HTML se genera en `target/site/jacoco/index.html`.

El umbral de cobertura (60% lineas / 50% ramas) aplica solo sobre dominio y capa de
aplicacion (la logica de negocio real); DTOs, mappers, controladores, adaptadores de
persistencia y configuracion quedan fuera del calculo por ser codigo de paso con poco valor
en pruebas unitarias aisladas.

## Arquitectura

```text
src/main/java/com/franchise
├── domain          Entidades (Franchise, Branch, Product, con anotaciones JPA) y excepciones
├── application     Puertos de entrada, puertos de salida y casos de uso
└── infrastructure  Controladores REST, DTO, mapeadores, persistencia JPA y configuracion
```

Los controladores dependen de puertos de entrada; los casos de uso acceden a la persistencia
mediante puertos de salida. Las entidades de dominio llevan anotaciones JPA directamente (en
vez de una entidad de persistencia separada) para no duplicar clases dado el alcance del
mini-proyecto; ArchUnit verifica que el dominio no dependa de Spring, Bean Validation ni
Lombok, y que las capas no se salten el orden de dependencias (dominio ← aplicacion ←
infraestructura).

El calculo del producto con mas stock por sucursal (`GetTopStockProductPerBranchUseCase`) se
resuelve con streams (estilo funcional) sobre el arbol Franchise → Branch → Product ya cargado,
en vez de una consulta imperativa con bucles.

## Docker

```bash
mvn clean package
docker build -t franchise-api .
docker run --rm -p 8080:8080 --env-file .env franchise-api
```
