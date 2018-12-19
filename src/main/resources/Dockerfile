FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/coding-challenge-1.0.2.jar /app/lib
ENTRYPOINT ["java","-cp","app:app/lib/*","com.n26.Application"]