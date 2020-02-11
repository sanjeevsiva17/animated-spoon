# Selecting Base Image: i.e. golang :alpine
FROM adoptopenjdk/openjdk8

#update image
RUN apt update

#zoomtail logo
RUN apt install -y figlet

# Selecting the working directory
WORKDIR /metamorphosis

# Copy all files from local system to alpine
COPY . .

#creating executable file
RUN ./gradlew clean build -x test

EXPOSE 8000

CMD ["java", "-jar", "build/libs/login-0.0.1-SNAPSHOT.jar"]

RUN figlet metamorphosis