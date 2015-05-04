Canonical Robot Command Language (CRCL)
====

The canonical robot command language (CRCL) is a low-level messaging language for sending commands to, and receiving status from a robot. CRCL commands are executed by a low-level device controller. The usual source of CRCL commands is a plan/program execution system. The exact meaning of each CRCL command is explained in the in-line documentation incorporated in the formal model. Implementations of CRCL must use the commands as documented. CRCL is intended for use with devices typically described as industrial robots and for other positionable automated devices such as automated guided vehicles (AGVs). Although CRCL is not a programming language, the commands are in the context of a session consisting of getting ready for activity, performing activities, and becoming quiescent. CRCL commands may be collected in files for testing purposes.

CRCL is intended primarily to provide commands that are independent of the kinematics of the robot that executes the commands. However, so that lower level control may be exercised using CRCL, joint level commands are also included.
