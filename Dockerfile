FROM siddharthmishra01/tomcat-mysql:1.0.0

WORKDIR /opt/apache-tomcat-8.5.55

COPY target/Books.war /opt/apache-tomcat-8.5.55/webapps/

EXPOSE 8080 3306
