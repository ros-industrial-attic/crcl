/*
 * This software is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain.
 * 
 * This software is experimental. NIST assumes no responsibility whatsoever 
 * for its use by other parties, and makes no guarantees, expressed or 
 * implied, about its quality, reliability, or any other characteristic. 
 * We would appreciate acknowledgement if the software is used. 
 * This software can be redistributed and/or modified freely provided 
 * that any derivative works bear some notice that they are derived from it, 
 * and any modified versions bear some notice that they have been modified.
 * 
 *  See http://www.copyright.gov/title17/92chap1.html#105
 * 
 */
package com.github.wshackle.atinetft_proxy;

import com.atiia.automation.sensors.NetFTRDTPacket;
import com.atiia.automation.sensors.NetFTSensor;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 *@author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class NetFTSensorProxy {

        private final NetFTSensor netFTSensor;
        
        public NetFTSensorProxy( InetAddress setAddress ) throws 
                UnknownHostException
        {
           netFTSensor = new NetFTSensor(setAddress);
        }
        
        /**Request a single RDT F/T record from the Net F/T sensor.<br>
         *@return the RDT Packet from the sensor.
         *@throws SocketException If there is an error with the socket.
         *@throws IOException If there is an I/O error with the load cell.
         */
        public DatagramSocket initLowSpeedData() throws 
                SocketException, IOException {
            
           return netFTSensor.initLowSpeedData();
        }
        
        /**Start interrupt-driven (high-speed) data collection from Net F/T 
         *sensor.
         *@param iCount     The number of samples to collect.  A value of 0
         *means the Net F/T should keep outputting samples until it is sent
         *a stop command.  The stop command can be sent by calling 
         *stopDataCollection.
         *@return A DatagramSocket that you should pass to subsequent calls to
         *readHighSpeedData in order to read the samples sent back from the Net 
         *F/T.
         *@throws SocketException   A SocketException is thrown if there is 
         *a problem setting up the socket that is returned.
         *@throws IOException       An IOException is thrown if there is a
         *problem sending the start command to the Net F/T.
         */
        public DatagramSocket startHighSpeedDataCollection( int iCount ) throws
                SocketException, IOException {
            return netFTSensor.startHighSpeedDataCollection(iCount);
        }
        
        /** Tares the current load of the sensor.  
         *  @throws SocketException A SocketException is thrown if there is 
         *  trouble setting up the socket to send the tare command.
         *  @throws IOException An IOException is thrown if there is 
         *  trouble communicating with the sensor. */        
        public void tare() throws SocketException, IOException {
            netFTSensor.tare();
        }
        
        private NetFTRDTPacketProxy[] toProxyArray(NetFTRDTPacket[] packetArray) {
            NetFTRDTPacketProxy[] proxyArray = new NetFTRDTPacketProxy[packetArray.length];
            for (int i = 0; i < proxyArray.length; i++) {
               proxyArray[i] = new NetFTRDTPacketProxy(packetArray[i]);
            }
            return proxyArray;
        }
        
        /**Read high speed data from the Net F/T sensor.  This method is not 
         *thread-safe, so take care that you do not perform other operations on
         *the DatagramSocket while this function is executing, i.e. calling
         *stopDataCollection while this function is executing could cause a 
         *"socket is closed" error.
         *@param cNetFTSocket   The socket returned from 
         * startHighSpeedDataCollection.
         *@param iCount         The number of samples to read from the Net F/T
         *sensor.  This can be less than the total number of samples that the
         *Net F/T will output. I.e. you could call startHighSpeedDataCollection
         *with a count of 1000, then call this function 10 times, each time
         *reading 100 samples.
         *@return   An array of NetFTRDTPackets of length iCount, which 
         *represent the F/T records collected from the sensor.  
         *@throws IOException   An IOException will be thrown if there is an
         *error reading data from the Net F/T.
         */
        public NetFTRDTPacketProxy[] readHighSpeedData( 
                DatagramSocket cNetFTSocket, int iCount ) throws IOException{
            
            return toProxyArray(netFTSensor.readHighSpeedData(cNetFTSocket, iCount));
        }       
        
        public NetFTRDTPacketProxy readLowSpeedData(DatagramSocket cNetFTSocket) throws
                IOException{
            return new NetFTRDTPacketProxy(netFTSensor.readLowSpeedData(cNetFTSocket));
        }
        
        /**Stop the Net F/T from outputting any more RDT samples.
         *@param cNetFTSocket   The socket returned by 
         *startHighSpeedDataCollection.  This socket will be closed by this
         *function, so don't use it again!
         *@throws IOException   An IOException will be thrown if there is an
         *error sending the stop command to the Net F/T.
         */
        public void stopDataCollection( DatagramSocket cNetFTSocket ) 
                throws IOException{
            netFTSensor.stopDataCollection(cNetFTSocket);
        }
       
        
        /** Get the version of the Net F/T interface.
         *  @return The version of the Net F/T interface.
         */
        public static String getVersion()
        {
            return NetFTSensor.getVersion();
        }
    
}
