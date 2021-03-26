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
package crcl.ui.client;

import crcl.base.CRCLCommandType;
import crcl.base.CRCLStatusType;
import crcl.utils.CRCLCopier;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class CurrentPoseListenerUpdateInfo {

    private final CrclSwingClientJPanel panel;
    private final CRCLStatusType stat;
    private final @Nullable
    CRCLCommandType cmd;
    private final boolean isHoldingObjectExpected;
    private final long statRecieveTime;

    public CurrentPoseListenerUpdateInfo(CrclSwingClientJPanel panel, CRCLStatusType stat, @Nullable CRCLCommandType cmd, boolean isHoldingObjectExpected, long statRecieveTime) {
        this.panel = panel;
        final CRCLStatusType statCopy = CRCLCopier.copy(stat);
        if (null == statCopy) {
            throw new NullPointerException("statCopy");
        }
        this.stat = statCopy;
        this.cmd = cmd != null ? CRCLCopier.copy(cmd) : null;
        this.isHoldingObjectExpected = isHoldingObjectExpected;
        this.statRecieveTime = statRecieveTime;
    }

    public CrclSwingClientJPanel getPanel() {
        return panel;
    }

    public CRCLStatusType getStat() {
        return stat;
    }

    public @Nullable
    CRCLCommandType getCmd() {
        return cmd;
    }

    public boolean isIsHoldingObjectExpected() {
        return isHoldingObjectExpected;
    }

    public long getStatRecieveTime() {
        return statRecieveTime;
    }

}
