set jarfile=crcl4java-ui-1.4-jar-with-dependencies.jar
set remotejarfile=https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-ui/1.4/crcl4java-ui-1.4-jar-with-dependencies.jar

if NOT exist %jarfile% (
    echo Downloading  %jarfile%
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%remotejarfile%', '%jarfile%')"
)

java -jar %jarfile% --mode GraphicalServer
