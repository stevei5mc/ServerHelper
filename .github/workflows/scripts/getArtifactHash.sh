#!/bin/bash
pwd
oldDir="$(pwd)"
version=$(grep "version = " ./build.gradle.kts |  sed 's/version = //g' | sed 's/"//g')
for edition in Nukkit WaterdogPE
do
  cd $edition/target
  echo "--------------------"
  echo "files sha256 value:"
  sha256sum "ServerHelper-$edition"*.jar | tee "ServerHelper-$edition-$version.sha256"
  echo "--------------------"
  ls -lah
  cd $oldDir
done