#!/bin/bash
clear

# Initializing
OJUL_HOME="$USER_HOME/.ojul"
TEMP_HOME="$OJUL_HOME/temp"
JAVA_HOME="$OJUL_HOME/oracle-java"

# Running ojul-updater
java -jar $OJUL_HOME/ojul.jar

# Changing directory to ojul's temporary directory
cd $TEMP_HOME

# Extracting and after that deleting downloaded archive files
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

# Renaming extracted directory name to 'java'
for JAVA_EXTRACTED_DIRECTORY in `ls`
do
    if [ -d $JAVA_EXTRACTED_DIRECTORY ]; then 
        echo "Renaming $JAVA_EXTRACTED_DIRECTORY to java ..."
        mv -f $JAVA_EXTRACTED_DIRECTORY java
        echo "Renamed $JAVA_EXTRACTED_DIRECTORY to java ..."
    fi
done
echo "Moving java to $JAVA_HOME ..."

# Copying 'java' directory to 'java-home'
cp -rf java $JAVA_HOME
echo "java has been moden to $JAVA_HOME ..."
echo "Cleaning $TEMP_HOME ..."

# Cleaning ojul's temporary directory
for JUNK_FILE in `ls`
do
    rm -rf $JUNK_FILE
done
echo "Cleaning $TEMP_HOME has been finished..."

