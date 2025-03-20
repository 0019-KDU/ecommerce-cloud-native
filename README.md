
# E-Commerce Microservices Architecture 

## Overview

This repository contains the documentation for an e-commerce application built with a microservices architecture. The system is designed to be scalable, modular, and flexible, allowing independent deployment of services. Each microservice handles a specific business function, and the architecture leverages modern tools for service discovery, configuration management, containerization, and distributed tracing.

**Note**: This repository is for documentation purposes only. The actual source code for the microservices is hosted in separate repositories.

## Architecture

The system operates within a private network and consists of several key components:

- **Microservices**: Customer, Product, Order, Payment, and Notification services, each responsible for a specific e-commerce function.
- **Databases**: Each service uses its own MongoDB ,postgresql instance for data isolation and independent scaling.
- **Communication**: Services communicate synchronously via HTTP for external requests and asynchronously via Kafka for events like order and payment confirmations.
- **Infrastructure**: Includes Eureka for service discovery, Config Server for centralized configuration, Docker for containerization, and Zipkin for distributed tracing.

The architecture diagram below provides a visual representation of the system:

![Architecture Diagram](diagrams/Untitled%20Diagram.drawio.png)

### Microservices

| Microservice         | Role                                              | Database | Communication Method            |
|----------------------|---------------------------------------------------|----------|---------------------------------|
| Customer Service     | Manages customer data and operations              | MongoDB  | HTTP (external requests)        |
| Product Service      | Handles product catalog and inventory             | postgresql  | HTTP (external requests)        |
| Order Service        | Processes and manages orders                      | postgresql  | HTTP, Kafka (async to Payment)  |
| Payment Service      | Handles payment processing                        | postgresql  | Kafka (async to Notification)   |
| Notification Service | Sends notifications to customers                  | MongoDB  | Kafka (receives from Payment)   |

### Infrastructure Components

| Component          | Role                                              | Connection Details                     |
|--------------------|---------------------------------------------------|----------------------------------------|
| Eureka Server      | Service discovery                                 | Connected to all microservices         |
| Config Server      | Centralized configuration management              | Connected to all microservices         |
| Docker             | Containerization for deployment and scaling       | Used by all services and databases     |
| Zipkin             | Distributed tracing for monitoring                | Receives data from Notification Service|
| Kafka              | Message broker for asynchronous communication     | Handles order and payment confirmations|

## Data Flow

The data flow within the system follows a structured process:

1. **External Requests**: An external client sends requests to endpoints `/customers`, `/products`, and `/orders`, which are routed to the respective microservices via HTTP.
2. **Order Processing**: The Order Service processes an order, stores it in MongoDB, and sends an asynchronous "order confirmation" message to Kafka, which is consumed by the Payment Service.
3. **Payment Processing**: The Payment Service processes the payment, stores transaction data in MongoDB, and sends a "payment confirmation" message to Kafka, consumed by the Notification Service.
4. **Notifications**: The Notification Service sends notifications (e.g., emails) to customers, logs the activity in MongoDB, and sends tracing data to Zipkin for monitoring.
5. **Tracing and Monitoring**: Zipkin collects tracing data to track request flows across services, aiding in debugging and performance analysis.

This flow ensures decoupling of services through asynchronous messaging, enhancing scalability and fault tolerance.

## Technologies Used

The system leverages the following technologies:

- **Framework**: Spring Boot (inferred from the use of Eureka and Config Server, common in Spring Cloud ecosystems).
- **Service Discovery**: Eureka, for dynamic service location.
- **Configuration Management**: Spring Cloud Config, for centralized configuration.
- **Database**: MongoDB, used by each service for data persistence.
- **Message Broker**: Kafka, for asynchronous communication between services.
- **Containerization**: Docker, for deployment and scaling.
- **Distributed Tracing**: Zipkin, for monitoring and debugging.

## Usage

This repository serves as a reference for understanding the e-commerce microservices architecture. Developers should refer to the individual microservice repositories for implementation details, such as source code, setup instructions, and deployment guides. The architecture diagram (`Untitled Diagram.drawio.png`) is included in the repository root for visual reference.

## Contributing

Contributions to improve this documentation are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your changes (`git checkout -b improve-docs`).
3. Make your changes and commit them (`git commit -m "Add detailed setup instructions"`).
4. Push to your branch (`git push origin improve-docs`).
5. Open a pull request with a description of your changes.

Please ensure your contributions align with the goal of making the documentation clear and comprehensive. For suggestions or issues, feel free to open an issue in the repository.

## License

This project is licensed under the MIT License. See the [LICENSE.md](LICENSE.md) file for details.


