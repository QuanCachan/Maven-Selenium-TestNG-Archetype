Place in this folder the selenium-server-standalone-<version>.jar to use for the Grid 2, if you need.

Steps to launch a Selenium Grid test:
(https://github.com/SeleniumHQ/selenium/wiki/Grid2)

1- follow the instructions in the README.txt in the drivers directory according to 

1- creating Hub by typing this command upon the directory where you place the selenium-server-standalone-<version>.jar
(if you follow this instruction from the begining, normally it is the folder containing this README.txt) :

	java -jar selenium-server-standalone-<version>.jar -role hub
	
You can view the status of the hub by opening a browser window and navigating to: http://localhost:4444/grid/console

2- creating Nodes

java -Dwebdriver.chrome.driver="..\drivers\chrome\chromedriver.exe" -jar selenium-server-standalone-3.13.0.jar -role node -hub http://localhost:4444/grid/register -browser "browserName=chrome, platform=WINDOWS, version=68.0.3440.84"

3- update your application.properties file, for instance, modify this line
(remember: all input values should be in LOWERCASE)

	grid2.hub=http://localhost:4444/wd/hub

4- launch your tests