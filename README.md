# order-logistic-system1.0
- Created Place order, Take order, get Order list API
- in docker-compose.yml replace yourGoogleApikey with your google maps api key
- start.sh still in progress

Steps for building order logistic system in wsl2:
1. mvn clean install -DskipTest to create jar
2. remove image order-logistic-system10_order-backend
3. docker-compose up