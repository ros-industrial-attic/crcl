
REM If you didn't put nodejs or Python27 in the default locations this 
REM may need to be edited.
set PATH=%PATH%;C:\Program Files\nodejs;C:\Python27;

set jarfile=crcl4java-utils-1.4-jar-with-dependencies.jar
set remotejarfile=https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.4/crcl4java-utils-1.4-jar-with-dependencies.jar

if NOT exist %jarfile% (
    echo Downloading  %jarfile%
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%remotejarfile%', '%jarfile%')"
)


if NOT exist node_modules\java (
    echo "Installing npm java ..."
    call npm install java
    echo npm exit Code = %ERRORLEVEL%
)