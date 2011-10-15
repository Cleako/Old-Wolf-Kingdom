@echo off
cd dist
xcopy LR_client.jar ..\LR /Y
cd ..
cd LR
7za.exe -x!7za.exe a ../LR.zip *.*
cd ..
cd lib
7za.exe -x!7za.exe a ../lib.zip *.*
cd ..
echo --------------------------------------------------------
md5.exe -l *.zip
echo --------------------------------------------------------
pause