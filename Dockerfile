FROM openjdk:8-jdk-alpine
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /back-dist/lib
COPY ${DEPENDENCY}/META-INF /back-dist/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /back-dist
ENTRYPOINT ["java","-cp","back-dist:back-dist/lib/*","com.colsubsidio.appeventos.AppeventosApplication"]