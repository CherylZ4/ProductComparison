FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD build/libs/ProductComparison.jar ProductComparison.jar
ENTRYPOINT ["java", "-jar", "ProductComparison.jar"]