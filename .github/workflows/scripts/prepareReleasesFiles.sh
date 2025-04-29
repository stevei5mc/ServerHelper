projectName="$1"
pwd
ls -lah ./staging
mkdir -vp bbs
for edition in Nukkit WaterdogPE
do
    mkdir -vp ./bbs/$edition
    cp ./bbs/*-$edition*.* ./bbs/$edition
    rm -rf ./bbs/$edition*-javadoc.jar
    ls -lah ./bbs/$edition
    echo "--------------------"
done
ls -lah