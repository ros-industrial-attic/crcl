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

/**
 *
 *@author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class NetFTRDTPacketProxy {

    private final NetFTRDTPacket netFTRDTPacket;

    public NetFTRDTPacketProxy(NetFTRDTPacket netFTRDTPacket) {
        this.netFTRDTPacket = netFTRDTPacket;
    }

    /**
     * Get the RDT sequence number of this packet. The RDT sequence number
     * increases by one for each packet that is sent, and thus can be used to
     * determine the total number of packets that have been sent by the sensor.
     *
     * @return RDT sequence number of this packet.
     */
    public long getRDTSequence() {
        return netFTRDTPacket.getRDTSequence();
    }

    /**
     * Get the FT sequence number of this packet. The F/T sequence number
     * increases at the internal sample rate of the Net F/T sensor, and thus can
     * be used to time-stamp the data.
     *
     * @return FT sequence number of this packet.
     */
    public long getFTSequence() {
        return netFTRDTPacket.getFTSequence();
    }

    /**
     * Get the bit-mapped status code of this packet.
     *
     * @return The bit-mapped int status code.
     */
    public int getStatus() {
        return netFTRDTPacket.getStatus();
    }

    /**
     * Get the force in the x-axis
     *
     * @return The force in the x-axis, in engineering units.
     */
    public int getFx() {
        return netFTRDTPacket.getFx();
    }

    /**
     * Get the force in the y-axis.
     *
     * @return The force in the y-axis, in engineering units.
     */
    public int getFy() {
        return netFTRDTPacket.getFy();
    }

    /**
     * Get the force in the z-axis.
     *
     * @return The force in the z-axis, in engineering units.
     */
    public int getFz() {
        return netFTRDTPacket.getFz();
    }

    /**
     * Get the torque in the x-axis.
     *
     * @return The torque in the x-axis, in engineering units.
     */
    public int getTx() {
        return netFTRDTPacket.getTx();
    }

    /**
     * Get the torque in the y-axis.
     *
     * @return The torque in the y-axis, in engineering units.
     */
    public int getTy() {
        return netFTRDTPacket.getTy();
    }

    /**
     * Get the torque in the z-axis.
     *
     * @return the torque in the z-axis, in engineering units.
     */
    public int getTz() {
        return netFTRDTPacket.getTz();
    }

    /**
     * Get the F/T data in an array of ints.
     *
     * @return A int[] which contains the force and torque data in the order Fx,
     * Fy, Fz, Tx, Ty, Tz. Data is in engineering units ("counts").
     */
    public int[] getFTArray() {
        return netFTRDTPacket.getFTArray();
    }

    /**
     * Create a new NetFTRDTPacket object.
     *
     * @param setRDTSequence The RDT sequence of this packet.
     * @param setFTSequence The F/T sequence of this packet.
     * @param setStatus The bit-mapped status code of this packet.
     * @param setFx The force in the x-axis, in engineering units.
     * @param setFy The force in the y-axis, in engineering units.
     * @param setFz The force in the z-axis, in engineering units.
     * @param setTx The torque in the x-axis, in engineering units.
     * @param setTy The torque in the y-axis, in engineering units.
     * @param setTz The torque in the z-axis, in engineering units.
     */
    public NetFTRDTPacketProxy(int setRDTSequence, int setFTSequence,
            int setStatus, int setFx, int setFy, int setFz, int setTx,
            int setTy, int setTz) {
        netFTRDTPacket = new NetFTRDTPacket(setRDTSequence, setFTSequence, setStatus, setFx, setFy, setFz, setTx, setTy, setTz);
    }

    /**
     * Create a new NetFTRDTPacket object
     *
     * @param fields The fields of the RDT data packet, in the order
     * RDTSequence, FTSequence, Status, Fx, Fy, Fz, Tx, Ty, Tz
     */
    public NetFTRDTPacketProxy(int[] fields) {
        netFTRDTPacket = new NetFTRDTPacket(fields);
    }

    @Override
    public String toString() {
        return "NetFTRDTPacketProxy{RDTSequence="+getRDTSequence()+", FTSequence="+getFTSequence()+", Status="+getStatus()+", Fx="+getFx()+", Fy="+getFy()+", Fz="+getFy()+", Tx="+getTx()+", Ty="+getTy()+", Tz="+getTz()+"}";
    }

    
    
}
