# Time logging service

This service exposes 3 apis for projects time logging.

Service documentation can be found on the link below:

https://app.swaggerhub.com/apis/lfrefatti/ampulheta/1.0.0

## Requirements

  * JDK 8
  * Maven 3.5.0
  * MongoDB

## Running this service

To run this service locally or to deploy it to a server you'll have to configure two environment variables:

   * APPLICATION_KEY (JWT secret for token generation and validation)
   * MONGODB_URI (MongoDB connection string)

## Running tests

```bash
  >> mvn -Dspring.profiles.active=test clean verify
```
## Authors
  * Luís Fernando Refatti