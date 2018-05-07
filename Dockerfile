FROM maven:3-jdk-8

COPY . /source
WORKDIR /source
RUN mkdir /app && \
    mvn clean package && \
    cp application/target/application-*.jar /app/demo.jar

RUN chmod 777 /source/wait-for-it.sh

EXPOSE 8080
CMD ["java", "-jar", "/app/demo.jar"]