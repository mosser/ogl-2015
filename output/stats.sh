#!/bin/bash



# List the available actions
ACTIONS=`grep '"action"[^}]*,$' *.json | cut -d ':' -f 3 | cut -d '"' -f 2 | sort | uniq`

for act in $ACTIONS
do
    COUNT=`grep '"action"[^}]*,$' *.json | grep $act | wc -l`
    echo $act - $COUNT
done
