FROM eclipse-temurin:17
ARG ARCHIVO_JAR=target/*.jar
COPY ${ARCHIVO_JAR} appexamenes.jar
ENTRYPOINT ["java","-jar","/appexamenes.jar"]
