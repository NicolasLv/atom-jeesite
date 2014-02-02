@echo off
echo 开始生成的SQLite-DAL代码[build.xml]...
rm -rf ./target
rm -rf ./logs
call ant -f ./build.xml
rem exit
