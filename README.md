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
