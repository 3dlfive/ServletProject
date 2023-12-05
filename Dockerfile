
FROM openjdk:latest
COPY ./target/tinder-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar  app.jar
EXPOSE 5000
#ENV JDBC_DATABASE_URL "spark-master"
#ENV JDBC_DATABASE_LOGIN "7077"
#ENV JDBC_DATABASE_PASSWORD "python3"
ENTRYPOINT ["java", "-cp", "app.jar", "App"]