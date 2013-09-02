#!/bin/bash
echo $1
java -cp .:./lib/cboss-hws-sdk-1.0.0.jar startVM $1 > startVM.log 2> /dev/null
cat startVM.log | grep "URL=" | sed 's#.*URL=##g' | xargs wget -q --no-check-certificate -O startVM.json
rm startVM.log
cat startVM.json
