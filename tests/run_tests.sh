#!/bin/bash

javac -cp .:../src/ TestFighter.java TestRunner.java

if [ $? -ne 0 ]
then
	echo "ERROR COMPILING!!"
	exit
fi

java -cp .:../src/  TestRunner
