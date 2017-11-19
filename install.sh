#!/bin/bash
#clear

# Initializing 
USER_HOME="$HOME"
BASHRC_FILE="$USER_HOME/.bashrc"
CURRENT_DIRECTORY=`pwd`
OJUL_HOME="$USER_HOME/.ojul"
TEMP_HOME="$OJUL_HOME/temp"
JAVA_HOME="$OJUL_HOME/oracle-java"

if [ ! -e $OJUL_HOME/release ]; then
	
	#Checking for required directories
	echo "Checking directories ..."
	if [ ! -e $OJUL_HOME ]; then 
		mkdir $OJUL_HOME
		echo "$OJUL_HOME has been created ..."
	fi
	if [ ! -e $TEMP_HOME ]; then
		mkdir $TEMP_HOME
		echo "$TEMP_HOME has been created ..."
	fi
	if [ ! -e $JAVA_HOME ]; then
		mkdir $JAVA_HOME
		echo "$JAVA_HOME has been created ..."
	fi
	echo "Directories are created ..."

	# Checking and/or creatating source (.hashrc) file
	if [ ! -e $BASHRC_FILE ]; then
		touch $BASHRC_FILE
	fi


    # Exporting path variable to source (.bashrc) file
	if grep -q "# Oracle JAVA Paths" "$BASHRC_FILE"
    then
        echo "Done with porting path variable to .bashrc file..."
    else
        echo "Exporting path variable to .bashrc file..."
        echo "" >> $BASHRC_FILE
	    echo "# Oracle JAVA Paths" >> $BASHRC_FILE
	    echo "export JAVA_HOME=$JAVA_HOME/java" >> $BASHRC_FILE
	    echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> $BASHRC_FILE
	    echo "alias ojul='bash $OJUL_HOME/update.sh'" >> $BASHRC_FILE
    fi

	#Reloading source (.bashrc) file
	#source ~/.bashrc

	# Copying all required/binary file for ojul to the application root directory
	ojul_source_folder="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
    cd $ojul_source_folder
    for FILE in `ls`
	do
		cp -rf $FILE $OJUL_HOME
		echo "Copying $FILE to $OJUL_HOME ..."
	done
else
	
	# Message
	echo "OJUL(Oracle JAVA Updater for Linux) is already installed ..."
fi

if [ ! -e $JAVA_HOME/java/release ]; then

	# Changing directory to ojul's temporary directory
	cd $TEMP_HOME
	echo "Directory changed to $TEMP_HOME ..."
	echo "Cleaning $TEMP_HOME ..."

	# Deleting all junk files from ojul's temporary directory
	for FILE in `ls`
	do
		rm -rf $FILE
		echo "Removed $FILE ..."
	done

	echo "Download is starting for Oracle JAVA from Oracle's official download server ...'"
	echo "Please keep patience while downloading the archive ..."
	echo "It may take some time according to your internet speed ..."

	# Downloding Oracle JDK from Oracles official site
	wget -c --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/9.0.1+11/jdk-9.0.1_linux-x64_bin.tar.gz"
	echo "Download has been finished ..."

	# Extracting and after that deleting downloaded archive file
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
	#cd $TEMP_HOME
	for JUNK_FILE in `ls`
	do
		rm -rf $JUNK_FILE
	done
	echo "Cleaning $TEMP_HOME has been finished..."

	#Reloading source (.bashrc) file
	source ~/.bashrc
fi

# Running ojul by default
bash $OJUL_HOME/update.sh
