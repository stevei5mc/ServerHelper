#!/bin/bash
projectName="$1"
targetPath="$2"
hashFileSaveName="$3"
oldDir="$(pwd)"
cd $targetPath
pwd
echo "-------------------"
cat *-edition.sha256| tee "$hashFileSaveName".sha256
echo "-------------------"
ls -lah
cd $oldDir