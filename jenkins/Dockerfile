FROM sysconjusticesystems/s4elite:apache-tomcat-9.0.86-jdk8
LABEL maintainer="akhilk@vrnda.com"
ADD --chown=tomcat:tomcat Elite2Web/target/Elite2Web.war /opt/tomcat/webapps
RUN mkdir -p /opt/tomcat/webapps/corrections
COPY --chown=tomcat:tomcat dist/. /opt/tomcat/webapps/corrections
ADD --chown=tomcat:tomcat jenkins/context.xml /opt/tomcat/conf
ADD --chown=tomcat:tomcat jenkins/setenv.sh /opt/tomcat/bin
ADD --chown=tomcat:tomcat support_libs/postgresql-9.4-1201-jdbc41.jar /opt/tomcat/lib
EXPOSE 8080