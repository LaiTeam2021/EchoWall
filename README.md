# EchoWall Backend

### Spring boot

# Team 
https://github.com/LaiTeam2021 

## Environment step
[Setup Build Config](https://developerforfun.quip.com/vn1XAQS5KVCn/Spring-Configuration)
[Install PostgreSQL For Local Testing](https://www.postgresql.org/download/)
Db_name: postgres Password:12345678

## Schema
https://lucid.app/lucidchart/e767cee1-e8f5-471a-9884-20f1d5de39de/edit?shared=true 


# Architecture
* RDS(PostgreSQL + JPA + Hibernate)  
* Redis(optional) 
* ElasticSearch(optional)  
* Storage(Google Cloud Storage)
* Social/Email Login(Google Authentication)
* Push notification(Firebase cloud message)
* Analytics(Google Analytics)
 
# How it works
 
 The application uses Spring boot (Web, Mybatis).
 
 * Use the idea of Domain Driven Design to separate the business term and infrastruture term.
 * Use [CQRS](https://martinfowler.com/bliki/CQRS.html) pattern to separate the read model and write model. [Example](https://www.baeldung.com/cqrs-for-a-spring-rest-api)
 
# DB
[schema quip](https://developerforfun.quip.com/QaX0AqkJCPbf/DB-Schema)


# Tutorial
[tutorial quip](https://developerforfun.quip.com/c7cDAVm16Tzb/Environment)

# Getting started

Configure **application.properties** to set up the environment
[configuration quip link](https://developerforfun.quip.com/lMMNOrJSI2ZR/Spring)

``
    spring.profiles.active=dev 
``    

You need Java 8 installed.

    ./gradlew bootRun

To test that it works, open a browser tab at http://localhost:8080/

# Note
Flyway or Liquibase for db migration

