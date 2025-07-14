
# 🏥 Carebridge - A Distributed Healthcare Platform

**Carebridge** is a scalable, containerized microservices-based healthcare platform designed to handle core operational workflows like user authentication, appointment billing, real-time analytics, and inter-service communication — all built with modern backend technologies.

---

## 🛠️ Tech Stack

- **Java + Spring Boot** (microservices, REST APIs)
- **Spring Cloud Gateway** (API Gateway)
- **PostgreSQL** (databases)
- **gRPC** (internal service communication)
- **Kafka** (event-driven streaming)
- **JWT Authentication** (secure access)
- **Docker + Docker Compose** (containerization)
- **AWS + LocalStack** (cloud simulation)
- **Spring Cloud** (service routing & config)
- **JUnit/TestContainers** (integration testing)

---

## 🧩 Microservices Overview

| Microservice         | Description |
|----------------------|-------------|
| `patient-service`     | Manages core patient data and triggers downstream events |
| `billing-service`     | Handles billing accounts, invoice generation, and payment tracking |
| `auth-service`        | Provides login, registration, and JWT token authentication |
| `analytics-service`   | Consumes real-time Kafka streams for generating insights |
| `api-gateway`         | Routes all external HTTP requests through a single entry point |
| `kafka`               | Powers event-driven communication between services |
| `patient-service-db`  | PostgreSQL DB for storing patient data |
| `auth-service-db`     | PostgreSQL DB for storing user credentials and roles |

---

## 🔗 Service Communication

- **gRPC**: Used between `patient-service` and `billing-service` to sync billing account creation when a new patient is added.
- **Kafka**: Streams patient events from `patient-service` to `analytics-service` for real-time insights.
- **REST**: Exposes secured endpoints via API Gateway using JWT-based authentication.

---

## 🧪 Testing

- **Integration Tests** written using JUnit and TestContainers.
- All services tested for intercommunication, database interaction, and security flows.

---

## 🚀 Deployment

- All services are **Dockerized** and orchestrated via **Docker Compose**.
- Simulated cloud deployment via **LocalStack**, replicating AWS components and workflows.

---

## 🛡️ Security

- JWT-based authentication and authorization handled by `auth-service`.
- Secured routes via Spring Security.
- Role-based access control (future scope).

---

## 📊 Analytics Service

- Real-time consumption of Kafka events.
- Aggregates patient activity for future ML models or dashboards.
- Extendable for more advanced insights (e.g., trend analysis, alerting).

---

## 🌐 API Gateway

- Built using **Spring Cloud Gateway**
- Centralized request routing and filtering
- Path-based routing to respective backend services
- Handles authentication token forwarding

---

## 📂 Folder Structure

```
carebridge/
├── auth-service/
├── billing-service/
├── patient-service/
├── analytics-service/
├── api-gateway/
├── kafka/
├── docker-compose.yml
├── README.md
```

---

## ✅ Features Summary

- Built 8 fully independent, containerized Spring Boot microservices
- gRPC used for internal communication to reduce HTTP overhead
- Kafka implemented for decoupled, real-time streaming of data
- Authentication using Spring Security + JWT
- Simulated production deployment with AWS via LocalStack
- Integration tested using JUnit + Docker environments

---

## 📌 Future Enhancements

- Add Appointment Service with time-slot management
- Integrate Notification Service for emails/SMS
- Implement centralized config and service discovery
- Role-based dashboards and usage analytics

---

## 📧 Contact

Created with 💻 by [Yash Yogesh Kathane]  
Email: [yashyogeshkathane@gmail.com]  
