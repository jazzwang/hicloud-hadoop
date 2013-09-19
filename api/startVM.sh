#!/bin/bash
if [ ! -f startVM.class ]; then echo "run 'ant' to build required class files first."; exit -1; fi
java -cp .:./lib/cboss-hws-sdk-1.0.0.jar startVM $1 > startVM.log 2> /dev/null
cat startVM.log | grep "URL=" | sed 's#.*URL=##g' | xargs wget -q --no-check-certificate -O startVM.json
cat startVM.json
printf "\n"
