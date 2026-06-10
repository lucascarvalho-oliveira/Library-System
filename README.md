<p align="center">
  <a href="./docs/README.en.md">
    <img src="https://img.shields.io/badge/Language-English-blue?style=for-the-badge">
  </a>
</p>

# Library System

Sistema de gerenciamento de biblioteca desenvolvido em Java, utilizando Programação Orientada a Objetos (POO), arquitetura em camadas (Controller, Service e Repository) e banco de dados MySQL.

O sistema permite o gerenciamento de usuários, autores, livros, exemplares e empréstimos, simulando o funcionamento de uma biblioteca real.

---

# Funcionalidades

## Usuários
- Cadastrar usuário
- Buscar usuário por ID
- Listar usuários
- Atualizar usuário
- Remover usuário

## Autores
- Cadastrar autor
- Buscar autor por ID
- Listar autores
- Atualizar autor
- Remover autor

## Livros
- Cadastrar livro
- Associar autores ao livro
- Buscar livro por ID
- Listar livros
- Atualizar livro
- Remover livro

## Exemplares
- Cadastrar exemplar
- Consultar exemplar
- Listar exemplares
- Inativar exemplar
- Controle de disponibilidade

## Empréstimos
- Realizar empréstimo
- Registrar devolução
- Calcular multa por atraso
- Consultar histórico de empréstimos
- Gerar relatórios

---

# Tecnologias Utilizadas

- Java
- Maven
- JDBC
- MySQL
- Programação Orientada a Objetos (POO)
- DTO (Data Transfer Object)
- Repository Pattern
- Service Layer Pattern

---

# Estrutura do Projeto

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

## Camadas

### Controller
Responsável pela interação com o usuário.

### Service
Responsável pelas regras de negócio.

### Repository
Responsável pelo acesso ao banco de dados.

### Model
Responsável por representar as entidades do sistema.

---

# Banco de Dados

## Criar Banco

```sql
CREATE DATABASE db_LibrarySystem;

USE db_LibrarySystem;
```

## Tabela Usuario

```sql
CREATE TABLE usuario(
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    data_nascimento DATE NOT NULL
);
```

## Tabela Autor

```sql
CREATE TABLE autor(
    id_autor INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    nacionalidade VARCHAR(40) NOT NULL,
    data_nascimento DATE NOT NULL
);
```

## Tabela Livro

```sql
CREATE TABLE livro(
    id_livro INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    volume INT NOT NULL,
    editora VARCHAR(100) NOT NULL,
    genero VARCHAR(45) NOT NULL
);
```

## Relacionamento Livro x Autor

```sql
CREATE TABLE livro_autor(
    id_livro INT,
    id_autor INT,

    PRIMARY KEY(id_livro, id_autor),

    FOREIGN KEY(id_livro) REFERENCES livro(id_livro),
    FOREIGN KEY(id_autor) REFERENCES autor(id_autor)
);
```

## Tabela Exemplar

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

## Tabela Emprestimo

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

## Relacionamento Emprestimo x Exemplar

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

# Modelo de Negócio

- Um usuário pode realizar vários empréstimos.
- Um livro pode possuir vários autores.
- Um autor pode escrever vários livros.
- Um livro pode possuir vários exemplares.
- Um empréstimo pode conter vários exemplares.
- Um exemplar pode participar de vários empréstimos ao longo do tempo.

---

# Como Executar

## 1. Clone o Repositório

```bash
git clone https://github.com/seu-usuario/Library-System.git
```

## 2. Crie o Banco de Dados

Execute o script SQL disponibilizado neste README.

## 3. Configure a Conexão

Altere as credenciais na classe de conexão:

```java
private static final String URL =
"jdbc:mysql://localhost:3306/db_LibrarySystem";

private static final String USER = "root";
private static final String PASSWORD = "sua_senha";
```

## 4. Execute o Projeto

Abra o projeto em uma IDE Java:

- IntelliJ IDEA
- NetBeans
- Eclipse

Execute a classe principal para iniciar o sistema.

---

# Objetivos do Projeto

Este projeto foi desenvolvido com o objetivo de praticar:

- Programação Orientada a Objetos
- JDBC
- Modelagem de Banco de Dados
- Relacionamentos SQL
- DTOs
- Arquitetura em Camadas
- Boas Práticas de Desenvolvimento Java

---

# Autor

Lucas Carvalho Jesus

Estudante de Análise e Desenvolvimento de Sistemas (ADS).

Projeto acadêmico desenvolvido para consolidar conhecimentos em Java, Banco de Dados, Engenharia de Software e Arquitetura em Camadas.
