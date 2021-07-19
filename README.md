![Build](https://github.com/Vi-Soft/helper-service/actions/workflows/java-gradle.yml/badge.svg)
# Vi-Soft-NG

The next generation of Vi-Soft's quality control system.

### Prerequisites:

1. JDK 1.8 or later
   https://www.oracle.com/java/technologies/javase-jdk8-downloads.html
2. Git 2.26.2 or later
   https://git-scm.com/downloads
3. Gradle 6.3 or later
   https://gradle.org/next-steps/?version=6.3&format=bin
4. PostgreSQL 12.5 or later
   https://www.postgresql.org/download/

### Getting started guide:

1. Create db `create database helper;`
2. Create jar `gradle clean build`
3. Running as a Packaged Application (Following ways)

   Way-1 : java -jar build/libs/helper-service-0.0.1-SNAPSHOT.jar

   Way-2 : gradle bootRun

4. Server url http://localhost:9080/help
5. Swagger http://localhost:9080/help/swagger-ui.html
 