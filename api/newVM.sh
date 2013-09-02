#!/bin/bash
java -cp .:./lib/cboss-hws-sdk-1.0.0.jar newVM $1 > newVM.log 2> /dev/null
cat newVM.log | grep "URL=" | sed 's#.*URL=##g' | xargs wget -q --no-check-certificate -O newVM.json
rm newVM.log
