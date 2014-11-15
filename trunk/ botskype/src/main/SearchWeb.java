/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dto.OSearchWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.ModelTable;
import util.StringUtil;
import util.Validate;

/**
 *
 * @author phat10130059
 */
public class SearchWeb {

    public static boolean stop = false;
    private static List<String> listError;
    private static List<String> visitedUrls; // Store all links you've already visited
    static Set<String> ignore = new HashSet<String>(); // Store all keywords you want ignore
    static List<String> allow = new ArrayList<String>();
    private static List<String> listPhoneEmail;

//    public static void main(String[] args) throws Exception {
////        search("http://enbac.com/");
//    }
    public static void init() {
        visitedUrls = new ArrayList<String>(); // Store all links you've already visited
        listError = new ArrayList<String>();
        listPhoneEmail = new ArrayList<String>();
    }

    public static void search(String web, ModelTable model, JLabel txtLinkShow, boolean phone, boolean email, boolean skype) {

        ignore.add("twitter.com");
        ignore.add("facebook.com");
        ignore.add("google.com");
        ignore.add("yahoo.com");
        ignore.add("youtube.com");
        ignore.add("linkedin.com");

        allow = StringUtil.cutString(web, "/");

        visitUrl(web, model, txtLinkShow, phone, email, skype);
    }

    public static void visitUrl(String url, ModelTable model, JLabel txtLinkShow, boolean phone, boolean email, boolean skype) {

        if (stop) {
//            System.out.println("stop");
            return;
        } else {



            url = url.toLowerCase(); // now its case insensitive

            if (url.startsWith("http")) {

                if (!listError.contains(url)) {
                    if (!visitedUrls.contains(url)) // Do this only if not visted yet
                    {
                        try {
                            Document doc = Jsoup.connect(url).get(); // Connect to Url and parse Document


                            if (url.length() > 50) {
                                String substring = url.substring(0, 50);
                                txtLinkShow.setText(substring + "...");
                            } else {
                                txtLinkShow.setText(url);
                            }


                            /* ... Select your Data here ... */

                            Elements nextLinks = doc.select("a[href]"); // Select next links - add more restriction!

                            for (Element next : nextLinks) // Iterate over all Links
                            {

                                if (stop) {
                                    break;
                                }

                                final String link = next.absUrl("href").toLowerCase();

                                boolean skip = false; // If false: parse the url, if true: skip it


                                for (String s : ignore) // Iterate over all ignored keywords - maybe there's a better solution for this
                                {
                                    if (link.contains(s)) // If the url contains ignored keywords it will be skipped
                                    {
                                        skip = true;
                                        break;
                                    }
                                }

                                if (!skip) {

                                    if (Validate.validateURL(link, allow.get(0), allow.get(2))) {

                                        if (!visitedUrls.contains(link)) {
                                            try {
                                                Document docCheck = Jsoup.connect(link).get();

                                                visitedUrls.add(link);

                                                List<OSearchWeb> search = SearchPhoneEmailSkype.search(link, phone, email, skype);
                                                for (OSearchWeb phoneEmail : search) {

                                                    if (!listPhoneEmail.contains(phoneEmail.getAccount())) {

                                                        listPhoneEmail.add(phoneEmail.getAccount());

                                                        model.insertRow(model.getRowCount(), new Object[]{
                                                                    listPhoneEmail.size(), phoneEmail.getAccount(),
                                                                    phoneEmail.getType(),
                                                                    "<HTML>"
                                                                    + "<a href=\"" + link + "\">" + docCheck.title() + "</a>"
                                                                });

                                                        System.out.println(docCheck.title());
                                                        System.out.println(link);
                                                        System.out.println(phoneEmail.getAccount());
                                                        System.out.println(phoneEmail.getType());
                                                    }
                                                }



                                                visitUrl(link, model, txtLinkShow, phone, email, skype); // Recursive call for all next Links
                                            } catch (Exception e) {
                                            }
                                        }
                                    }
                                }



                            }
                        } catch (IOException ex) {

                            listError.add(url);
                            visitedUrls.remove(url);
//                        ex.printStackTrace();

                        }
                    } else {

                        try {
                            Document doc = Jsoup.connect(url).get(); // Connect to Url and parse Document
                            if (url.length() > 50) {
                                String substring = url.substring(0, 50);
                                txtLinkShow.setText(substring + "...");
                            } else {
                                txtLinkShow.setText(url);
                            }

                            /* ... Select your Data here ... */

                            Elements nextLinks = doc.select("a[href]"); // Select next links - add more restriction!

                            for (Element next : nextLinks) // Iterate over all Links
                            {

                                if (stop) {
                                    break;
                                }

                                String link = next.absUrl("href").toLowerCase();


                                boolean skip = false; // If false: parse the url, if true: skip it


                                for (String s : ignore) // Iterate over all ignored keywords - maybe there's a better solution for this
                                {
                                    if (link.contains(s)) // If the url contains ignored keywords it will be skipped
                                    {
                                        skip = true;
                                        break;
                                    }
                                }

                                if (!skip) {
                                    if (Validate.validateURL(link, allow.get(0), allow.get(2))) {
                                        if (!visitedUrls.contains(link)) {
                                            try {
                                                Document docCheck = Jsoup.connect(link).get();

                                                visitedUrls.add(link);

                                                List<OSearchWeb> search = SearchPhoneEmailSkype.search(link, phone, email, skype);
                                                for (OSearchWeb phoneEmail : search) {

                                                    if (!listPhoneEmail.contains(phoneEmail.getAccount())) {

                                                        listPhoneEmail.add(phoneEmail.getAccount());

                                                        model.insertRow(model.getRowCount(), new Object[]{
                                                                    listPhoneEmail.size(), phoneEmail.getAccount(),
                                                                    phoneEmail.getType(),
                                                                    "<HTML>"
                                                                    + "<a href=\"" + link + "\">" + docCheck.title() + "</a>"
                                                                });

                                                        System.out.println(docCheck.title());
                                                        System.out.println(link);
                                                        System.out.println(phoneEmail.getAccount());
                                                        System.out.println(phoneEmail.getType());
                                                    }
                                                }


                                                visitUrl(link, model, txtLinkShow, phone, email, skype); // Recursive call for all next Links

                                            } catch (Exception e) {
                                            }


                                        }
                                    }
                                }


                            }
                        } catch (IOException ex) {

                            listError.add(url);
                            visitedUrls.remove(url);

//                        ex.printStackTrace();

                        }

                    }
                }



            }
        }

    }
}
