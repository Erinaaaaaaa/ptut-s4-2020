#!/bin/bash

rm -rf out
mkdir out

find src | grep .java$ >> compile.list
find ../Metier/src | grep .java$ >> compile.list

echo ----- DEBUT COMPILATION -----
echo
javac -encoding utf-8 -cp .:../Metier/lib/jansi-1.18.jar -d out @compile.list
echo
echo -----  FIN COMPILATION  -----

cp -r src/* out

rm compile.list

echo ----- DEBUT EXECUTION -----
echo
java -cp ./out:../Metier/lib/jansi-1.18.jar twist.net.ControleurIA "$@"
echo
echo -----  FIN EXECUTION  -----