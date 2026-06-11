#!/bin/bash
pwd
oldDir="$(pwd)"

modules=$(grep -oP 'project\s*\(\s*":\K[^"]+' settings.gradle.kts)

if [ -z "$modules" ]; then
    echo "警告: 无法从 settings.gradle.kts 提取模块"
    exit 1
fi

echo "检测到的模块: $modules"

version=$(grep "project.version=" "./gradle.properties" | sed 's/project.version=//')
rm -rf ./Common/target/ServerHelper-Common-$version.jar   # 删除掉无用文件，因为这个文件是存放着通用的代码的（已在编译时一起打包进其他的jar里）
mkdir -vp staging
echo "---------"

for edition in $modules; do
    cd ./$edition/target/ || exit 1
    sha256sum "ServerHelper-$edition"-*.jar >> "ServerHelper-$edition-$version.sha256"
    ls -lah
    cd $oldDir
    cp ./$edition/target/*.* ./staging
    echo "---------"
done

cd ./staging || exit 1

cat ServerHelper-*-$version.sha256 2>/dev/null >> ServerHelper-$version-all_files.sha256

ls -lah

sha256sum -c "ServerHelper-$version-all_files.sha256"
if [ $? -ne 0 ]; then
    echo "校验失败"
    exit 1
fi

echo "校验成功。"

rm -f ./ServerHelper-*-$version.sha256
ls -lah
cd ../