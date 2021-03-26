/* 
 * This is public domain software, however it is preferred
 * that the following disclaimers be attached.
 * 
 * Software Copywrite/Warranty Disclaimer
 * 
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of their
 * official duties. Pursuant to title 17 Section 105 of the United States
 * Code this software is not subject to copyright protection and is in the
 * public domain. This software is experimental.
 * NIST assumes no responsibility whatsoever for its use by other
 * parties, and makes no guarantees, expressed or implied, about its
 * quality, reliability, or any other characteristic. We would appreciate
 * acknowledgment if the software is used. This software can be
 * redistributed and/or modified freely provided that any derivative works
 * bear some notice that they are derived from it, and any modified
 * versions bear some notice that they have been modified.
 * 
 */
package crcl.ui.misc;

import crcl.base.CRCLCommandInstanceType;
import crcl.base.CRCLCommandType;
import crcl.utils.XFuture;
import crcl.utils.CRCLSocket;
import crcl.utils.CRCLUtils;
import crcl.utils.XpathUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.xml.sax.SAXException;

/**
 *
 * @author Will Shackleford{@literal <william.shackleford@nist.gov> }
 * @param <T> Type of object to be shown/modified.
 */
@SuppressWarnings("nullness")
public class ObjTableJPanel<T> extends javax.swing.JPanel {

    /**
     * Creates new form CmdTableJPanel
     */
    @SuppressWarnings("initialization")
    public ObjTableJPanel() {
        initComponents();
        setupTableSelection();
    }

    private XpathUtils xpu = null;
    private File @MonotonicNonNull [] schemaFiles = null;
    private String defaultDocumentation = null;

    private void setupTableSelection() {

        ListSelectionModel cellSelectionModel = this.jTable1.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                jButtonNew.setEnabled(false);
                jButtonDelete.setEnabled(false);
                jButtonAddToList.setEnabled(false);
                jButtonRemoveFromList.setEnabled(false);
                jButtonEditMultiLineText.setEnabled(false);
                int row = jTable1.getSelectedRow();
                DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
                if (row < 0 || row > tm.getRowCount()) {
                    return;
                }
                Object col0Val = tm.getValueAt(row, 0);
                Object col1Val = tm.getValueAt(row, 1);
                String type = col0Val.toString();
                String name = col1Val.toString();
                String typenoparams = removeTypeParams(type);
                Class<?> clss = null;
                try {
                    if (col0Val instanceof Class) {
                        clss = (Class) col0Val;
                    } else if (typenoparams.equals("boolean")) {
                        clss = boolean.class;
                    } else if (typenoparams.equals("long")) {
                        clss = long.class;
                    } else if (typenoparams.equals("double")) {
                        clss = double.class;
                    } else if (typenoparams.equals("int")) {
                        clss = int.class;
                    } else if (typenoparams.equals("float")) {
                        clss = float.class;
                    } else {
                        clss = Class.forName(typenoparams);
                    }
                    String documentation = null;
                    String doctype = typenoparams;
                    Object cur_obj = getObject(name);
                    if (null != cur_obj) {
                        doctype = cur_obj.getClass().getCanonicalName();
                    }
                    int pindex = doctype.lastIndexOf('.');
                    if (pindex > 0) {
                        doctype = doctype.substring(pindex + 1);
                    }
                    if (null != xpu && null != schemaFiles) {
                        try {
                            documentation = xpu.getDocumentation(schemaFiles, doctype);
                        } catch (SAXException | IOException | XPathExpressionException | ParserConfigurationException ex) {
                            Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (null != documentation) {
                            jTextPaneDocumentation.setText(documentation);
                        } else if (null != defaultDocumentation) {
                            jTextPaneDocumentation.setText(defaultDocumentation);
                        }
                        jTextPaneDocumentation.setCaretPosition(0);
                        jScrollPaneDocumentation.getVerticalScrollBar().setValue(0);
                    }
                    if (name.equals("this") || row == 0) {
                        return;
                    }
                    if (isList(getParentObject(name).getClass())) {
                        jButtonRemoveFromList.setEnabled(true);
                        return;
                    }
                    if (isCompound(clss, customExcludedPathStrings)) {
                        jButtonNew.setEnabled(true);
                        if (null != getObject(name)) {
                            jButtonDelete.setEnabled(true);
                        }
                        return;
                    }
                    if (isList(clss)) {
                        jButtonAddToList.setEnabled(true);
                        return;
                    }
                    if (clss.equals(String.class)) {
                        jButtonEditMultiLineText.setEnabled(true);
                    }
                } catch (Exception ex) {
                    System.out.println("type = " + type);
                    System.err.println("row = " + row);
                    System.err.println("col0Val = " + col0Val);
                    System.err.println("col1Val = " + col1Val);
                    System.err.println("typenoparams = " + typenoparams);
                    Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private final Map<Integer, TableCellEditor> editorMap = new HashMap<>();
    private final Map<Integer, DefaultTableCellRenderer> rendererMap = new HashMap<>();
    private final Map<Integer, Color> colorMap = new HashMap<>();
    private final Set<Integer> noneditableSet = new HashSet<>();

    private JTable createJTable() {
        return new JTable() {

            @Override
            public Component prepareRenderer(
                    TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (this.getSelectedRow() == row) {
                    return c;
                }
                Color color = colorMap.get(row);
                if (color != null) {
                    c.setBackground(color);
                }
                return c;
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 2 && editorMap.containsKey(row)) {
                    return editorMap.get(row);
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 2 && rendererMap.containsKey(row)) {
                    return rendererMap.get(row);
                }
                return super.getCellRenderer(row, column);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                boolean returnvar = false;
                returnvar = super.isCellEditable(row, column);
                if (returnvar && column == 2 && noneditableSet.contains(row)) {
                    return false;
                }
                return returnvar;
            }

        };
    }

    private class NewDeletePanel extends JPanel {

        private final JButton jButtonNew = new JButton("New");
        private final JButton jButtonDelete = new JButton("Delete");

        NewDeletePanel() {
            super();

//                this.setPreferredSize(new Dimension(400,400));
//                this.setSize(new Dimension(400,400));
//                this.setMinimumSize(new Dimension(400,400));
            jButtonNew.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setNewTableItem();
                }
            });
            jButtonDelete.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //setNewTableItem();
                    System.err.println("FIXME: implement delete");
                }
            });
            this.add(jButtonNew);
            this.add(jButtonDelete);
        }
    };

    Map<Integer, NewDeletePanel> pnlMap = new HashMap<>();

//    private class MyTableModel extends DefaultTableModel {
//
//        final private DefaultTableModel orig;
//
//        public MyTableModel(final DefaultTableModel _orig) {
//            super();
//            this.orig = _orig;
//        }
//
//
//        @Override
//        public boolean isCellEditable(int row, int column) {
//            return column != 2 && null == pnlMap.get(row);
//        }
//
//        @Override
//        public int getRowCount() {
//            if(null == orig) {
//                return 0;
//            }
//            return orig.getRowCount();
//        }
//
//        @Override
//        public int getColumnCount() {
//            if(null == orig) {
//                return 3;
//            }
//            return orig.getColumnCount();
//        }
//
//        @Override
//        public String getColumnName(int columnIndex) {
//           return orig.getColumnName(columnIndex);
//        }
//
//        @Override
//        public Class<?> getColumnClass(int columnIndex) {
//           return orig.getColumnClass(columnIndex);
//        }
//
//        @Override
//        public Object getValueAt(int rowIndex, int columnIndex) {
//           return orig.getValueAt(rowIndex, columnIndex);
//        }
//
//        @Override
//        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//                 orig.setValueAt(aValue, rowIndex, columnIndex);
//        }
//
//        @Override
//        public void addTableModelListener(TableModelListener l) {
//            orig.addTableModelListener(l);
//        }
//
//        @Override
//        public void removeTableModelListener(TableModelListener l) {
//            orig.removeTableModelListener(l);
//        }
//
//    };
    private class MyTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component returnvar = null;
            if (column == 2) {
                try {
                    DefaultTableModel tm = (DefaultTableModel) table.getModel();
                    String type = (String) tm.getValueAt(row, 0);
                    String typenoparams = removeTypeParams(type);
                    Class<?> clss = Class.forName(typenoparams);
                    pnlMap.remove(row);
                    if (isCompound(clss, customExcludedPathStrings)) {
                        NewDeletePanel pnl = new NewDeletePanel();
                        pnlMap.put(row, pnl);
                        int rheight = table.getRowHeight(row);
                        table.setRowHeight(row, Math.max(rheight, pnl.getPreferredSize().height));
                        return pnl;
                    }
//                    if (isList(clss)) {
//                        jButtonAddToList.setEnabled(true);
//                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            returnvar = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return returnvar;
        }
    };

    private static String removeTypeParams(String type) {
        String typenoparams = type;
        int ltindex = typenoparams.indexOf('<');
        if (ltindex > 0) {
            typenoparams = typenoparams.substring(0, ltindex);
        }
        return typenoparams;
    }

    private static String getTypeParams(String type) {
        String params = "";
        int ltindex = type.indexOf('<');
        int gtindex = type.indexOf('>');
        if (gtindex > 0 && ltindex > 0) {
            params = type.substring(ltindex + 1, gtindex);
        }
        return params;
    }

    private static boolean isList(Class<?> clss) {
        return List.class.isAssignableFrom(clss);
    }

    private static boolean isCompound(Class<?> clss, List<String> customExcludedPathStrings) {
        if (clss.isPrimitive()) {
            return false;
        }
        if (List.class.isAssignableFrom(clss)) {
            return false;
        }
        if (String.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Boolean.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Float.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Double.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Short.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Integer.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.lang.Long.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.math.BigDecimal.class.isAssignableFrom(clss)) {
            return false;
        }
        if (java.math.BigInteger.class.isAssignableFrom(clss)) {
            return false;
        }
        if (clss.isEnum()) {
            return false;
        }
        if (null == classes) {
            classes = getClasses(customExcludedPathStrings);
        }
        List<Class<?>> availClasses = getAssignableClasses(clss, classes);
        return availClasses.size() > 0;
    }

    private T obj;

    /**
     * Get the value of obj
     *
     * @return the value of obj
     */
    public @Nullable
    T getObj() {
        return obj;
    }

    private void updateObjFromTable() {
        DefaultTableModel tm = (DefaultTableModel) this.jTable1.getModel();
        final int row_count = tm.getRowCount();
        for (int i = 0; i < row_count; i++) {
            try {
                String type = tm.getValueAt(i, 0).toString();
                String name = tm.getValueAt(i, 1).toString();
                Object o = tm.getValueAt(i, 2);
                this.setObjectForName(type, name, o);

            } catch (SecurityException | IllegalArgumentException ex) {
                System.err.println("i = " + i);
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    final private Color myBlue = new Color(150, 150, 255);

    private Object getDefaultForClass(Class<?> clss) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        if (String.class.isAssignableFrom(clss)) {
            return "";
        }
        if (java.math.BigDecimal.class.isAssignableFrom(clss)) {
            return BigDecimal.ZERO;
        }
        if (java.math.BigInteger.class.isAssignableFrom(clss)) {
            return BigInteger.ONE;
        }
        if (Boolean.class.isAssignableFrom(clss)) {
            return Boolean.FALSE;
        }
        if (Double.class.isAssignableFrom(clss)) {
            return 0.0;
        }
        if (Float.class.isAssignableFrom(clss)) {
            return 0.0f;
        }
        if (Short.class.isAssignableFrom(clss)) {
            return (short) 0;
        }
        if (Integer.class.isAssignableFrom(clss)) {
            return 0;
        }
        if (Long.class.isAssignableFrom(clss)) {
            return Long.valueOf(0);
        }
        if (clss.isEnum()) {
            return clss.getEnumConstants()[0];
        }
        if (!Modifier.isAbstract(clss.getModifiers())) {
            final Constructor<?> declaredConstructor = clss.getDeclaredConstructor();
            return declaredConstructor.newInstance();
        }
        return null;
    }

    private Field getField(Class<?> clss, String name) {
        Field f = null;
        if (clss == null) {
            return null;
        }
        try {
            f = clss.getField(name);
        } catch (Exception ex) {
            Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.FINEST, "exception normally ignored", ex);
        }
        if (null != f) {
            return f;
        }
        try {
            f = clss.getDeclaredField(name);
        } catch (Exception ex) {
            Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.FINEST, "exception normally ignored", ex);
        }
        if (null != f) {
            return f;
        }
        return getField(clss.getSuperclass(), name);
    }

    private static boolean isMethodSetter(Method method) {
        int submModifieres = method.getModifiers();
        return method.getName().startsWith("set")
                && method.getParameterCount() == 1
                && Modifier.isPublic(submModifieres)
                && (!Modifier.isStatic(submModifieres))
                && (!Modifier.isNative(submModifieres))
                && (method.getReturnType() == void.class || method.getReturnType() == Void.class)
                && (method.getExceptionTypes() == null || method.getExceptionTypes().length == 0);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void addObjectToTable(String name_prefix,
            DefaultTableModel tm, Object o, Class<?> clss) {
        Class<?> oclss = clss;
        if (null != o) {
            oclss = o.getClass();
        } else {
            Object rowArray[] = new Object[]{oclss, name_prefix, null};
            addRow(tm, rowArray);
            return;
        }
        if (oclss.isPrimitive()) {
            Object rowElementArray[] = new Object[]{int.class, name_prefix, o};
            addRow(tm, rowElementArray);
            return;
        }
        if (null != defaultDocumentation) {
            jTextPaneDocumentation.setText(defaultDocumentation);
            jTextPaneDocumentation.setCaretPosition(0);
            jScrollPaneDocumentation.getVerticalScrollBar().setValue(0);
        }

        if (oclss.isArray()) {
            int arrayLength = Array.getLength(o);
            Object rowLengthArray[] = new Object[]{int.class, name_prefix + "length", arrayLength};
            addRow(tm, rowLengthArray);
            Class<?> arrayComponentType = oclss.getComponentType();
            if (arrayComponentType.isPrimitive()) {
                for (int i = 0; i < arrayLength; i++) {
                    Object object = Array.get(o, i);
                    Object rowElementArray[] = new Object[]{int.class, name_prefix + "[" + i + "]", object};
                    addRow(tm, rowElementArray);
                }
            } else {
                for (int i = 0; i < arrayLength; i++) {
                    Object object = Array.get(o, i);
                    if (null != object) {
                        addObjectToTable(name_prefix + "[" + i + "].", tm, object, object.getClass());
                    } else {
                        addObjectToTable(name_prefix + "[" + i + "].", tm, null, arrayComponentType);
                    }
                }
            }
            return;
        }
        Field fa[] = oclss.getFields();
        SortedSet<String> names = new TreeSet<>();
        for (int i = 0; i < fa.length; i++) {
            try {
                Field field = fa[i];
                if (name_prefix.contains(field.getName())) {
                    continue;
                }
                boolean fieldIsPublic = Modifier.isPublic(field.getModifiers());
                boolean fieldIsStatic = Modifier.isStatic(field.getModifiers());
                if (fieldIsPublic && !fieldIsStatic) {
                    Class<?> fieldType = field.getType();
                    boolean fieldIsPrimitive = fieldType.isPrimitive();
                    Set<String> methodNames = new TreeSet<>();
                    Method fieldMethods[] = fieldType.getMethods();
                    for (int j = 0; j < fieldMethods.length; j++) {
                        Method fieldMethod = fieldMethods[j];
                        if (Modifier.isPublic(fieldMethod.getModifiers())) {
                            methodNames.add(fieldMethod.getName());
                        }
                    }
                    if (!methodNames.contains("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1))) {
                        Object fieldValue = field.get(o);
                        Object rowArray[] = new Object[]{fieldType, name_prefix + field.getName(), fieldValue};
                        addRow(tm, rowArray);
                        if (!fieldIsPrimitive) {
                            addObjectToTable(name_prefix + field.getName() + ".", tm, fieldValue, fieldType);
                        }
                    }
                }
            } catch (Exception ex) {
                System.err.println("o = " + o);
                System.err.println("oclss = " + oclss);
                System.err.println("name_prefix = " + name_prefix);
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Method ma[] = oclss.getMethods();

        Map<String, Method> getterMap = new HashMap<>();
        for (Method m : ma) {
            Class decClass = m.getDeclaringClass();
            if (decClass == Object.class
                    || decClass == java.awt.Component.class
                    || decClass == java.awt.Container.class
                    || decClass == java.awt.Window.class
                    || decClass == javax.swing.JComponent.class
                    || decClass == javax.swing.JInternalFrame.class
                    || decClass == javax.swing.JMenuBar.class) {
                continue;
            }
            final int modifiers = m.getModifiers();
            if (!Modifier.isPublic(modifiers)) {
                continue;
            }
            if (Modifier.isStatic(modifiers)) {
                continue;
            }
            if (Modifier.isNative(modifiers)) {
                continue;
            }
            Class exTypes[] = m.getExceptionTypes();
            if (exTypes != null && exTypes.length > 0) {
                continue;
            }
            try {
                if (m.getName().startsWith("get") && m.getParameterCount() == 0) {
                    String mname = m.getName().substring(3);
                    if (mname.equals("Class")) {
                        continue;
                    }
                    names.add(mname);
                    getterMap.put(mname, m);
                }
            } catch (Exception e) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.FINEST, "exception normally ignored", e);
            }
            try {
                if (m.getName().startsWith("is") && m.getParameterCount() == 0) {
                    String mname = m.getName().substring(2);
                    if (mname.equals("Class")) {
                        continue;
                    }
                    names.add(mname);
                    getterMap.put(mname, m);
                }
            } catch (Exception e) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.FINEST, "exception normally ignored", e);
            }
        }

        for (String name : names) {
            try {
                Method getMethod = getterMap.get(name);
                if (null == getMethod) {
                    continue;
                }
                Class mclss = getMethod.getReturnType();

                boolean hasSetter = false;
                for (Method method : ma) {
                    int submModifieres = method.getModifiers();
                    if (method.getName().equals("set" + name)
                            && isMethodSetter(method)
                            && method.getParameterTypes()[0] == mclss) {
                        hasSetter = true;
                        break;
                    }
                }
                boolean subHasSetter = false;
                if (!mclss.isPrimitive()) {
                    Method subMa[] = mclss.getMethods();
                    for (Method subMethod : subMa) {
                        if (isMethodSetter(subMethod)) {
                            subHasSetter = true;
                            break;
                        }
                    }
                }
                String type = mclss.getCanonicalName();
                String list_item_type = null;
                if (List.class.isAssignableFrom(mclss) && null != getMethod) {
                    try {
                        ParameterizedType prt = (ParameterizedType) getMethod.getGenericReturnType();
                        list_item_type = prt.getActualTypeArguments()[0].getTypeName();
                        type += "<" + list_item_type + ">";
                    } catch (Exception e) {
                    }
                } else if (!hasSetter) {
                    continue;
                }
                this.colorMap.put(tm.getRowCount(), Color.LIGHT_GRAY);
                if (mclss.isEnum()) {
                    JComboBox comboBox = new JComboBox();
                    for (Object oc : mclss.getEnumConstants()) {
                        comboBox.addItem(oc);
                    }
                    this.editorMap.put(tm.getRowCount(), new DefaultCellEditor(comboBox));
                }
                Object mo = null;
                if (null != o && null != getMethod) {
                    try {
                        mo = getMethod.invoke(o);
                    } catch (Exception ex) {
                        Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE,
                                "name_prefix=" + name_prefix
                                + ",name=" + name
                                + ",getMethod=" + getMethod
                                + ",o=" + o
                                + ",mo=" + mo,
                                ex);
                        throw new RuntimeException(ex);
                    }
                }
                if (mclss.equals(boolean.class)) {
                    if (mo == null) {
                        mo = Boolean.FALSE;
                    }
                    boolean mo_boolean = (boolean) mo;
                    final JCheckBox jc = new JCheckBox("", mo_boolean);
                    jc.setBackground(Color.LIGHT_GRAY);
                    this.rendererMap.put(tm.getRowCount(), new DefaultTableCellRenderer() {

                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                            return jc;
                        }
                    });
                    this.editorMap.put(tm.getRowCount(), new DefaultCellEditor(jc) {

                        @Override
                        public Object getCellEditorValue() {
                            if (jc.isSelected()) {
                                return Boolean.TRUE;
                            }
                            return Boolean.FALSE;
                        }

                    });

                }
                String fieldName = name.substring(0, 1).toLowerCase() + name.substring(1);
                if (null == mo) {
                    Field field = this.getField(clss, fieldName);
                    if (null != field) {
                        Annotation annotations[] = field.getDeclaredAnnotations();
                        for (Annotation a : annotations) {
                            if (XmlElement.class.isAssignableFrom(a.annotationType())) {
                                XmlElement xe = (XmlElement) a;
                                if (xe.required()) {
                                    try {
                                        mo = this.getDefaultForClass(mclss);
                                        if (null != mo) {
                                            Method mset = oclss.getMethod("set" + name, mclss);
                                            mset.invoke(o, mo);
                                        }
                                    } catch (Exception ex) {
                                        Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, "mclss=" + mclss + ", mo=" + mo + ",o=" + o + ",xe=" + xe, ex);
                                    }
                                }
                            }
                        }
                    }
                }
                if (name_prefix.contains(name)) {
                    continue;
                }
                Object rowArray[] = new Object[]{type, name_prefix + name, mo};
                addRow(tm, rowArray);
                if (isCompound(mclss, customExcludedPathStrings)) {
                    this.noneditableSet.add(tm.getRowCount() - 1);
                }
                if (mclss.equals(String.class) && mo != null
                        && mo.toString().contains("\n")) {
                    final JTextArea jta = new JTextArea();
                    this.noneditableSet.add(tm.getRowCount() - 1);
                    this.rendererMap.put(tm.getRowCount() - 1, new DefaultTableCellRenderer() {

                        @Override
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                            jta.setText(value.toString());
                            table.setRowHeight(row, Math.max(table.getRowHeight(row), jta.getPreferredSize().height));
                            return jta;
                        }
                    });
                }
                if (name_prefix.contains(name)) {
                    continue;
                }
                if (List.class.isAssignableFrom(mclss) && null != getMethod && null != mo) {
                    this.noneditableSet.add(tm.getRowCount() - 1);
                    this.colorMap.put(tm.getRowCount() - 1, Color.green.brighter());
                    List l = (List) mo;
                    Class lclss = Object.class;
                    try {
                        ParameterizedType prt = (ParameterizedType) getMethod.getGenericReturnType();
                        lclss = Class.forName(prt.getActualTypeArguments()[0].getTypeName());
                    } catch (Exception e) {
                    }
                    for (int i = 0; i < l.size(); i++) {
                        Object lo = l.get(i);
                        if (null != lo) {
                            String item_name = name_prefix + name + ".get(" + i + ")";
                            rowArray = new Object[]{list_item_type, item_name, lo};
                            this.colorMap.put(tm.getRowCount(), Color.yellow);
                            addRow(tm, rowArray);
                            addObjectToTable(item_name + ".", tm, lo, lclss);
                        }
                    }
                } else if (subHasSetter && mo != null
                        && !mclss.equals(java.math.BigDecimal.class)
                        && !mclss.equals(java.math.BigInteger.class)) {
                    this.noneditableSet.add(tm.getRowCount() - 1);
                    this.colorMap.put(tm.getRowCount() - 1, myBlue);
                    addObjectToTable(name_prefix + name + ".", tm, mo, mclss);
                }
            } catch (SecurityException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addRow(DefaultTableModel tm, Object[] rowArray) {
        if (rowArray[1].toString().length() < 1) {
            System.out.println("rowArray = " + Arrays.toString(rowArray));
        }
        tm.addRow(rowArray);
    }

    /**
     * Set the value of obj
     *
     * @param obj new value of obj
     */
    public void setObj(T obj) {
        this.obj = obj;
        updateTableFromObject();
    }

    private void updateTableFromObject() {
        DefaultTableModel tm = (DefaultTableModel) this.jTable1.getModel();
        tm.setRowCount(0);
        Class<?> clss = obj.getClass();
        rendererMap.clear();
        editorMap.clear();
        colorMap.clear();
        noneditableSet.clear();
        Object oa[] = new Object[]{obj.getClass(), "this", obj};
        addRow(tm, oa);
        colorMap.put(0, myBlue);
        addObjectToTable("", tm, obj, clss);
        updateOutText(obj);
    }

    private JDialog dialog = null;
    private boolean cancelled = false;
    private Function<T, XFuture<Boolean>> isValid = null;
    private volatile CRCLSocket crclSocket;

    private static <T> T editObjectPriv(JDialog _dialog, T _obj,
            XpathUtils xpu,
            File schemaFiles @Nullable [],
            Function<T, XFuture<Boolean>> isValid,
            CRCLSocket crclSocket) {
        ObjTableJPanel<T> panel = new ObjTableJPanel<>();
        panel.dialog = _dialog;
        panel.setObj(_obj);
        panel.isValid = isValid;
        panel.crclSocket = crclSocket;
        String clssname = _obj.getClass().getCanonicalName();
        int pindex = clssname.lastIndexOf('.');
        if (pindex > 0) {
            clssname = clssname.substring(pindex + 1);
        }
        if (null != xpu && null != schemaFiles) {
            String documentation = null;
            try {
                documentation = xpu.getDocumentation(schemaFiles, clssname);
            } catch (SAXException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            panel.xpu = xpu;
            panel.schemaFiles = schemaFiles;
            if (null != documentation) {
                panel.jTextPaneDocumentation.setText(documentation);
                panel.defaultDocumentation = documentation;
            }
        }
        panel.updateOutText(_obj);
        panel.jTextPaneDocumentation.setCaretPosition(0);
        panel.jScrollPaneDocumentation.getVerticalScrollBar().setValue(0);
        _dialog.add(panel);
        _dialog.pack();
        _dialog.setVisible(true);
        if (panel.cancelled) {
            return null;
        }
        return panel.getObj();
    }

    private void updateOutText(T _obj) {
        try {
            if (null != crclSocket) {
                if (_obj instanceof CRCLCommandType) {
                    CRCLCommandInstanceType instance = new CRCLCommandInstanceType();
                    instance.setCRCLCommand((CRCLCommandType) _obj);
                    String outText = crclSocket.commandInstanceToPrettyString(instance, true);
                    jTextAreaOutput.setText(outText);
                } else if (_obj instanceof CRCLCommandInstanceType) {
                    CRCLCommandInstanceType instance = (CRCLCommandInstanceType) _obj;
                    String outText = crclSocket.commandInstanceToPrettyString(instance, true);
                    jTextAreaOutput.setText(outText);
                }
            }
        } catch (Exception ex) {
//            Logger.getLogger(ObjTableJPanel.class
//                    .getName()).log(Level.SEVERE, null, ex);
            String outText = ex.toString() + "\n\n"
                    + CRCLUtils.traceToString(ex.getStackTrace());
            jTextAreaOutput.setText(outText);
        }
    }

    public static <T> T editObject(T _obj,
            @Nullable Frame _owner,
            String _title,
            boolean _modal) {
        JDialog dialog = new JDialog(_owner, _obj.getClass().getCanonicalName() + ":" + _title, _modal);
        return editObjectPriv(dialog, _obj, null, null, null, null);
    }

    public static <T> T editObject(T _obj,
            @Nullable Frame _owner,
            String _title,
            boolean _modal,
            XpathUtils xpu,
            File schemaFiles @Nullable [],
            Function<T, XFuture<Boolean>> isValid,
            CRCLSocket crclSocket) {
        JDialog dialog = new JDialog(_owner, _obj.getClass().getCanonicalName() + ":" + _title, _modal);
        return editObjectPriv(dialog, _obj, xpu, schemaFiles, isValid, crclSocket);
    }

    public static <T> T editObject(T _obj,
            XpathUtils xpu,
            File schemaFiles @Nullable [],
            Function<T, XFuture<Boolean>> isValid,
            CRCLSocket crclSocket) {
        JDialog dialog = new JDialog();
        dialog.setTitle(_obj.getClass().getCanonicalName());
        dialog.setModal(true);
        return editObjectPriv(dialog, _obj, xpu, schemaFiles, isValid, crclSocket);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCancel = new javax.swing.JButton();
        jButtonOK = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = createJTable();
        jButtonNew = new javax.swing.JButton();
        jButtonAddToList = new javax.swing.JButton();
        jButtonRemoveFromList = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonEditMultiLineText = new javax.swing.JButton();
        jPanelColorCode = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanelDocumentationOutput = new javax.swing.JPanel();
        jTabbedPanelDocOut = new javax.swing.JTabbedPane();
        jScrollPaneOutput = new javax.swing.JScrollPane();
        jTextAreaOutput = new javax.swing.JTextArea();
        jScrollPaneDocumentation = new javax.swing.JScrollPane();
        jTextPaneDocumentation = new javax.swing.JTextPane();
        jButtonUpdateTableToText = new javax.swing.JButton();
        jButtonTextToTable = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(formListener);

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(formListener);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Type", "Name", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButtonNew.setText("New");
        jButtonNew.setEnabled(false);
        jButtonNew.addActionListener(formListener);

        jButtonAddToList.setText("Add To List");
        jButtonAddToList.setEnabled(false);
        jButtonAddToList.addActionListener(formListener);

        jButtonRemoveFromList.setText("Remove From List");
        jButtonRemoveFromList.setEnabled(false);
        jButtonRemoveFromList.addActionListener(formListener);

        jButtonDelete.setText("Delete");
        jButtonDelete.setEnabled(false);
        jButtonDelete.addActionListener(formListener);

        jButtonEditMultiLineText.setText("Edit Multi-line Text");
        jButtonEditMultiLineText.setEnabled(false);
        jButtonEditMultiLineText.addActionListener(formListener);

        jPanelColorCode.setBorder(javax.swing.BorderFactory.createTitledBorder("Color Code"));

        jLabel1.setBackground(new java.awt.Color(150, 150, 255));
        jLabel1.setText("Compound Type");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(0, 255, 0));
        jLabel2.setText("List");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("List Item");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel3.setOpaque(true);

        javax.swing.GroupLayout jPanelColorCodeLayout = new javax.swing.GroupLayout(jPanelColorCode);
        jPanelColorCode.setLayout(jPanelColorCodeLayout);
        jPanelColorCodeLayout.setHorizontalGroup(
            jPanelColorCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelColorCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelColorCodeLayout.setVerticalGroup(
            jPanelColorCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelColorCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelColorCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelDocumentationOutput.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextAreaOutput.setColumns(20);
        jTextAreaOutput.setRows(5);
        jScrollPaneOutput.setViewportView(jTextAreaOutput);

        jTabbedPanelDocOut.addTab("Ouput", jScrollPaneOutput);

        jTextPaneDocumentation.setEditable(false);
        jScrollPaneDocumentation.setViewportView(jTextPaneDocumentation);

        jTabbedPanelDocOut.addTab("Documentation", jScrollPaneDocumentation);

        javax.swing.GroupLayout jPanelDocumentationOutputLayout = new javax.swing.GroupLayout(jPanelDocumentationOutput);
        jPanelDocumentationOutput.setLayout(jPanelDocumentationOutputLayout);
        jPanelDocumentationOutputLayout.setHorizontalGroup(
            jPanelDocumentationOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPanelDocOut)
        );
        jPanelDocumentationOutputLayout.setVerticalGroup(
            jPanelDocumentationOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDocumentationOutputLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPanelDocOut, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButtonUpdateTableToText.setText("Table To Text");
        jButtonUpdateTableToText.addActionListener(formListener);

        jButtonTextToTable.setText("Text To Table");
        jButtonTextToTable.addActionListener(formListener);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelDocumentationOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelColorCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButtonNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAddToList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoveFromList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEditMultiLineText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jButtonTextToTable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUpdateTableToText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDocumentationOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelColorCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOK)
                    .addComponent(jButtonNew)
                    .addComponent(jButtonAddToList)
                    .addComponent(jButtonRemoveFromList)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonEditMultiLineText)
                    .addComponent(jButtonUpdateTableToText)
                    .addComponent(jButtonTextToTable))
                .addContainerGap())
        );
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButtonCancel) {
                ObjTableJPanel.this.jButtonCancelActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonOK) {
                ObjTableJPanel.this.jButtonOKActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonNew) {
                ObjTableJPanel.this.jButtonNewActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonAddToList) {
                ObjTableJPanel.this.jButtonAddToListActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonRemoveFromList) {
                ObjTableJPanel.this.jButtonRemoveFromListActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonDelete) {
                ObjTableJPanel.this.jButtonDeleteActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonEditMultiLineText) {
                ObjTableJPanel.this.jButtonEditMultiLineTextActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonUpdateTableToText) {
                ObjTableJPanel.this.jButtonUpdateTableToTextActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonTextToTable) {
                ObjTableJPanel.this.jButtonTextToTableActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.cancelled = true;
        this.dialog.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        this.cancelled = false;
        this.updateObjFromTable();
        if (null != this.isValid) {
            // The predicate is potentially blocking. To avoid blocking it may return
            // a CompleteableFuture<Boolean> which is then scheduled to set the dialogs
            // visible state when/if it completes.
            this.isValid.apply(this.obj)
                    .thenAccept(b -> runOnDispatchThread(() -> this.dialog.setVisible(!b)));
            return;
        }
        this.dialog.setVisible(false);
    }//GEN-LAST:event_jButtonOKActionPerformed

    private static void runOnDispatchThread(final Runnable r) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            javax.swing.SwingUtilities.invokeLater(r);
        }
    }
    static private List<Class<?>> classes = null;

    static private List<Class<?>> addClasses(String prefix, File dir, List<Class<?>> classes) {
        File fa[] = dir.listFiles();
        if (fa == null) {
            return classes;
        }
        for (File f : fa) {
            if (f.isDirectory()) {
                classes = addClasses(prefix + f.getName() + ".", f, classes);
            } else if (f.getName().endsWith(".class")) {
                String clssNameToLookup = "";
                try {
                    String name = f.getName();
                    name = name.substring(0, name.length() - 6);
                    if (name.indexOf('$') >= 0) {
                        continue;
                    }
                    clssNameToLookup = prefix + name;
                    Class<?> clss = Class.forName(clssNameToLookup);
                    if (!Modifier.isAbstract(clss.getModifiers())
                            && !clss.isSynthetic()
                            && !clss.isAnonymousClass()
                            && !clss.isMemberClass()) {
                        classes.add(clss);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ObjTableJPanel.class
                            .getName()).log(Level.SEVERE, "clssNameToLookup={0}", clssNameToLookup);
                    Logger.getLogger(ObjTableJPanel.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return classes;
    }

    private static final List<String> staticIncludedPathStrings
            = Arrays.asList(new String[]{
        "target/classes",
        "target\\classes",
        "wshackle",
        "rcslib",
        "crcl",
        "aprs",});

    private static final List<String> staticExcludedPathStrings
            = Arrays.asList(new String[]{
        "vaadin",
        "google",
        "apache",
        "commons-io",
        "commons-math",
        "xerces",
        "exificient",
        "activation",
        "jaxb-impl",
        "checker-qual",
        "checker-compat-qual",
        "javassist",
        "xstream",
        "logback",
        "drools",
        "eclipse-collections",
        "jSerialComm",
        "ATINetFT"
    });

    private final List<String> customExcludedPathStrings = new ArrayList<>();

    public void addCustomExcludedPathStrings(String s) {
        if (null == s || s.length() < 1) {
            throw new IllegalArgumentException("s=" + s);
        }
        customExcludedPathStrings.add(s);
    }

    private static boolean containsStringInCollection(String inputString, Collection<String> collection) {
        for (String collectionElement : collection) {
            if (inputString.contains(collectionElement)) {
                return true;
            }
        }
        return false;
    }

    static public List<Class<?>> getClasses(List<String> customExcludedPathStrings) {
        String name = "";
        File jar = null;
        List<Class<?>> classes = new ArrayList<>();
        try {
            final String[] classpaths = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
            System.out.println("classpaths = " + Arrays.toString(classpaths));
            for (String classpathEntry : classpaths) {
                if (classpathEntry.endsWith(".jar")
                        && containsStringInCollection(classpathEntry, staticIncludedPathStrings)
                        && !containsStringInCollection(classpathEntry, staticExcludedPathStrings)
                        && !containsStringInCollection(classpathEntry, customExcludedPathStrings)) {
                    System.out.println("classpathEntry = " + classpathEntry);
                    JarInputStream is = null;
                    try {
                        jar = new File(classpathEntry);
                        is = new JarInputStream(new FileInputStream(jar));
                        JarEntry entry;
                        while ((entry = is.getNextJarEntry()) != null) {
//                            System.out.println("entry.getName() = " + entry.getName());
                            if (!entry.getName().startsWith("crcl/base/")) {
                                continue;
                            }
                            if (entry.getName().endsWith(".class")) {
                                name = entry.getName();
                                name = name.substring(0, name.length() - 6);
                                name = name.replaceAll("/", ".");
                                if (name.indexOf('$') >= 0) {
                                    continue;
                                }
                                Class<?> clss;
                                try {
                                    clss = Class.forName(name);
                                    if (!Modifier.isAbstract(clss.getModifiers())
                                            && !clss.isSynthetic()
                                            && !clss.isAnonymousClass()
                                            && !clss.isMemberClass()) {
                                        classes.add(clss);

                                    }
                                } catch (Throwable ex) {
                                    System.err.println("entry.getName() = " + entry.getName());
                                    System.err.println("entry.getName().startsWith(\"crcl\") = " + entry.getName().startsWith("crcl"));
                                    System.err.println("entry = " + entry);
                                    System.err.println("name = " + name);
                                    Logger.getLogger(ObjTableJPanel.class
                                            .getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ObjTableJPanel.class
                                .getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            if (null != is) {
                                is.close();
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ObjTableJPanel.class
                                    .getName()).log(Level.SEVERE, "classpathEntry=" + classpathEntry, ex);
                        }
                    }
                } else {
                    File dir = new File(classpathEntry);
                    classes = addClasses("", dir, classes);
                }
            }
        } catch (Throwable t) {
            System.err.println("name = " + name);
            System.err.println("jar = " + jar);
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, t);
        }
        return classes;
    }

    private Object getObject(String name) throws IllegalAccessException, InvocationTargetException {
        int pindex = name.lastIndexOf('.');
        String endname = name;
        if (pindex > 0) {
            endname = name.substring(pindex + 1);
        }
        if (name.equals("this")) {
            return obj;
        }
        return getObject(getParentObject(name), endname);
    }

    private Object getObject(Object pobj, String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (pobj instanceof List) {
            List<?> l = (List) pobj;
            String indexString = name.substring(name.lastIndexOf('(') + 1, name.lastIndexOf(')'));
            return l.get(Integer.parseInt(indexString));
        }
        Method mget = null;
        Class<?> pobjClass = pobj.getClass();
        try {
            mget = pobjClass.getMethod("get" + name);

        } catch (NoSuchMethodException ex) {
        }
        try {
            if (null == mget) {
                mget = pobjClass.getMethod("is" + name);
            }
        } catch (NoSuchMethodException ex) {
        }
        if (mget == null) {
            if (name.endsWith("]")) {
                int bIndex = name.lastIndexOf("[");
                String indexString = name.substring(bIndex + 1, name.length() - 1);
                int indexVal = Integer.parseInt(indexString);
                return Array.get(pobj, bIndex);
            }
            if (pobjClass.isArray() && name.equals("length")) {
                return Array.getLength(pobj);
            }
            try {
                Field field = pobjClass.getField(name);
                if (Modifier.isPublic(field.getModifiers())) {
                    return field.get(pobj);
                }
            } catch (Exception ex) {
                System.out.println("pobj = " + pobj);
                System.out.println("name = " + name);
                System.out.println("pobjClass = " + pobjClass);
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.err.println("Method to get object for " + name + " does not exist");
            return null;
        }
        Object objectReturnedFromMget = mget.invoke(pobj);
        return objectReturnedFromMget;
    }

    private Object getParentObject(String name) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object pobj = this.obj;
        String pnames[] = name.split("[.]");
        for (int i = 0; i < pnames.length - 1; i++) {
            pobj = getObject(pobj, pnames[i]);
            if (pobj == null) {
                System.err.println("Parent object for " + pnames[i] + " of " + name + "does not exist");
                return null;
            }
        }
        return pobj;
    }

    private Class<?> stringToClass(String type) throws ClassNotFoundException {
        if (type.equals("boolean")) {
            return boolean.class;
        }
        if (type.equals("int")) {
            return int.class;
        }
        if (type.equals("short")) {
            return short.class;
        }
        if (type.equals("long")) {
            return long.class;
        }
        if (type.equals("float")) {
            return float.class;
        }
        if (type.equals("double")) {
            return double.class;
        }
        return Class.forName(type);
    }

    private Object convertToType(Class<?> tclass, String name, Object o) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        if (tclass.equals(boolean.class)) {
            return Boolean.valueOf(o.toString());
        }
        if (tclass.equals(int.class)) {
            return Integer.valueOf(o.toString());
        }
        if (tclass.equals(short.class)) {
            return Short.valueOf(o.toString());
        }
        if (tclass.equals(long.class)) {
            return Long.valueOf(o.toString());
        }
        if (tclass.equals(float.class)) {
            return Float.valueOf(o.toString());
        }
        if (tclass.equals(double.class)) {
            return Double.valueOf(o.toString());
        }
        Object tobj = o;
        if (null != o && !tclass.isAssignableFrom(o.getClass())) {
            String ostring = o.toString();
            Method valueOf = null;

            try {
                valueOf = tclass.getMethod("valueOf", String.class);
            } catch (NoSuchMethodException ex) {
            } catch (SecurityException ex) {
                Logger.getLogger(ObjTableJPanel.class.getName())
                        .log(Level.SEVERE,
                                "SecurityException looking for method valueOf in tclass=" + tclass + ",name=" + name + ",o=" + o,
                                ex);
            }
            if (null != valueOf) {
                tobj = valueOf.invoke(null, new Object[]{ostring});
            } else {
                Constructor<?> constructor = null;

                try {
                    constructor = tclass.getConstructor(String.class);
                    tobj = constructor.newInstance(ostring);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(ObjTableJPanel.class.getName())
                            .log(Level.SEVERE,
                                    "NoSuchMethodException looking for constructor in tclass=" + tclass + ",name=" + name + ",o=" + o,
                                    ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(ObjTableJPanel.class.getName())
                            .log(Level.SEVERE,
                                    "SecurityException looking for constructor in tclass=" + tclass + ",name=" + name + ",o=" + o,
                                    ex);
                }
            }
        }
        return tobj;
    }

    @SuppressWarnings("unchecked")
    private void setObjectForName(String type, String name, Object o) {
        try {
            if (type.startsWith("java.util.List")) {
                return;
            }
            if (name.endsWith(")")) {
                return;
            }
            if (name.endsWith(".length") && o.getClass().isArray()) {
                return;
            }
            if (name.endsWith("]")) {
                int bindex = name.lastIndexOf("[");
                String indexString = name.substring(bindex + 1, name.length() - 1);
                int indexVal = Integer.parseInt(indexString);
                Object pobj = this.getParentObject(name);
                Class<?> tclass = this.stringToClass(type);
                Object tobj = this.convertToType(tclass, name, o);
                try {
                    Array.set(pobj, indexVal, tobj);
                } catch (Exception ex) {
                    System.out.println("type = " + type);
                    System.err.println("name = " + name);
                    System.err.println("pobj.getClass() = " + pobj.getClass());
                    System.err.println("o.getClass() = " + o.getClass());
                    System.err.println("pobj = " + pobj);
                    System.err.println("o = " + o);
                    System.out.println("tclass = " + tclass);
                    System.err.println("tobj = " + tobj);
                    System.err.println("indexVal = " + indexVal);
                    Logger.getLogger(ObjTableJPanel.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
            Object orig_obj = this.getObject(name);
            if (null != orig_obj && orig_obj.equals(o)) {
                return;
            }
            if (null == orig_obj && null == o) {
                return;
            }
            Class<?> tclass = this.stringToClass(type);
            Object tobj = this.convertToType(tclass, name, o);
            Object pobj = this.getParentObject(name);
            if (pobj instanceof List) {
                return;
            }
            String endname = name;
            int pindex = name.lastIndexOf('.');
            if (pindex > 0) {
                endname = endname.substring(pindex + 1);
            }
            Method mset = null;
            Class<?> pobjClass = pobj.getClass();
            if (name.endsWith(".length") && pobjClass.isArray()) {
                return;
            }
            try {
                mset = pobjClass.getMethod("set" + endname, tclass);
            } catch (NoSuchMethodException ex) {
            }
            if (mset == null) {
                if (this.jTable1.getRowCount() == 1) {

                    setObj((T) tobj);
                    return;
                }
                try {
                    //                }
                    Field field = pobjClass.getField(endname);
                    if (Modifier.isPublic(field.getModifiers())
                            && !Modifier.isStatic(field.getModifiers())
                            && !Modifier.isFinal(field.getModifiers())) {
                        field.set(pobj, tobj);
                        return;
                    }
                } catch (NoSuchFieldException ex) {
                    System.out.println("pobjClass = " + pobjClass);
                    System.out.println("pobj = " + pobj);
                    System.out.println("tobj = " + tobj);
                    System.out.println("o = " + o);
                    System.out.println("o.getClass() = " + o.getClass());
                    System.out.println("endname = " + endname);
                    Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.err.println("Method to set " + name + " does not exist");
                return;
            }
            mset.invoke(pobj, new Object[]{tobj});

        } catch (Exception ex) {
            System.err.println("Error in setObjectForName(" + type + "," + name + ", " + o + ")");
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void addObjectToList(String type, String name, Object o) {
        try {
            Class<?> tclass = this.stringToClass(type);
            Object tobj = this.convertToType(tclass, name, o);
            Object pobj = this.getParentObject(name);
            String endname = name;
            int pindex = name.lastIndexOf('.');
            if (pindex > 0) {
                endname = endname.substring(pindex + 1);
            }
            List l = (List) getObject(pobj, endname);
            l.add(tobj);
        } catch (SecurityException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeObjectFromList(String name) {
        try {
            List<?> l = (List) this.getParentObject(name);
            int index1 = name.lastIndexOf(".get(");
            int index2 = name.lastIndexOf(')');
            String indexString = name.substring(index1 + 5, index2);
            int index = Integer.parseInt(indexString);
            l.remove(index);
        } catch (SecurityException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteTableItem() {
        try {
            int row = jTable1.getSelectedRow();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            if (row < 0 || row > tm.getRowCount()) {
                jButtonNew.setEnabled(false);
                jButtonDelete.setEnabled(false);
                return;
            }
            this.updateTableFromObject();
        } catch (Exception ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void setNewTableItem() {
        try {
            int row = jTable1.getSelectedRow();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            if (row < 0 || row > tm.getRowCount()) {
                jButtonNew.setEnabled(false);
                return;
            }
            String type = (String) tm.getValueAt(row, 0);
            String name = (String) tm.getValueAt(row, 1);
            Class clss = this.stringToClass(type);
            if (null == classes) {
                classes = getClasses(customExcludedPathStrings);
            }
            List<Class<?>> availClasses = getAssignableClasses(clss, classes);

            Class ca[] = availClasses.toArray(new Class[availClasses.size()]);
            Class selectedClass;
            if (ca.length < 2) {
               selectedClass=ca[0];
            } else if (ca.length < 4) {
                int selected = JOptionPane.showOptionDialog(this.dialog,
                        "Select class of new " + clss.getCanonicalName(),
                        name + " = new " + clss.getCanonicalName(),
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        ca,
                        null);
                selectedClass = ca[selected];
            } else {
                selectedClass = ListChooserJPanel.choose(dialog, "Select class of new " + clss.getCanonicalName(), ca, null);
//                JDialog classSelectDialog = new JDialog(dialog, true);
//                classSelectDialog.setLayout(new FlowLayout());
//                JComboBox<Class> comboBox = new JComboBox<>(ca);
//                classSelectDialog.add(comboBox);
//                JButton okButton = new JButton("Ok");
//                okButton.addActionListener(e -> classSelectDialog.setVisible(false));
//                classSelectDialog.add(okButton);
//                classSelectDialog.pack();
//                classSelectDialog.setVisible(true);
//                selected = comboBox.getSelectedIndex();
            }
            this.updateObjFromTable();
            Object newo = null;
            newo = selectedClass.getDeclaredConstructor().newInstance();
            this.setObjectForName(type, name, newo);
            this.updateTableFromObject();

        } catch (Exception ex) {
            Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        this.setNewTableItem();
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonAddToListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddToListActionPerformed
        try {
            addToList();
        } catch (Exception ex) {
            Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAddToListActionPerformed

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void addToList() throws NoSuchMethodException, InstantiationException, HeadlessException, InvocationTargetException, ClassNotFoundException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int row = jTable1.getSelectedRow();
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        if (row < 0 || row > tm.getRowCount()) {
            jButtonNew.setEnabled(false);
            return;
        }
        String type = (String) tm.getValueAt(row, 0);
        String name = (String) tm.getValueAt(row, 1);
        String typeparams = getTypeParams(type);
        Class clss = Class.forName(typeparams);
        if (null == classes) {
            classes = getClasses(customExcludedPathStrings);
        }
        List<Class<?>> availClasses = getAssignableClasses(clss, classes);
        Class ca[] = availClasses.toArray(new Class[availClasses.size()]);
        int selected = JOptionPane.showOptionDialog(this.dialog,
                "Select class of new " + clss.getCanonicalName(),
                name + " = new " + clss.getCanonicalName(),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                ca,
                null);
        Object newo = ca[selected].getDeclaredConstructor().newInstance();
        this.updateObjFromTable();
        this.addObjectToList(typeparams, name, newo);
        this.updateTableFromObject();
    }

    private void jButtonRemoveFromListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveFromListActionPerformed
        try {
            int row = jTable1.getSelectedRow();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            if (row < 0 || row > tm.getRowCount()) {
                jButtonNew.setEnabled(false);
                return;
            }
            String name = (String) tm.getValueAt(row, 1);
            this.updateObjFromTable();
            this.removeObjectFromList(name);
            this.updateTableFromObject();

        } catch (Exception ex) {
            Logger.getLogger(ObjTableJPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonRemoveFromListActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        this.deleteTableItem();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonEditMultiLineTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditMultiLineTextActionPerformed

        int row = this.jTable1.getSelectedRow();
        if (row < 0 || row >= this.jTable1.getRowCount()) {
            this.jButtonEditMultiLineText.setEnabled(false);
            return;
        }
        String type = this.jTable1.getValueAt(row, 0).toString();
        String name = this.jTable1.getValueAt(row, 1).toString();
        Object oIn = this.jTable1.getValueAt(row, 2);
        String textIn = oIn == null ? "" : oIn.toString();
        String out = MultiLineStringJPanel.editText(textIn, null, name + ":" + type, true);
        if (null != out) {
            this.setObjectForName(type, name, out);
            this.updateTableFromObject();
        }
    }//GEN-LAST:event_jButtonEditMultiLineTextActionPerformed

    private void jButtonUpdateTableToTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateTableToTextActionPerformed
        updateObjFromTable();
        updateOutText(getObj());
    }//GEN-LAST:event_jButtonUpdateTableToTextActionPerformed

    @SuppressWarnings("unchecked")
    private void jButtonTextToTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTextToTableActionPerformed
        if (null != crclSocket) {
            try {
                String text = jTextAreaOutput.getText();
                CRCLCommandInstanceType instance = crclSocket.stringToCommand(text, true);
                CRCLCommandType cmd = instance.getCRCLCommand();
                if (obj.getClass().isInstance(cmd)) {
                    setObj((T) cmd);
                    updateTableFromObject();
                }
            } catch (Exception ex) {
                Logger.getLogger(ObjTableJPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonTextToTableActionPerformed

    public static List<Class<?>> getAssignableClasses(Class<?> baseClss, List<Class<?>> classes) {
        List<Class<?>> assignableClasses = new ArrayList<>();
        for (Class<?> clss : classes) {
            if (baseClss.isAssignableFrom(clss)) {
                assignableClasses.add(clss);
            }
        }
        return assignableClasses;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddToList;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEditMultiLineText;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JButton jButtonRemoveFromList;
    private javax.swing.JButton jButtonTextToTable;
    private javax.swing.JButton jButtonUpdateTableToText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelColorCode;
    private javax.swing.JPanel jPanelDocumentationOutput;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneDocumentation;
    private javax.swing.JScrollPane jScrollPaneOutput;
    private javax.swing.JTabbedPane jTabbedPanelDocOut;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextAreaOutput;
    private javax.swing.JTextPane jTextPaneDocumentation;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(ObjTableJPanel.class.getName());
}
