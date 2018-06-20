FROM openjdk:8-jre-alpine
VOLUME /tmp
COPY ./target/blog-0.0.1.jar /tmp
WORKDIR /tmp
EXPOSE 8080
CMD ["java", "-jar", "blog-0.0.1.jar"]