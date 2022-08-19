#!/bin/sh

usage() {
  echo "Usage: sh copy.sh"
  exit 1
}

echo "begin copy sql......."
cp ../db/*.sql ./mysql/db

echo "begin copy gateway......"
cp ../cjxycloud-gateway/target/cjxycloud-gateway.jar ./cjxy/gateway/jar/cjxycloud-gateway.jar

