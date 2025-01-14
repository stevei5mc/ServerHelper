artifactName="$1"
projectPath="$2"
cd ./$projectPath
pwd
mvn -B package --file pom.xml
mkdir staging
cp target/"$artifactName"*.jar staging
ls -la ./target
cd ./staging
pwd
ls -la
echo "--------------------"
sha256sum "$artifactName"*.jar | tee "$artifactName"-hash-sha256.txt
mv ./"$artifactName"-hash-sha256.txt ./"$artifactName"-hash-sha256-$(sha256sum "$artifactName"-hash-sha256.txt|awk '{print $1}').txt
echo "--------------------"
md5sum "$artifactName"*.jar | tee "$artifactName"-hash-md5.txt
mv ./"$artifactName"-hash-md5.txt ./"$artifactName"-hash-md5-$(md5sum "$artifactName"-hash-md5.txt|awk '{print $1}').txt
echo "--------------------"
ls -la
cd ../../
