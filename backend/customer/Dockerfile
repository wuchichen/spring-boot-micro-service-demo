# use jdk instead of jre
FROM openjdk:11.0-jdk

# create folder
RUN mkdir /springapp

# navigate into folder
WORKDIR /springapp

# source folder
COPY ./src ./src

# maven wrapper configuration folder
COPY ./.mvn ./.mvn

# pom file & maven wrapper shell script
COPY ./pom.xml ./mvnw ./

# show all files
RUN ls

# build with maven wrapper and no unit testing
RUN /bin/sh -c "/springapp/mvnw package -Dmaven.test.skip=true clean install"

# expose default port
EXPOSE 9091

# run .jar binary file
CMD ["java","-jar","./target/app.jar"]