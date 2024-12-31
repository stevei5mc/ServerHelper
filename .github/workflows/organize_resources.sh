projectName="$1"
cd staging
for hashType in sha256 md5; do
  # 找到所有哈希文件并合并
  hashFiles=$(find . -name "*-edition-hash-$hashType.txt")
  cat $hashFiles | tee "${projectName}-all-edition-hash-$hashType.txt"
  # 计算哈希值并重命名文件
  mv "${projectName}-all-edition-hash-$hashType.txt" "${projectName}-all-edition-hash-$hashType-$($hashTypesum "${projectName}-all-edition-hash-$hashType.txt" | awk '{print $1}').txt"
  echo "--------------------"
done
ls -la
cd ../
mkdir -v github_releases
cp ./staging/*.* ./github_releases
ls -la ./github_releases
rm -rf ./staging/*-javadoc.jar
ls -la ./staging
mkdir -v releases
cd releases
mkdir -v Nukkit WaterdogPE
cd ../
cp ./staging/*-Nukkit-*.* ./releases/Nukkit
ls -la ./releases/Nukkit
cp ./staging/*-WaterdogPE-*.* ./releases/WaterdogPE
ls -la ./releases/WaterdogPE
ls -la