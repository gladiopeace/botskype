/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

//import db.DatabaseConnection1;
import db.DatabaseConnection2;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author phat10130059
 */
public class CheckDate {

    private static DatabaseConnection2 db = new DatabaseConnection2();

    public static boolean validate() {


        Date dateNow = new Date();

        try {
            Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-20");
            System.out.println(dateEnd.toString());

            db.makeConnection();

            String sql = "select CURDATE() as date";
            ResultSet rs = db.executeQuery(sql, new String[]{});
            if (rs.next()) {
                dateNow = rs.getDate("date");
                System.out.println(dateNow);
            }

            if (dateEnd.after(dateNow)) {
                System.out.println("true");
                return true;
            } else {
                System.out.println("false");
                return false;
            }



        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection();
        }

        return false;
    }
}
