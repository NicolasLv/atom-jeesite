@echo off
:START
echo ��ѡ�����еĳ����ţ�
set /p xuanze=0.��������  1.MySQL  2.SQLite  3.DBSize  9.�˳� : 
if %xuanze%==0 goto CMD0
if %xuanze%==1 goto CMD1
if %xuanze%==2 goto CMD2
if %xuanze%==3 goto CMD3

if %xuanze%==9 goto CMD9

rem ��������
echo ����������������룡
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
