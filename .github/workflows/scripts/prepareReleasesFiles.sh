#!/bin/bash
pwd
mkdir staging
mkdir -vp bbs
version=$(grep "project.version=" "./gradle.properties" | sed 's/project.version=//')
echo "--------------------"
for edition in Common Nukkit WaterdogPE
do
    mkdir -vp ./bbs/$edition
    cp ./$edition/target/*.* ./staging
    cp ./$edition/target/*.* ./bbs/$edition
    rm -rf ./bbs/$edition/*-javadoc.jar   # javadoc.jar 不会在论坛上发布，只在 github releases 上发布
    ls -lah ./bbs/$edition
    echo "--------------------"
done
cd ./staging
cat ServerHelper-*-$version.sha256 | tee ServerHelper-$version.sha256
echo "--------------------"
ls -lah
cd ../