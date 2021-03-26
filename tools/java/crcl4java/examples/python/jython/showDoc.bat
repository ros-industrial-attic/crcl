REM This script assumes Java 8 is installed and on the PATH so that jjs.exe will be on the path.
REM Tested on Windows 7 with JDK 8 installed.
REM The jarfile will be downloaded if it has not been already.

REM Edit this to match your installation directory, or edit the PATH variable
REM in the control panel  (search for environment) 
REM set PATH=%PATH%;C:\Program Files\Java\jdk1.8.0_92\bin;

set jarfile=crcl4java-utils-1.4-javadoc.jar
set remotejarfile=https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.4/crcl4java-utils-1.4-javadoc.jar
if NOT exist %jarfile% (
    echo Downloading  %jarfile%
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%remotejarfile%', '%jarfile%')"
)
if NOT exist "crcl4java-utils-javadoc" (
    mkdir crcl4java-utils-javadoc
    unzip %jarfile% -d crcl4java-utils-javadoc
)

start "" "file:"%~dp0"crcl4java-utils-javadoc/index.html"

set jarfile=crcl4java-base-1.4-20160428.123047-1-javadoc.jar
set remotejarfile=https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-base/1.4/crcl4java-base-1.4-javadoc.jar
if NOT exist %jarfile% (
    echo Downloading  %jarfile%
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%remotejarfile%', '%jarfile%')"
)
if NOT exist "crcl4java-base-javadoc" (
    mkdir crcl4java-base-javadoc
    unzip %jarfile% -d crcl4java-base-javadoc
)

start "" "file:"%~dp0"crcl4java-base-javadoc/index.html"