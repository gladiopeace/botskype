/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import main.Demo1;
import dto.OUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author phat10130059
 */
public class ExcelUtil {

//    public static void main(String[] args) {
//        List<String> read = read("skyId.xlsx", null);
//        for (String string : read) {
//            System.out.println(string);
//        }
//    }
    public static void write(List<String> list, String path, JFileChooser fileChooser) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Skype IDs");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();


        for (int i = 0; i < list.size(); i++) {
            data.put(String.valueOf(i + 1), new Object[]{list.get(i)});
        }

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(fileChooser, "Save SkypeIDs success", "Sucess", JOptionPane.DEFAULT_OPTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> read(String path, JFileChooser fileChooser) {
        List<String> list = new ArrayList<String>();

        File file = new File(path);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            JOptionPane.showMessageDialog(fileChooser, "File is not exist", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            FileInputStream fileWrite = new FileInputStream(file);

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(fileWrite);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue() + "t");
                            if (!list.contains(cell.getStringCellValue())) {
                                list.add(cell.getStringCellValue());
                                break;
                            }
                    }

                }
//                System.out.println("");
            }
            fileWrite.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static void writeListSkype(List<OUser> list, String path, JFileChooser fileChooser) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Skype");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();

        data.put("1", new Object[]{"Skype ID", "Full Name", "Country", "Sex", "Birth Day"});
        for (int i = 0; i < list.size(); i++) {
            data.put(String.valueOf(i + 2), new Object[]{list.get(i).getSkypeId(), list.get(i).getFullName(),
                        list.get(i).getCountry(), list.get(i).getSex(), list.get(i).getBirthDay()
                    });
        }

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(fileChooser, "Save list success", "Sucess", JOptionPane.DEFAULT_OPTION);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new Demo1(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }
}
