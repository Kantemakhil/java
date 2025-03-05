#!/bin/bash

#set -x

source /assets/utils.sh
source /assets/profile

alias cp='cp'
export CATALINA_HOME=/usr/local/tomcat

get_property() {
  # if the elite configuration file does not exist ...
  filename=$1
  pattern=$2
  default_value=$3
  if [ ! -f $filename ]; then
    echo $default_value
  else
    cat $filename | grep "^${pattern}" | awk -F= '{gsub(/^[ \t]+/, "", $2); gsub(/[ \t]+$/, "", $2); print $2}'
  fi
}

update_tomcat_configs() {
  elite_properties=$1
  env_file=$2
  echo_yellow "Loading the configurations from ${elite_properties} ..."
  oracle_host=$(get_property ${elite_properties} 'oracle.db.address')
  oracle_port=$(get_property ${elite_properties} 'oracle.db.port.host')
  oracle_sid=$(get_property ${elite_properties} 'oracle.db.sid')
  jdbc_url="jdbc:postgresql://${oracle_host}:${oracle_port}/${oracle_sid}?currentSchema=oms_owner"

  # compose the default elite.properties
  grep -v "datasource"  > ${CATALINA_HOME}/conf/catalina.properties.tmp < ${CATALINA_HOME}/conf/catalina.properties
  cp ${CATALINA_HOME}/conf/catalina.properties.tmp ${CATALINA_HOME}/conf/catalina.properties
  echo "datasource.driver-class-name=org.postgresql.Driver" >> ${CATALINA_HOME}/conf/catalina.properties
  echo "datasource.url=${jdbc_url}" >> ${CATALINA_HOME}/conf/catalina.properties
  rm -fR ${CATALINA_HOME}/conf/catalina.properties.tmp

  # trying to get the credentials from the $env_file
  if [ -f $env_file ]; then
    database_user=$(get_property $env_file "DATABASE_USER")
    database_pass=$(get_property $env_file "DATABASE_PASS")
    if [ "${database_user}" != "" ] && [ "${database_pass}" != "" ]; then
      echo "datasource.username=${database_user}" >> ${CATALINA_HOME}/conf/catalina.properties
      echo "datasource.password=${database_pass}" >> ${CATALINA_HOME}/conf/catalina.properties
    fi
  fi

  # as fallback, trying to get the credentials from the enviroment variables
  configured=$(grep "datasource.username" < $CATALINA_HOME/conf/catalina.properties)
  if [ "{configured}" == "" ]; then
    if [ "${DATABASE_USER}" != "" ] && [ "${DATABASE_PASS}" != "" ]; then
      echo "datasource.username=${DATABASE_USER}" >> ${CATALINA_HOME}/conf/catalina.properties
      echo "datasource.password=${DATABASE_PASS}" >> ${CATALINA_HOME}/conf/catalina.properties
    fi
  fi  

  configured=$(grep "datasource.username" < $CATALINA_HOME/conf/catalina.properties)
  if [ "{configured}" == "" ]; then
    echo_red ">>> ERROR: Database credentials not found!!!"
    echo     "You can provide the credentials via /assets/.env or /configs/.env file"
    echo     ""
    echo     "To have a better understanding of credentials with Docker on this project you can check"
    echo     "the \"Security for credentials configurations\" section in the project README file."
    exit 1
  fi
  cp -fR /assets/context.xml $CATALINA_HOME/conf
}

export elite_properties=/assets/elite.properties
if [ -f /configs/elite.properties ]; then 
  export elite_properties=/configs/elite.properties
fi

sleep 30s

export env_file="/assets/.env"
if [ -f "/configs/.env" ]; then
  export env_file="/configs/.env"
fi

update_tomcat_configs $elite_properties $env_file

cd $CATALINA_HOME
$CATALINA_HOME/bin/catalina.sh run

