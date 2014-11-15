/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dto.OSearchWeb;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.StringUtil;
import util.Validate;

/**
 *
 * @author phat10130059
 */
public class SearchPhoneEmailSkype {

    public static List<OSearchWeb> search(String url, boolean phone, boolean email, boolean skype) {

        List<OSearchWeb> list = new ArrayList<OSearchWeb>();
        try {
            Document doc = Jsoup.connect(url).get();
//            System.out.println(doc);
            Elements allElements = doc.getAllElements();
            //        System.out.println(allElements.);


            for (Element e : allElements) {
//            System.out.println(e.text());

                if (!e.text().equals("")) {

                    //search phone on web page
                    if (phone) {

                        List<String> listFind = Validate.validatePhone1(e.text());

                        for (String str : listFind) {

                            if (!Validate.checkExist(list, str)) {

                                OSearchWeb oSearchWeb = new OSearchWeb();
                                oSearchWeb.setAccount(str);
                                oSearchWeb.setType("Phone");
                                list.add(oSearchWeb);

                            }


                        }
                    }

                    //search email on web page

                    if (email) {

                        List<String> listFindEmail = Validate.validateEmail(e.text());

                        for (String str : listFindEmail) {

                            if (!Validate.checkExist(list, str)) {

                                OSearchWeb oSearchWeb = new OSearchWeb();
                                oSearchWeb.setAccount(str);
                                oSearchWeb.setType("Email");
                                list.add(oSearchWeb);

                            }
                        }

                    }



                }
            }

            if (skype) {

                Elements nextLinks = doc.select("a[href^=skype:]");

//                List<String> list = new ArrayList<String>();

                for (Element e : nextLinks) {
                    //            System.out.println(e.text());
                    String link = e.attr("href");

                    String substring = link.substring(6, link.length());

                    List<String> cutString = StringUtil.cutString(substring, "\\?chat");
                    String nickSkype = cutString.get(0);

                    if (!Validate.checkExist(list, nickSkype)) {
                        
                        OSearchWeb oSearchWeb = new OSearchWeb();
                        oSearchWeb.setAccount(nickSkype);
                        oSearchWeb.setType("Skype");
                        list.add(oSearchWeb);

                    }

//                    list.add(cutString.get(0));

                }
            }


        } catch (Exception ex) {
//            ex.printStackTrace();
        }

        return list;
    }
//    public static void main(String[] args) {
//        List<OSearchWeb> search = search("http://stackoverflow.com/questions/8204680/java-regex-email", true, true);
//        for (OSearchWeb oSearchWeb : search) {
//            System.out.println(oSearchWeb.getAccount());
//        }
//    }
}
