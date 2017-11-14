#!/bin/bash
clear
OJUL_HOME=`pwd`
TEMP_HOME="$OJUL_HOME/temp/"
JAVA_HOME="$OJUL_HOME/oracle-java/"
java -jar $OJUL_HOME/ojul.jar
cd $TEMP_HOME
for JAVA_ARCHIVE in `ls`
do
    if [ -f $JAVA_ARCHIVE ]; then 
        echo "Extrancting of $JAVA_ARCHIVE has been started..."
        tar -xvf $JAVA_ARCHIVE -C $TEMP_HOME
        echo "Extrancting has been finised..."
        echo "Completely removing $JAVA_ARCHIVE ..."
        rm -f $JAVA_ARCHIVE
        echo "$JAVA_ARCHIVE has been removed completely..."
    fi
done
for JAVA_EXTRANTED_DIRECTORY in `ls`
do
    if [ -d $JAVA_EXTRANTED_DIRECTORY ]; then 
        echo "Renaming $JAVA_EXTRANTED_DIRECTORY to java ..."
        mv -f $JAVA_EXTRANTED_DIRECTORY java
        echo "Renamed $JAVA_EXTRANTED_DIRECTORY to java ..."
    fi
done
echo "Moving java to $JAVA_HOME ..."
cp -rf java $JAVA_HOME
echo "java has been moden to $JAVA_HOME ..."
echo "Cleaning $TEMP_HOME ..."
for JUNK_FILE in `ls`
do
    rm -rf $JUNK_FILE
done
echo "Cleaning $TEMP_HOME has been finished..."

