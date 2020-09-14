# vsav.video backend

This is a multi-module Maven project using Spring Boot. The project compiles to a runnable .jar that spins up an embedded Tomcat instance that the application is served from.

## Build

Run `mvn clean package install -U` to build the project. You will require Maven and JDK 11.

## Run

You will find a generated jar in `/parent/application/target` that you can run with `java -jar` after building.

You will require an `application.properties` file with the appropriate details filled in; you will find a sample one in `application/LOCAL-application.properties`. Place your filled out `application.properties` in the same directory as the jar before running.

You will also require a local MySQL server running on the port specified in your `application.properties`. You can find the SQL scripts to build the database and tables in the root (parent) project, along with some instructions.
