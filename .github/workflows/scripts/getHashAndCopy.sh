#!/bin/bash
pwd
oldDir="$(pwd)"
version=$(grep "project.version=" "./gradle.properties" | sed 's/project.version=//')
rm -rf ./Common/target/ServerHelper-Common-$version.jar   # 删除掉无用文件，因为这个文件是存放着通用的代码的（已在编译时一起打包进其他的jar里）
for edition in Common Nukkit WaterdogPE
do
    cd ./$edition/target/
    sha256sum "ServerHelper-$edition"-*.jar >> "ServerHelper-$edition-$version.sha256"
    ls -lah
    cd $oldDir
    cp ./$edition/target/*.* ./staging
done
cd ./staging
ls -lah
cat "ServerHelper-*-$version.sha256" | tee "ServerHelper-$version-all-files.sha256" # 合并哈希记录到一个文件上
# 在上传之前先校验文件一遍以防出在复制时出现问题
sha256sum -c "ServerHelper-$version-all-files.sha256"
if [ $? -ne 0 ]; then
    echo "$hashSha256File 校验失败"
    exit 1
fi
echo "$hashSha256File 校验成功。"
rm -f ./"ServerHelper-$edition-$version.sha256" # 删除临时的哈希记录文件
ls -lah
cd ../