/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

//import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ModelTable extends DefaultTableModel {

    Class[] types;
    boolean[] canEdit;

    public boolean[] getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean[] canEdit) {
        this.canEdit = canEdit;
    }

    public Class[] getTypes() {
        return types;
    }

    public void setTypes(Class[] types) {
        this.types = types;
    }

    public ModelTable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }
}
