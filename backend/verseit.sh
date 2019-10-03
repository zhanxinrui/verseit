#!/bin/bash

app_path=/opt/
pid_list=`ps -ef | grep verseit | grep -v "grep" | awk '{print $2}'`
if [ -z "$pid_list" ]
  then
    echo "no verseit pid alive!"
else
  echo "kill verseit pid $pid_list ..."
  kill -9 $pid_list
  echo "stop verseit success."
fi
echo "start newBee ..."
cd $app_path
nohup java -jar verse-0.0.1.jar >> /mnt/logs/verseit.log &
tail -f /mnt/logs/verseit.log