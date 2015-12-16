# IndyJug 12/2015 Presentation #

### What is this repository for? ###

* Quick summary
  Example code for a Spring Boot application using various aspects of the Spring-Boot-Actuator project.

  Moved here and updated from this original [fork](https://github.com/tmendenhall/gs-actuator-service)

* Version

    Based on [Spring Boot](https://github.com/spring-projects/spring-boot)
    and the [Spring Boot Actuator](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready) project.

    The [reference documentation](http://docs.spring.io/spring-boot/docs/1.3.0.RELEASE/reference/html/) is very helpful.

### How do I get set up? ###

* Summary of set up
    * Import the project into any IDE that supports a build.gradle file.

    * Standalone Build with Gradle

        `./gradlew wrapper` to Initialize the build wrapper
        `./gradlew assemble` to create an executable jar.

    * The Project Includes a Procfile for Heroku deployment.

* Configuration

    * application.yml and application-prod.yml for environment specific configuration
    * specify at runtime with the --spring.profiles.active flag

* Dependencies

  See build.gradle
* How to run tests

    ```./gradlew test```

* Deployment instructions

    * Jar

    ```./gradlew assemble``` will produce a jar file in the build/libs directory
    execute with

    ```java -jar <jarfile.jar> --spring.profiles.active=<comma delimited profile list>```

    Default logback logging to the console

    * Heroku

       Using the exising Procfile you can deploy directly to [Heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku)


### Contacts ###

* Tyler.Mendenhall@[E-gineering](http://www.e-gineering.com).com