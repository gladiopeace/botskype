/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;
import util.StringUtil;

/**
 *
 * @author phat10130059
 */
public class ConvertString {

    public static String convert(String str, String condition, String user) {
        
        List<String> cutString = StringUtil.cutString(str, condition);
        String joinString = StringUtil.joinString(cutString, user);
        
        return joinString;
    }
}
