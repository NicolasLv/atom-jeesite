echo 开始生成的SQLite-DAL代码[build.xml]...
rm -rf ./dalgen-sqlite/target
rm -rf ./dalgen-sqlite/logs
call ant -f ./dalgen-sqlite/build.xml
rem exit
