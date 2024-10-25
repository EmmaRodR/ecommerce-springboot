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


### Base URL
- `https://ecommerce-springboot-backend.onrender.com`

---

## Authorization

### Register and Login
- **`POST /auth/register`**: Register a new user
- **`POST /auth/authenticate`**: Login
- **`GET /auth/guest-token`**: Generate guest token

---

## Categories
Create, read, update, or delete categories.

- **`GET /api/v1/categories`**: Get all categories
- **`POST /api/v1/categories`**: Create a category
- **`GET /api/v1/categories/{id}`**: Get category by ID
- **`DELETE /api/v1/categories/{id}`**: Delete a category
- **`PATCH /api/v1/categories/{id}`**: Update a category

---

## Orders
Create, view, update, or delete orders.

- **`GET /api/v1/orders/{userId}`**: Get orders by user ID
- **`POST /api/v1/orders/{userId}`**: Create an order for a user
- **`DELETE /api/v1/orders/{userId}`**: Delete an order
- **`PATCH /api/v1/orders/{userId}`**: Update an order

---

## Cart
Manage cart contents.

- **`GET /api/v1/cart`**: View cart contents
- **`POST /api/v1/cart`**: Add product to cart
- **`DELETE /api/v1/cart`**: Remove product from cart
- **`PATCH /api/v1/cart`**: Update product in cart
- **`GET /api/v1/cart/merge/{userId}/{sessionId}`**: Merge carts by user ID and session ID

---

## Products
Create, read, update, or delete products.

- **`GET /api/v1/products`**: Get all products
- **`POST /api/v1/products`**: Create a new product
- **`GET /api/v1/products/{id}`**: Get product by ID
- **`DELETE /api/v1/products/{id}`**: Delete a product
- **`PATCH /api/v1/products/{id}`**: Update a product
- **`GET /api/v1/products/productsByName`**: Get products by name
- **`GET /api/v1/products/productsByCategory`**: Get products by category

---

## 🔒 Seguridad

La seguridad se maneja con **Spring Security** y **JWT**. Cada usuario autenticado recibe un token JWT para autorizarse en endpoints protegidos:

```http
Authorization: Bearer <token_jwt>
