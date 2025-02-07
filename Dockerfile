FROM openjdk:23-jdk-slim
WORKDIR /app
COPY target/receipt-0.0.1-SNAPSHOT.jar receipt.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "receipt.jar" ]