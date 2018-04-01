[![Build Status](https://travis-ci.org/mxro/web-api-example-v2.svg?branch=master)](https://travis-ci.org/mxro/web-api-example-v2)

[![Known Vulnerabilities](https://snyk.io/test/github/mxro/web-api-example-v2/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/mxro/web-api-example-v2?targetFile=pom.xml)

# web-api-example-v2

Provides an example for a simple server providing a REST API, implemented in Jetty.

This server can be deployed as stand-alone Java application or as a WAR to a Tomcat/Jetty server.

## About

This project provides a simple, Java-based web server. This web server can be started on any system with an installed JRE (version 1.8+) on a port specified by the user.

Once started, the server will provide the following services:

### Hello World (path /hello-world)

Upon a **get** request, this service will return a status code 200 and the following JSON content:

```
{ "theAnswer": 42}
```

### Health Service (path /health)

Upon a **get** request, this service will return a status code 200 and the following JSON content:

```
{ "running": true}
```

Upon a **head** request, this service will return the status code 200.

### Application Meta Data Service (path /meta)

Upon a **get** request, this service will return build information from the server executable used in the following format:

```
{
"version": "0.0.1-SNAPSHOT",
"description": "Provides an example for a simple REST API implemented in Jetty.",
"lastcommit": "98d6120f7a3fe535a9adbc52c182d676772dc9e1"
}
```

`version` is the version of the Maven module used. `description` the project description for the module. `lastcommit` is the hash value of git version of the project at the time of the build.

## Build Information

To build this project, you will need the following:

- JRE 1.8+
- Maven 3.10+
- Git 1.8+

To checkout the project on your local machine:

```
git clone git@github.com:mxro/web-api-example.git
```

To install the project in your local repository and to assemble the executable JAR:

```
mvn install
```

To generate the project reports and Maven site:

```
mvn site
```

The reports and site will be available in the project directory `target/site`. Open `index.html` in this directory as a good starting point to explore the reports.

## Usage

### Command Line

Once you have assembled the executable JAR by executing `mvn install` (see above), the following jar file will be available in the directory `target/`.

```
web-api-example-x.x.x-distribution.jar
```

(Please replace x.x.x with the version number you are building)

Given this file, you can start the server as follows:

```
java -jar web-api-example-x.x.x-distribution.jar
```

By default, the server will start on the port `8989`. You can now access the services using the following URLs:

```
http://localhost:8989/hello-world
http://localhost:8989/meta
http://localhost:8989/health
```

Optionally, you can provide a port number as argument, such as:

```
java -jar web-api-example-x.x.x-distribution.jar 10001
```

The server will be started on the port number provided.

### Java API

You can also start the server through the Java API. For this, install the Maven artifact into your repository and reference it as described below (see Dependency below). Once the correct dependency has been defined, you can start a server as follows:

```java
WebApiServer server = WebApi.createServer();
server.start(port);
```

The `WebApiServer` interface provides an additional method `stop`. You can use this to stop the server as follows:

```java
server.stop();
```

## Deployment

### Standalone JAR

To deploy this project, you will only require the distribution JAR file. It can be deployed to any server that has JRE 1.8+ installed.

### WAR

Building the project (Maven phase package) will assemble a WAR file. You can upload this WAR file to a Java Application server.

### Elastic Beanstalk

This project can easily be deployed to AWS Elastic Beanstalk.

Simply configure your Maven settings file as described in step 1 and 2 in [this blog post](https://maxrohde.com/2018/04/01/upload-elastic-beanstalk-application-using-maven/).

Also create a S3 bucket equal to the project id of this project (`web-api-example-v2`).

You can now trigger a deployment to Elastic Beanstalk with the following command:

```
mvn clean install beanstalk:upload-source-bundle beanstalk:create-application-version beanstalk:create-environment
```

## Dependency

This project has not yet been published to any repository. However, you can build it from source, install it into your
repository and then reference it as follows:

```
<dependency>
	<groupId>de.mxro.webapiexample</groupId>
	<artifactId>web-api-example-v2</artifactId>
	<version>[insert latest version here]</version>
</dependency>
```
