FROM azul/zulu-openjdk:17-latest
COPY build/libs/backInTime-shop-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
