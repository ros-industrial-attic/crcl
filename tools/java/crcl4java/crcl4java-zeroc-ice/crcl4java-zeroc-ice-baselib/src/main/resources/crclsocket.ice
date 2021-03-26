
#include <crcl4java.ice>

module crcl {
        module zerocice {
            //sequence<java2slice::crcl::base::CRCLStatusTypeIce> StatusSequence;
            interface CRCLSocketWrapper {

                void writeCommand(java2slice::crcl::base::CRCLCommandInstanceTypeIce cmd);
                java2slice::crcl::base::CRCLStatusTypeIce readStatus();
            };
        };   
        
};
