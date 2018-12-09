# SAMPLE PROJECT

## Build

Launch the command

    ./gradlew clean build

Test coverage reports can be found in : 

    ./build/reports/coverage/index.html

Test execution reports can be found in : 

    ./build/reports/tests/test/index.html



## Run

Launch the command:

    ./gradlew bootRun

Or build and launch:

    java -jar build/libs/demo-0.0.1-SNAPSHOT.jar

## Database

Data is persisted in H2. Database is initialized with 3 products. 

Database is maintained with `liquibase` changelogs.

## API

Api `swagger` documentation can be found in `./doc/api.yml` or can be downloaded from http://localhost:8080/v2/api-docs if the application is running.

Api can be tested using swagger ui available at http://localhost:8080/swagger-ui.html .
