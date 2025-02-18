FROM openjdk:17-alpine

RUN apk update && \
    apk add --no-cache netcat-openbsd

WORKDIR /app

COPY ./the-app.jar /app/the-app.jar
COPY ./await-db /app/await-db

RUN chmod +x /app/await-db

ENTRYPOINT ["/app/await-db"]

CMD ["java", "-jar", "/app/the-app.jar"]

