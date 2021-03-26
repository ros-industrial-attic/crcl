

This project creates a proxy class so that other maven projects can indirectly
use the classes for the ATI_SensorDemo  for the Force/Torque sensor from
ATI Industrial Automation  that needs to be downloaded separately 
and is not packaged for maven.

      
      
      Download jar from 
      
      https://www.ati-ia.com/Library/software/net_ft/ATINetFT.jar
      
      or from the webpage on the device itself.

      Or get the source and compile from

      https://www.ati-ia.com/Library/software/net_ft/src.zip
      
      
      On Windows PC OPTIMUS install with this command(Paths will differ on other computers)

SET  JAVA_HOME=C:\Users\Public\Documents\Downloaded_Tools\AdoptOpenJDK\jdk-11.0.2+7
C:\Users\Public\Documents\Downloaded_Tools\ApacheNetbeans10.0\netbeans\java\maven\bin\mvn.cmd install:install-file -Dfile=C:\Users\Public\Documents\Downloaded_Tools\ATINetFT.jar -DgroupId=com.ati-ia -DartifactId=ATINetFT -Dversion=1.0 -Dpackaging=jar
          
    On linux with the source compiled in /home/shackle/NetBeansProjects/ATINetFT/ 
it could be installed with

mvn install:install-file -Dfile=/home/shackle/NetBeansProjects/ATINetFT/dist/ATINetFT.jar -DgroupId=com.ati-ia -DartifactId=ATINetFT -Dversion=1.0 -Dpackaging=jar

       The project can then be built with the standard

mvn install


            
