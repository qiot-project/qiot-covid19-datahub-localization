# station-service project

This microservice is responsible for translating geographical coordinates into location.

The main purpose of the service is to decouple localization functionalities from the esternal service providing them.

Example: curl "http://localhost:5034/v1/location?longitude=9&latitude=45"