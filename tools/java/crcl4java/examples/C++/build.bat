
SET mypath=%~dp0
cd %mypath%

call killCppServer.bat
call killCppClient.bat

del /F /Q *.obj
del /F /Q *.exe

call setup.bat


REM Set Visual Studio (with C++) Installation directory.
REM This will likely need to be edited on each system.
set MSVISUALC_DIR=C:\Program Files (x86)\Microsoft Visual Studio 14.0\VC

echo %MSVISUALC_DIR%

rem cd "%MSVISUALC_DIR%"
call "%MSVISUALC_DIR%\vcvarsall.bat" x64
rem cd %mypath%

REM Just check these in case we got it wrong
dir "%JAVA_HOME%"
dir "%JVM_LIBDIR%\jvm.lib"
dir "%JVM_DLLDIR%\jvm.dll"

cl.exe /EHsc /I "generated" /I "%JAVA_HOME%\include"  /I "%JAVA_HOME%\include\win32" generated\crclj*.cpp crclServer.cpp  /link "/LIBPATH:%JVM_LIBDIR%" jvm.lib /out:CRCLServer.exe

cl.exe /EHsc /I "generated"  /I "%JAVA_HOME%\include"  /I "%JAVA_HOME%\include\win32" generated\crclj*.cpp crclClient.cpp  /link "/LIBPATH:%JVM_LIBDIR%" jvm.lib /out:CRCLClient.exe
