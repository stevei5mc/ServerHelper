#!/bin/bash
targetName="$1"
targetSuffixName="$2"
targetPath="$3"
oldDir="$(pwd)"
cd ./$targetPath
pwd
echo "--------------------"
sha256sum "$targetName"*."$targetSuffixName" | tee "$targetName"-edition.sha256
echo "--------------------"
ls -lah
cd $oldDir
pwd