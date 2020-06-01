FROM peeyush24/tomcat-mysql:latest
WORKDIR /opt/apache-tomcat-8.5.55
COPY target/Books.war /opt/apache-tomcat-8.5.55/webapps/
#COPY migrations/migrate.sh .
#RUN chmod +x migrate.sh
#COPY migrations migrations
#RUN wget -qO- https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/6.4.2/flyway-commandline-6.4.2-linux-x64.tar.gz | tar xvz && ln -s `pwd`/flyway-6.4.2/flyway /usr/local/bin
#CMD ["&&", "./migrate.sh"]
EXPOSE 8080 3306
