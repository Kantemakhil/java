#!/usr/bin/env bash
#set -x

# LSB Tags
### BEGIN INIT INFO
# Provides:          s4elite
# Required-Start:    $local_fs $network
# Required-Stop:     $local_fs $network
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: S4 start script.
# Description:       S4 Elite Running under Docker.
### END INIT INFO

# Startup script for s4elite under *nix systems

##################################################
# Set the name which is used by other variables.
# Defaults to the file name without extension.
##################################################
NAME={0##*/}

# To get the service to restart correctly on reboot, uncomment below (3 lines):
# ========================
# chkconfig: 3 99 99
# description: S4 Elite under Docker
# processname: s4elite
# ========================

# Configuration files path
#
# ./configs/elite.properties:/etc/elite/s4elite.properties
#   Parametter file containing all the configurations


usage() {
    echo "Usage: ${0##*/} {start|stop|restart|update|status}"
    exit 1
}
[ $# -gt 0 ] || usage


docker_hub_state="none"


# load the properties file replacing dots 2 underscores, 
# ie: database.image-tag => database_image_tag
load_s4elite_configs() {
  configs_filename=$1
  echo ">>> Loading configurations from '$configs_filename' ..."
  if [ ! -e "$configs_filename" ]; then
    printf '\n>>> ERROR'
    printf "\nThe configuration file \"$configs_filename\" was not found!"
    return -1
  fi

  while IFS='=' read -r key value; do
    if [ "${key}" != "" ]; then
      key=$(echo $key | tr '.' '_' | tr '-' '_' | xargs echo -n)
      value=$(echo $value | xargs echo -n)
      eval "${key}='${value}'"
      echo "${key}=${value}"
    fi
  done < "$configs_filename"
  return 0
}



docker_login() {
  if [ "${docker_hub_state}" != "logged" ]; then

    if [ -f "${configs_dir}/.env" ]; then
      source "${configs_dir}/.env"  
    fi
 
    if [ "${DOCKER_USER}" == "" ] || [ "${DOCKER_PASS}" == "" ] ; then 
      echo "Please enter the Docker Hub credentials ..."
      printf "Username: "
      read username
      printf "Password: "
      read -s password
      export DOCKER_USER="${username}"
      export DOCKER_PASS="${password}"
    fi

    printf "%s\n" "${DOCKER_PASS}" | docker login -u ${DOCKER_USER} --password-stdin
    if [ "-$?-" != "-0-" ]; then
      exit 1
    else
      export docker_hub_state="logged"
    fi

    if [ "${DOCKER_ORGANIZATION}" == "" ]; then
      export owner_username=${username}
    else
      export owner_username=$DOCKER_ORGANIZATION
    fi
  fi
}


show_iface_ips() {
  ip addr | awk '/^[0-9]+:/ {  sub(/:/,"",$2); iface=$2 } /^[[:space:]]*inet / {  split($2, a, "/"); print iface":"a[1] }'
}


setup_docker_network() {
  echo "Adjusting network subnet ..."
  elite_network=$(docker network ls | grep "${network_name}")
  if [ "${elite_network}" == "" ]; then
    docker network create --subnet=${network_address} ${network_name} 
  fi  
}


real_native_path() {
  os_info=$(uname | awk '{ print tolower($0)}')
  real_path=$(realpath "$1")
  if [[ $os_info = *"mingw"* ]] || [[ $os_info = *"cygwin"* ]]; then
    native_path=$(echo $real_path | awk '{ drive=toupper(substr($0,2,1)); print "/"drive"_DRIVE"substr($0,3) }')
    echo "${native_path}"
  else
    echo "${real_path}"
  fi
}


run_database() {
  if [ "${s4elite_depends}" == "${oracle_db_instance}" ]; then
    echo "Checking Oracle Database ..."
    docker_login
    db_instance_id=$(docker ps -qa --filter name=$oracle_db_instance)
    # if the instance never run ...
    if [ "${db_instance_id}" == "" ]; then
      echo "  . Starting Oracle Database ..."
      native_configs_dir=$(real_native_path "${configs_dir}")
      cmd="docker run -d --privileged -v ${native_configs_dir}:/configs --network=${network_name} --hostname ${oracle_db_hostname} --ip ${oracle_db_address} --name ${oracle_db_instance} -p ${oracle_db_port_host}:${oracle_db_port_container} -t ${oracle_db_image_tag}"
      echo "${cmd}"
      $cmd
      sleep 60s
    else
      # if the instance is not running then start it
      echo "  . Activating Oracle Database ..."
      running_status=$(docker ps -q --filter name=$oracle_db_instance)
      if [ "${running_status}" == "" ]; then
        docker start ${db_instance_id} > /dev/null 2>&1
        sleep 60s
      fi
    fi
  fi
}


update_app() {

  echo "Checking Elite S4 Application for update ..."
  instance_id=$(docker ps -qa --filter name=$s4elite_instance)
  if [ "${instance_id}" != "" ]; then

    image_name_tag=$(docker inspect $instance_id | jq -r '.[]|.Config.Image')
    if [ "${image_name_tag}" != "" ]; then
      docker_login
      status=$(docker pull $image_name_tag | grep "Downloaded newer image")
      if [ "${status}" != "" ]; then

        echo '>>> There is one update for this image ... '

        # stop the docker instance
        stop_app false

        # remove the docker instance
        docker rm $instance_id

        docker rmi $(docker images -q -f "dangling=true")

        start_app true
      else
        echo "The instance is already the latest version, nothing to do :-)"
      fi
    fi
  else
    start_app true
  fi
}



start_app() {

  docker_login
  
  start_database=$1
  if [ "${start_database}" == "true" ]; then
    run_database
  fi

  echo "Starting Elite S4 Application ..."
  app_instance_id=$(docker ps -qa --filter name="${s4elite_instance}")

  # if the instance never run ...
  if [ "${app_instance_id}" == "" ]; then
    native_configs_dir=$(real_native_path "${configs_dir}")
    cmd="docker run -d --privileged -v ${native_configs_dir}:/configs --network=${network_name} --hostname ${s4elite_hostname} --ip ${s4elite_address} -p ${s4elite_port_host}:${s4elite_port_container} --name ${s4elite_instance} -t ${s4elite_image_tag}"
    echo "${cmd}"
    $cmd
  else
    # if the instance is not running then start it
    running_status=$(docker ps -q --filter name=$s4elite_instance)
    if [ "${running_status}" == "" ]; then
      docker start ${app_instance_id} > /dev/null 2>&1
    fi
  fi
}

stop_app() {
  echo "Stopping Elite S4 Application ..."
  app_instance_id=$(docker ps -q --filter name=$s4elite_instance)
  # if the instance never run ...
  if [ "${app_instance_id}" != "" ]; then
    docker stop $app_instance_id  > /dev/null 2>&1
  fi

  stop_database=$1
  if [ "${stop_database}" == "true" ]; then
    echo "Stopping Oracle Database ..."
    db_instance_id=$(docker ps -q --filter name=$oracle_db_instance)
    # if the instance never run ...
    if [ "${db_instance_id}" != "" ]; then
      docker stop $db_instance_id  > /dev/null 2>&1
    fi
  fi
}

check_status() {
  instance_id=$(docker ps -q --filter name=$s4elite_instance)
  if [ "${instance_id}" != "" ]; then
    pid=$(docker inspect -f '{{.State.Pid}}' $instance_id)
    if [ "$pid" != "" ]; then
      echo ">>> Elite S4 Application running pid=${pid}"
      echo ""
      return 0
    else
      echo "Elite S4 Application is NOT running"
      return 1
    fi
  fi  
}

export configs_dir=/etc/elite
if [ -f "./elite.properties" ]; then
  export configs_dir=$(realpath .)
elif [ -f "./configs/elite.properties" ]; then
  export configs_dir=$(realpath ./configs)
fi
export configs_filename="${configs_dir}/elite.properties"

if [ -f $configs_filename ]; then
  load_s4elite_configs $configs_filename
else
  echo "The configuration file not be found either. Candidates:"
  echo "    $(realpath .)/elite.properties"
  echo "    $(realpath ./configs)/elite.properties"
  echo "    /etc/elite/elite.properties"
  exit 1
fi

ACTION=$1
case "$ACTION" in

  start)
    start_app true
    ;;


  stop)
    stop_app true
    ;;

  restart)
    service $NAME stop
    service $NAME start
    ;;

  update)
    update_app
    ;;

  check|status)
    check_status
    ;;

  *)
    usage

    ;;
esac

exit 0
