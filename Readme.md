# Hibernate Spring Boot and JPA

Read the Tutorial - https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/

## Setup the Application

1. Create a database named `libreria`.

2. Open `src/main/resources/application.properties` and change `spring.datasource.username` and `spring.datasource.password` properties as per your MySQL installation.

3. Type `mvn spring-boot:run` from the root directory of the project to run the application.

# Docker environment

## Setup with Dockerfile

1. Clone project: git clone https://github.com/martinambrueso/TPFinalPDA/tree/master

2. Move to project folder and in root directory run this comand: mvn package

3. Move to folder project (C:\Users\{user}\Desktop\{folder_project}\target) and run command test inside to folder: java -cp /TPPDAFinal-0.0.1-SNAPSHOT.jar com.example.jpa.TPPDAFinalRestApi.

4. If the status returned is success, then you will do run this command: docker build -t springio/gs-spring-boot-docker .

5. And now, you can run this docker app with: docker run -p 8080:8080 springio/gs-spring-boot-docker

6. To view de documentation module, visit http://localhost:8080/swagger-ui.html/

7. The base URL to test this app is: http://localhost:8080/api/v1
