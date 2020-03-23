@echo off

echo .java > exclude.tmp

mkdir out

dir /s/b *.java > compile.list

javac -encoding utf-8 -d out @compile.list

XCOPY /E /EXCLUDE:exclude.tmp src out

del compile.list
del exclude.tmp

java -cp ./out twist.Controleur