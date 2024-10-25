# 🛒 E-commerce API - Backend

Un proyecto backend para una aplicación de ecommerce, desarrollado en **Spring Boot** y **Java**. Este API ofrece gestión completa de usuarios, productos, categorías, y pedidos, con una seguridad robusta mediante **JWT**. El proyecto incluye usuarios de prueba, está documentado con Swagger para facilitar la integración y prueba de endpoints, y utiliza una base de datos **PostgreSQL** alojada en Render.

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
  - Rol: `CLIENTE`

---

## 📋 Endpoints Principales

### Autenticación y Usuarios
- `POST /api/auth/register`: Registro de nuevos usuarios.
- `POST /api/auth/login`: Autenticación y generación de JWT.
- `GET /api/users/{id}`: Obtiene información del usuario.

### Productos y Categorías
- `GET /api/products`: Lista de productos.
- `POST /api/products`: Crea un producto (Administrador).
- `PUT /api/products/{id}`: Actualiza un producto.
- `DELETE /api/products/{id}`: Elimina un producto (Administrador).
- `GET /api/categories`: Lista de categorías.

### Carrito y Órdenes
- `POST /api/cart`: Añadir productos al carrito.
- `GET /api/cart`: Ver contenido del carrito.
- `POST /api/orders`: Crear orden de compra a partir del carrito.

### Gestión de Órdenes
- `GET /api/orders`: Listado de todas las órdenes.
- `GET /api/orders/{id}`: Detalle de una orden específica.
- `PUT /api/orders/{id}`: Actualiza el estado de una orden.
- `DELETE /api/orders/{id}`: Elimina una orden (Administrador).

---

## 🔒 Seguridad

La seguridad se maneja con **Spring Security** y **JWT**. Cada usuario autenticado recibe un token JWT para autorizarse en endpoints protegidos:

```http
Authorization: Bearer <token_jwt>
