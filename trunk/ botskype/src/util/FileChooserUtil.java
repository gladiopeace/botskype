/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import dto.OUser;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author phat10130059
 */
public class FileChooserUtil {

    public static void write(List<String> allRowValue, JFrame jframe, String message) {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter(".xlsx", "xlsx", "Excel 2013");


        fileChooser.setFileFilter(filter2);
        fileChooser.setFileFilter(filter);


        fileChooser.setDialogTitle("Save");

        int userSelection = fileChooser.showSaveDialog(jframe);


        if (userSelection == JFileChooser.APPROVE_OPTION) {


            try {

                String path = "";

                File fileToSave = fileChooser.getSelectedFile();

                String name = fileToSave.getName();

                String ext = fileChooser.getFileFilter().getDescription();

                if (name.endsWith(".txt")) {
                    path = fileToSave.getAbsolutePath();
                } else if (name.endsWith(".xlsx")) {
                    path = fileToSave.getAbsolutePath();
                } else {
                    if (ext.equals(".txt")) {
                        path = fileToSave.getAbsolutePath() + ext;
                    } else if (ext.equals(".xlsx")) {
                        path = fileToSave.getAbsolutePath() + ext;
                    }
                }

                if (ext.equals(".txt")) {

//                    List<String> allRowValue = TableUtil.getAllRowValue(table);

                    TextUtil.write(path, allRowValue, fileChooser, message);

                } else if (ext.equals(".xlsx")) {

//                    List<String> allRowValue = TableUtil.getAllRowValue(table);

                    ExcelUtil.write(allRowValue, path, fileChooser);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static List<String> read(JFrame jframe) {
        List<String> list = null;

        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter(".xlsx", "xlsx", "Excel 2013");

        fileChooser.setFileFilter(filter2);
        fileChooser.setFileFilter(filter);

        fileChooser.setDialogTitle("Open");

        int userSelection = fileChooser.showOpenDialog(jframe);


        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {

                String path = "";

                File fileToOpen = fileChooser.getSelectedFile();

                String name = fileToOpen.getName();

                if (name.endsWith(".txt")) {

                    path = fileToOpen.getAbsolutePath();
                    
                    list = TextUtil.read(path, fileChooser);
                    
                    
                } else if (name.endsWith(".xlsx")) {
                    
                    path = fileToOpen.getAbsolutePath();
                    
                    list = ExcelUtil.read(path, fileChooser);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return list;


    }
    
    public static void writeList(List<OUser> list, JFrame jframe) {
        JFileChooser fileChooser = new JFileChooser();

//        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt", "text");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter(".xlsx", "xlsx", "Excel 2013");


        fileChooser.setFileFilter(filter2);
//        fileChooser.setFileFilter(filter);


        fileChooser.setDialogTitle("Save");

        int userSelection = fileChooser.showSaveDialog(jframe);


        if (userSelection == JFileChooser.APPROVE_OPTION) {


            try {

                String path = "";

                File fileToSave = fileChooser.getSelectedFile();

                String name = fileToSave.getName();

                String ext = fileChooser.getFileFilter().getDescription();

               if (name.endsWith(".xlsx")) {
                    path = fileToSave.getAbsolutePath();
                } else {
                   if (ext.equals(".xlsx")) {
                        path = fileToSave.getAbsolutePath() + ext;
                    }
                }

               if (ext.equals(".xlsx")) {

//                    List<String> allRowValue = TableUtil.getAllRowValue(table);

                    ExcelUtil.writeListSkype(list, path, fileChooser);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
