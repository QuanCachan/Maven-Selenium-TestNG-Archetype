"# Maven-Selenium-TestNG-Archetype" 

Introduction
============

This archetype generates a Maven project with Selenium WebDriver and TestNG Test embedded.

To install the archetype in your local repo:

	git clone https://github.com/QuanCachan/Maven-Selenium-TestNG-Archetype.git
	cd Maven-Selenium-TestNG-Archetype
	mvn install

Now, from the workspace that you want to create your Maven project using this archetype, follow these steps:
    
    First, ensure that there's no pom.xml file in your workspace directory.

    Then, type:

        mvn archetype:generate -DarchetypeGroupId=fr.henix.squash -DarchetypeArtifactId=maven-selenium-testNG-archetype -DarchetypeVersion=0.0.1-SNAPSHOT -DgroupId=myGroupId -DartifactId=myArtifactId
    						 
where *myGroupId* : group id of the project to be created; *myArtifactId* : artifact id of the project to be created

This archetype uses Java bindings for Selenium version 3.12.0 and TestNG version 6.13.1.

Project Structure
-----------------------------------

The project follows the standard Maven structure, so all the tests go in the *src/test/java* folder.  Tests should inherit from the **TestBase** class. In this class, a factory method
from **WebDriverFactory** class is in charge of generating the instance of the WebDriver interface you need. Different parameters are passed into the factory:

* base URL : base URL of the AUT (application under test)
* Grid 2 hub URL :  URL of the hub (if using Grid 2)
* browser features: a) name b) version c) platform
* username / password : in case of BASIC authenticated site

Those parameters are retrieved from the *src/main/resources/your.group.Id/application.properties* file. You can also populate the properties file from command line (through -D<property in mvn command or through
Hudson/Jenkins).

**TestBase** class provides 30 seconds as interval for polling element from the DOM (implicity wait), and also it takes care of closing the driver when all the tests are executed in the suite. 
(Feel free to update all this values according to your needs)

**HomePageTest** class (in *src/test/java/your.group.Id/pages*) is just an example of a test class for testing the homepage of a web application. In the setup method of this class, the **PageFactory** class is used
 to help supporting the **PageObject** pattern (see below for more information). Briefly according to this pattern, each page is an object. *src/main/java/your.group.Id/pages/HomePage* class is an example of 
 a class representing the home page. Notice how the constructor accepts the "WebDriver" interface as parameter and all the "services" available for that page should be exposed here. It also allows to
 decouple the DOM element from the functionalities offered by the page.
 
 
Adding Chrome Driver to the project
-----------------------------------

If you need to use chromedriver, you should put the proper driver file downloaded from http://chromedriver.chromium.org/downloads into *src/main/resources/your.group.Id/drivers/chrome*. If you are on Windows, the file should be named *chromedriver.exe*,
if on Unix-based system, the file should be named *chromedriver*.


Adding Other Browser Driver to the project
-----------------------------------

If you need to use other internet browser for the tests, you should put the proper driver file downloaded from their sites into *src/main/resources/your.group.Id/drivers/<browserName>*.
Then in the *src/main/java/your.group.Id/webdriver*, in WebDriverFactory Java class, create the corresponding methode *setYouBrowserDriver()* as the *setChromeDriver()* one.

TestNG
------
For more info around TestNG framework, go to http://testng.org/doc/index.html. If you prefer, you could substitute this framework with JUnit.


Page Object pattern
-------------------
For more info around this pattern, read this wiki page: https://github.com/SeleniumHQ/selenium/wiki/PageObjects


Further Notes
-------
The project is just a starting point, feel free to modify it according to your needs.
And don't forget to update your application.properties before testing if your configurations changed ^^. 

Credits
-------
The Maven-Selenium-TestNG-Archetype project is an open source project licensed under the Apache License 2.0.