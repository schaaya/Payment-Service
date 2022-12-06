# WALLET MICROSERVICE


A payment service running on JVM that manages the transactions using 
different payment instruments.

***DESCRIPTION***

This project consists of three independent microservices.They are UserService, WalletService & PaymentService.
-"UserService" stores user information using MySQL database. It is a Spring Boot JPA application.
-"PaymentService" is a Spring Boot JPA Application that handles the transactions.
-"WalletService" is a Spring Boot JPA application that stores user wallet information.

The communication between the modules is established using RestTemplate.
We send sms and email to sender and receiver on successful transaction. A transaction job is processed every month which sends transaction history of a user to mail id.

Tech stack used: Spring boot, MySQL and integrated with Swagger for API documentation.

**Api requirements and running instructions**
1. Java 17
2. Maven 3 to build the application
3. Download and install MySQL server(version 8 or higher).
4. Connect to the MySQL Server and create database schema - payment_service.
5. The user,schema,database must be configured in the application properties.

```
spring.datasource.url=jdbc:mysql://localhost:3306/payment_service
spring.datasource.username=root
spring.datasource.password=Password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

```

6.To start the application, run following commands:

```
mvn clean install

mvn spring-boot:run

```
NOTE :Steps 5 and 6 must be configured separately for all three microservices.

**API ENDPOINTS**


http://localhost:8080/wallets Gets all wallets Some wallets are generated after the first start of the application by Flyway.

http://localhost:8080/wallets/{id} Gets wallet with transactions

http://localhost:8080/wallets/user?userId={user} Gets list of wallets by user

http://localhost:8080/wallets/{id}/transactions Gets list of transactions by wallet id Some transactions are generated after the first start of the application by Flyway.


**TECHNOLOGY UNDER USE**

-MySQL features a distinct storage-engine framework that facilitates system administrators to configure the MySQL database server for a flawless performance.

-Spring Boot, including Spring Data JPA for JPA based repositories

-Tomcat, an embedded web server to run the application.

-Gson from com.google.code.gson to serialize objects to JSON.


**IMPLEMENTED FEATURES**

1.UserService:

UserService is a Spring Boot JPA application that stores user information.
-MySql Database is used.

-Swagger for API documentation is also used. http://localhost:9016/swagger-ui.html#/

2.WalletService:
-WalletService is a Spring Boot JPA application that stores user wallet information.

-MySql and Redis databases are used.

-Kafka is used for messaging queue.

-Wallet APIs for createNewWallet, updateWallet etc.

-User id and balance stored in Redis.

-RestTemplate is used to interact with UserService microservice.

-EmailService class is responsible for sending email to a user for transaction history.

-Swagger for API documentation. http://localhost:9022/swagger-ui.html#

3.PaymentService:

-PaymentService is a Spring Boot JPA application responsible for payment transactions between users.

-Transaction APIs for sendMoney and getBalance.

**IMPROVEMENTS**

1. Validations on user, wallet and payment entities.
2. Authentication can be put on the Spring Security.
3. This project is a backend microservice, can implement frontend for it.
4. Improve test cases.


