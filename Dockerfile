# Building api
#FROM maven as build

#WORKDIR /code

#COPY . .

#RUN mvn clean install

# Starting TOMCAT
#FROM tomcat:8

#COPY --from=build /code/target/*.war /usr/local/tomcat/webapps/

#EXPOSE 8181:8080
#CMD ["catalina.sh", "run"]


# Starting TOMCAT
FROM tomcat
ADD target/controle-fitoteca.war /usr/local/tomcat/webapps/
#EXPOSE 8181
#CMD ["catalina.sh", "run"]