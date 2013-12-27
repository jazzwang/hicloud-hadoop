#!/bin/bash
if [ ! -f listVM.class ]; then echo "run 'ant' to build required class files first."; exit -1; fi
java -cp .:./lib/cboss-hws-sdk-1.0.0.jar listVM > listVM.json 2>/dev/null
#cat listVM.log | grep "URL=" | sed 's#.*URL=##g' | xargs wget -q --no-check-certificate -O listVM.json
cat listVM.json | sed 's#,#\n#g' | sed 's#:# #' | tr -d '"[{}]' | awk '/operationStatus/ { A=$2 } /instanceId/ { B=$2 } /name/ { C=$2 } /ipForVm/ { D=$2 } /startDate/ { E=$2 ; print B,A,C,D,E } ' | column -t
