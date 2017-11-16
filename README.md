# Welcome to OJUL (Oracle Java Updater for Linux)

It had never been an easy way to download, install and update time to time Oracle-JDK in GNU/Linux OS. There have embedded updater for Microsoft Windows based JDK but there have no suitable way to update Oracle-JDK in GNU/Linux OS. OJUL is a small effort to make that staff easier for Oracle JDK users. 

OJUL is completely free to use and distribute. Anyone is welcome to modify this to improve the functionality. 

Here two additional Java library is used:
	
* [JSOUP](https://jsoup.org/)
* [Jackson](https://github.com/FasterXML/jackson)

### License:
	GPL-V3

### Basic useful feature list:

* Download and Install the latest Oracle JDK from official website
* Update Oracle JDK by running one terminal command


## Installation Instruction


### System Requirements:
	Any GNU/Linux OS


### Download:

	Download OJUL from here : 
	
[Download URL](https://goo.gl/qWioSL) 
	
	Download the latest version from the drive.


### Extract: 

```tar -xvf /home/ashik/Download/ojul-0.0.1.zip -C /home/ashik/Download```

    Extract the ojul-*.*.*.zip file and you will find a ojul-*.*.* file containing the application itself. You will find 5 files inside the extracted file. 
    
    1. COPYRIGHT
    2. install.sh
    3. installation-guide.pdf
    4. ojul.jar
    5. README.md
    6. release
    7. update.sh


### Installation:

	Open the terminal in your current directory and run, ‘bash install.sh’ . Installation process of OJUL and Oracle JDK both will be started. This installer will install OJUL itself and also install Oracle-JDK by downloading that. This process will install Oracle-JDK and after that it’ll update the JDK. 
    
    
    
    
### Installation Verification:

	Close the current terminal and open a new terminal and type ‘java -version’, It’ll display you the latest installed Oracle-JDK version.
    
    
### Update Oracle-JDK:
	It is quite easy. Just open up a terminal window and type ‘ojul’. It’ll show you about the current status of JDK update. If any update found then It’ll inform you to install. Just by pressing ‘Y’/‘y’. After successfully updating your JDK version, it’ll clean all previous junk file and inform you.
