#!/bin/bash
projectName="$1"
targetPath="$2"
hashFileSaveName="$3"
oldDir="$(pwd)"
cd $targetPath
pwd
echo "-------------------"
cat *-edition.sha256.txt | tee "$hashFileSaveName".sha256.txt
#mv ./"$projectName"-all-edition-hash-sha256.txt ./"$projectName"-all-edition-hash-sha256-$(sha256sum "$projectName"-all-edition-hash-sha256.txt|awk '{print $1}').txt
#echo "-------------------"
#cat *-edition.md5 | tee "$hashFileSaveName"-all-edition.md5
#mv ./"$projectName"-all-edition-hash-md5.txt ./"$projectName"-all-edition-hash-md5-$(md5sum "$projectName"-all-edition-hash-md5.txt |awk '{print $1}').txt
echo "-------------------"
ls -lah
cd $oldDir