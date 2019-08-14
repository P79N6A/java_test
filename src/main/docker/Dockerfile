FROM oracle/openjdk:8
VOLUME /tmp
ADD jdevtemplateboot.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=product"]