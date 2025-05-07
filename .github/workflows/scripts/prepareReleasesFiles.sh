#!/bin/bash
pwd
ls -lah ./staging
mkdir -vp bbs
echo "--------------------"
for edition in Nukkit WaterdogPE
do
    mkdir -vp ./bbs/$edition
    cp ./bbs/*$edition*.* ./bbs/$edition
    rm -rf ./bbs/*$edition*-javadoc.jar   # javadoc 不会在论坛上发布，只在 github releases 上发布
    ls -lah ./bbs/$edition
    echo "--------------------"
done
ls -la ./bbs