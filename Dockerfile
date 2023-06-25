FROM openjdk
WORKDIR usr/lib

ADD ./target/Inventory-Service-0.0.1-SNAPSHOT.jar /usr/lib/Inventory-Service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","Inventory-Service-0.0.1-SNAPSHOT.jar"]
