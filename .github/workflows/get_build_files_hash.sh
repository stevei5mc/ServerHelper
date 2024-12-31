artifactName="$1"
pwd
ls -la
echo "directory: $artifactName"
echo "--------------------"
sha256sum *.jar | tee "$artifactName"_edition-hash-sha256.txt
echo "--------------------"
md5sum *.jar | tee "$artifactName"_edition-hash-md5.txt
echo "--------------------"
mv ./"$artifactName"_edition-hash-sha256.txt ./"$artifactName"_edition-hash-sha256-$(sha256sum "$artifactName"_edition-hash-sha256.txt|awk '{print $1}').txt
mv ./"$artifactName"_edition-hash-md5.txt ./"$artifactName"_edition-hash-md5-$(md5sum "$artifactName"_edition-hash-md5.txt|awk '{print $1}').txt
ls -la