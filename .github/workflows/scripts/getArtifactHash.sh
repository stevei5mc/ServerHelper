#!/bin/bash
pwd
oldDir="$(pwd)"
version=$(grep "version =" "./build.gradle.kts" | sed 's/.*version = "\(.*\)".*/\1/')
for edition in Nukkit WaterdogPE
do
  cd $edition/target || exit 1
  echo "--------------------"
  echo "files sha256 value:"
  sha256sum "ServerHelper-$edition"*.jar | tee "ServerHelper-$edition-$version.sha256"
  echo "--------------------"
  ls -lah
  cd $oldDir
done