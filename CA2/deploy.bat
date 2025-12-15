@echo off
echo Deploying CA2 Web Application...
echo.

REM Check if CATALINA_HOME is set
if "%CATALINA_HOME%"=="" (
    echo ERROR: CATALINA_HOME environment variable is not set.
    echo Please set CATALINA_HOME to your Tomcat installation directory.
    echo Example: set CATALINA_HOME=C:\apache-tomcat-9.0.xx
    pause
    exit /b 1
)

echo Using Tomcat at: %CATALINA_HOME%

REM Stop Tomcat if running
echo Stopping Tomcat...
call "%CATALINA_HOME%\bin\shutdown.bat" 2>nul

REM Wait a moment
timeout /t 3 /nobreak >nul

REM Remove old deployment
echo Removing old CA2 deployment...
if exist "%CATALINA_HOME%\webapps\CA2" (
    rmdir /s /q "%CATALINA_HOME%\webapps\CA2"
)
if exist "%CATALINA_HOME%\webapps\CA2.war" (
    del "%CATALINA_HOME%\webapps\CA2.war"
)

REM Copy new deployment
echo Copying new CA2 application...
xcopy /e /i /y "webapps\*" "%CATALINA_HOME%\webapps\CA2\"

REM Start Tomcat
echo Starting Tomcat...
start "Tomcat" "%CATALINA_HOME%\bin\startup.bat"

echo.
echo Deployment complete!
echo Wait 10-15 seconds for Tomcat to start, then access:
echo http://localhost:8080/CA2/
echo http://localhost:8080/CA2/login.action
echo http://localhost:8080/CA2/register.action
echo.
pause