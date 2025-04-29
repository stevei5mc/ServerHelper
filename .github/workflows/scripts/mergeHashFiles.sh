projectName="$1"
cd staging
cat *-edition-hash-sha256.txt | tee "$projectName"-all-edition-hash-sha256.txt
#mv ./"$projectName"-all-edition-hash-sha256.txt ./"$projectName"-all-edition-hash-sha256-$(sha256sum "$projectName"-all-edition-hash-sha256.txt|awk '{print $1}').txt
echo "-------------------"
cat *-edition-hash-md5.txt | tee "$projectName"-all-edition-hash-md5.txt
#mv ./"$projectName"-all-edition-hash-md5.txt ./"$projectName"-all-edition-hash-md5-$(md5sum "$projectName"-all-edition-hash-md5.txt |awk '{print $1}').txt
echo "-------------------"
ls -lah
cd ../