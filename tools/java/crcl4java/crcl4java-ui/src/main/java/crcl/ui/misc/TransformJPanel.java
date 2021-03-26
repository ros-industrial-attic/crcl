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
package crcl.ui.misc;

import crcl.ui.client.CrclSwingClientJPanel;
import crcl.base.CRCLProgramType;
import crcl.base.MiddleCommandType;
import crcl.base.MoveToType;
import crcl.base.PointType;
import crcl.base.PoseType;
import crcl.ui.PoseDisplay;
import crcl.ui.PoseDisplayMode;
import static crcl.ui.PoseDisplayMode.XYZ_XAXIS_ZAXIS;
import crcl.utils.CRCLException;
import crcl.utils.CRCLPosemath;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class TransformJPanel extends javax.swing.JPanel {

    private CrclSwingClientJPanel pendantClient;

    /**
     * Set the value of pendantClient
     *
     * @param pendantClient new value of pendantClient
     */
    public void setPendantClient(CrclSwingClientJPanel pendantClient) {
        this.pendantClient = pendantClient;
    }

    /**
     * Creates new form TransformJPanel
     */
    @SuppressWarnings("initialization")
    public TransformJPanel() {
        initComponents();
        this.jTableOrigPoint1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                try {
                    if (!ignoreModelUpdates) {
                        setOrigPoint1(getPointFromTable(jTableOrigPoint1));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(TransformSetupJFrame.class.getCanonicalName()).log(Level.SEVERE, "TableChanged", ex);
                }
            }
        });
        this.jTableOrigPoint2.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                try {
                    if (!ignoreModelUpdates) {
                        setOrigPoint2(getPointFromTable(jTableOrigPoint2));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(TransformSetupJFrame.class.getCanonicalName()).log(Level.SEVERE, "TableChanged", ex);
                }
            }
        });
        this.jTableNewPoint1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                try {
                    if (!ignoreModelUpdates) {
                        setNewPoint1(getPointFromTable(jTableNewPoint1));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(TransformSetupJFrame.class.getCanonicalName()).log(Level.SEVERE, "TableChanged", ex);
                }
            }
        });
        this.jTableNewPoint2.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                try {
                    if (!ignoreModelUpdates) {
                        setNewPoint2(getPointFromTable(jTableNewPoint2));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(TransformSetupJFrame.class.getCanonicalName()).log(Level.SEVERE, "TableChanged", ex);
                }
            }
        });
    }

    static public PointType getPointFromTable(JTable table) {
        final TableModel model = table.getModel();
        PointType pt = new PointType();
        pt.setX(Double.parseDouble(model.getValueAt(0, 1).toString()));
        pt.setY(Double.parseDouble(model.getValueAt(1, 1).toString()));
        pt.setZ(Double.parseDouble(model.getValueAt(2, 1).toString()));
        return pt;
    }

     private PointType origPoint1 = CRCLPosemath.newZeroedPoint();

    /**
     * Get the value of origPoint1
     *
     * @return the value of origPoint1
     */
    public PointType getOrigPoint1() {
        return origPoint1;
    }

    /**
     * Set the value of origPoint1
     *
     * @param origPoint1 new value of origPoint1
     */
    public void setOrigPoint1(PointType origPoint1) {
        this.origPoint1 = origPoint1;
        if (null != origPoint1) {
            updatePointTable(this.jTableOrigPoint1, origPoint1);
        }
    }

    private volatile boolean ignoreModelUpdates = false;

    public synchronized void updatePointTable(JTable table, PointType pt) {
        ignoreModelUpdates = true;
        final TableModel model = table.getModel();
        model.setValueAt(pt.getX(), 0, 1);
        model.setValueAt(pt.getY(), 1, 1);
        model.setValueAt(pt.getZ(), 2, 1);
        ignoreModelUpdates = false;
    }

     private PoseType transform = CRCLPosemath.identityPose();

    /**
     * Get the value of transform
     *
     * @return the value of transform
     */
    public PoseType getTransform() {
        return transform;
    }

    /**
     * Set the value of transform
     *
     * @param transform new value of transform
     */
    public void setTransform(PoseType transform) {
        this.transform = transform;
        PoseDisplay.updatePoseTable(transform, jTablePose, XYZ_XAXIS_ZAXIS);
    }

    private void updateTransformBothPoints() {
        try {
            PoseType newTransform = CRCLPosemath.compute2DTransform(origPoint1, origPoint2, newPoint1, newPoint2);
            this.setTransform(newTransform);
            PointType newPoint1Transformed = CRCLPosemath.multiply(transform, origPoint1);
            PointType point1Error = CRCLPosemath.subtract(newPoint1, newPoint1Transformed);
            PointType newPoint2Transformed = CRCLPosemath.multiply(transform, origPoint2);
            PointType point2Error = CRCLPosemath.subtract(newPoint2, newPoint2Transformed);
            TableModel m = this.jTableTransformErrors.getModel();
            m.setValueAt(point1Error.getX(), 0, 1);
            m.setValueAt(point1Error.getY(), 1, 1);
            m.setValueAt(point1Error.getZ(), 2, 1);
            m.setValueAt(point2Error.getX(), 3, 1);
            m.setValueAt(point2Error.getY(), 4, 1);
            m.setValueAt(point2Error.getZ(), 5, 1);

        } catch (CRCLException ex) {
            Logger.getLogger(TransformSetupJFrame.class.getName()).log(Level.SEVERE, null, ex);
            pendantClient.showMessage(ex);
        }
    }

    private void updateTransformPoint1() {
        try {
            PoseType newTransform = CRCLPosemath.compute2DTransform(origPoint1, origPoint1, newPoint1, newPoint1);
            this.setTransform(newTransform);
            PointType newPoint1Transformed = CRCLPosemath.multiply(transform, origPoint1);
            PointType point1Error = CRCLPosemath.subtract(newPoint1, newPoint1Transformed);
            PointType newPoint2Transformed = CRCLPosemath.multiply(transform, origPoint2);
            PointType point2Error = CRCLPosemath.subtract(newPoint2, newPoint2Transformed);
            TableModel m = this.jTableTransformErrors.getModel();
            m.setValueAt(point1Error.getX(), 0, 1);
            m.setValueAt(point1Error.getY(), 1, 1);
            m.setValueAt(point1Error.getZ(), 2, 1);
            m.setValueAt(point2Error.getX(), 3, 1);
            m.setValueAt(point2Error.getY(), 4, 1);
            m.setValueAt(point2Error.getZ(), 5, 1);

        } catch (CRCLException ex) {
            Logger.getLogger(TransformSetupJFrame.class.getName()).log(Level.SEVERE, null, ex);
            pendantClient.showMessage(ex);
        }
    }

    private void updateTransformPoint2() {
        try {
            PoseType newTransform = CRCLPosemath.compute2DTransform(origPoint2, origPoint2, newPoint2, newPoint2);
            this.setTransform(newTransform);
            PointType newPoint1Transformed = CRCLPosemath.multiply(transform, origPoint1);
            PointType point1Error = CRCLPosemath.subtract(newPoint1, newPoint1Transformed);
            PointType newPoint2Transformed = CRCLPosemath.multiply(transform, origPoint2);
            PointType point2Error = CRCLPosemath.subtract(newPoint2, newPoint2Transformed);
            TableModel m = this.jTableTransformErrors.getModel();
            m.setValueAt(point1Error.getX(), 0, 1);
            m.setValueAt(point1Error.getY(), 1, 1);
            m.setValueAt(point1Error.getZ(), 2, 1);
            m.setValueAt(point2Error.getX(), 3, 1);
            m.setValueAt(point2Error.getY(), 4, 1);
            m.setValueAt(point2Error.getZ(), 5, 1);

        } catch (CRCLException ex) {
            Logger.getLogger(TransformSetupJFrame.class.getName()).log(Level.SEVERE, null, ex);
            pendantClient.showMessage(ex);
        }
    }

     private PointType origPoint2 = CRCLPosemath.newZeroedPoint();

    /**
     * Get the value of origPoint2
     *
     * @return the value of origPoint2
     */
    public PointType getOrigPoint2() {
        return origPoint2;
    }

    /**
     * Set the value of origPoint2
     *
     * @param origPoint2 new value of origPoint2
     */
    public void setOrigPoint2(PointType origPoint2) {
        this.origPoint2 = origPoint2;
        if (null != origPoint2) {
            updatePointTable(this.jTableOrigPoint2, origPoint2);
        }
    }

     private PointType newPoint1 = CRCLPosemath.newZeroedPoint();

    /**
     * Get the value of newPoint1
     *
     * @return the value of newPoint1
     */
    public PointType getNewPoint1() {
        return newPoint1;
    }

    /**
     * Set the value of newPoint1
     *
     * @param newPoint1 new value of newPoint1
     */
    public void setNewPoint1(PointType newPoint1) {
        this.newPoint1 = newPoint1;
        if (null != newPoint1) {
            updatePointTable(this.jTableNewPoint1, newPoint1);
        }
    }

     private PointType newPoint2 = CRCLPosemath.newZeroedPoint();

    /**
     * Get the value of newPoint2
     *
     * @return the value of newPoint2
     */
    public PointType getNewPoint2() {
        return newPoint2;
    }

    /**
     * Set the value of newPoint2
     *
     * @param newPoint2 new value of newPoint2
     */
    public void setNewPoint2(PointType newPoint2) {
        this.newPoint2 = newPoint2;
        if (null != newPoint1) {
            updatePointTable(this.jTableNewPoint2, newPoint2);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "nullness","deprecation","rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonComputeTransformPoint1 = new javax.swing.JButton();
        jButtonOrigPoint2Current = new javax.swing.JButton();
        jButtonOrigPoint2Program = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableOrigPoint2 = new javax.swing.JTable();
        jButtonOrigPoint1Current = new javax.swing.JButton();
        jButtonOrigPoint1Program = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOrigPoint1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonComputeTransformPoint2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableNewPoint1 = new javax.swing.JTable();
        jButtonNewPoint1Current = new javax.swing.JButton();
        jButtonNewPoint1Program = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableNewPoint2 = new javax.swing.JTable();
        jButtonNewPoint2Current = new javax.swing.JButton();
        jButtonNewPoint2Program = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jTablePose = new javax.swing.JTable();
        jButtonTransformProgram = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableTransformErrors = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButtonComputeTranformBothPoints = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Original Points"));

        jButtonComputeTransformPoint1.setText("Transform from Point1");
        jButtonComputeTransformPoint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComputeTransformPoint1ActionPerformed(evt);
            }
        });

        jButtonOrigPoint2Current.setText("Current");
        jButtonOrigPoint2Current.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrigPoint2CurrentActionPerformed(evt);
            }
        });

        jButtonOrigPoint2Program.setText("Program");
        jButtonOrigPoint2Program.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrigPoint2ProgramActionPerformed(evt);
            }
        });

        jTableOrigPoint2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X",  new Double(0.0)},
                {"Y",  new Double(0.0)},
                {"Z",  new Double(0.0)}
            },
            new String [] {
                "Axis", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableOrigPoint2);

        jButtonOrigPoint1Current.setText("Current");
        jButtonOrigPoint1Current.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrigPoint1CurrentActionPerformed(evt);
            }
        });

        jButtonOrigPoint1Program.setText("Program");
        jButtonOrigPoint1Program.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOrigPoint1ProgramActionPerformed(evt);
            }
        });

        jTableOrigPoint1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X",  new Double(0.0)},
                {"Y",  new Double(0.0)},
                {"Z",  new Double(0.0)}
            },
            new String [] {
                "Axis", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableOrigPoint1);

        jLabel4.setText("Point 1: ");

        jLabel5.setText("Point 2: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jButtonComputeTransformPoint1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonOrigPoint1Current)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonOrigPoint1Program)))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonOrigPoint2Current)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonOrigPoint2Program)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOrigPoint1Current)
                    .addComponent(jButtonOrigPoint1Program))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOrigPoint2Current)
                    .addComponent(jButtonOrigPoint2Program))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonComputeTransformPoint1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("New Points"));

        jButtonComputeTransformPoint2.setText("Transform from Point2");
        jButtonComputeTransformPoint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComputeTransformPoint2ActionPerformed(evt);
            }
        });

        jTableNewPoint1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X",  new Double(0.0)},
                {"Y",  new Double(0.0)},
                {"Z",  new Double(0.0)}
            },
            new String [] {
                "Axis", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableNewPoint1);

        jButtonNewPoint1Current.setText("Current");
        jButtonNewPoint1Current.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewPoint1CurrentActionPerformed(evt);
            }
        });

        jButtonNewPoint1Program.setText("Program");
        jButtonNewPoint1Program.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewPoint1ProgramActionPerformed(evt);
            }
        });

        jLabel2.setText("Point 1: ");

        jLabel3.setText("Point 2: ");

        jTableNewPoint2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X",  new Double(0.0)},
                {"Y",  new Double(0.0)},
                {"Z",  new Double(0.0)}
            },
            new String [] {
                "Axis", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableNewPoint2);

        jButtonNewPoint2Current.setText("Current");
        jButtonNewPoint2Current.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewPoint2CurrentActionPerformed(evt);
            }
        });

        jButtonNewPoint2Program.setText("Program");
        jButtonNewPoint2Program.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewPoint2ProgramActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonComputeTransformPoint2)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButtonNewPoint1Current)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonNewPoint1Program))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButtonNewPoint2Current)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonNewPoint2Program)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNewPoint1Current)
                    .addComponent(jButtonNewPoint1Program))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNewPoint2Current)
                    .addComponent(jButtonNewPoint2Program))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonComputeTransformPoint2)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Computed"));

        jTablePose.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X",  new Double(0.0)},
                {"Y",  new Double(0.0)},
                {"Z",  new Double(0.0)},
                {"XI",  new Double(1.0)},
                {"XJ",  new Double(0.0)},
                {"XK",  new Double(0.0)},
                {"ZI",  new Double(0.0)},
                {"ZJ",  new Double(0.0)},
                {"Zk",  new Double(1.0)},
                {"Roll",  new Double(0.0)},
                {"Pitch",  new Double(0.0)},
                {"Yaw",  new Double(0.0)}
            },
            new String [] {
                "Pose Axis", "Position"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        jButtonTransformProgram.setText("Apply");
        jButtonTransformProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTransformProgramActionPerformed(evt);
            }
        });

        jTableTransformErrors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"X1", null},
                {"Y1", null},
                {"Z1", null},
                {"X2", null},
                {"Y2", null},
                {"Z2", null}
            },
            new String [] {
                "Axis", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTableTransformErrors);

        jLabel1.setText("Errors");

        jButtonComputeTranformBothPoints.setText("Compute ");
        jButtonComputeTranformBothPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComputeTranformBothPointsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jTablePose, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButtonComputeTranformBothPoints)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTransformProgram))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonComputeTranformBothPoints)
                    .addComponent(jButtonTransformProgram))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTablePose, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOrigPoint1ProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrigPoint1ProgramActionPerformed
        if (null != pendantClient) {
            MiddleCommandType cmd = pendantClient.currentProgramCommand();
            if (cmd instanceof MoveToType) {
                MoveToType moveToCmd = (MoveToType) cmd;
                PoseType pose = moveToCmd.getEndPosition();
                if (null != pose) {
                    PointType posePoint = pose.getPoint();
                    if (null != posePoint) {
                        setOrigPoint1(posePoint);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonOrigPoint1ProgramActionPerformed

    private void jButtonOrigPoint2CurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrigPoint2CurrentActionPerformed
        if (null != pendantClient) {
            PoseType pose = pendantClient.currentStatusPose();
            if (null != pose) {
                PointType posePoint = pose.getPoint();
                if (null != posePoint) {
                    setOrigPoint2(posePoint);
                }
            }
        }
    }//GEN-LAST:event_jButtonOrigPoint2CurrentActionPerformed

    private void jButtonOrigPoint2ProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrigPoint2ProgramActionPerformed
        if (null != pendantClient) {
            MiddleCommandType cmd = pendantClient.currentProgramCommand();
            if (cmd instanceof MoveToType) {
                MoveToType moveToCmd = (MoveToType) cmd;
                PoseType pose = moveToCmd.getEndPosition();
                if (null != pose) {
                    PointType posePoint = pose.getPoint();
                    if (null != posePoint) {
                        setOrigPoint2(posePoint);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonOrigPoint2ProgramActionPerformed

    private void jButtonNewPoint1CurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewPoint1CurrentActionPerformed
        if (null != pendantClient) {
            PoseType pose = pendantClient.currentStatusPose();
            if (null != pose) {
                PointType posePoint = pose.getPoint();
                if (null != posePoint) {
                    setNewPoint1(posePoint);
                }
            }
        }
    }//GEN-LAST:event_jButtonNewPoint1CurrentActionPerformed

    private void jButtonNewPoint1ProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewPoint1ProgramActionPerformed
        if (null != pendantClient) {
            MiddleCommandType cmd = pendantClient.currentProgramCommand();
            if (cmd instanceof MoveToType) {
                MoveToType moveToCmd = (MoveToType) cmd;
                PoseType pose = moveToCmd.getEndPosition();
                if (null != pose) {
                    PointType posePoint = pose.getPoint();
                    if (null != posePoint) {
                        setNewPoint1(posePoint);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonNewPoint1ProgramActionPerformed

    private void jButtonNewPoint2CurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewPoint2CurrentActionPerformed
        if (null != pendantClient) {
            PoseType pose = pendantClient.currentStatusPose();
            if (null != pose) {
                PointType posePoint = pose.getPoint();
                if (null != posePoint) {
                    setNewPoint2(posePoint);
                }
            }
        }
    }//GEN-LAST:event_jButtonNewPoint2CurrentActionPerformed

    private void jButtonNewPoint2ProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewPoint2ProgramActionPerformed
        if (null != pendantClient) {
            MiddleCommandType cmd = pendantClient.currentProgramCommand();
            if (cmd instanceof MoveToType) {
                MoveToType moveToCmd = (MoveToType) cmd;
                PoseType pose = moveToCmd.getEndPosition();
                if (null != pose) {
                    PointType posePoint = pose.getPoint();
                    if (null != posePoint) {
                        setNewPoint2(posePoint);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonNewPoint2ProgramActionPerformed

    private void jButtonOrigPoint1CurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOrigPoint1CurrentActionPerformed
        if (null != pendantClient) {
            PoseType pose = pendantClient.currentStatusPose();
            if (null != pose) {
                PointType posePoint = pose.getPoint();
                if (null != posePoint) {
                    setOrigPoint1(posePoint);
                }
            }
        }
    }//GEN-LAST:event_jButtonOrigPoint1CurrentActionPerformed

    static private String currentDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'at'HHmm");
        return sdf.format(new Date());
    }
    
    private void jButtonTransformProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTransformProgramActionPerformed
        try {
            PoseType transform = CrclSwingClientJPanel.tableToPose(jTablePose, PoseDisplayMode.XYZ_XAXIS_ZAXIS);
            CRCLProgramType inProgram = pendantClient.getProgram();
            if (null != inProgram) {
                CRCLProgramType newProgram = CRCLPosemath.transformProgram(transform, inProgram);
                File curFile = pendantClient.getLastOpenedProgramFile();
                if (curFile == null) {
                    curFile = File.createTempFile("program", ".xml");

                }
                String name = curFile.getName();
                int transformedIndex = name.indexOf(".transformed");
                if (transformedIndex > 0) {
                    name = name.substring(0, transformedIndex);
                }
                int xmlIndex = name.indexOf(".xml");
                if (xmlIndex > 0) {
                    name = name.substring(0, xmlIndex);
                }
                File newFile = new File(curFile.getParentFile(), name + ".transformed." + currentDateString() + ".xml");
                pendantClient.setProgram(newProgram);
                pendantClient.saveXmlProgramFile(newFile);
            } else {
                pendantClient.showMessage("Can not apply tranform to program when no program is loaded.");
            }
        } catch (Exception ex) {
            Logger.getLogger(TransformJPanel.class.getName()).log(Level.SEVERE, null, ex);
            pendantClient.showMessage(ex);
        }
    }//GEN-LAST:event_jButtonTransformProgramActionPerformed

    private void jButtonComputeTransformPoint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComputeTransformPoint1ActionPerformed
        this.updateTransformPoint1();
    }//GEN-LAST:event_jButtonComputeTransformPoint1ActionPerformed

    private void jButtonComputeTransformPoint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComputeTransformPoint2ActionPerformed
        this.updateTransformPoint2();
    }//GEN-LAST:event_jButtonComputeTransformPoint2ActionPerformed

    private void jButtonComputeTranformBothPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComputeTranformBothPointsActionPerformed
        this.updateTransformBothPoints();
    }//GEN-LAST:event_jButtonComputeTranformBothPointsActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
//            Thread.dumpStack();
            JFrame frame = new JFrame();
            frame.add(new TransformJPanel());
            frame.pack();
            frame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonComputeTranformBothPoints;
    private javax.swing.JButton jButtonComputeTransformPoint1;
    private javax.swing.JButton jButtonComputeTransformPoint2;
    private javax.swing.JButton jButtonNewPoint1Current;
    private javax.swing.JButton jButtonNewPoint1Program;
    private javax.swing.JButton jButtonNewPoint2Current;
    private javax.swing.JButton jButtonNewPoint2Program;
    private javax.swing.JButton jButtonOrigPoint1Current;
    private javax.swing.JButton jButtonOrigPoint1Program;
    private javax.swing.JButton jButtonOrigPoint2Current;
    private javax.swing.JButton jButtonOrigPoint2Program;
    private javax.swing.JButton jButtonTransformProgram;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTableNewPoint1;
    private javax.swing.JTable jTableNewPoint2;
    private javax.swing.JTable jTableOrigPoint1;
    private javax.swing.JTable jTableOrigPoint2;
    private javax.swing.JTable jTablePose;
    private javax.swing.JTable jTableTransformErrors;
    // End of variables declaration//GEN-END:variables
}
