E-Commerce Backend API

A comprehensive e-commerce backend system built with Spring Boot as part of my computer engineering internship. This project demonstrates modern Java development practices, RESTful API design, and enterprise-level backend architecture.

Table of Contents

- [About The Project](#about-the-project)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

About The Project

This repository contains the backend API for an e-commerce application, designed to handle core operations such as product management, user registration/authentication, order processing, and more. The project aims to apply real-world software engineering principles and Spring Boot best practices.

Features

- User Authentication & Authorization (JWT)
- Product Catalog Management (CRUD)
- Cart and Order Management
- Category Management
- RESTful API Design
- Exception Handling
- Validation & Error Responses
- Pagination & Sorting
- Role-based Access Control
- Logging & Monitoring

Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- Hibernate
- H2 / PostgreSQL (switchable DB)
- Lombok
- Maven

## Getting Started

### Prerequisites

- Java 17+
- Maven
- (Optional) MySQL Server

### Installation

1. Clone the repository:**
   ```bash
   git clone https://github.com/ejderburak/e-commerce.git
   cd e-commerce
   ```

2. Configure Database:
   - To use MySQL, update `src/main/resources/application.properties` with your DB credentials.


API Endpoints

Some core endpoints (see Swagger UI for full list):

- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Authenticate and get JWT
- `GET /api/products` - List products (with pagination/sorting)
- `POST /api/products` - Add new product (admin only)
- `PUT /api/products/{id}` - Update product (admin only)
- `DELETE /api/products/{id}` - Delete product (admin only)
- `GET /api/categories` - List categories
- `POST /api/cart` - Add item to cart
- `POST /api/orders` - Place an order

> For full API specification, use Swagger UI or refer to the controller classes.

Project Structure

```
src/
 └── main/
     ├── java/
     │    └── com/
     │         └── ejderburak/
     │              └── ecommerce/
     │                   ├── controller/
     │                   ├── service/
     │                   ├── model/
     │                   ├── repository/
     │                   ├── security/
     │                   └── exception/
     └── resources/
          ├── application.properties
```

Contributing

Contributions are welcome! If you want to learn or improve the codebase, feel free to open issues or submit pull requests. Please follow conventional commit messages and ensure tests pass.

Contact

- Author: Burak Ejder
- LinkedIn: www.linkedin.com/in/burak-ejder-941692246
- Email: brkejderr@gmail.com
- GitHub: [ejderburak](https://github.com/ejderburak)

---

> This project is part of my computer engineering internship and serves as a learning exercise for mastering Spring Boot and backend development.
