#!/bin/bash
targetDir="$1"
hashSha256File="$2"
oldDir="$(pwd)"
cd $targetDir
sha256sum -c $hashSha256File
if [ $? -ne 0 ]; then
    echo "$hashSha256File 校验失败"
    exit 1
fi
echo "$hashSha256File 校验成功。"
cd $oldDir