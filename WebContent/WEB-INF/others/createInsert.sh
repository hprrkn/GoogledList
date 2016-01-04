#!bin/bash

#create sql(insert) from memo.txt

cnt=1
while read line
do
	if [ $cnt -eq 1 ] ; then
		echo ${line:8:4} >> changedSample.txt
		echo ${line:13:2} >> changedSample.txt
	else
		echo ${line} | cut -c2- >> changedSample.txt
	fi
		cnt=`expr $cnt + 1`
done < $1 

cnt2=1
sql="INSERT INTO searchhistory (word,created) VALUES"
while read line
do
	if [ $cnt2 -eq 1 ] ; then
		year=$line
	elif [ $cnt2 -eq 2 ] ; then
		month=$line
	else
		val=" ('${line}','${year}-${month}-1'),"
		sql=$sql$val
	fi
	cnt2=`expr $cnt2 + 1`
done < changedSample.txt

rm changedSample.txt
unset cnt
unset cnt2
echo $sql
