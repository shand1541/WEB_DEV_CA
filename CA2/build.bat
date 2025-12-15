@echo off
echo Compiling CA2 Struts2 Application...

cd /d "%~dp0"

echo Cleaning old class files...
del /q webapps\WEB-INF\classes\*.class 2>nul

echo Compiling Java source files...
javac -cp ".;webapps\WEB-INF\lib\*" -d webapps\WEB-INF\classes src\AddItemAction.java src\AdminAction.java src\BidInfo.java src\DatabaseManager.java src\LoginAction.java src\LoginCheckInterceptor.java src\LogoutAction.java src\Member.java src\MemberDAO.java src\MyBidsAction.java src\Product.java src\ProductDAO.java src\ProfileAction.java src\RegisterAction.java src\ViewItemBidsAction.java src\ViewItemsAction.java src\ViewOtherProfileAction.java src\ViewUsersAction.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo.
    echo Class files created in webapps\WEB-INF\classes:
    dir /b webapps\WEB-INF\classes\*.class
) else (
    echo Compilation failed!
    exit /b 1
)

echo.
echo To create WAR file, you can use:
echo jar -cvf ca2.war -C webapps .

pause