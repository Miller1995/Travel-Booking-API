# About Travel-Booking-API
Development of a backend for managing (booking) places where you can practice extreme sports, 
as well as to help people choose the best time to go there. Application developed using Java 17, 
Spring Boot, Spring Security, Spring Data JPA, REST API, PostgreSQL, Lombok, SonarLint, Swagger, JUnit and MockMvc.

# Features
### This API provides HTTP endpoint's and tools for the following:

* To register and/or authenticate a user, we can use the following endpoints: 
![auth](https://github.com/Miller1995/Travel-Booking-API/assets/17921423/bf1eb4f8-f98e-44b3-96c6-ad1b0015d305)

* For travel management we can use the following endpoints:
![2222](https://github.com/Miller1995/Travel-Booking-API/assets/17921423/9f1d5126-7d49-43cb-ae45-717318f3b117)

* For users management we can use the following endpoints:
![333333](https://github.com/Miller1995/Travel-Booking-API/assets/17921423/066a55de-eb4f-4062-b5d0-f5bdedf0d431)


# Steps to Setup
1. Clone the application

git clone https://github.com/Miller1995/Travel-Booking-API.git

2. Create PostgreSQL database

create database "travel-booking"

3. Change PostgreSQL username and password

open src/main/resources/application.properties 
change spring.datasource.username and spring.datasource.password

4. Run the app using maven

mvn spring-boot:run
