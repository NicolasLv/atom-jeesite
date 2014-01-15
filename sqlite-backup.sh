#!/bin/sh

dbPath=/home/obullxl/atom-jeesite/WebRoot/public
dbFile=$dbPath/jeesite.db
maillist='obullxl@163.com,obullxl@gmail.com'

echo 'sending dbfile......'
mutt -s '$dbFile' -a $dbFile $maillist
