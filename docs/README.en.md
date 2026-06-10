<p align="center">
  <a href="../README.md">
    <img src="https://img.shields.io/badge/Language-Português-green?style=for-the-badge">
  </a>
</p>

# Library System

Library management system developed in Java, using Object-Oriented Programming (OOP), layered architecture (Controller, Service, and Repository), and a MySQL database.

The system allows the management of users, authors, books, copies, and loans, simulating the operation of a real library.

---

# Features

## Users
- Register users
- Search users by ID
- List users
- Update users
- Remove users

## Authors
- Register authors
- Search authors by ID
- List authors
- Update authors
- Remove authors

## Books
- Register books
- Associate authors with books
- Search books by ID
- List books
- Update books
- Remove books

## Copies
- Register copies
- Search copies
- List copies
- Deactivate copies
- Availability control

## Loans
- Create loans
- Register returns
- Calculate late fees
- View loan history
- Generate reports

---

# Technologies Used

- Java
- Maven
- JDBC
- MySQL
- Object-Oriented Programming (OOP)
- DTO (Data Transfer Object)
- Repository Pattern
- Service Layer Pattern

---

# Project Structure

```text
src
│
├── application
│   └── controller
│
├── model
│   ├── dto
│   └── enums
│
├── repository
│
├── service
│
└── database
```

## Layers

### Controller
Responsible for user interaction.

### Service
Responsible for business rules.

### Repository
Responsible for database access.

### Model
Responsible for representing the system entities.

---

# Database

## Create Database

```sql
CREATE DATABASE db_LibrarySystem;

USE db_LibrarySystem;
```

## User Table

```sql
CREATE TABLE usuario(
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    data_nascimento DATE NOT NULL
);
```

## Author Table

```sql
CREATE TABLE autor(
    id_autor INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    nacionalidade VARCHAR(40) NOT NULL,
    data_nascimento DATE NOT NULL
);
```

## Book Table

```sql
CREATE TABLE livro(
    id_livro INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    volume INT NOT NULL,
    editora VARCHAR(100) NOT NULL,
    genero VARCHAR(45) NOT NULL
);
```

## Book-Author Relationship

```sql
CREATE TABLE livro_autor(
    id_livro INT,
    id_autor INT,

    PRIMARY KEY(id_livro, id_autor),

    FOREIGN KEY(id_livro) REFERENCES livro(id_livro),
    FOREIGN KEY(id_autor) REFERENCES autor(id_autor)
);
```

## Copy Table

```sql
CREATE TABLE exemplar(
    id_exemplar INT PRIMARY KEY AUTO_INCREMENT,
    id_livro INT,
    patrimonio INT NOT NULL,
    localizacao VARCHAR(200),
    status VARCHAR(15),

    FOREIGN KEY(id_livro) REFERENCES livro(id_livro)
);
```

## Loan Table

```sql
CREATE TABLE emprestimo(
    id_emprestimo INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,

    data_emprestimo DATE NOT NULL,
    data_retorno DATE NOT NULL,
    data_entrega DATE,

    multa DECIMAL(15,2),

    FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
);
```

## Loan-Copy Relationship

```sql
CREATE TABLE emprestimo_exemplar(
    id_emprestimo INT,
    id_exemplar INT,

    PRIMARY KEY(id_emprestimo, id_exemplar),

    FOREIGN KEY(id_emprestimo) REFERENCES emprestimo(id_emprestimo),
    FOREIGN KEY(id_exemplar) REFERENCES exemplar(id_exemplar)
);
```

---

# Business Model

- A user can have multiple loans.
- A book can have multiple authors.
- An author can write multiple books.
- A book can have multiple copies.
- A loan can contain multiple copies.
- A copy can participate in multiple loans over time.

---

# How to Run

## 1. Clone the Repository

```bash
git clone https://github.com/your-username/Library-System.git
```

## 2. Create the Database

Run the SQL script provided in this README.

## 3. Configure the Connection

Update the credentials in the connection class:

```java
private static final String URL =
"jdbc:mysql://localhost:3306/db_LibrarySystem";

private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

## 4. Run the Project

Open the project in a Java IDE:

- IntelliJ IDEA
- NetBeans
- Eclipse

Run the main class to start the system.

---

# Project Objectives

This project was developed with the goal of practicing:

- Object-Oriented Programming
- JDBC
- Database Modeling
- SQL Relationships
- DTOs
- Layered Architecture
- Java Best Practices

---

# Author

Lucas Carvalho Jesus

Student of Analysis and Systems Development (ADS).

Academic project developed to consolidate knowledge in Java, Databases, Software Engineering, and Layered Architecture.
