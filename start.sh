#!/bin/bash
# start Docker Compose
docker-compose down

# start Docker Compose
docker image rm order-logistic-system10_order-backend

# start Docker Compose
docker-compose up