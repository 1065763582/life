#!/bin/bash
export JAVA_HOME=/usr/java/jdk1.8.0_131
echo ${JAVA_HOME}
echo 'Start the program : life-0.0.1-SNAPSHOT.jar'
chmod 777 /Users/Shared/Jenkins/Home/workspace/life/life/target/life-0.0.1-SNAPSHOT.jar
echo '-------Starting-------'
cd /Users/Shared/Jenkins/Home/workspace/life/life/target/
/Users/lizhiqiang/eclipse-workspace/tmux-MacOSX/reattach-to-user-namespace nohup ${JAVA_HOME}/bin/java -jar life-0.0.1-SNAPSHOT.jar &
echo 'start success'