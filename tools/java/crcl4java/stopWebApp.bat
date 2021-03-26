


set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_60\
set PATH=%JAVA_HOME%\bin;%PATH%
set PATH=%PATH%;C:\Program Files\NetBeans 8.0.2\java\maven\bin;
echo JAVA_HOME %JAVA_HOME%
@REM mvn -version


set OLDDIR=%CD%
echo "OLDDIR=" %OLDDIR%
SET mypath=%~dp0
echo mypath %mypath%

cd %mypath%\crcl4java-vaadin-webapp

mvn jetty:stop

cd %OLDDIR%
