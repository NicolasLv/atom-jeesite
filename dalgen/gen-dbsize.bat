echo 开始生成的DBSize代码[build-dbsize.xml]...
rm -rf ./dalgen-mysql/target
rm -rf ./dalgen-mysql/logs
call ant -f ./dalgen-mysql/build-dbsize.xml
rem exit
