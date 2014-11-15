/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dto.OSearchWeb;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author phat10130059
 */
public class Validate {

    //043.974.3410
    public static final Pattern p1 = Pattern.compile("(\\d{3}[\\.]\\d{3}[\\.]\\d{4})+");
    //0969.061.788
    public static final Pattern p2 = Pattern.compile("(\\d{4}[\\.]\\d{3}[\\.]\\d{3})+");
    //043-974-3410
    public static final Pattern p3 = Pattern.compile("(\\d{3}[-]\\d{3}[-]\\d{4})+");
    //0969-061-788
    public static final Pattern p4 = Pattern.compile("(\\d{4}[-]\\d{3}[-]\\d{3})+");
    //043 974 3410
    public static final Pattern p5 = Pattern.compile("(\\d{3}[\\s]\\d{3}[\\s]\\d{4})+");
    //0969 061 788
    public static final Pattern p6 = Pattern.compile("(\\d{4}[\\s]\\d{3}[\\s]\\d{3})+");
    //(043) 974.3410
    public static final Pattern p7 = Pattern.compile("(\\(\\d{3}\\)\\s\\d{3}\\.\\d{4})+");
    //(043) 974-3410
    public static final Pattern p8 = Pattern.compile("(\\(\\d{3}\\)\\s\\d{3}-\\d{4})+");
    //(043) 974 3410
    public static final Pattern p9 = Pattern.compile("(\\(\\d{3}\\)\\s\\d{3}\\s\\d{4})+");
    //(04) 7308 0008
    public static final Pattern p10 = Pattern.compile("(\\(\\d{2}\\)\\s\\d{4}\\s\\d{4})+");
    //(84-4) 3512 1806
    public static final Pattern p11 = Pattern.compile("(\\(\\d{2}\\-\\d{1}\\)\\s\\d{4}\\s\\d{4})+");
//    //08-38489845
//    public static final Pattern p12 = Pattern.compile("(\\d{2}-\\d{8})+");
    //0936 392898
    public static final Pattern p13 = Pattern.compile("(\\d{4} \\d{6})+");
    //0988 54 3456
    public static final Pattern p14 = Pattern.compile("(\\d{4} \\d{2} \\d{4})+");
    //0123.888.0123
    public static final Pattern p15 = Pattern.compile("(\\d{4}\\.\\d{3}\\.\\d{4})+");
    //08 7300 8889
    public static final Pattern p16 = Pattern.compile("(\\d{2}\\s\\d{4}\\s\\d{4})+");
    //0123 888 0123
    public static final Pattern p17 = Pattern.compile("(\\d{4}[\\s]\\d{3}[\\s]\\d{4})+");

    public static List<String> validatePhone1(String phone) {

        List<String> list = new ArrayList<String>();

        Matcher m = p1.matcher(phone);
        while (m.find()) {
            //043.974.3410
            String t = "";
            List<String> cutString = StringUtil.cutString(m.group(), "\\.");
            for (String str : cutString) {
                t = t + str;
            }
            list.add(t);
        }

        m = p2.matcher(phone);
        while (m.find()) {
            //0969.061.788
            String t = "";
            List<String> cutString = StringUtil.cutString(m.group(), "\\.");
            for (String str : cutString) {
                t = t + str;
            }
            list.add(t);
        }

        m = p3.matcher(phone);
        while (m.find()) {
            //043-974-3410
            String t = "";
            List<String> cutString = StringUtil.cutString(m.group(), "-");
            for (String str : cutString) {
                t = t + str;
            }
            list.add(t);
        }

        m = p4.matcher(phone);
        while (m.find()) {
            //0969-061-788
            String t = "";
            List<String> cutString = StringUtil.cutString(m.group(), "-");
            for (String str : cutString) {
                t = t + str;
            }
            list.add(t);
        }

        m = p5.matcher(phone);
        while (m.find()) {
            //043 974 3410
            String t = "";
            List<String> cutString = StringUtil.cutString(m.group(), " ");
            for (String str : cutString) {
                t = t + str;
            }
            list.add(t);
        }

        m = p6.matcher(phone);
        while (m.find()) {
            //0969 061 788
            String t = "";
            List<String> cutString = StringUtil.cutString(m.group(), " ");
            for (String str : cutString) {
                t = t + str;
            }
            list.add(t);
        }

        m = p7.matcher(phone);
        while (m.find()) {
            //(043) 974.3410
            String str = m.group().trim();
            String substring = str.substring(1, 4) + str.substring(6, 9) + str.substring(10, 14);
            list.add(substring);
        }
        m = p8.matcher(phone);
        while (m.find()) {
            //(043) 974-3410
            String str = m.group().trim();
            String substring = str.substring(1, 4) + str.substring(6, 9) + str.substring(10, 14);
            list.add(substring);
        }
        m = p9.matcher(phone);
        while (m.find()) {
            //(043) 974 3410
            String str = m.group().trim();
            String substring = str.substring(1, 4) + str.substring(6, 9) + str.substring(10, 14);
            list.add(substring);
        }
        m = p10.matcher(phone);
        while (m.find()) {
            //(04) 7308 0008
            String str = m.group().trim();
            String substring = str.substring(1, 3) + str.substring(5, 9) + str.substring(10, 14);
            list.add(substring);
        }
        m = p11.matcher(phone);
        while (m.find()) {
            //(84-4) 3512 1806
            String str = m.group().trim();
            String substring = "+" + str.substring(1, 3) + str.substring(4, 5) + str.substring(7, 11) + str.substring(12, 16);
            list.add(substring);
        }
//        m = p12.matcher(phone);
//        while (m.find()) {
//            list.add(m.group());
//        }
        m = p13.matcher(phone);
        while (m.find()) {
            //0936 392898
            String str = m.group().trim();
            String substring = str.substring(0, 4) + str.substring(5, 11);
            list.add(substring);
        }
        m = p14.matcher(phone);
        while (m.find()) {
            //0988 54 3456
            String str = m.group().trim();
            String substring = str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 12);
            list.add(substring);
        }
        m = p15.matcher(phone);
        while (m.find()) {
            //0123.888.0123
            String t = "";
            List<String> cutString = StringUtil.cutString(m.group(), "\\.");
            for (String str : cutString) {
                t = t + str;
            }
            list.add(t);

        }
        m = p16.matcher(phone);
        while (m.find()) {
            //08 7300 8889
            String t = "";
            List<String> cutString = StringUtil.cutString(m.group(), " ");
            for (String str : cutString) {
                t = t + str;
            }
            list.add(t);

        }
        m = p17.matcher(phone);
        while (m.find()) {
            //0123 888 0123
            String t = "";
            List<String> cutString = StringUtil.cutString(m.group(), " ");
            for (String str : cutString) {
                t = t + str;
            }
            list.add(t);
        }


        return list;
    }

    public static List<String> validateEmail(String email) {
        List<String> list = new ArrayList<String>();

        Pattern p = Pattern.compile("([A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6})+", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);

        while (m.find()) {
//            System.out.println(m.group());
            list.add(m.group());
        }
        return list;
    }

    public static boolean checkExist(List<OSearchWeb> list, String strTmp) {

        for (OSearchWeb str : list) {
            if (str.getAccount().equals(strTmp)) {
                return true;
            }
        }

        return false;
    }

    public static boolean validateURL(String url, String head, String name) {
//        Pattern p = Pattern.compile("^" + head + "//[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*" + name + "*");
        Pattern p = Pattern.compile("^" + head + "//" + name + "/*");
        Matcher m = p.matcher(url);
        if (m.find()) {
//            System.out.println(m.group());
            return true;
        }

        return false;
    }

    public static String validateSendSMSText(String sms) {
        String tmpLast = "";
        String tmpFirst = "";

//        String sms = "{dai phat,le hang | binh tay | binh chau} hi : $user$ . today i send";


        if (sms.contains("{") && sms.contains("}")) {

            boolean endsWith = sms.endsWith("}");
            boolean startsWith = sms.startsWith("{");

            if (endsWith) {
                sms = sms + " ";
            }

            if (startsWith) {
                sms = " " + sms;
            }



            List<String> cutString = StringUtil.cutString(sms, "\\}");
            tmpLast = cutString.get(1);


            List<String> cutString1 = StringUtil.cutString(cutString.get(0), "\\{");
            tmpFirst = cutString1.get(0);

//        System.out.println(tmpFirst + "" +tmpLast);


            if (cutString1.size() >= 2) {
                List<String> catalogue = StringUtil.cutString(cutString1.get(1), "\\|");

                Random randomGenerator = new Random();
                int index = randomGenerator.nextInt(catalogue.size());
                String item = catalogue.get(index);

//                System.out.println(tmpFirst + item + tmpLast);
                sms = tmpFirst + item + tmpLast;
            }

        }

        return sms;
    }
    
    public static boolean validatePhone(String phone) {
        
        Pattern p = Pattern.compile("\\+\\d{8,20}");
        Matcher m = p.matcher(phone);
        if (m.find()) {
            return true;
        }

        return false;
    }
    
    public static boolean validateEmail2(String email) {
        
        Pattern p = Pattern.compile("([A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6})+", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        if (m.find()) {
            return true;
        }

        return false;
    }

//    public static void main(String[] args) {
//        boolean validateEmail2 = validateEmail2("pahjdf@asdf.vn");
//        System.out.println(validateEmail2);
//    }
}
