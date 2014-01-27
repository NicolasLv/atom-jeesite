@echo off
:START
echo 请选择运行的程序编号：
set /p xuanze=0.所有命令  1.MySQL  2.SQLite  3.DBSize  9.退出 : 
if %xuanze%==0 goto CMD0
if %xuanze%==1 goto CMD1
if %xuanze%==2 goto CMD2
if %xuanze%==3 goto CMD3

if %xuanze%==9 goto CMD9

rem 其他输入
echo 输入错误，请重新输入！
echo '
echo '
goto START

pause

:CMD0
  start gen-none.bat
  start gen-mysql.bat
  start gen-sqlite.bat
  start gen-dbsize.bat
  exit
:CMD1
  start gen-none.bat
  start gen-mysql.bat
  exit
:CMD2
  start gen-none.bat
  start gen-sqlite.bat
  exit
:CMD3
  start gen-none.bat
  start gen-dbsize.bat
  exit

:CMD9
  pause>nul
