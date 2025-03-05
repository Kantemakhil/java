#!/usr/bin/env bash

#set -x

adduser -D -s /bin/sh -u 5000 tomcat
sed -i -r 's/^tomcat:!:/tomcat:x:/' /etc/shadow

sed -i "/\(^\|^\#\)\(^export\ CATALINA_HOME\=\).*/c export CATALINA_HOME\=\"${CATALINA_HOME}\"" /assets/entrypoint.sh

cp /build-artifacts/backend/Elite2Web.war ${CATALINA_HOME}/webapps
cp -fR /build-artifacts/backend/postgresql*.jar ${CATALINA_HOME}/lib
cp -fR /build-artifacts/backend/oracle*.jar ${CATALINA_HOME}/lib
cp -fR /build-artifacts/frontend/dist/* ${CATALINA_HOME}/webapps/ROOT
cp -fR /assets/context.xml ${CATALINA_HOME}/conf
rm -fR /build-artifacts

chmod 644 /assets/*
chmod +x  /assets/*.sh
chmod 700 /assets

ln -s /assets/profile /home/tomcat/.bash_profile
cat /assets/profile >> /etc/profile 

chown -fR tomcat:tomcat /assets
chown -fR tomcat:tomcat ${CATALINA_HOME}

