# URL-Shortener
A Spring Boot URL Shortener that helps you manage long URLs by reducing their size!

## Instructions
To run this application, you need to have installed on your machine:
- Java 17
- Spring
- Text Editor
- MySQL

- To install java 17 visit this site -> [Link text](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- To install Springboot visit this site -> [Link text](https://docs.spring.io/spring-boot/installing.html) or enable it in your ide (e.g. in Vs Code you have to install the relative packages).
- Install visual stidio -> [Link text](https://code.visualstudio.com/)
- Install MySql -> [Link text](https://www.mysql.com/)

## Run the application
To be able to run the application, you have first to set up and run the DB schema to store the URLs. So first of all, open MySQL (CLI or Workbench). Then, in the project folder, you will find a folder called db. Inside there will be a .sql file. Run this file in your MySQL environment to create the db schema.

Then, in src/main/resources you will find a .properties file. Inside this file you have to insert the credentials to be able to access your previously created db schema.
```
spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```
Here, you have to substitute the ${...} with the actual information of the db.

After these passages, if you want to run this program locally, you have also to substitute ${APP_DOMAIN} with -> http://localhost:8080.

If you want to run this code in a cloud environment, do not change these elements, since they are going to be your environmental variables.

## Once everything is set
After all these passages, you can navigate to the UrlshortnerApplication.java file and run it! Then just open the browser, paste `localhost:8080` and hit enter!



