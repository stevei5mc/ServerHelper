#!/bin/bash
artifactName="$1"
projectPath="$2"
oldDir="$(pwd)"
cd ./$projectPath
pwd
mvn -B package --file pom.xml
mkdir staging
cp target/"$artifactName"*.jar staging
ls -lah ./staging
cd ../
cd $oldDir