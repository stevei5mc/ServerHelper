artifactName="$1"
pwd
sha256sum *.jar | tee $artifactName_edition-hash-sha256.txt
md5sum *.jar | tee $artifactName_edition-hash-md5.txt
mv ./$artifactName_edition-hash-sha256.txt ./$artifactName_edition-hash-sha256-$(sha256sum $artifactName_edition-hash-sha256.txt|awk '{print $1}').txt
mv ./$artifactName_edition-hash-md5.txt ./$artifactName_edition-hash-md5-$(md5sum $artifactName_edition-hash-md5.txt|awk '{print $1}').txt
ls -la