
REM THIS Should match the directory where the latest JDK is installed.
REM It is likely different on your computer.
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_92

REM Add both the bin subdirectory and the location of jvm.dll to the PATH.
set PATH=%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin\server;%PATH%;

if exist CRCLClient.exe  (
    CRCLClient.exe
) else (
    if exist CRCLClient\x64\Debug\CRCLClient.exe (
        CRCLClient\x64\Debug\CRCLClient.exe
    ) else (
        if exist CRCLClient\x64\Release\CRCLClient.exe (
            CRCLClient\x64\Release\CRCLClient.exe
        )
    )
)
