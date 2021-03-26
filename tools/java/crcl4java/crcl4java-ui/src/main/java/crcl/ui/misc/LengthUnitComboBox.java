/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crcl.ui.misc;

import crcl.base.LengthUnitEnumType;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * Combo Box with model set to LengthUnitEnumType values.
 * 
 * @author Will Shackleford {@literal <william.shackleford@nist.gov>}
 */
public class LengthUnitComboBox extends JComboBox<crcl.base.LengthUnitEnumType>{
    
    @SuppressWarnings("initialization")
    public LengthUnitComboBox() {
        setModel(new DefaultComboBoxModel<>(LengthUnitEnumType.values()));
        setSelectedItem(LengthUnitEnumType.MILLIMETER);
    }

    @Override
    public LengthUnitEnumType getSelectedItem() {
        return (LengthUnitEnumType) super.getSelectedItem(); 
    }
    private static final Logger LOG = Logger.getLogger(LengthUnitComboBox.class.getName());
    
    
}
