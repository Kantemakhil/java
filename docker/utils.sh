#!/usr/bin/env bash

ccred='\033[1;31m'
ccyellow='\033[1;33m'
ccgreen='\033[1;32m'
ccend='\033[0m'

echo_red() {
    echo -e "${ccred}$@${ccend}"
}

echo_yellow() {
    echo -e "${ccyellow}$@${ccend}"
}

echo_green() {
    echo -e "${ccgreen}$@${ccend}"
}


real_native_path() {
  os_info=$(uname | awk '{ print tolower($0)}')
  real_path=$(absolute_path "$1")
  if [[ $os_info = *"mingw"* ]] || [[ $os_info = *"cygwin"* ]]; then
    native_path=$(echo $real_path | awk '{ drive=toupper(substr($0,2,1)); print "/"drive"_DRIVE"substr($0,3) }')
    echo "${native_path}"
  else
    echo "${real_path}"
  fi
}


absolute_path() {
  curr_dir=$(pwd)
  if [ -f /usr/bin/realpath ]; then
    /usr/bin/realpath "$1"
  else
    if [ -d "$1" ]; then
      (cd "$1"; pwd)
    elif [ -f "$1" ]; then
      if [[ $1 = /* ]]; then
        echo "$1"
      elif [[ $1 == */* ]]; then
        echo "$(cd "${1%/*}"; pwd)/${1##*/}"
      else
        echo "$(pwd)/$1"
      fi
    else
      echo $1  
    fi
  fi  
}


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


props2env() {

  configs_filename=$1
  show_vars=$(echo $2 | awk '{ print tolower($0) }')

  if [ ! -f "$configs_filename" ]; then
    echo_red ">>> ERROR"
    echo_red "\nConfiguration file \"$configs_filename\" not found!"
    exit 1
  fi

  configs_real_path=$(absolute_path $configs_filename)

  while IFS='' read -r line || [[ -n "$line" ]]; do
    if [ "${line}" != "" ] && [[ "${line}" != "#"* ]]; then
      IFS='=' read -r key value <<< "${line}"
      if [ "${key}" != "" ]; then
        key=$(echo $key | tr '.' '_' | tr '-' '_' | xargs echo -n)
        value=$(echo $value | xargs echo -n)
        eval "export ${key}=\"${value}\""
        if [ "${show_vars}" != "" ] && [ "${show_vars}" != "0" ] && [ "${show_vars}" != "false" ];  then
          echo "${key}=\"${value}\""
        fi
      fi
    fi
  done <  "$configs_filename"
}

temporary_dir() {
  export TMPDIR="$(dirname $(mktemp -u))"
  kernel=$(uname | awk '{print tolower($0)}')
  if [[ "${kernel}" == "mingw"* ]] ; then
    if [ "$1" != "" ]; then path="$1"; else path="$(pwd)"; fi
    export TMPDIR=$(echo "${path}" | awk '{ print substr($0, 1, 2)"/temp"}')
    if [ ! -p $TMPDIR ]; then
      mkdir -p $TMPDIR
    fi
  fi
  echo $TMPDIR
}

random_filename() {
  prefix=$1
  suffix=$2
  kernel=$(uname | awk '{print tolower($0)}')
  if [[ "${kernel}" == "darwin"* ]] ; then MD5="md5"; else MD5="md5sum"; fi
  rnd=$(echo $RANDOM | $MD5 | awk '{ print $1}')
  echo "${prefix}${rnd}${suffix}"
}

fast_delete() {
  path=$1
  if [ -d $path ] || [ -f $path ]; then
    prefix="$(basename ${path})"
    to_remove="$(temporary_dir ${path})/$(random_filename $prefix '')"
    mv "${path}" "${to_remove}"
    nohup rm -fR "${to_remove}" > /dev/null 2>&1 &
  fi
}

trim() {
  text=$1
  echo -n $1
}

