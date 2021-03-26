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
package crcl.ui;

import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.base.VectorType;
import crcl.utils.CRCLPosemath;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.checkerframework.checker.nullness.qual.Nullable;
import rcs.posemath.PmEulerZyx;
import rcs.posemath.PmException;
import rcs.posemath.PmRotationMatrix;
import rcs.posemath.PmRpy;
import rcs.posemath.Posemath;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class PoseDisplay {

    private PoseDisplay() {
    }

    public static void updateDisplayMode(JTable table, PoseDisplayMode displayMode, boolean editable) {
        switch (displayMode) {
            case XYZ_XAXIS_ZAXIS:
                setPoseDisplayModelXAxisZAxis(table, editable);
                break;

            case XYZ_RPY:
                setPoseDisplayModelRpy(table, editable);
                break;

            case XYZ_RX_RY_RZ:
                setPoseDisplayModelRxRyRz(table, editable);
                break;
        }
    }

    @SuppressWarnings({"nullness", "rawtypes"})
    static private void setPoseDisplayModelXAxisZAxis(JTable table, boolean editable) {
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"X", null},
                    {"Y", null},
                    {"Z", null},
                    {"XI", null},
                    {"XJ", null},
                    {"XK", null},
                    {"ZI", null},
                    {"ZJ", null},
                    {"Zk", null}
                },
                new String[]{
                    "Pose Axis", "Position"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, editable
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    @SuppressWarnings({"nullness", "rawtypes"})
    static private void setPoseDisplayModelRpy(JTable table, boolean editable) {
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"X", null},
                    {"Y", null},
                    {"Z", null},
                    {"Roll", null},
                    {"Pitch", null},
                    {"Yaw", null}
                },
                new String[]{
                    "Pose Axis", "Position"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, editable
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    @SuppressWarnings({"nullness", "rawtypes"})
    static private void setPoseDisplayModelRxRyRz(JTable table, boolean editable) {
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"X", null},
                    {"Y", null},
                    {"Z", null},
                    {"Rx", null},
                    {"Ry", null},
                    {"Rz", null}
                },
                new String[]{
                    "Pose Axis", "Position"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, editable
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public static void updatePoseTable(@Nullable PoseType p, JTable jTable, PoseDisplayMode displayMode) {
        try {
            if (null == p) {
                p = CRCLPosemath.identityPose();
            }
            DefaultTableModel tm = (DefaultTableModel) jTable.getModel();
            PointType point = p.getPoint();
            if (null != point) {
                updatePointTable(point, tm, 0);
            }
            switch (displayMode) {
                case XYZ_XAXIS_ZAXIS:
                    updateXaxisZaxisTable(p, tm, 3);
                    break;

                case XYZ_RPY:
                    updateRpyTable(p, tm, 3);
                    break;

                case XYZ_RX_RY_RZ:
                    updateRxRyRzTable(p, tm, 3);
                    break;

            }
        } catch (PmException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private static final Logger LOGGER = Logger.getLogger(PoseDisplay.class.getName());

    private static void updateRpyTable(PoseType p, DefaultTableModel tm, int index) throws PmException {
        PmRpy rpy = CRCLPosemath.toPmRpy(p);
        if (null != rpy && tm.getRowCount() > 2 + index) {
            tm.setValueAt(Math.toDegrees(rpy.r), 0 + index, 1);
            tm.setValueAt(Math.toDegrees(rpy.p), 1 + index, 1);
            tm.setValueAt(Math.toDegrees(rpy.y), 2 + index, 1);
        }
    }

    private static void updateRxRyRzTable(PoseType p, DefaultTableModel tm, int index) throws PmException {
        PmRotationMatrix mat = CRCLPosemath.toPmRotationMatrix(p);
        PmEulerZyx zyx = new PmEulerZyx();
        Posemath.pmMatZyxConvert(mat, zyx);

        if (tm.getRowCount() > 2 + index) {
            tm.setValueAt(Math.toDegrees(zyx.x), 0 + index, 1);
            tm.setValueAt(Math.toDegrees(zyx.y), 1 + index, 1);
            tm.setValueAt(Math.toDegrees(zyx.z), 2 + index, 1);
        }
    }

    private static void updateXaxisZaxisTable(PoseType p, DefaultTableModel tm, int index) {
        VectorType xv = p.getXAxis();
        VectorType zv = p.getZAxis();
        if (null != xv && tm.getRowCount() > 2 + index) {
            tm.setValueAt(xv.getI(), 0 + index, 1);
            tm.setValueAt(xv.getJ(), 1 + index, 1);
            tm.setValueAt(xv.getK(), 2 + index, 1);
        }
        if (null != zv && tm.getRowCount() > 5 + index) {
            tm.setValueAt(zv.getI(), 3 + index, 1);
            tm.setValueAt(zv.getJ(), 4 + index, 1);
            tm.setValueAt(zv.getK(), 5 + index, 1);
        }
    }

    public static void updatePointTable(PointType pt, DefaultTableModel tm, int index) {
        if (null != pt && tm.getRowCount() > 2 + index) {
            tm.setValueAt(pt.getX(), 0 + index, 1);
            tm.setValueAt(pt.getY(), 1 + index, 1);
            tm.setValueAt(pt.getZ(), 2 + index, 1);
        }
    }
}
