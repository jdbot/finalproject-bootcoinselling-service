FROM openjdk:11
VOLUME /tmp
ADD ./target/bootcoinselling-service-0.0.1-SNAPSHOT.jar bootcoinselling.jar
ENTRYPOINT ["java","-jar","bootcoinselling.jar"]