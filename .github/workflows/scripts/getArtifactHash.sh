#!/bin/bash
pwd
oldDir="$(pwd)"
version=$(grep "project.version=" "./gradle.properties" | sed 's/project.version=//')
rm -rf ./Common/target/ServerHelper-Common-$version.jar   #删除掉无用文件，因为这个文件是存放着通用的代码的（已在编译时一起打包进其他的jar里）
for edition in Common Nukkit WaterdogPE
do
  cd $edition/target || exit 1
  echo "--------------------"
  echo "files sha256 value:"
  sha256sum "ServerHelper-$edition"*.jar | tee "ServerHelper-$edition-$version.sha256"
  echo "--------------------"
  ls -lah
  cd $oldDir
done
echo "--------------------"