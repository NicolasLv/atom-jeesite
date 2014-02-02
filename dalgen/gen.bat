@echo off
:START
set NOW=%CD%
echo 请选择运行的程序编号：
set /p xuanze=0.所有命令  1.MySQL  2.SQLite  9.退出 : 
if %xuanze%==0 goto CMD0
if %xuanze%==1 goto CMD1
if %xuanze%==2 goto CMD2

if %xuanze%==9 goto CMD9

rem 其他输入
echo 输入错误，请重新输入！
echo '
echo '
goto START

pause

:CMD0
  cd %NOW%\dalgen-mysql
  start gen-mysql.bat
  
  cd %NOW%\dalgen-sqlite
  start gen-sqlite.bat
  
  cd %NOW%\dalgen-mysql
  start gen-dbsize.bat
  exit
:CMD1
  cd %NOW%\dalgen-mysql
  start gen-mysql.bat
  exit
:CMD2
  cd %NOW%\dalgen-sqlite
  start gen-sqlite.bat
  exit

:CMD9
  pause>nul
