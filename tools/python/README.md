## Running the sample Python programs

The sample Python code implements a simulator for a robot or gripper
that responds to CRCL commands, and an interpreter shell that lets you
send commands and view status back.

*crclsim.py* is the simulator, and *crclsh.py* is the interpretive shell.

Run a simulator for a robot like this:

```
./crclsim.py -n HappyRobot -p 12301 -r  -d
```

The arguments are the (optional) name of the robot, to help
distinguish printed output, the TCP/IP port to listen to for
connections, the -r flag to indicate that it should handle
robot-specific CRCL commands, and a -d flag to turn debug printing on.

The program will wait for connections from the shell.

In another terminal, do the same for a gripper:

```
./crclsim.py -n AngryGripper -p 12302 -g -d
```

The -g option means it should be a gripper.

In a third terminal, run the interactive shell: 

```
./crclsh.py -r 12301 -R localhost -g 12302 -G localhost
```

The arguments tell the shell to look for the robot simulator on TCP/IP
port 12301, on the host 'localhost', and the gripper on port 12302
likewise on 'localhost'. You will see a prompt that lets you type
commands:

```
Interactive commands:
q, ^D, ^C                : quit
?                        : print this help message
[Enter]                  : print robot and gripper status
init                     : send InitCanon to both the robot and gripper
end                      : send EndCanon to both the robot and gripper
open                     : send OpenToolChanger to the grippper
close <name>             : send CloseToolChanger for gripper <name>
set <value>              : send SetEndEffector for gripper <value>
params <name value> ...  : send gripper SetEndEffectorParameters for pairs
move <x y z xi xj xk zi zj zk> : send MoveTo to robot
```


