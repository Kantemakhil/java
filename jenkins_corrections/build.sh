#!/usr/bin/env bash

#set -x

chmod +x docker/*.sh
source docker/utils.sh


export env_state=none
export project_dir=$(dirname $(absolute_path "$0"))
export build_dir="${project_dir}/.build-artifacts"
export elite_properties="${project_dir}/docker/elite.properties"
export branch_name=develop
export bitbucket_hostname=${SYSCON_BITBUCKET_HOSTNAME:-bitbucket.org}

update_branch_name() {
  curr_dir=$(pwd)
  cd "${project_dir}"
  branch_name=$(git branch | sed -n -e 's/^\* \(.*\)/\1/p' | sed -e 's:\/:\_:g')
  valid_chars='[^a-zA-Z0-9|\-|\_]'
  export branch_name="${branch_name//$valid_chars}"
}


setup_build_environment() {

  if [ "${env_state}" == "none" ]; then

    mkdir -p $build_dir

    update_branch_name
  
    if [ ! -f $elite_properties ]; then
      cp docker/elite-SAMPLE.properties $elite_properties
    fi

    # update the local parameter file with the branch name ...
    s4elite_image_tag=$(get_property $elite_properties "s4elite.image.tag")
    image_name=$(echo $s4elite_image_tag | awk -F: '{ print $1 }')
    export s4elite_image_tag="${image_name}:${branch_name}"
    sed -i "/\(^s4elite\.image\.tag=\).*/c s4elite.image.tag=${s4elite_image_tag}" $elite_properties

    # convert the property file into environment variables
    props2env $elite_properties false
  
    # The database credentials priority is to pick from the local environment variables
    # It will override the .env file (usefull strategy during the CircleCI build)
    if [ "${project_dir}/docker/.env" ]; then
      if [ "${DATABASE_USER}" == "" ]; then
        export DATABASE_USER=$(get_property "${project_dir}/docker/.env" "DATABASE_USER")
      fi
      if [ "${DATABASE_PASS}" == "" ]; then
        export DATABASE_PASS=$(get_property "${project_dir}/docker/.env" "DATABASE_PASS")
      fi
    fi

    # Generating the .env file backed into the Docker image
    if [ "${DATABASE_USER}" != "" ] && [ "${DATABASE_PASS}" != "" ] ; then
      echo "DATABASE_USER=${DATABASE_USER}"  > "${build_dir}/.env"
      echo "DATABASE_PASS=${DATABASE_PASS}" >> "${build_dir}/.env"
    else
      echo_red ">>> ERROR: Database credentials not found!!!"
      echo     "You can provide the credentials via docker/.env file"
      echo     "   DATABASE_USER=xxxxxxx"
      echo     "   DATABASE_PASS=xxxxxxx"
      echo     ""
      echo     "To have a better understanding of credentials with Docker on this project you can check"
      echo     "the \"Security for credentials configurations\" section in the project README file."
      exit 1
    fi
    export env_state=done
  fi  

}


clean_build() {
  local date1=$(date +"%s");
  echo_yellow "Cleaning build ..."
  fast_delete "${project_dir}/Elite2Common/target"
  fast_delete "${project_dir}/Elite2DAO/target"
  fast_delete "${project_dir}/Elite2Business/target"
  fast_delete "${project_dir}/Elite2Web/target"
  fast_delete "${project_dir}/Elite2Angular/dist"
  fast_delete "${project_dir}/Elite2Angular/node_modules"
  fast_delete "${build_dir}"
  local date2=$(date +"%s");
  local diff=$(($date2-$date1))
  echo_green "Clean done in: ${diff} second(s)!"
}


add_bitbucket_keys() {
  local current_dir=$(pwd)
  cd ~
  ssh_dir="$(pwd)/.ssh"
  mkdir -p "${ssh_dir}"
  host_keys_filename="${ssh_dir}/known_hosts"
  bitbucket_key=$(ssh-keyscan bitbucket.org 2>/dev/null)
  if [ ! -f "${host_keys_filename}" ]; then
    echo $bitbucket_key > "${host_keys_filename}"
  else 
    has_key=$(grep "${bitbucket_key}" < "${host_keys_filename}" )
    if [ "${has_key}" == "" ]; then
      printf "\n%s" "${bitbucket_key}" >> $host_keys_filename
    fi 
  fi
  cd "${current_dir}"
}


patch_ui_components() {
  add_bitbucket_keys
  ui_components_dir="$(temporary_dir ${project_dir})/$(random_filename 'ui-components-' '')"
  if [ -d "${ui_components_dir}" ]; then
    fast_delete "${ui_components_dir}"
  fi
  git clone git@${bitbucket_hostname}:cool_syscon_team/ui-components.git "${ui_components_dir}"
  fast_delete "${project_dir}/Elite2Angular/src/app/core/ui-components"
  mv "${ui_components_dir}/src/app/ui-components" "${project_dir}/Elite2Angular/src/app/core"
  fast_delete "${ui_components_dir}"
}


build_ui() {

  local date1=$(date +"%s");  
  echo_yellow "Building UI ..."

  update_branch_name
  if [ "${branch_name}" != "S4-1984-s4-ui-updates-based-on-legals-mo"] && ["${branch_name}" != "S4_ANG_11_API_UPGRADE"]; then
    patch_ui_components
  fi

  cd "${project_dir}/Elite2Angular"

  export NODE_OPTIONS=--max-old-space-size=1024

  # update javascript library dependencies ...
  yarn
  # Workaround for ENOENT error where node-sass needs to be rebuilt
  #npm rebuild node-sass@5.0.0

  flags="--aot --output-hashing all"
  if [ "${branch_name#master}" != "${branch_name}" ] || [ "${branch_name#release}" != "${branch_name}" ]; then
    flags="--prod --aot -sm=false -ec=true --named-chunks=false -oh=all --build-optimizer=true -vc=true"
  fi
  free -m 
  node --version
  npm --version
  #node --max-old-space-size=6144 ./node_modules/@angular/cli/bin/ng build
  npm run prod
  
  if [ "$?" != "0" ]; then
    echo_red "ERROR invoking: yarn ng build $flags --no-progress"
    exit 1
  fi  

  fast_delete "${build_dir}/frontend"
  mkdir -p "${build_dir}/frontend"
  cp -fR "${project_dir}/Elite2Angular/dist" "${build_dir}/frontend"

  cd $project_dir

  local date2=$(date +"%s");
  local diff=$(($date2-$date1))
  echo_green "UI built in: ${diff} second(s)!"
}


build_war() {

  local date1=$(date +"%s");  
  echo_yellow "Building WAR ..."

  if [ "${CIRCLECI}" == "" ]; then
    mvn install:install-file -Dfile=./support_libs/ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/syncfusion-docio-18.4.0.30.jar -DgroupId=syncfusion -DartifactId=syncfusion-docio -Dversion=18.4.0.30 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/syncfusion-ej2-wordprocessor-18.4.0.30.jar -DgroupId=syncfusion -DartifactId=syncfusion-ej2-wordprocessor -Dversion=18.4.0.30 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/syncfusion-javahelper-18.4.0.30.jar -DgroupId=syncfusion -DartifactId=syncfusion-javahelper -Dversion=18.4.0.30 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/jasperreports-6.7.0.jar -DgroupId=jasperreports.sf -DartifactId=jasperreports -Dversion=6.7.0 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/sendgrid-java.jar -DgroupId=sendgrid -DartifactId=sendgrid-java -Dversion=1.0 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/jolt-core-0.1.0.jar -DgroupId=com.bazaarvoice.jolt -DartifactId=jolt-core -Dversion=0.1.0 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/json-utils-0.1.0.jar -DgroupId=com.bazaarvoice.jolt -DartifactId=json-utils -Dversion=0.1.0 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/itext-2.1.7.js8.jar -DgroupId=com.lowagie -DartifactId=itext -Dversion=2.1.7.js8 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/jcommon-1.0.23.jar -DgroupId=org.jfree -DartifactId=jcommon -Dversion=1.0.23 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/jfreechart-1.0.19.jar -DgroupId=org.jfree -DartifactId=jfreechart -Dversion=1.0.19 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/castor-xml-1.4.1.jar -DgroupId=org.codehaus.castor -DartifactId=castor-xml -Dversion=1.4.1 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/commons-digester-2.1.jar -DgroupId=commons-digester -DartifactId=commons-digester -Dversion=2.1 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/ecj-3.21.0.jar -DgroupId=org.eclipse.jdt -DartifactId=ecj -Dversion=3.21.0 -Dpackaging=jar
  else
    mvn install:install-file -Dfile=./support_libs/ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/syncfusion-docio-18.4.0.30.jar -DgroupId=syncfusion -DartifactId=syncfusion-docio -Dversion=18.4.0.30 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/syncfusion-ej2-wordprocessor-18.4.0.30.jar -DgroupId=syncfusion -DartifactId=syncfusion-ej2-wordprocessor -Dversion=18.4.0.30 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/syncfusion-javahelper-18.4.0.30.jar -DgroupId=syncfusion -DartifactId=syncfusion-javahelper -Dversion=18.4.0.30 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/jasperreports-6.7.0.jar -DgroupId=jasperreports.sf -DartifactId=jasperreports -Dversion=6.7.0 -Dpackaging=jar
    mvn install:install-file -Dfile=./support_libs/placeholder.jar -DgroupId=net.syscon.elite -DartifactId=Elite2Common -Dversion=1.0 -Dpackaging=jar
    mvn install:install-file -Dfile=./support_libs/placeholder.jar -DgroupId=net.syscon.elite -DartifactId=Elite2DAO -Dversion=1.0 -Dpackaging=jar
    mvn install:install-file -Dfile=./support_libs/placeholder.jar -DgroupId=net.syscon.elite -DartifactId=Elite2Business -Dversion=1.0 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/sendgrid-java.jar -DgroupId=sendgrid -DartifactId=sendgrid-java -Dversion=1.0 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/jolt-core-0.1.0.jar -DgroupId=com.bazaarvoice.jolt -DartifactId=jolt-core -Dversion=0.1.0 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/json-utils-0.1.0.jar -DgroupId=com.bazaarvoice.jolt -DartifactId=json-utils -Dversion=0.1.0 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/itext-2.1.7.js8.jar -DgroupId=com.lowagie -DartifactId=itext -Dversion=2.1.7.js8 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/jcommon-1.0.23.jar -DgroupId=org.jfree -DartifactId=jcommon -Dversion=1.0.23 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/jfreechart-1.0.19.jar -DgroupId=org.jfree -DartifactId=jfreechart -Dversion=1.0.19 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/castor-xml-1.4.1.jar -DgroupId=org.codehaus.castor -DartifactId=castor-xml -Dversion=1.4.1 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/commons-digester-2.1.jar -DgroupId=commons-digester -DartifactId=commons-digester -Dversion=2.1 -Dpackaging=jar
	mvn install:install-file -Dfile=./support_libs/ecj-3.21.0.jar -DgroupId=org.eclipse.jdt -DartifactId=ecj -Dversion=3.21.0 -Dpackaging=jar
	

    mvn -f Elite2Common/pom.xml dependency:go-offline
    if [ "$?" != "0" ]; then
      echo_red "ERROR invoking: mvn -f Elite2Common/pom.xml dependency:go-offline"
      exit 1
    fi

    mvn -f Elite2DAO/pom.xml dependency:go-offline
    if [ "$?" != "0" ]; then
      echo_red "ERROR invoking: mvn -f Elite2DAO/pom.xml dependency:go-offline"
      exit 1
    fi

    mvn -f Elite2Business/pom.xml dependency:go-offline
    if [ "$?" != "0" ]; then
      echo_red "ERROR invoking: mvn -f Elite2Business/pom.xml dependency:go-offline"
      exit 1
    fi

    mvn -f Elite2Web/pom.xml dependency:go-offline
    if [ "$?" != "0" ]; then
      echo_red "ERROR invoking: mvn -f Elite2Web/pom.xml dependency:go-offline"
      exit 1
    fi

  fi

  mvn -f Elite2Common/pom.xml clean install -U
  if [ "$?" != "0" ]; then
    echo_red "ERROR invoking: mvn -f Elite2Common/pom.xml clean install -U"
    exit 1
  fi

  mvn -f Elite2DAO/pom.xml clean install -U
  if [ "$?" != "0" ]; then
    echo_red "ERROR invoking: mvn -f Elite2DAO/pom.xml clean install -U"
    exit 1
  fi

  mvn -f Elite2Business/pom.xml clean install -U
  if [ "$?" != "0" ]; then
    echo_red "ERROR invoking: mvn -f Elite2Business/pom.xml clean install -U"
    exit 1
  fi

  update_branch_name
  sub_hash=$(git log -n 1 origin/$branch_name --pretty=format:"%h %cs")
  app_elite_version="${branch_name}-${sub_hash}"
  find ${project_dir}/Elite2Web/src/main/profiles -type f | grep .properties | xargs sed -i "/\(^app\.elite\.version=\).*/c app.elite.version=${app_elite_version}"

  mvn -f Elite2Web/pom.xml clean install -U
  if [ "$?" != "0" ]; then
    echo_red "ERROR invoking: mvn -f Elite2Web/pom.xml clean install -U"
    exit 1
  fi
  
  mkdir -p "${build_dir}/backend"
  fast_delete "${build_dir}/backend"/*
  cp -f "${project_dir}/Elite2Web/target"/*.war "${build_dir}/backend/Elite2Web.war"
  cp -f ${project_dir}/support_libs/postgresql-9.4-1201-jdbc41.jar ${build_dir}/backend
  cp -f ${project_dir}/support_libs/ojdbc7.jar ${build_dir}/backend
  cd $project_dir
  local date2=$(date +"%s");
  local diff=$(($date2-$date1))
  echo_green "WAR built in: ${diff} second(s)!"
}


deploy_app() {
  if [ "${CIRCLECI}" == "" ]; then
    local date1=$(date +"%s");
    echo_yellow "Deploying the application on Tomcat ..."
    if [ ! -f "${build_dir}/backend/Elite2Web.war" ]; then
      build_war
    fi
    if [ ! -d "${build_dir}/frontend/dist" ]; then
      build_ui
    fi
    fast_delete "$CATALINA_HOME/webapps/Elite2Web"
    fast_delete "$CATALINA_HOME/webapps/Elite2Web.war"
    sleep 1s # wait tomcat to process the undeployment ...
    cp -fR "${build_dir}/backend/Elite2Web.war" "$CATALINA_HOME/webapps"
    cp -fR "${build_dir}/frontend/dist"/* "$CATALINA_HOME/webapps/ROOT"
	cp -fR "${project_dir}/video.jsp" "$CATALINA_HOME/webapps/ROOT"
    local date2=$(date +"%s");
    local diff=$(($date2-$date1))
    echo_green "Deploy done in: ${diff} second(s)!"
  fi
}


setup_docker_network() {
  echo "Adjusting network subnet ..."
  elite_network=$(docker network ls | grep "${network_name}")
  if [ "${elite_network}" == "" ]; then
    docker network create --subnet=${network_address} ${network_name} 
  fi  
}


docker_login() {

  # The docker credentials priority is to pick from the local environment variables
  # It will override the .env file (usefull strategy during the CircleCI build)
  if [ "${project_dir}/docker/.env" ]; then
    if [ "${DOCKER_USER}" == "" ]; then
      export DOCKER_USER=$(get_property "${project_dir}/docker/.env" "DOCKER_USER")
    fi
    if [ "${DOCKER_PASS}" == "" ]; then
      export DOCKER_PASS=$(get_property "${project_dir}/docker/.env" "DOCKER_PASS")
    fi
  fi

  # fallback, ask the user to enter with the credentials ...
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
  fi

  if [ "${DOCKER_ORGANIZATION}" == "" ]; then
	  export owner_username=${username}
  else
	  export owner_username=$DOCKER_ORGANIZATION
  fi

}


build_docker_image() {
  local date1=$(date +"%s");
  echo_yellow "Building Docker image ..."
  setup_build_environment
  #if [ ! -d "${build_dir}/frontend/dist" ]; then
   # build_ui
  #fi
  if [ ! -f "${build_dir}/backend/Elite2Web.war" ]; then
    build_war
  fi
  docker_login

  # if the instance exists, remove it ...
  container_id=$(docker ps -aq --filter name=${s4elite_instance})
  if [ "${container_id}" != "" ]; then
    docker rm -f ${container_id}
  fi
  echo_green "Docker image ${s4elite_image_tag} creation"
  # building the docker image with the Dockerfile in the current directory 
  docker build -t $s4elite_image_tag .

  if [ "$?" == "0" ]; then
    local date2=$(date +"%s");
    local diff=$(($date2-$date1))
    echo_green "Docker image built in: ${diff} second(s)!"
  else
    echo_red "ERROR >> There was a problem generating the docker image"  
    exit 1
  fi
}


publish_docker_image() {
  local date1=$(date +"%s");
  echo_yellow "Publishing Docker image ..."
  setup_build_environment
  build_docker_image
  docker_login
  docker push $s4elite_image_tag  
  if [ "$?" == "0" ]; then
    local date2=$(date +"%s");
    local diff=$(($date2-$date1))
    echo_green "Docker image published in: ${diff} second(s)!"
  else
    echo_red "ERROR >> There was a problem publishing the docker image"  
    exit 1
  fi
}


docker_run() {
  local date1=$(date +"%s");
  echo_yellow "Publishing Docker image ..."
  curr_dir=$(pwd)
  cd "${project_dir}/docker"
  setup_docker_network
  "${project_dir}/docker/s4elite.sh" update
  cd "${curr_dir}"
  local date2=$(date +"%s");
  local diff=$(($date2-$date1))
  echo_green "Docker run done in: ${diff} second(s)!"
}


usage() {
    echo_red "Usage: ${0##*/} { clean | ui | war | deploy | docker image | docker publish | docker run }"
    exit 1
}

[ $# -gt 0 ] || usage


ACTION=$1
case "$ACTION" in
  clean)
    clean_build
    ;;

  ui)
    build_ui
    ;;

  war)
    build_war
    ;;

  deploy)
    deploy_app
    ;;

  docker)
    if [ "$2" == "image" ]; then
      build_docker_image
    elif [ "$2" == "publish" ]; then
      publish_docker_image
    elif [ "$2" == "run" ]; then
      docker_run
    else
      usage
    fi  
    ;;

  *)
    usage
    ;;

esac

exit 0