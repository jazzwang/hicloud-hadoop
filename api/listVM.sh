#!/bin/bash
if [ ! -f listVM.class ]; then echo "run 'ant' to build required class files first."; exit -1; fi
java -cp .:./lib/cboss-hws-sdk-1.0.0.jar listVM > listVM.log 2>/dev/null
cat listVM.log | grep "URL=" | sed 's#.*URL=##g' | xargs wget -q --no-check-certificate -O listVM.json
rm listVM.log
cat listVM.json | sed 's#,#\n#g' | grep "instanceId" | sed 's#.*:"##' | tr -d '"'
