@echo off
:START
set NOW=%CD%
echo ��ѡ�����еĳ����ţ�
set /p xuanze=0.��������  1.MySQL  2.SQLite  9.�˳� : 
if %xuanze%==0 goto CMD0
if %xuanze%==1 goto CMD1
if %xuanze%==2 goto CMD2

if %xuanze%==9 goto CMD9

rem ��������
echo ����������������룡
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
