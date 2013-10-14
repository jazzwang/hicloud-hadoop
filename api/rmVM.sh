#!/bin/bash
if [ ! -f rmVM.class ]; then echo "run 'ant' to build required class files first."; exit -1; fi
java -cp .:./lib/cboss-hws-sdk-1.0.0.jar rmVM $1 > rmVM.log 2> /dev/null
cat rmVM.log | grep "URL=" | sed 's#.*URL=##g' | xargs wget -q --no-check-certificate -O rmVM.json
cat rmVM.json
printf "\n"
