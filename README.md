# 🛒 E-commerce API - Backend Spring Boot

Proyecto backend para una aplicación de ecommerce, desarrollado en **Spring Boot** y **Java**. Este API ofrece gestión completa de usuarios, productos, categorías, y ordenes, con una seguridad robusta mediante **JWT**. El proyecto incluye usuarios de prueba, está documentado con Swagger para facilitar la integración y prueba de endpoints, y utiliza una base de datos **PostgreSQL** alojada en Render.

---


  🔹 **Explora la API con Swagger**:  
Para probar y documentar los endpoints de la API, he utilizado **Swagger**, que facilita la interacción y exploración de los recursos disponibles. Puedes ver la documentación en tiempo real aquí: [Documentación de Swagger](https://ecommerce-springboot-backend.onrender.com/swagger-ui.html).

Los tiempos de espera y rendimiento de la app pueden estar condicionados por el servidor donde esta alojado. Talvez demore en realizar ciertas acciones o cargas de elementos.


### Home
![Texto alternativo](https://i.postimg.cc/2St5BS75/swaggerhome.png)
### Auth
![Texto alternativo](https://i.postimg.cc/fRyLL9XF/auth.png)
### Products
![Texto alternativo](https://i.postimg.cc/3JnNYBym/Products-Swagger.png)
### Categories
![Texto alternativo](https://i.postimg.cc/SNyRB5Km/Categorieswagger.png)
### Cart
![Texto alternativo](https://i.postimg.cc/HW0jPHdS/Cartswagger.png)
### Orders
![Texto alternativo](https://i.postimg.cc/0jCQ6GVg/ordersswagger.png)



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
  - Usuario: `Customer`
  - Contraseña: `Customer12345`
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

## Categorías
Crear, obtener, actualizar o eliminar categorías.

- **`GET /api/v1/categories`**: Obtener todas las categorías
- **`POST /api/v1/categories`**: Crear una nueva categoría
- **`GET /api/v1/categories/{id}`**: Obtener categoría por ID
- **`DELETE /api/v1/categories/{id}`**: Eliminar una categoría
- **`PATCH /api/v1/categories/{id}`**: Actualizar una categoría

---

## Carrito
Administrar el contenido del carrito.

- **`GET /api/v1/cart`**: Ver contenido del carrito
- **`POST /api/v1/cart`**: Agregar producto al carrito
- **`DELETE /api/v1/cart`**: Eliminar producto del carrito
- **`PATCH /api/v1/cart`**: Actualizar producto en el carrito
- **`GET /api/v1/cart/merge/{userId}/{sessionId}`**: Fusionar carritos por ID de usuario e ID de sesión

---

## Órdenes
Crear, ver, actualizar o eliminar órdenes.

- **`GET /api/v1/orders/{userId}`**: Obtener órdenes por ID de usuario
- **`POST /api/v1/orders/{userId}`**: Crear una orden para un usuario
- **`DELETE /api/v1/orders/{userId}`**: Eliminar una orden
- **`PATCH /api/v1/orders/{userId}`**: Actualizar una orden

---

## 👥 ERM

![ERM](https://i.postimg.cc/g2QKpRXY/ERMecommerce.png)



## Autor

Emmanuel Rodriguez - [LinkedIn](https://www.linkedin.com/in/emmanuelrodr%C3%ADguezbuzzo/)
