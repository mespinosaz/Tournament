#!/bin/bash

javac -cp .:../src/ *.java

if [ $? -ne 0 ]
then
	echo "ERROR COMPILING!!"
	exit
fi

java -cp .:../src/  TestRunner
