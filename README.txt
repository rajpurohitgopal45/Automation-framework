Verimi Automation Task

To execute the project you will need to run the command in cmd : mvn clean install

My credential used in script, if you want yours then update the below file.
"src/resources/inputData.properties"


Prerequisite :
1) I've used chrome version 109, you will need to use chromedriver.exe file according to your chrome version.
  location of chromedriver file : "src/resources/chromedriver.exe"
2) maven should be installed at your system in order to execute the project.


Output : you can get result i.e. lines containing word "bezahlen" in below two options.
1) Scripts will generate the test execution report in html format file, so need to open it in browser. you can see whether execution passed or failed.
   test report file location : "reports/Test_Report.html"
2) output also available on terminal as I've used System.out.println.


Refer below technologies used in framework.
1) maven     3.8.5
2) TestNG    7.1.0
3) JAVA      1.8.0_333
4) Selenium  3.141