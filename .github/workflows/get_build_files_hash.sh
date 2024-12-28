artifactName="$1"
pwd
ls -la
echo "directory: $artifactName"
sha256sum *.jar | tee "$artifactName"_edition-hash-sha256.txt
md5sum *.jar | tee "$artifactName"_edition-hash-md5.txt
mv ./"$artifactName"_editio-hash-sha256.txt ./"$artifactName"_edition-hash-sha256-$(sha256sum "$artifactName"_edition-hash-sha256.txt|awk '{print $1}').txt
mv ./"$artifactName"_edition-hash-md5.txt ./"$artifactName"_edition-hash-md5-$(md5sum "$artifactName"_edition-hash-md5.txt|awk '{print $1}').txt
ls -la