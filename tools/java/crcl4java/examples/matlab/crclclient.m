% Matlab script using crcl4java jar file.
% A CRCL server should be started before running this script.
% Connects sends a simple command and checks the status.
%
% Add crcl4java-utils-1.3-jar-with-dependencies.jar to
% javaclasspath
% eg.
fprintf('Running crclclient.m\n');

% If the CRCLSocket class is not already loaded download the jar (if necessary)
% and add the jar to the javaclasspath.
if exist('CRCLSocket','class') ~= 8 
    jarfilename='crcl4java-utils.jar';
    fulljarfilename=fullfile(pwd(),jarfilename);
    fprintf('fulljarfilename= %s\n',fulljarfilename);
    % Check To see if jar file was already downloaded but not added.
    if exist(fulljarfilename, 'file') == 2
        fprintf('Adding %s to javapath\n',jarfilename);
        javaaddpath(jarfilename);
    else
        remotejarurl='https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar';
        fprintf('Downloading %s\n',remotejarurl);
        outfilename = websave(jarfilename,remotejarurl);
        fprintf('Adding %s to javapath\n',outfilename);
        javaaddpath(outfilename)
    end
    
    import crcl.base.*
    import crcl.utils.*
    import java.math.BigInteger
    import java.math.BigDecimal
end

% Connect to server running on localhost on default port (64444)
%  ( One could use the run.bat or run.sh scripts in the main crcl4java 
%  directory to start server. If you get 
%  "java.net.ConnectException: Connection refused" error, then most likely
%  the server is not running.) 
fprintf('Connecting to socket ...\n');
s = CRCLSocket('localhost',64444);

% Create an instance which is just a container for a single command.
instance = CRCLCommandInstanceType();

% Create an InitCanon command, put it in the instance, give it a unique ID
% and send it.
fprintf('Sending InitCanon command ...\n');
init = InitCanonType();
init.setCommandID(7);
instance.setCRCLCommand(init);
s.writeCommand(instance);

% Create an GetStatus command, put it in the instance, give it a unique ID,
%  and send it.
fprintf('Asking for status ...\n');
getStat = GetStatusType();
getStat.setCommandID(9);
instance.setCRCLCommand(getStat)
s.writeCommand(instance,false)
 
% Wait for the response from the GetStatus request as a CRCLStatus object.
stat = s.readStatus(false);
            
% Print out the status details.
cmdStat = stat.getCommandStatus();
IDback = cmdStat.getCommandID();
fprintf('CommandID=%d\n',IDback);
fprintf('State=%s\n',char(cmdStat.getCommandState().toString()));

while IDback ~= init.getCommandID() || cmdStat.getCommandState() ~= CommandStateEnumType.CRCL_DONE
        
    % Create an GetStatus command, put it in the instance, give it a unique ID,
    %  and send it.
    getStat = GetStatusType();
    getStat.setCommandID(9);
    instance.setCRCLCommand(getStat)
    s.writeCommand(instance,false)

    % Wait for the response from the GetStatus request as a CRCLStatus object.
    stat = s.readStatus(false);

    % Print out the status details.
    cmdStat = stat.getCommandStatus();
    IDback = cmdStat.getCommandID();
    fprintf('CommandID=%d\n',IDback);
    fprintf('State=%s\n',char(cmdStat.getCommandState().toString()));

end
        
% Create an MoveTo command, put it in the instance, give it a unique ID,
% create a pose for the end postion and send it.
fprintf('Sending MoveTo command ...\n');
moveTo = MoveToType();
moveTo.setCommandID(8);
pose =  CRCLPosemath.pose(CRCLPosemath.point(248.5,2.5,0.1),CRCLPosemath.vector(1,0,0),CRCLPosemath.vector(0,0,1));
moveTo.setEndPosition(pose)
moveTo.setMoveStraight(false)
instance.setCRCLCommand(moveTo)
s.writeCommand(instance,false)
IDback = -1
        
        

% Create an GetStatus command, put it in the instance, give it a unique ID,
%  and send it.
fprintf('Asking for status ...\n');
getStat = GetStatusType();
getStat.setCommandID(9);
instance.setCRCLCommand(getStat)
s.writeCommand(instance,false)
 
% Wait for the response from the GetStatus request as a CRCLStatus object.
stat = s.readStatus(false);
            
% Print out the status details.
cmdStat = stat.getCommandStatus();
IDback = cmdStat.getCommandID();
fprintf('CommandID=%d\n',IDback);
fprintf('State=%s\n',char(cmdStat.getCommandState().toString()));

while IDback ~= moveTo.getCommandID() || cmdStat.getCommandState() ~= CommandStateEnumType.CRCL_DONE
        
    % Create an GetStatus command, put it in the instance, give it a unique ID,
    %  and send it.
    getStat.setCommandID(getStat.getCommandID()+1);
    instance.setCRCLCommand(getStat)
    s.writeCommand(instance,false)

    % Wait for the response from the GetStatus request as a CRCLStatus object.
    stat = s.readStatus(false);

    % Print out the status details.
    cmdStat = stat.getCommandStatus();
    IDback = cmdStat.getCommandID();
    fprintf('\nCommandID=%d\n',IDback);
    fprintf('State=%s\n',char(cmdStat.getCommandState().toString()));
    pt = stat.getPoseStatus().getPose().getPoint();
    x =pt.getX();
    y =pt.getY();
    z =pt.getZ();
    fprintf('X=%f ',x);
    fprintf('Y=%f ',y);
    fprintf('Z=%f\n',z);

    jst = stat.getJointStatuses();
    l = jst.getJointStatus();
    lit = l.iterator();            
    while lit.hasNext() 
        js = lit.next();
        jsn = js.getJointNumber();
        jsp = js.getJointPosition().doubleValue();
        fprintf('JointNumber=%d ',jsn);
        fprintf('JointPosition=%f\n',jsp);
    end
end

s.close();