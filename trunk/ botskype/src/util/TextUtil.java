/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author phat10130059
 */
public class TextUtil {

    public static void write(String path, List<String> list, JFileChooser fileChooser, String message) {
        FileWriter fw = null;
        try {

            File file = new File(path);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }


            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);


            for (String str : list) {
                bw.write(str);
                bw.newLine();
            }

            bw.close();

//            JOptionPane.showMessageDialog(fileChooser, "Save SkypeIDs success", "Sucess", JOptionPane.DEFAULT_OPTION);

            JOptionPane.showMessageDialog(fileChooser, message, "Sucess", JOptionPane.DEFAULT_OPTION);
            
            System.out.println("Save success");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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

        FileReader fr = null;

        try {

            fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                if (!list.contains(sCurrentLine)) {
                    list.add(sCurrentLine);
                }


            }

            br.close();

            System.out.println("open success");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return list;
    }
}
