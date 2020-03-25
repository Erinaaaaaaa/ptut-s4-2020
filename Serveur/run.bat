@echo off

echo .java > exclude.tmp

del /s /q out
rmdir /s /q out
mkdir out

powershell (gci -Path src -Recurse *.java^|Resolve-Path -Relative) >> compile.list
powershell (gci -Path ../Metier/src -Recurse *.java^|Resolve-Path -Relative) >> compile.list

javac -encoding utf-8 -cp .;lib/jansi-1.18.jar -d out @compile.list

XCOPY /E /EXCLUDE:exclude.tmp src out

del compile.list
del exclude.tmp

echo ----- DEBUT EXECUTION -----
echo.
java -cp ./out;./lib/jansi-1.18.jar twist.Main
echo.
echo -----  FIN EXECUTION  -----


pause