# spring-boot-jwt-example

This is a sample project to demonstrate how **JWT Json Web Token** authentication works using **Spring Security** and **Spring Boot**. There is an sql script inside the project to create the necessary tables in **MySQL**. 

## Sample User Credentials

```
-email: john@doe.com
-password: 123123
```
## Usage

You should first modify the application.properties file to connect to your local MySQL instance and run the db script. After running the db script, you can run the sample application with `mvn spring-boot:run` and the application will start at [http://localhost:8080](http://localhost:8080).

## Endpoints

```
POST /auth/login              authorization endpoint that returns JWT token upon successful request.
GET  /api/v1/user/getAll      get all users
```

## References

1. Spring Boot 1.4.3 Reference https://docs.spring.io/spring-boot/docs/1.4.3.RELEASE/reference/htmlsingle/
2. JWT https://jwt.io/
3. MySQL https://www.mysql.com/

