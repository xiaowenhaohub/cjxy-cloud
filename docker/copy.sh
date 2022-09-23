#!/bin/sh

usage() {
  echo "Usage: sh copy.sh"
  exit 1
}

echo "begin copy sql......."
cp -rf ../db/*.sql ./mysql/db

echo "begin copy gateway......"
cp -rf ../cjxycloud-gateway/target/cjxycloud-gateway.jar ./modules/gateway/jar/cjxycloud-gateway.jar

echo "begin copy leaf......"
cp -rf ../cjxycloud-leaf/target/cjxycloud-leaf.jar ./modules/leaf/jar/cjxycloud-leaf.jar

echo "begin copy auth......"
cp -rf ../cjxycloud-auth/target/cjxycloud-auth.jar ./modules/auth/jar/cjxycloud-auth.jar

echo "begin copy social......"
cp -rf ../cjxycloud-social/target/cjxycloud-social.jar ./modules/social/jar/cjxycloud-social.jar

echo "begin copy user......"
cp -rf ../cjxycloud-user/target/cjxycloud-user.jar ./modules/user/jar/cjxycloud-user.jar

echo "begin copy website......"
cp -rf ../cjxycloud-website/target/cjxycloud-website.jar ./modules/website/jar/cjxycloud-website.jar
