/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.User;

/**
 *
 * @author phat10130059
 */
public class CheckExistSkypeIdOnServer {

    public static boolean checkExist(String nick) {

        try {
            User[] searchUsers = Skype.searchUsers(nick);

            for (User user : searchUsers) {

                if (nick.equals(user.getId())) {
                    return true;
                }

            }

        } catch (SkypeException ex) {
            return false;
        }

        return false;
    }
}
