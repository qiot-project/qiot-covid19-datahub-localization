# QIoT Localization Service

This microservice is responsible for translating geographical coordinates into location.

The main purpose of the service is to decouple localization functionalities from the esternal service providing them.

Example: curl "http://localhost:5034/v1/location?longitude=9&latitude=45"

## Choose one of:

### To build and create container image

$ ./mvnw clean package

### To build, create container image and push it to registry

$ ./mvnw -Dhttps.protocols=TLSv1.2 clean package -Dquarkus.container-image.push=true

### To build native and create container image

$ ./mvnw clean package -Pnative

### To build native, create container image and push it to registry

$ ./mvnw -Dhttps.protocols=TLSv1.2 clean package -Pnative -Dquarkus.container-image.push=true