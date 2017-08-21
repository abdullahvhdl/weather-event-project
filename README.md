#### Weather Events Project

##### Requirements

-   Java 8 Runtime Environment
-   MySQL 5.x
-   Maven 3.x (compatible with Java 8)

##### Steps

-   Open the MySQL command line and execute the commands below to create the database and its user:

    - `create database eventdb;`
    -   `use eventdb;`
    -   `create user 'eventdb'@'localhost' identified by 'eventdb';`
    -   `grant all privileges on eventdb.* to 'eventdb'@'localhost';`


-   Open the command line of your operational system and go to the folder of the project
-   Execute

    - `mvn clean install`

-   At this time you will be able to access api at : 
    
    -   `http://localhost:8080/weatherservice/weather`
    
-   Use the parameters: `lon`, `lat`, `date` and `rows`    