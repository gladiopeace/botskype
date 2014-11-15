/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phat10130059
 */
public class TableUtil {

    public static void sortTable(ModelTable dmodel, JTable table, TableRowSorter<ModelTable> sorter) {
        sorter = new TableRowSorter<ModelTable>(dmodel);
        table.setRowSorter(sorter);

    }

    public static void clearTable(ModelTable dmodel) {
        while (dmodel.getRowCount() != 0) {
            dmodel.removeRow(dmodel.getRowCount() - 1);
        }
    }

    public static List<String> getAllRowValue(JTable table, int column) {
        List<String> list = new ArrayList<String>();

        int row = table.getRowCount();
        
        for (int j = 0; j < row; j++) {
            list.add(table.getValueAt(j, column).toString());
        }
        return list;
    }
    
    public static void setValueColumn(JTable table, int column, Object value){
        int rowCount = table.getRowCount();
        
        for (int i = 0; i < rowCount; i++) {
            
            table.setValueAt(value, i, column);
            
        }
    }
}
