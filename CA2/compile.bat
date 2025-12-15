@echo off
echo Compiling Java classes...

REM Compile core classes first
javac -cp "webapps\WEB-INF\lib\*" -d webapps\WEB-INF\classes src\DatabaseManager.java src\Member.java src\Product.java src\BidInfo.java

REM Compile DAO classes
javac -cp "webapps\WEB-INF\lib\*;webapps\WEB-INF\classes" -d webapps\WEB-INF\classes src\MemberDAO.java src\ProductDAO.java

REM Compile Action classes and interceptors
javac -cp "webapps\WEB-INF\lib\*;webapps\WEB-INF\classes" -d webapps\WEB-INF\classes src\LoginCheckInterceptor.java src\LoginAction.java src\LogoutAction.java src\RegisterAction.java src\ProfileAction.java src\AddItemAction.java src\ViewItemsAction.java src\AdminAction.java src\ViewUsersAction.java src\MyBidsAction.java src\ViewOtherProfileAction.java src\ViewItemBidsAction.java

echo Compilation complete!