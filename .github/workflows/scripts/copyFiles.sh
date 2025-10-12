#!/bin/bash
pwd
mkdir -vp staging
echo "--------------------"
version=$(grep "project.version=" "./gradle.properties" | sed 's/project.version=//')
for edition in Common Nukkit WaterdogPE
do
    ls -lah ./$edition/target/
    cp ./$edition/target/*.* ./staging
    echo "--------------------"
done
cd ./staging
cat ServerHelper-*-$version.sha256 >> ServerHelper-$version.sha256
ls -lah
cd ../