FROM openjdk:11-jdk-slim-stretch as build
WORKDIR /backend
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
#RUN chmod +x ./gradlew
#RUN ./gradlew clean bootJar
ADD ./build/libs/myTemplate-0.0.1-SNAPSHOT.jar /backend/backend.jar
RUN mkdir -p /build/dependency && (cd /build/dependency; jar -xf /backend/backend.jar)

FROM openjdk:11-jdk-slim-stretch
VOLUME /tmp
ARG DEPENDENCY="/build/dependency"
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /backend/lib
COPY --from=build ${DEPENDENCY}/META-INF /backend/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /backend
ENTRYPOINT ["java","-cp","backend:backend/lib/*","com.example.myTemplate.MyTemplateApplication"]