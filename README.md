# CelebrityFinder

Solution to "Find Celebrity" problem.

## Build project
Use Maven to build project using the following command in the project root directory
````
mvn clean install
````

## Run project
Run in command line in the project root directory
````
java -jar target/finder-0.0.1-SNAPSHOT.jar
````

## Data
To load or change data edit "data.sql" file under src/main/resources folder or use embedded H2 database console navigating to "http://localhost:8080/console/" in your web browser.
````
user: "sa"
password: ""
JDBC URL: "jdbc:h2:mem:team"
````

## Find Person
To find the celebrity within the data in the H2 database, navigate to "http://localhost:8080/find" in your browser or perform GET HTTP call.
