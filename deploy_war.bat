@echo off
echo Creating WAR file for deployment...

REM Create temporary directory
if exist temp_war rmdir /s /q temp_war
mkdir temp_war
mkdir temp_war\WEB_INF
mkdir temp_war\WEB_INF\classes
mkdir temp_war\WEB_INF\lib

REM Copy HTML files
copy webapps\*.html temp_war\

REM Copy web.xml
copy webapps\WEB_INF\web.xml temp_war\WEB_INF\

REM Copy class files
copy webapps\WEB_INF\classes\*.class temp_war\WEB_INF\classes\

REM Copy MySQL JAR
copy webapps\WEB_INF\lib\*.jar temp_war\WEB_INF\lib\

REM Create WAR file
cd temp_war
jar -cvf ..\gaming_portal.war *
cd ..

REM Deploy WAR file
copy gaming_portal.war "C:\apache-tomcat-9.0.109\webapps\"

REM Cleanup
rmdir /s /q temp_war
del gaming_portal.war

echo WAR file deployed to Tomcat!
pause