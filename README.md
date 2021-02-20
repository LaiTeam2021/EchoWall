# developerForFun

### Spring boot + MyBatis codebase

# Team 
https://github.com/LaiTeam2021 
 
# How it works
 
 The application uses Spring boot (Web, Mybatis).
 
 * Use the idea of Domain Driven Design to separate the business term and infrastruture term.
 * Use MyBatis to implement the [Data Mapper](https://martinfowler.com/eaaCatalog/dataMapper.html) pattern for persistence.
 * Use [CQRS](https://martinfowler.com/bliki/CQRS.html) pattern to separate the read model and write model. [Example](https://www.baeldung.com/cqrs-for-a-spring-rest-api)
 
 
# Getting started

You need Java 8 installed.

    ./gradlew bootRun

To test that it works, open a browser tab at http://localhost:8080/

