# Wallet-Microservices

Requirements
Java - 1.8.x

Maven - 3.x.x

Steps to Setup
1. Clone the application

git clone https://github.com/Eng-omar-essa/Wallet-Microservices.git

2. Build and run the app using maven

cd wallet-microservices

mvn package

java -jar target/wallet-0.0.1-SNAPSHOT.jar

Alternatively, you can run the app directly without packaging it like so -

mvn spring-boot:run

# Docker
 docker build . -t wallet-service

 docker run -p 5000:8080 wallet-service
 
 docker tag image wallet-service omarissa/wallet-service:0.0.1-SNAPSHOT
 
