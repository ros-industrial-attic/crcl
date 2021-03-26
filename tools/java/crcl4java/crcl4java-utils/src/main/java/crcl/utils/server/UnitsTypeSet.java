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
package crcl.utils.server;

import crcl.base.AngleUnitEnumType;
import crcl.base.ForceUnitEnumType;
import crcl.base.LengthUnitEnumType;
import crcl.base.TorqueUnitEnumType;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
@SuppressWarnings("nullness")
public class UnitsTypeSet {
    private AngleUnitEnumType angleUnit = AngleUnitEnumType.DEGREE;
    private LengthUnitEnumType lengthUnit = LengthUnitEnumType.MILLIMETER;
    private ForceUnitEnumType forceUnit = ForceUnitEnumType.NEWTON;
    private TorqueUnitEnumType torqueUnit = TorqueUnitEnumType.NEWTON_METER;

    public AngleUnitEnumType getAngleUnit() {
        return angleUnit;
    }

    public void setAngleUnit(AngleUnitEnumType angleUnit) {
        this.angleUnit = angleUnit;
    }

    public LengthUnitEnumType getLengthUnit() {
        return lengthUnit;
    }

    public void setLengthUnit(LengthUnitEnumType lengthUnit) {
        this.lengthUnit = lengthUnit;
    }

    public ForceUnitEnumType getForceUnit() {
        return forceUnit;
    }

    public void setForceUnit(ForceUnitEnumType forceUnit) {
        this.forceUnit = forceUnit;
    }

    public TorqueUnitEnumType getTorqueUnit() {
        return torqueUnit;
    }

    public void setTorqueUnit(TorqueUnitEnumType torqueUnitEnumType) {
        this.torqueUnit = torqueUnitEnumType;
    }
    
}
