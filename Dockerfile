FROM openjdk:8-jdk-alpine
RUN mkdir -p /back-dist/config/
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /back-dist/lib
COPY ${DEPENDENCY}/META-INF /back-dist/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /back-dist
RUN ln -r /back-dist/application.properties /back-dist/config/application.properties
ENTRYPOINT ["java","-cp","back-dist:back-dist/lib/*","com.colsubsidio.appeventos.AppeventosApplication"]