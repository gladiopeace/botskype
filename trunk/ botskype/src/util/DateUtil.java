/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author phat10130059
 */
public class DateUtil {
    public static String getHoursToString(Date date) {
        DateFormat df = new SimpleDateFormat("h:mm a");

        String hours = df.format(date);

        return hours;
    }
    
    public static String getDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        String d = df.format(date);

        return d;
    }
}
