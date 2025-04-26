artifactName="$1"
projectPath="$2"
cd ./$projectPath
pwd
mvn -B package --file pom.xml
mkdir staging
cp target/"$artifactName"*.jar staging
ls -la ./staging
cd ../