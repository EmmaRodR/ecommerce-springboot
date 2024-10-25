# 🛒 E-commerce API - Backend Spring Boot

Este es proyecto backend para una aplicación de ecommerce, desarrollado en **Spring Boot** y **Java**. Este API ofrece gestión completa de usuarios, productos, categorías, y ordenes, con una seguridad robusta mediante **JWT**. El proyecto incluye usuarios de prueba, está documentado con Swagger para facilitar la integración y prueba de endpoints, y utiliza una base de datos **PostgreSQL** alojada en Render.

---

## 📚 Resumen del Proyecto

- **CRUD Completo**: Gestión de productos, categorías, usuarios y pedidos.
- **Autenticación Segura**: Implementada con JWT para manejar roles de administrador y cliente.
- **Carrito y Órdenes**: Carrito de compras y generación de órdenes de compra.
- **Documentación Swagger**: Explora la API con Swagger en tiempo real.
- **Usuarios de Prueba**: Usuarios predeterminados (Admin e Invitado) para pruebas rápidas.

---

## 🛠️ Tecnologías y Herramientas Utilizadas

- **Java 17**: Lenguaje de programación.
- **Spring Boot 3**: Framework principal.
- **Spring Data JPA**: Abstracción para la persistencia de datos usando Hibernate como ORM.
- **Spring Security**: Manejo de seguridad.
- **JWT**: Autenticación basada en tokens.
- **Hibernate**: ORM para gestionar la persistencia de datos.
- **Swagger**: Generación de documentación de la API.
- **PostgreSQL**: Base de datos alojada en Render.
- **Maven**: Construcción y gestión de dependencias.

---

## 👥 Usuarios de Prueba

Para probar rápidamente, se incluyen dos usuarios preconfigurados:

- **Administrador**:
  - Usuario: `Admin`
  - Contraseña: `ADMIN12345`
  - Rol: `ADMIN`

- **Invitado**:
  - Usuario: `Invitado`
  - Contraseña: `Invitado123`
  - Rol: `CUSTOMER`

---

## 📋 Endpoints Principales

### Autenticación y Usuarios
- `POST /api/v1/auth/register`: Registro de nuevos usuarios.
- `POST /api/v1/auth/login`: Autenticación y generación de JWT.

### Productos
- `GET /api/v1/products`: Lista de productos.
- `POST /api/v1/products`: Crea un producto (Solo Administrador).
- `PATCH /api/v1/products/{id}`: Actualiza un producto (Solo Administrador).
- `DELETE /api/v1/products/{id}`: Elimina un producto (Solo Administrador).

### Categorías
- `GET /api/v1/categories`: Lista de categorías.
- `POST /api/v1/categories`: Crea una categoría (Solo Administrador).
- `PATCH /api/v1/categories/{id}`: Actualiza una categoría (Solo Administrador).
- `DELETE /api/v1/categories/{id}`: Elimina una categoría (Solo Administrador).

### Carrito y Órdenes
- `POST /api/v1/cart`: Añadir productos al carrito.
- `GET /api/v1/cart`: Ver contenido del carrito.
- `POST /api/v1/orders`: Crear orden de compra a partir del carrito.

### Gestión de Órdenes
- `GET /api/v1/orders`: Listado de todas las órdenes.
- `GET /api/v1/orders/{id}`: Detalle de una orden específica.
- `PUT /api/v1/orders/{id}`: Actualiza el estado de una orden.
- `DELETE /api/v1/orders/{id}`: Elimina una orden (Administrador).

---

## 🔒 Seguridad

La seguridad se maneja con **Spring Security** y **JWT**. Cada usuario autenticado recibe un token JWT para autorizarse en endpoints protegidos:

```http
Authorization: Bearer <token_jwt>
