# LibraryDesk

LibraryDesk is a web-based Library Management System built with Java, Spring Boot, Thymeleaf, and MySQL.

The project started as a Java console-based Library Management System and was later converted into a web application with a responsive user interface and a cloud-hosted database.

## 🚀 Live Demo

https://librarydesk-production.up.railway.app

## 📌 Project Overview

LibraryDesk helps manage books, students, and book borrowing/returning through a simple web interface.

The application allows users to:

- View available books
- Search for books
- Add students
- Issue books to students
- Return borrowed books
- Track borrowing records
- Calculate late fees
- Store data permanently in MySQL
- Access the application through a deployed Railway server

## ✨ Features

### 📚 Book Management

- View all available books
- Search books by title
- Display author, ISBN, and available copies
- Automatically update book copies when a book is issued or returned

### 👨‍🎓 Student Management

- Add students to the database
- Display registered students
- Use students directly from the Issue Book system

### 📖 Issue Book

- Select a book from the database
- Select a student from the database
- Automatically record the issue date
- Automatically calculate the due date
- Decrease available book copies
- Create a borrowing record

### ↩️ Return Book

- Display currently borrowed books
- Select a borrowing record
- Record the return date
- Calculate applicable late fees
- Increase available book copies
- Update the borrowing record

### 📋 Borrowing Records

- View borrowing history
- Display book and student information
- Display issue, due, and return dates
- Display late fees

### 🔐 Security

Spring Security is integrated into the application.

CSRF protection is enabled for POST forms to protect state-changing requests.

### 📱 Responsive Design

The interface is designed to work across:

- Desktop
- Laptop
- Tablet
- Mobile

Responsive CSS media queries adjust the navigation, forms, book grids, tables, and page layouts for smaller screens.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend programming |
| Spring Boot | Web application framework |
| Spring MVC | Request handling and controllers |
| Spring Data JPA | Database interaction |
| Hibernate | ORM |
| Thymeleaf | Server-side HTML rendering |
| MySQL | Database |
| Spring Security | Application security |
| HTML5 | Page structure |
| CSS3 | Styling and responsive design |
| Maven | Dependency management and build |
| Railway | Cloud deployment |
| Git/GitHub | Version control |

## 🗂️ Project Structure

```text
LibraryDesk
│
├── src
│   └── main
│       ├── java
│       │   └── com.LibraryDesk
│       │       ├── controller
│       │       ├── model
│       │       ├── repository
│       │       └── LibraryDeskApplication.java
│       │
│       └── resources
│           ├── static
│           │   └── style.css
│           │
│           ├── templates
│           │   ├── home.html
│           │   ├── books.html
│           │   ├── student.html
│           │   ├── issuebook.html
│           │   ├── return.html
│           │   ├── records.html
│           │   └── login.html
│           │
│           └── application.properties
│
├── pom.xml
├── README.md
└── ARCHITECTURE.md



---

# `ARCHITECTURE.md`

```markdown id="r8k2p"
# LibraryDesk Architecture

## 1. Overview

LibraryDesk follows a layered Spring Boot architecture.

The application separates the presentation layer, controller layer, data-access layer, and database.

The main technologies are:

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- MySQL
- Spring Security

---

## 2. High-Level Architecture

```text
                    ┌─────────────────────┐
                    │       User          │
                    │ Browser / Mobile    │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    Thymeleaf UI     │
                    │ HTML + CSS Pages    │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    Spring MVC       │
                    │    Controllers      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Spring Data JPA   │
                    │    Repositories     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │      Hibernate      │
                    │        ORM          │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       MySQL         │
                    │      Database       │
                    └─────────────────────┘








