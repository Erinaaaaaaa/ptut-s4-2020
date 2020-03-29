@echo off

echo .java > exclude.tmp

del /s /q out > NUL
rmdir /s /q out > NUL
mkdir out

powershell (gci -Path src -Recurse *.java^|Resolve-Path -Relative) >> compile.list
powershell (gci -Path ../Metier/src -Recurse *.java^|Resolve-Path -Relative) >> compile.list

echo ----- DEBUT COMPILATION -----
echo.
javac -encoding utf-8 -cp .;../Metier/lib/jansi-1.18.jar -d out @compile.list
echo.
echo -----  FIN COMPILATION  -----

XCOPY /E /EXCLUDE:exclude.tmp src out > NUL

del compile.list > NUL
del exclude.tmp > NUL

echo ----- DEBUT EXECUTION -----
echo.
java -cp ./out;../Metier/lib/jansi-1.18.jar twist.net.ControleurIA %*
echo.
echo -----  FIN EXECUTION  -----


pause