FROM openjdk:17-jdk-slim AS coreinstaller
COPY ../../Core .
RUN ./mvnw install

FROM openjdk:17-jdk-slim AS packager
COPY --from=coreinstaller /root/.m2 /root/.m2
COPY ../../WithdrawalMicroservice .
RUN ./mvnw package -DskipTests

FROM amazoncorretto:17-alpine3.17-jdk AS javabuilder
RUN apk add --no-cache binutils
RUN $JAVA_HOME/bin/jlink \
    --module-path "$JAVA_HOME/jmods" \
    --add-modules java.compiler,java.sql,java.naming,java.management,java.instrument,java.rmi,java.desktop,jdk.internal.vm.compiler.management,java.xml.crypto,java.scripting,java.security.jgss,jdk.httpserver,java.net.http,jdk.naming.dns,jdk.crypto.cryptoki,jdk.unsupported \
    --verbose \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /customjre

FROM alpine:latest AS application
ENV JAVA_HOME=/jre
ENV PATH="${PATH}:${JAVA_HOME}/bin"
COPY --from=javabuilder /customjre ${JAVA_HOME}
COPY --from=packager target/WithdrawalMicroservice-*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar", "--spring.profiles.active=docker"]