echo ��ʼ���ɵ�DBSize����[build-dbsize.xml]...
rm -rf ./dalgen-mysql/target
rm -rf ./dalgen-mysql/logs
call ant -f ./dalgen-mysql/build-dbsize.xml
rem exit
