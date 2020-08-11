# Lab-forward Code Challenge for Backend Engineer Candidate


[![CircleCI](https://circleci.com/gh/circleci/circleci-docs/tree/teesloane-patch-5.svg?style=svg)](https://circleci.com/gh/mohamedelkashif/addressline-analyzer)  [![codecov](https://codecov.io/gh/mohamedelkashif/addressline-analyzer/branch/master/graph/badge.svg)](https://codecov.io/gh/mohamedelkashif/addressline-analyzer) 
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


## General Info
1. The task is implemented using Java as programming language and [Spring boot](http://spring.io/projects/spring-boot) as back-end framework.
2. I used [IntelliJ IDEA](https://www.jetbrains.com/idea/) as IDE.
3. The project is attached to [circleci](https://circleci.com/) as CI/CD and [codecov](https://codecov.io/) so every time I make a push to the Git repo the project is built and tests are built then send the code covergae report to [codecov](https://codecov.io/)
4. You can view [circleci](https://circleci.com/) and [codecov](https://codecov.io/) coverage report through the badges above.

## Definition of done
1. Implement the required update endpoint.
2. Add required test cases for for the update endpoint and for the service.
3. Add delete endpoint to the application.
4. Refactor the existing code by adding some classes and remove some line of code to make the code dry.
5. Add a health check endpoint
6. Add Swagger UI as part of UI or as a documentation.
7. Add Dockerfile to the project.
8. Application docker image is pushed to [DockerHun](https://hub.docker.com/) so you can pull the image directly and use it.
9. Implement [circleci](https://circleci.com/) pipeline in case of automatic deployment.
10. Run [codecov](https://codecov.io/) as coverage report.
11. Deploy the application to [Amazon web services](https://aws.amazon.com/) using ElasticBeanStalk service.


## Installation and setup
1. Unzip the compressed file to any where on your computer if you download or just clone the repository.
2. Navigate to the cloned/downloaded folder and open it using your favourite IDE but but I prefer [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Running

#### Gradle
You can run the project using gradle but make sure it is installed in your machine or configured in your IDE through the following command
`./gradlew bootRun` 

#### IDE -> Intellij
You can run the application directly from intellij but make sure you are using Java 8

Start the application from JAR file
`java -jar build/libs/lf-code-challenge-0.1.0.jar`

#### Docker
1. You can run the project through docker either by building the image from Dockerfile then running the container through the following commands
```
docker build -t labforward .
```
```
 docker run -p 8080:8080 labforward
```
 Or by pulling the image directly from [Docker Hub](https://hub.docker.com/) `
docker pull mohamedmkashif/labforward-helloworld:latest`

Run the pulled image throught the following command `docker run -p 8080:8080 -t mohamedmkashif/labforward-helloworld`

## Usage & available end-points
There are four end-points available in this project, one for the main POST request and the other is for health check

| Method        | Endpoint              | Body            |
| ------------- |:---------------------:| --------------- |   
| GET           | localhost:8080/hello  | no body         |
| GET           | localhost:8080/hello  | ID              |
| POST          | localhost:8080/hello  | message         |
| PATCH         | localhost:8080/hello  | Greeting object |


```
AWS: http://addresslineanalyzer-env.eba-rpnkjgva.us-east-1.elasticbeanstalk.com/api/v1/address
```


- By using your favorite HTTP client **I recommend using POSTMAN** or by using cURL or Swagger, you can use and test all the endpoints.


## Testing
You can run the available test through the this command `./gradlew clean test --info` or you can run test from IDE

## Swagger API documentation
The available end-point is documented by swagger through this link 

- `http://localhost:8080/swagger-ui.html`
- `http://labforwardhelloworld-env-1.eba-2pdttwmw.us-east-2.elasticbeanstalk.com/swagger-ui.html#/`

## Deployment
* The project is deployed on [Amazon web services](https://aws.amazon.com/) [using Elastic Beanstalk](https://aws.amazon.com/elasticbeanstalk/) ![AWS deployment](/docs/AWS-deployment.png).
* AWS endpoint 
```
http://labforwardhelloworld-env-1.eba-2pdttwmw.us-east-2.elasticbeanstalk.com/hello
```

## Author
[Mohamed Kashif](mailto:mohammedd.kashiff@gmail.com)


