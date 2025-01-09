artifactName="$1"
pwd
ls -la
echo "directory: $artifactName"
echo "--------------------"
sha256sum *.jar | tee "$artifactName"-hash-sha256.txt
echo "--------------------"
md5sum *.jar | tee "$artifactName"-hash-md5.txt
echo "--------------------"
mv ./"$artifactName"-hash-sha256.txt ./"$artifactName"-hash-sha256-$(sha256sum "$artifactName"-hash-sha256.txt|awk '{print $1}').txt
mv ./"$artifactName"-hash-md5.txt ./"$artifactName"-hash-md5-$(md5sum "$artifactName"-hash-md5.txt|awk '{print $1}').txt
ls -la