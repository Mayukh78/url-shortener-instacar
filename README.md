# URL Shortner Project Using Spring Boot and Postgres

Built a url shortner project using Spring Boot and Postgres

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You need to have maven,docker,docker compose installed on your system.
To install maven on ubuntu,use the following command
```
sudo apt install maven
```
For installing docker and docker compose,follow official documentation.

## Deployment

First clone my repository, then move to project directory
```
cd url-shortener-instacar/
```
Then deploy the docker project using the following command
```
docker-compose up --build
```
If you want to run with administrator privilege use ```sudo docker-compose up --build``` .
After spring successfully starts the application you can access it on ```http://localhost:8080/``` . That's it.

## Implementation Logic

Whenever we get a POST request, we generate a random number and convert it to 7 digit string. The encoding method is base62. So we can store 62^7 different url. If our generated code is already present in the database,then we generate another random number and same process goes on. If the user has put a custom short code,we first check if that code already exists in the database. If it's already present, we return saying the custom already exists,otherwise we save it. 

If a link has a corresponding expiration date,whenever a GET request for that link comes,we check current time is greater than expiration date. If it's greater,we remove from the entry the database.

## Project Code Structure

In root directory there are 2 main files.

```pom.xml``` file contains information of project and configuration information for the maven to build the project such as dependencies, build directory, source directory, test source directory, plugin, goals etc.

```docker-compose.yml```file does, specifying what images are required, what ports they need to expose, whether the have access to the host filesystem, what commands should be run, and so on.

### Model and Controller

```/src/main/java/com/example/urlshortener/``` contains all the implementation logic.

```UrlShortenerApplication.java``` starts the spring boot application.

```UrlDetails.java``` contains what attributes will a record contains and what should be their corresponding coloumn name in postgres database

```UrlDetailsRepository.java``` contains methods that are required to perform various operations(save,delete etc)in database.

```RequestDetails.java``` is used for mapping all the request parameters to a java object.

```CodeDetails.java``` is used for generating the short code

```UrlController.java``` is the controller class that is responsible for managing the user requests.

### View

```url-shortener-instacar/src/main/resources/templates/``` contains all views of our program. 

We used Thymeleaf template engine. The code that are re-used in every or most web pages(for ex: navbar,footer etc) are stored ```component.html``` file. 

The ```application.properties``` file is nothing more than simple key-value storage for configuration properties. In this file we mention in which port should spring boot application run, username & password for postgres database etc.