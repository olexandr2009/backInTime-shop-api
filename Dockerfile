FROM azul/zulu-openjdk:17-latest
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
