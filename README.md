# Spring Boot Order Logistic Backend System

This is a Spring Boot-based backend system for managing orders. The system provides three APIs for creating, viewing, and updating orders. 
It uses MySQL as its database.

## Prerequisites

- JDK 17
- Docker
- Docker Compose

## Getting Started
To get started with this system, follow these steps:

1. Clone the repository to your local machine:

Copy

```
git clone https://github.com/katrinalau157/order-logistic-system1.0

```

2. Navigate to the project directory

3. run start.sh in wsl2

Copy

```
sh start.sh

```

To get started with this system (old way), follow these steps:

1. Clone the repository to your local machine:

Copy

```
git clone https://github.com/katrinalau157/order-logistic-system1.0

```

2. Navigate to the project directory:

3. run mvn clean install -DskipTest to create jar

4. Start the services using Docker Compose:

Copy

```
docker-compose up

```

This will start the MySQL service and the Order backend system in separate containers.

1. Access the Order backend system at `http://localhost:8080`.

## Configuration

The system can be configured using the following environment variables:
- `GOOGLE_MAPS_APIKEY`: setup your google API key (Important!!!!)