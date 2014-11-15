/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phat10130059
 */
public class StringUtil {

//    public static void main(String[] args) {
//        List<String> cutString = cutString("$user$ haha gui $user$ nene keke $user$ binh , sen : $user$ ", "\\$user\\$");
//        String joinString = joinString(cutString, "phat");
//        System.out.println(joinString);
//    }

    public static List<String> cutString(String str, String condition) {

        List<String> tmpList = new ArrayList<String>();

        for (String retval : str.split(condition)) {
            tmpList.add(retval);
        }

        return tmpList;

    }

    public static String joinString(List<String> list, String join) {

        String tmp = "";

        for (int i = 0; i < list.size(); i++) {

            if (i != (list.size() - 1)) {
                tmp = tmp + list.get(i) + join;
            } else {
                tmp = tmp + list.get(i);
            }


        }
        return tmp;
    }
}
