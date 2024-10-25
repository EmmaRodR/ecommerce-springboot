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


### URL Base
- `https://ecommerce-springboot-backend.onrender.com`

---

## Autorización

### Registro e Inicio de Sesión
- **`POST /auth/register`**: Registrar un nuevo usuario
- **`POST /auth/authenticate`**: Iniciar sesión
- **`GET /auth/guest-token`**: Generar token de invitado

---

## Categorías
Crear, obtener, actualizar o eliminar categorías.

- **`GET /api/v1/categories`**: Obtener todas las categorías
- **`POST /api/v1/categories`**: Crear una nueva categoría
- **`GET /api/v1/categories/{id}`**: Obtener categoría por ID
- **`DELETE /api/v1/categories/{id}`**: Eliminar una categoría
- **`PATCH /api/v1/categories/{id}`**: Actualizar una categoría

---

## Órdenes
Crear, ver, actualizar o eliminar órdenes.

- **`GET /api/v1/orders/{userId}`**: Obtener órdenes por ID de usuario
- **`POST /api/v1/orders/{userId}`**: Crear una orden para un usuario
- **`DELETE /api/v1/orders/{userId}`**: Eliminar una orden
- **`PATCH /api/v1/orders/{userId}`**: Actualizar una orden

---

## Carrito
Administrar el contenido del carrito.

- **`GET /api/v1/cart`**: Ver contenido del carrito
- **`POST /api/v1/cart`**: Agregar producto al carrito
- **`DELETE /api/v1/cart`**: Eliminar producto del carrito
- **`PATCH /api/v1/cart`**: Actualizar producto en el carrito
- **`GET /api/v1/cart/merge/{userId}/{sessionId}`**: Fusionar carritos por ID de usuario e ID de sesión

---

## Productos
Crear, obtener, actualizar o eliminar productos.

- **`GET /api/v1/products`**: Obtener todos los productos
- **`POST /api/v1/products`**: Crear un nuevo producto
- **`GET /api/v1/products/{id}`**: Obtener producto por ID
- **`DELETE /api/v1/products/{id}`**: Eliminar un producto
- **`PATCH /api/v1/products/{id}`**: Actualizar un producto
- **`GET /api/v1/products/productsByName`**: Obtener productos por nombre
- **`GET /api/v1/products/productsByCategory`**: Obtener productos por categoría
---

## 🔒 Seguridad

La seguridad se maneja con **Spring Security** y **JWT**. Cada usuario autenticado recibe un token JWT para autorizarse en endpoints protegidos:

```http
Authorization: Bearer <token_jwt>
