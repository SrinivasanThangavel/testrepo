FROM openjdk
WORKDIR usr/lib

ADD ./target/testrepo.jar /usr/lib/testrepo.jar

ENTRYPOINT ["java","-jar","testrepo.jar"]
