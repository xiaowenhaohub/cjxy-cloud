#!/bin/sh

usage() {
  echo "usage:  ./bin.sh [base|modules|send {发送到服务器}|sshkey {配置ssh}|stop|rm]"
}

sshkey() {
  echo "配置ssh-key......"
  ssh-copy-id root@$1
}

send() {

  echo "发送docker到$1:/app/cjxycloud......"
  scp -r ../docker root@$1:~/app/cjxycloud/
}

base(){
  docker-compose up -d cjxycloud-mysql cjxycloud-redis cjxycloud-nacos
}

modules() {
  docker-compose up -d cjxycloud-gateway cjxycloud-leaf cjxycloud-auth cjxycloud-user cjxycloud-website cjxycloud-social
}

case "$1" in
"base")
  base
;;
"modules")
  modules
;;
"stop")
  stop
;;
"rm")
  rm
;;
"send")
  send $2
;;
"sshkey")
  sshkey $2
;;
*)
  usage
;;
esac
