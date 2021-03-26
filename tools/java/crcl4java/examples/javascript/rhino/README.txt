
Rhino is an open-source implementation of JavaScript written entirely in Java. 
It is typically embedded into Java applications to provide scripting to end 
users. It is embedded in J2SE 6 as the default Java scripting engine.

See https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino

On Ubuntu Linux, installing the rhino package will put a shell script on the 
path called "rhino" that executes a javascript file or interprets commands 
interactively from the command line.

Before running the client, a CRCL server should be started. One possible server
is the server started with runJavaGuiServer.sh script.

To run the client on Linux:

./runRhinoClient.sh

The first time it is run a jar file will be downloaded and rhino will be 
installed with:

    sudo apt-get install rhino

The client connects to a CRCL server on localhost on the default port, sends a 
couple of commands, requests status and prints the status.

