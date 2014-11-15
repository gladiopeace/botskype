/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.skype.ChatMessage;
import com.skype.Friend;
import com.skype.Skype;
import com.skype.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import util.DateUtil;
import util.ModelTable;
import util.Validate;

/**
 *
 * @author phat10130059
 */
public class SendSmsAuto {

    private static List<String> listSend;
    public static boolean stop = false;

    public static void init() {
        listSend = new ArrayList<String>();
    }

    public static void send(List<String> list, String sms, Integer seconds, ModelTable dmodel, JTable table, boolean langVi) {

        List<String> listSkypeIdFriend = ContactLister.getAllSkypeIDFriend();

        seconds = seconds * 1000;

        if (list != null) {
            for (String skypeId : list) {

                if (stop) {
                    return;
                }

                if (!listSend.contains(skypeId)) {

                    try {

                        User user = Skype.getUser(skypeId);

                        String convertSms = ConvertString.convert(sms, "\\$user\\$", user.getId());

                        String validateSendSMSText = Validate.validateSendSMSText(convertSms);


                        if (!listSkypeIdFriend.contains(skypeId)) {
                            Friend addFriend = Skype.getContactList().addFriend(user, validateSendSMSText);
                            Skype.getContactList().removeFriend(addFriend);
                        } else {
                            ChatMessage send = user.send(validateSendSMSText);
                        }

                        Date time = new Date();

                        dmodel.insertRow(dmodel.getRowCount(), new Object[]{
                                    DateUtil.getHoursToString(time) + ": " + "Send to " + user.getId()
                                });

                        int rowCount = table.getRowCount();

                        for (int i = 0; i < rowCount; i++) {
                            String val = (String) table.getValueAt(i, 2);
                            if (val.equals(skypeId)) {
                                if (langVi) {
                                    table.setValueAt("Xong", i, 7);
                                } else {
                                    table.setValueAt("Finish", i, 7);
                                }
                                break;
                            }
                        }

                        Thread.sleep(seconds);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    listSend.add(skypeId);

                }

            }

            System.out.println("send finish");

            if (langVi) {
                JOptionPane.showMessageDialog(new Demo1(), "Gửi thành công", "Sucess", JOptionPane.DEFAULT_OPTION);
            } else {
                JOptionPane.showMessageDialog(new Demo1(), "Send success", "Sucess", JOptionPane.DEFAULT_OPTION);
            }


        }

    }
}
