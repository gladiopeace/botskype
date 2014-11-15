/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phat10130059
 */
public class OpenBrowser {

    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openWebpage(String strUrl) {
        try {
            URL urL = new URL(strUrl);

            openWebpage(urL.toURI());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        try {
//            openWebpage(new URL("http://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button"));
//        } catch (MalformedURLException ex) {
//            ex.printStackTrace();
//        }
//    }
}
