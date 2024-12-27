projectName="$1"
cd staging
cat *-edition-hash-sha256.txt | tee $projectName-all-edition-hash-sha256.txt
cat *-edition-hash-md5.txt | tee $projectName-all-edition-hash-md5.txt
mv ./$projectName-all-edition-hash-sha256.txt ./$projectName-all-edition-hash-sha256-$(sha256sum $projectName-all-edition-hash-sha256.txt|awk '{print $1}').txt
mv ./$projectName-all-edition-hash-md5.txt ./$projectName-all-edition-hash-md5-$(md5sum $projectName-all-edition-hash-md5.txt |awk '{print $1}').txt
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