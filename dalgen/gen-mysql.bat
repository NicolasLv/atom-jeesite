echo 开始生成的MySQL-DAL代码[build.xml]...
rm -rf ./dalgen-mysql/target
rm -rf ./dalgen-mysql/logs
call ant -f ./dalgen-mysql/build.xml
rem exit
