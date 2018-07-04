FROM openjdk:8-jre-alpine
VOLUME /tmp
COPY ./target/*.war /tmp
WORKDIR /tmp
EXPOSE 8080
CMD ["java", "-jar", "blog-0.0.2.war"]