# BI-VWM  Semestral project

# BooleanModel

## Description
This is Semestral project in BI-VWM subject (Search the web and multimedia databases).
Goal of the project is to implement **Bolean model and Inverted index search** and compare it with sequential (or linear) search in database. Database consists of text files.
The result is java web application. The application uses 3 different databases (100, 500 and 1000 docs) and runs on Apache Tomcat.

Project documentation is [here](https://github.com/Gallym/BooleanModel/blob/master/Documentation.pdf)

## Live example

Check out live example here -> http://34.107.5.82:8080/BooleanModelApp/ 

_**Note**: make sure you use **http** not **https**_

## Deploy and Run (Unix)

### Requirments 

* Java 8
* Maven (optional)
* Apache Tomcat 7+ (prefered 9)

### Apache Tomcat

Download Apache Tomcat 9 zip or tar.gz from [here](https://tomcat.apache.org/download-90.cgi)

Unpack it in directory you like

### Deploy!

Copy BoleanModelApp.war file in `~/**path-to-tomcat**/webapps/`

_**Note**: this is the simpliest way to deploy application on Tomcat, for advanced configuration and deployment go [here](https://tomcat.apache.org/tomcat-9.0-doc/deployer-howto.html)_

### Run! 

Run Apache Tomcat server with command `~/**path-to-tomcat**/bin/catalina.sh start`

Open web browser and go to http://localhost:8080/BooleanModelApp/ .

_**Note**: application needs some time to preprocess database, please be paitient and wait a bit._

You can also recompile WAR file with Maven. In project directory run command `mvn clear install`. After go to `target` directory. Copy new generated WAR file to `~/**path-to-tomcat**/webapps/` and restart Tomcat.
