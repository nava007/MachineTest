set projectLocation=C:\Navaneeth\eclipsespace\SnapHRM
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause