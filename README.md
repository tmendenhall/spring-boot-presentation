# IndyJug 12/2015 Presentation #

### What is this repository for? ###

* Quick summary
  Example code for a Spring Boot application

  Moved here and updated from this [fork](https://github.com/tmendenhall/gs-actuator-service)
  which has a lot of good documentation.

* Version
Based on Spring Boot 1.3.0-RELEASE

### How do I get set up? ###

* Summary of set up
    * Build with Gradle
    * Includes a Procfile for Heroku deployment.

* Configuration

    application.yml and application-prod.yml for environment specific configuration
* Dependencies

  See build.gradle
* How to run tests

    ```./gradlew test```
* Deployment instructions

    * Jar

    ```./gradlew assemble``` will produce a jar file in the build/libs directory
    execute with
    ```java -jar <jarfile.jar> --spring.profiles.active=<any desired profiles>```

    Default logging to the console

    * Heroku

       Using the exising Procfile you can deploy directly to[Heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku)


### Who do I talk to? ###

* Tyler Mendenhall tyler.mendenhall@e-gineering.com