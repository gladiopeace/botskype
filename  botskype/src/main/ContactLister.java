/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author phat10130059
 */
import com.skype.ContactList;
import com.skype.Friend;
import com.skype.Skype;
import com.skype.SkypeException;
import dto.OFriend;
import java.util.ArrayList;
import java.util.List;

public class ContactLister {

    public static List<OFriend> getAllFriend() {

        List<OFriend> listFriend = new ArrayList<OFriend>();

        try {
            ContactList list = Skype.getContactList();



            Friend fr[] = list.getAllFriends();

            for (int i = 0; i < fr.length; i++) {
                Friend f = fr[i];

                if (!f.getId().contains("@chat.facebook.com")) {
                    //Getting the friend ID

                    OFriend oFriend = new OFriend();
                    oFriend.setAccount(f.getId());
                    oFriend.setFullName(f.getFullName());
                    oFriend.setStatus(f.getStatus().toString());
                    oFriend.setCountry(f.getCountry());
                    oFriend.setSex(f.getSex().toString());


                    listFriend.add(oFriend);

                }


            }
        } catch (SkypeException ex) {

//            System.out.println(ex.getMessage());

            ex.printStackTrace();
        }

        return listFriend;

    }

    public static List<String> getAllSkypeIDFriend() {

        List<String> listSkypeId = new ArrayList<String>();

        try {
            ContactList list = Skype.getContactList();



            Friend fr[] = list.getAllFriends();

            for (int i = 0; i < fr.length; i++) {
                Friend f = fr[i];

                if (!f.getId().contains("@chat.facebook.com")) {
                    //Getting the friend ID


                    listSkypeId.add(f.getId());

                }


            }
        } catch (SkypeException ex) {
            ex.printStackTrace();
        }

        return listSkypeId;

    }
//    public static void main(String[] args) throws SkypeException, InterruptedException {
//        List<OFriend> allFriend = ContactLister.getAllFriend();
//        for (OFriend oFriend : allFriend) {
//            System.out.println(oFriend);
//        }
//    }
}