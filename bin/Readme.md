# Hibernate Spring Boot and JPA

Stack technologies used: Java, Spring Boot, Hibernate ORM, MySQL Database, Swagger Docs, Docker container

## Setup the Application

1. Create a database named `libreria`.

2. Open `src/main/resources/application.properties` and change `spring.datasource.username` and `spring.datasource.password` properties as per your MySQL installation.

3. Type `mvn spring-boot:run` from the root directory of the project to run the application.

# Docker environment

## Setup with Dockerfile

1. Clone project: 

```
git clone https://github.com/martinambrueso/TPFinalPDA.git
```

2. Move to project folder and in root directory run this comand: 

```
mvn package
```

3. Move to folder project (C:\Users\{user}\Desktop\{folder_project}\target) and run command test inside to folder: 

```
java -jar target/TPPDAFinal-0.0.1-SNAPSHOT.jar
```

4. If the status returned is success, then you will do run this command: 

```
docker build -t springio/gs-spring-boot-docker .
```

5. And now, you can run this docker app with: 

```
docker run -p 8080:8080 springio/gs-spring-boot-docker
```

6. To view the documentation module, visit http://localhost:8080/swagger-ui.html/

7. The base URL to test this app is: http://localhost:8080/api/v1


# Or local environment

## You can run this projecto from your local device, only you need Apache Maven installed or Spring Boot Suit

1. Clone project: 

```
git clone https://github.com/martinambrueso/TPFinalPDA.git
```

2. Move to project folder and in root directory run this comand: 

```
mvn package && java -jar target/TPPDAFinal-0.0.1-SNAPSHOT.jar
```

3. The application will be running on port 8080: http://localhost:8080/api/v1
