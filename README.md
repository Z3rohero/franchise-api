# franchise-api

> ⚠️ En refactorizacion (rama `refactor/franchise-api`): este README se completa en el
> ultimo bloque de la refactorizacion, cuando los endpoints y la persistencia esten listos.

API REST para gestionar una lista de franquicias: cada franquicia tiene un nombre y un
listado de sucursales, cada sucursal tiene un nombre y un listado de productos, y cada
producto tiene un nombre y una cantidad de stock.

Desarrollada con Java 21 y Spring Boot 3.2.5 siguiendo una arquitectura hexagonal, y
persistida en Supabase (Postgres). Este proyecto reutiliza la arquitectura de una
refactorizacion previa (`events-api`) aplicada a un dominio nuevo.

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

Las tablas se crean/actualizan automaticamente (`spring.jpa.hibernate.ddl-auto=update`).
Si prefieres provisionarlas manualmente en el editor SQL de Supabase, usa los scripts de
[`docs/sql`](./docs/sql) (diagrama, `schema.sql` y `seed.sql`).

## Documentacion interactiva

Con la aplicacion en ejecucion:

- Swagger UI: http://localhost:8080/swagger-ui.html
- Contrato OpenAPI: http://localhost:8080/api-docs

## Pruebas y cobertura

```bash
mvn verify
```

## Docker

```bash
mvn clean package
docker build -t franchise-api .
docker run --rm -p 8080:8080 --env-file .env franchise-api
```
