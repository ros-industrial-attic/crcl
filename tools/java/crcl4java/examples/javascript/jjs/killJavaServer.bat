REM This script assumes Java 8 is installed and on the PATH so that jjs.exe will be on the path.
REM Tested on Windows 7 with JDK 8 installed.
REM The jarfile will be downloaded if it has not been already.

REM Edit this to match your installation directory, or edit the PATH variable
REM in the control panel  (search for environment) 
set PATH=%PATH%;C:\Program Files\Java\jdk1.8.0_92\bin;

SET mypath=%~dp0
echo %mypath%
cd %mypath%

taskkill /f /im jjs.exe
del jpsl.txt
del jpslcrcl.txt
jps -l >jpsl.txt
jps -l | C:\Windows\System32\find.exe  "crcl"  >jpslcrcl.txt
for /f %%x in (jpslcrcl.txt) do taskkill /PID %%x

