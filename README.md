🖇️re:Mine Server Repository
## Architecture
![그림1](https://github.com/re-Mine/reMine-Server/assets/96935231/59a63481-7a67-4d82-a9eb-d7ed9a9a2597)

## Building and deploying the application

### Building the application

The project uses [Gradle](https://gradle.org) as a build tool. It already contains
`./gradlew` wrapper script, so there's no need to install gradle.

To build the project execute the following command:

```bash
  ./gradlew build
```

### Running the application

Create the image of the application by executing the following command:

```bash
  ./gradlew assemble
```

Create docker image:

```bash
  docker-compose build
```

Run the distribution (created in `build/install/spring-boot-template` directory)
by executing the following command:

```bash
  docker-compose up
```

This will start the API container exposing the application's port
(set to `4550` in this template app).

In order to test if the application is up, you can call its health endpoint:

```bash
  curl http://localhost:4550/health
```

You should get a response similar to this:

```
  {"status":"UP","diskSpace":{"status":"UP","total":249644974080,"free":137188298752,"threshold":10485760}}
```

The backend is done with
- Java `17`
- Spring Boot `3.3.2`
- Gradle 8.5
- Spring Data JPA, OAuth2, Spring Cloud GCP, lombok and so on.
