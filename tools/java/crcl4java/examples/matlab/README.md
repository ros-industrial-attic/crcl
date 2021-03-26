
Matlab can use crcl4java fairly directly. 

Use javaaddpath to add the path to the crcl4java jar file.


You may want to check the version of java matlab is using with 
version -java

If it is too old, restart matlab with env MATLAB_JAVA=<path-to-newer-jre> matlab.

