/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.User;
import dto.OUser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import util.DateUtil;
import util.ModelTable;

/**
 *
 * @author phat10130059
 */
public class SearchSkypeId {

    public static boolean stopSearchEmailphone = false;

    public static List<OUser> search(String str, String country) {

        List<OUser> list = new ArrayList<OUser>();

        if (!str.equals("")) {
            try {
                User[] searchUsers = Skype.searchUsers(str);

                for (User user : searchUsers) {

                    System.out.println(user.getCountry());


                    if (country.equals("All") || country.equals("Tất cả")) {

                        OUser oUser = new OUser();
                        oUser.setSkypeId(user.getId());
                        oUser.setStatus(user.getStatus().toString());
                        oUser.setFullName(user.getFullName());
                        oUser.setCountry(user.getCountry());
                        oUser.setSex(user.getSex().toString());
                        if (user.getBirthDay() != null) {
                            String date = DateUtil.getDateToString(user.getBirthDay());
                            oUser.setBirthDay(date);

                        }

                        list.add(oUser);
                    } else {
                        if (country.trim().equals(user.getCountry().trim())) {
                            OUser oUser = new OUser();
                            oUser.setSkypeId(user.getId());
                            oUser.setStatus(user.getStatus().toString());
                            oUser.setFullName(user.getFullName());
                            oUser.setCountry(user.getCountry());
                            oUser.setSex(user.getSex().toString());
                            if (user.getBirthDay() != null) {
                                String date = DateUtil.getDateToString(user.getBirthDay());
                                oUser.setBirthDay(date);

                            }
                            list.add(oUser);
                        }
                    }

                }

            } catch (SkypeException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public static List<String> searchByListEmail(List<String> listEmail, ModelTable dmodel, JLabel jLabel14, JTable table3, boolean langVi) {
        List<String> list = new ArrayList<String>();

        for (String email : listEmail) {

            if (stopSearchEmailphone) {
                break;
            }
            
            try {
                jLabel14.setText(email);

                User[] searchUsers = Skype.searchUsers(email);
                for (User user : searchUsers) {

                    dmodel.insertRow(dmodel.getRowCount(), new Object[]{
                                user.getId(), email
                            });

//                    list.add(user.getId());
                }

                int rowCount = table3.getRowCount();

                for (int i = 0; i < rowCount; i++) {
                    String val = (String) table3.getValueAt(i, 0);
                    if (val.equals(email)) {
                        if (langVi) {
                            table3.setValueAt("Xong", i, 1);
                        } else {
                            table3.setValueAt("Finish", i, 1);
                        }
                        break;
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return list;
    }
}
