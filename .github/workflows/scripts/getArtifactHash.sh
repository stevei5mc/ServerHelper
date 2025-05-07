#!/bin/bash
targetName="$1"
targetSuffixName="$2"
targetPath="$3"
oldDir="$(pwd)"
cd ./$targetPath
pwd
echo "--------------------"
sha256sum "$targetName"*."$targetSuffixName" | tee "$targetName"-edition.sha256
#mv ./"$targetName"-hash-sha256.txt ./"$targetName"-hash-sha256-$(sha256sum "$targetName"-hash-sha256.txt|awk '{print $1}').txt
#echo "--------------------"
#md5sum "$targetName"*."$targetSuffixName" | tee "$targetName"-edition.md5
#mv ./"$targetName"-hash-md5.txt ./"$targetName"-hash-md5-$(md5sum "$targetName"-hash-md5.txt|awk '{print $1}').txt
echo "--------------------"
ls -lah
cd $oldDir
pwd