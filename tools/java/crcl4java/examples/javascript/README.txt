
This directory includes examples for javascript stand-alone 
interpreters/virtual machines. For an example connecting to javascript in 
web browsers see the crcl4java-vaadin-webapp.

The three interpreters tested were:

    * nodejs -- JavaScript runtime built on Chrome's V8 JavaScript engine.
    * jjs -- command to execute Nashorn interpreter that comes with Java 8
    * rhino -- stand-alone interpreter developed by mozilla.

Each directory has a server and a client.

The server should be started and left running before the client is started.

The server will bind to the default port and wait for CRCL commands to be sent,
and return CRCL status messages when requested.

The client will connect to the default port on localhost, send a couple of 
commands and then request status until the last command is done.


