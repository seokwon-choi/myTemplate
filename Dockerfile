FROM openjdk:11-jdk-slim-stretch as build
WORKDIR /boot/app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*-SNAPSHOT.jar)

FROM openjdk:11-jdk-slim-stretch
VOLUME /tmp
ARG DEPENDENCY="/boot/app/build/dependency"
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
COPY ${JAR_FILE} backend.jar
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.myTemplate.MyTemplateApplication"]