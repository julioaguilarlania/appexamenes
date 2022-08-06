FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} appexamenes.jar
ENTRYPOINT ["java","-jar","/appexamenes.jar"]
