/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.wshackle.fanuccrclservermain;

import com.github.wshackle.fanuc.robotserver.ITPProgram;
import com.github.wshackle.fanuc.robotserver.IVar;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public interface FanucCRCLServerDisplayInterface {

    public JTextField getjTextFieldRobotNeighborhoodPath();

    public JTextField getjTextFieldHostName();

    public void setVisible(boolean _visible);

    public void setConnected(boolean _connected);

    public void dispose();

    public JTextArea getjTextAreaErrors();

    public JSlider getjSliderOverride();

    public void updatePerformanceString(String s);

    public JCheckBox getjCheckBoxLogAllCommands();

    public void setPreferRobotNeighborhood(boolean _preferRobotNeighborhood);

    public JSlider getjSliderMaxOverride();

    public void setMain(FanucCRCLMain _main);

    public void setPrograms(List<ITPProgram> _tpPrograms);

    public JMenuItem getjMenuItemReconnectRobot();

    public JMenuItem getjMenuItemResetAlarms();

    public JRadioButton getjRadioButtonUseDirectIP();

    public JRadioButton getjRadioButtonUseRobotNeighborhood();

    public JTextField getjTextFieldLimitSafetyBumper();

    public void updateCartesianLimits(float xMax,
            float xMin,
            float yMax,
            float yMin,
            float zMax,
            float zMin);

    public void updateJointLimits(float lowerJointLimits[], float upperJointLimits[]);

    public void setOverrideVar(IVar overrideVar);

    public @Nullable
    IVar getOverrideVar();

    public void setMorSafetyStatVar(IVar morSafetyStatVar);

    public @Nullable
    IVar getMorSafetyStatVar();

    public void setMoveGroup1ServoReadyVar(IVar var);

    public @Nullable
    IVar getMoveGroup1ServoReadyVar();

}
