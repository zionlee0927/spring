# Spring Boot Example Project

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

This repository contains a comprehensive **Spring Boot example project**, primarily built with **Kotlin**. It serves as a robust template showcasing modern backend development practices, including strong security, modular architecture, event-driven patterns, and extensive automated testing.

---

## 🚀 Technologies Used

* **Spring Boot**: The leading framework for building robust, production-ready, stand-alone Spring applications with minimal configuration.
* **Kotlin**: A modern, concise, and safe programming language with excellent Java interoperability, making development efficient and enjoyable (99.7% of the codebase).
* **Gradle**: A powerful build automation tool with Kotlin DSL, managing dependencies and project building effectively.
* **Spring Security**: Provides robust authentication and authorization mechanisms to secure the application's endpoints and data.
* **Docker**: Used for containerization, ensuring consistent deployment across different environments.
* **Vim Snippet**: Minor usage (0.3%), likely for code snippets used within the Vim editor.

---

## ✨ Features

This project demonstrates key aspects of modern backend development, focusing on maintainability, scalability, and reliability:

* **RESTful API Development:** Build well-structured and easy-to-consume APIs, adhering to REST principles.
* **Gradle Build Automation:** Utilize Kotlin DSL for efficient dependency management and comprehensive build tasks.
* **Docker Containerization:** Create Docker images and manage multi-service environments with `docker-compose` for simplified deployment and consistent setups.
* **Spring Security Integration:** Implement robust security measures, including:
    * **Authentication:** Secure access to your application (e.g., basic authentication, JWT).
    * **Authorization:** Control user permissions with role-based access control.
    * **Vulnerability Protection:** Safeguard against common web vulnerabilities.
* **Hexagonal Architecture (Ports and Adapters):** Structure the application to cleanly separate the core **domain logic** from external concerns like databases or web interfaces. This promotes:
    * **High Testability:** Easily test business logic independently.
    * **Maintainability:** Easier to understand and modify specific parts of the system.
    * **Flexibility:** Adapt to changes in external technologies without altering the core domain.
    * **Clear Layers:** Defines distinct Domain, Application (Use Cases/Services), and Infrastructure (Adapters) layers.
* **Event-Driven :** Leverage event-driven principles for loose coupling and scalability:
    * **Domain Events:** Represent significant occurrences within the business domain.
    * **Event Publishing:** Emit domain events when state changes occur.
    * **Event Handling:** Consume and react to domain events to trigger subsequent actions asynchronously.
* **Automated Testing:** Emphasize a robust and comprehensive testing strategy:
    * **Unit Tests:** Verifying the logic of individual components in isolation (e.g., using JUnit, MockK).
    * **Integration Tests:** Testing the interactions between different parts of the application, such as the application layer and infrastructure adapters (e.g., using Spring Test).
    * **Spring REST Docs:** Automatically generating precise and readable **API documentation** directly from integration tests, ensuring your documentation stays accurate and up-to-date with code changes.
    * **Testcontainers:** Providing lightweight, disposable **containerized services** for integration tests, allowing realistic testing against actual databases (e.g., PostgreSQL, MySQL) or message brokers (e.g., Kafka) without needing them installed locally.
    * **Potentially End-to-End Tests:** Validating the application's complete flow from start to finish.

---

## 📦 Getting Started

Follow these steps to set up and run the project on your local machine for development and exploration.

### Prerequisites

Before you begin, make sure you have the following installed:

* **JDK 17** or higher
* **Gradle** (optional, as `gradlew` wrapper is included)
* **Docker** & **Docker Compose** (for containerized execution)

### Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/zionlee0927/spring.git](https://github.com/zionlee0927/spring.git)
    cd spring
    ```

### Running the Application

You can run the application either by starting the database with Docker Compose and then running the application with Gradle for a flexible development setup, or by running the application directly if you have a local database configured.

#### 1. Running Application with Docker Compose for DB (Recommended for Development)

This approach lets you manage your database (and other infrastructure services) via Docker Compose while running your Spring Boot application directly from your IDE or Gradle. This is great for fast iteration during development.

1.  **Start the database container(s) and other infrastructure services:**
    ```bash
    docker-compose up -d
    ```
    If your `docker-compose.yaml` has multiple infrastructure services you want to run, you can list them or simply run `docker-compose up -d` to start all services in detached mode.

2.  **Run the Spring Boot application using Gradle:**
    ```bash
    ./gradlew bootRun
    ```
    The application will typically start on `http://localhost:8080`. It will connect to the database container started in the previous step.

3.  **To stop the database container(s):**
    ```bash
    docker-compose down
    ```
