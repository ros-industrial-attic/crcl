You need to manually generate the C++ code and headers, and YACC and Lex files, as follows: 

    for file in *.xsd ; do rosrun xml_common xmlInstanceParserGenerator -i xml_common/ -a crcl/ -x $file ; done

    mv *.cc *.hh *.lex *.y ../src

Then run the build:

    cd <path/to/ros/workspace>
    catkin_make
