/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.User;
import dto.OFriend;
import dto.OUser;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import util.ComboboxUtil;
import util.DateUtil;
import util.FileChooserUtil;
import util.ModelTable;
import util.ReadWriteFile;
import util.TableUtil;
import util.Validate;

/**
 *
 * @author phat10130059
 */
public class Demo1 extends javax.swing.JFrame {

    /**
     * Creates new form Demo1
     */
    private ModelTable dmodel1;
    public ModelTable dmodel2;
    public ModelTable dmodel3;
    public ModelTable dmodel4;
    public ModelTable dmodel5;
    private ModelTable dmodel6;
    public ModelTable dmodel7;
    private TableRowSorter<ModelTable> sorter;
    public SearchWeb searchWeb;
    public SendSmsAuto sendSmsAuto;
    //language
    private Properties prop = new Properties();
    public boolean langVi;
    private InputStream input = null;
    //text language
    //tab1
    private String LbMessage;
    private String LbSeconds;
    private String LbSkypeId;
    private String BtSendSMS;
    private String BtContinueSMS;
    private String BtPauseSMS;
    private String BtStopSMS;
    private String BtAddSkypeId;
    private String BtImportSkypeId;
    private String BtSaveListSkypeId;
    //tab2
    private String LbSearchAllSkype;
    private String LbCountry;
    private String BtSearchAllSkype;
    private String BtSaveIdSearchAllSkype;
    //tab3
    private String BtImportEmailOrPhone;
    private String BtSearchSkypeId;
    private String BtStop;
    //tab4
    private String BtSearchWeb;
    private String CbPhone;
    private String BtSavePhone;
    private String BtSaveEmail;
    private String BtSaveSkype;
    private String BtSaveAll;
    private String BtClearSkypeId;
    //tab5
    private String BtSearchFriend;
    //tab6
    private String LbPhone;
    //tab logs
    private String BtClearLog;
    private String BtExportLog;
    //header tabpanel
    private String Tab1;
    private String Tab2;
    private String Tab3;
    private String Tab4;
    private String Tab5;
    private String Tab6;
    //table
    private String No;
    private String Status;
    private String SkypeID;
    private String FullName;
    private String Country;
    private String Sex;
    private String BirthDay;
    private String EmailOrPhone;
    private String Account;
    private String Result;
    private String Text;
    private String Send;
    private String Logs;
    private String Search;

    public Demo1() {
        //check file
        File file = new File("lang.phat");
        File file2 = new File("lange_vi.properties");
        File file3 = new File("lange_en.properties");

        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "File lang.phat is not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        if (!file2.exists()) {
            JOptionPane.showMessageDialog(this, "File lange_vi is not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        if (!file3.exists()) {
            JOptionPane.showMessageDialog(this, "File lange_en is not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        //check language
        String rLang = ReadWriteFile.read("lang.phat");
        if (rLang.equals("vi")) {
            langVi = true;
            initLanguage("lange_vi.properties");
        } else if (rLang.equals("en")) {
            langVi = false;
            initLanguage("lange_en.properties");
        }


//        if (!CheckDate.validate()) {
//            System.out.println("Date off");
//            System.exit(0);
//        }


        creatTable1();
        creatTable2();
        creatTable3();
        creatTable4();
        creatTable5();
        creatTable6();
        creatTable7();

        initComponents();

        initTable1();
        initTable2();
        initTable3();
        initTable4();
        initTable5();
        initTable6();
        initTable7();


        initCompobox();
        initSpinner();
        initRadioButton();

        initTabPanel();
        //remove tab search website
//        jTabbedPane1.remove(3);


        setSize(735, 690);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("/image/favicon.png")).getImage());
        this.setTitle("Skype Ads");

        try {
            boolean running = Skype.isRunning();
            if (!running) {
                JOptionPane.showMessageDialog(this, "Skype is not running\nApplication will exit!", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initLanguage(String file) {
        try {
            input = new FileInputStream(file);
            Reader reader = new InputStreamReader(input, "UTF-8");

            // load a properties file
            prop.load(reader);

            //tab1
            LbMessage = prop.getProperty("LbMessage");
            LbSeconds = prop.getProperty("LbSeconds");
            LbSkypeId = prop.getProperty("LbSkypeId");
            BtSendSMS = prop.getProperty("BtSendSMS");
            BtContinueSMS = prop.getProperty("BtContinueSMS");
            BtPauseSMS = prop.getProperty("BtPauseSMS");
            BtStopSMS = prop.getProperty("BtStopSMS");
            BtAddSkypeId = prop.getProperty("BtAddSkypeId");
            BtImportSkypeId = prop.getProperty("BtImportSkypeId");
            BtSaveListSkypeId = prop.getProperty("BtSaveListSkypeId");
            //tab2
            LbSearchAllSkype = prop.getProperty("LbSearchAllSkype");
            LbCountry = prop.getProperty("LbCountry");
            BtSearchAllSkype = prop.getProperty("BtSearchAllSkype");
            BtSaveIdSearchAllSkype = prop.getProperty("BtSaveIdSearchAllSkype");
            //tab3
            BtImportEmailOrPhone = prop.getProperty("BtImportEmailOrPhone");
            BtSearchSkypeId = prop.getProperty("BtSearchSkypeId");
            BtStop = prop.getProperty("BtStop");
            //tab4
            BtSearchWeb = prop.getProperty("BtSearchWeb");
            CbPhone = prop.getProperty("CbPhone");
            BtSavePhone = prop.getProperty("BtSavePhone");
            BtSaveEmail = prop.getProperty("BtSaveEmail");
            BtSaveSkype = prop.getProperty("BtSaveSkype");
            BtSaveAll = prop.getProperty("BtSaveAll");
            BtClearSkypeId = prop.getProperty("BtClearSkypeId");
            //tab5
            BtSearchFriend = prop.getProperty("BtSearchFriend");
            //tab6
            LbPhone = prop.getProperty("LbPhone");
            //tab log
            BtClearLog = prop.getProperty("BtClearLog");
            BtExportLog = prop.getProperty("BtExportLog");
            //heder tabpanel
            Tab1 = prop.getProperty("Tab1");
            Tab2 = prop.getProperty("Tab2");
            Tab3 = prop.getProperty("Tab3");
            Tab4 = prop.getProperty("Tab4");
            Tab5 = prop.getProperty("Tab5");
            Tab6 = prop.getProperty("Tab6");
            //table
            No = prop.getProperty("No");
            Status = prop.getProperty("Status");
            SkypeID = prop.getProperty("SkypeID");
            FullName = prop.getProperty("FullName");
            Country = prop.getProperty("Country");
            Sex = prop.getProperty("Sex");
            BirthDay = prop.getProperty("BirthDay");
            EmailOrPhone = prop.getProperty("EmailOrPhone");
            Account = prop.getProperty("Account");
            Result = prop.getProperty("Result");
            Text = prop.getProperty("Text");
            Send = prop.getProperty("Send");
            Logs = prop.getProperty("Logs");
            Search = prop.getProperty("Search");

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void initRadioButton() {
        if (langVi) {
            radioVi.setSelected(true);
        } else {
            radioEn.setSelected(true);
        }
    }

    private void initTabPanel() {
        jTabbedPane1.setTitleAt(0, Tab1);
        jTabbedPane1.setTitleAt(1, Tab2);
        jTabbedPane1.setTitleAt(2, Tab3);

        jTabbedPane1.setTitleAt(3, Tab4);


        jTabbedPane1.setTitleAt(4, Tab5);
        jTabbedPane1.setTitleAt(5, Tab6);




    }

    private void initSpinner() {
        spinner.setModel(new SpinnerNumberModel(2, 2, 100, 1));
        spinner.setEditor(new JSpinner.NumberEditor(spinner));
        JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        tf.setEditable(false);
        tf.setBackground(Color.WHITE);
    }

    private void initCompobox() {

        if (langVi) {
            comBoxCountry.addItem("Tất cả");
        } else {
            comBoxCountry.addItem("All");
        }

        List<String> listCountry = ComboboxUtil.allCountry();

        for (String country : listCountry) {

            comBoxCountry.addItem(country);

        }


    }

    private void initTable1() {
        TableUtil.sortTable(dmodel1, table1, sorter);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.getTableHeader().setReorderingAllowed(false);

    }

    private void initTable2() {
//        table2.setShowGrid(false);
//        table2.setIntercellSpacing(new Dimension(0, 0));
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table2.getTableHeader().setReorderingAllowed(false);
    }

    private void initTable3() {
//        table3.setShowGrid(false);
//        table3.setIntercellSpacing(new Dimension(0, 0));
        table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table3.getTableHeader().setReorderingAllowed(false);
    }

    private void initTable4() {
        table4.getTableHeader().setReorderingAllowed(false);
//        table3.setIntercellSpacing(new Dimension(0, 0));
//        table4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void initTable5() {
        TableUtil.sortTable(dmodel5, table5, sorter);
        table5.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table5.getTableHeader().setReorderingAllowed(false);

    }

    private void initTable6() {

        table6.getTableHeader().setReorderingAllowed(false);
        table6.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


    }

    private void initTable7() {
        table7.setShowGrid(false);
        table7.setIntercellSpacing(new Dimension(0, 0));
    }

    private void creatTable1() {
        Object rows[][] = {};

        Object[] headers;
        if (langVi) {
            headers = new Object[]{"STT", "Trạng thái", "Tên Skype", "Tên", "Quốc gia", "Giới tính", "Ngày sinh"};
        } else {
            headers = new Object[]{"No.", "Status", "Skype ID", "Full name", "Country", "Sex", "Birth day"};
        }

        dmodel1 = new ModelTable(rows, headers);
        dmodel1.setCanEdit(new boolean[]{false, false, false, false, false, false, false});
        dmodel1.setTypes(new Class[]{Integer.class, String.class, String.class, String.class, String.class, String.class, String.class});

    }

    private void creatTable2() {
        Object rows[][] = {};
        Object[] headers;

        if (langVi) {
            headers = new Object[]{"Tên Skype", "Email hoặc SĐT"};
        } else {
            headers = new Object[]{"Skype ID", "Email or Phone"};
        }

        dmodel2 = new ModelTable(rows, headers);
        dmodel2.setCanEdit(new boolean[]{false, false});
        dmodel2.setTypes(new Class[]{String.class, String.class});

    }

    private void creatTable3() {
        Object rows[][] = {};
        Object[] headers;
        if (langVi) {
            headers = new Object[]{"Email hoặc SĐT", "Tìm kiếm"};
        } else {
            headers = new Object[]{"Email or Phone", "Search"};
        }


        dmodel3 = new ModelTable(rows, headers);
        dmodel3.setCanEdit(new boolean[]{false, false});
        dmodel3.setTypes(new Class[]{String.class, String.class});

    }

    private void creatTable4() {
        Object rows[][] = {};
        Object[] headers;
        if (langVi) {
            headers = new Object[]{"STT", "Tài khoản", "Kết quả", "Nội dung"};
        } else {
            headers = new Object[]{"No.", "Account", "Result", "Text"};
        }


        dmodel4 = new ModelTable(rows, headers);
        dmodel4.setCanEdit(new boolean[]{false, false, false, false});
        dmodel4.setTypes(new Class[]{String.class, String.class, String.class, String.class});

    }

    private void creatTable5() {
        Object rows[][] = {};
        Object[] headers;
        if (langVi) {
            headers = new Object[]{"STT", "Trạng thái", "Tên Skype", "Tên", "Quốc gia", "Giới tính"};
        } else {
            headers = new Object[]{"No.", "Status", "Account", "Full Name", "Country", "Sex"};
        }

        dmodel5 = new ModelTable(rows, headers);
        dmodel5.setCanEdit(new boolean[]{false, false, false, false, false, false});
        dmodel5.setTypes(new Class[]{Integer.class, String.class, String.class, String.class, String.class, String.class});

    }

    private void creatTable6() {
        Object rows[][] = {};
        Object[] headers;
        if (langVi) {
            headers = new Object[]{"STT", "Trạng thái", "Tên Skype", "Tên", "Quốc gia", "Giới tính", "Ngày sinh", "Gửi"};
        } else {
            headers = new Object[]{"No.", "Status", "Account", "Full Name", "Country", "Sex", "Birth Day", "Send"};
        }

        dmodel6 = new ModelTable(rows, headers);
        dmodel6.setCanEdit(new boolean[]{false, false, false, false, false, false, false, false});
        dmodel6.setTypes(new Class[]{Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class});

    }

    private void creatTable7() {
        Object rows[][] = {};
        Object[] headers;
        if (langVi) {
            headers = new Object[]{"Lịch sử"};
        } else {
            headers = new Object[]{"Logs"};
        }
        dmodel7 = new ModelTable(rows, headers);
        dmodel7.setCanEdit(new boolean[]{false});
        dmodel7.setTypes(new Class[]{String.class});

    }

    private void searchAllTable1() {
        TableUtil.clearTable(dmodel1);

        btSearch.setEnabled(false);

        if (langVi) {
            jLabel7.setText("Tìm...");
        } else {
            jLabel7.setText("Searching...");
        }


        String country = String.valueOf(comBoxCountry.getSelectedItem());

        List<OUser> list = SearchSkypeId.search(txtSearch.getText(), country);

        for (int i = 0; i < list.size(); i++) {

            dmodel1.insertRow(dmodel1.getRowCount(), new Object[]{
                        i + 1,
                        list.get(i).getStatus(),
                        list.get(i).getSkypeId(),
                        list.get(i).getFullName(),
                        list.get(i).getCountry(),
                        list.get(i).getSex(),
                        list.get(i).getBirthDay()
                    });
        }

        if (langVi) {
            jLabel7.setText("Tìm kiếm xong!");
        } else {
            jLabel7.setText("Search finish!");
        }



        btSearch.setEnabled(true);


//        JOptionPane.showMessageDialog(this, "Search finish");


    }

    private void searchAllTable2() {
        
        //set value empty for column "Send"
        TableUtil.setValueColumn(table3, 1, "");
        
        TableUtil.clearTable(dmodel2);

        List<String> allRowValue = TableUtil.getAllRowValue(table3, 0);

        if (langVi) {
            jLabel6.setText("Tìm...");
        } else {
            jLabel6.setText("Searching...");
        }

        SearchSkypeId.searchByListEmail(allRowValue, dmodel2, jLabel14, table3, langVi);

        if (langVi) {
            jLabel6.setText("Tìm kiếm xong!");
        } else {
            jLabel6.setText("Search finish!");
        }


        btSearchIDOnEmailPhone.setEnabled(true);
        btStopSearchIDOnEmailPhone.setEnabled(false);



//        for (String id : list) {
//            dmodel2.insertRow(dmodel2.getRowCount(), new Object[]{
//                        id
//                    });
//        }


    }

    private void searchAllTable3(List<String> listEmailPhone) {

        TableUtil.clearTable(dmodel3);

        for (String emailPhone : listEmailPhone) {
            dmodel3.insertRow(dmodel3.getRowCount(), new Object[]{
                        emailPhone, ""
                    });
        }
        if (langVi) {
            JOptionPane.showMessageDialog(this, "Nhập Email and Phone xong!");
        } else {
            JOptionPane.showMessageDialog(this, "Import Email and Phone Finish!");
        }

    }

    private void searchAllTable4() {

        TableUtil.clearTable(dmodel4);

        String web = txtWebsite.getText().trim();

        //search
        searchWeb.stop = false;
        searchWeb.init();
        searchWeb.search(web, dmodel4, lbLinkWeb, cbPhone.isSelected(), cbEmail.isSelected(), cbSkype.isSelected());

        //search finish
        if (langVi) {
            lbLinkWeb.setText("Tìm kiếm xong");
        } else {
            lbLinkWeb.setText("Search Finish");
        }


        btStopSearchWeb.setEnabled(false);

        btSearchWeb.setEnabled(true);
        cbSkype.setEnabled(true);
        cbEmail.setEnabled(true);
        cbPhone.setEnabled(true);

        btSavePhone.setEnabled(true);
        btSaveEmail.setEnabled(true);
        btSaveNickSkype.setEnabled(true);
        btSaveAll.setEnabled(true);



    }

    private void searchAllTable5() {

        TableUtil.clearTable(dmodel5);

        List<OFriend> allFriend = ContactLister.getAllFriend();

        for (int i = 0; i < allFriend.size(); i++) {
            dmodel5.insertRow(dmodel5.getRowCount(), new Object[]{
                        (i + 1),
                        allFriend.get(i).getStatus(),
                        allFriend.get(i).getAccount(),
                        allFriend.get(i).getFullName(),
                        allFriend.get(i).getCountry(),
                        allFriend.get(i).getSex()
                    });
        }

        if (langVi) {
            JOptionPane.showMessageDialog(this, "Tìm kiếm bạn xong!");
        } else {
            JOptionPane.showMessageDialog(this, "Search friends finish!");
        }



    }

    private void searchAllTable6() {



        List<String> list = FileChooserUtil.read(this);

        if (list == null) {
            return;
        }

        if (list.size() > 0) {
            TableUtil.clearTable(dmodel6);

            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).trim().equals("")) {
                    try {
//                        if (CheckExistSkypeIdOnServer.checkExist(list.get(i).trim())) {

                        User user = Skype.getUser(list.get(i).trim());

                        String birthDay = "";

                        if (user.getBirthDay() != null) {
                            birthDay = DateUtil.getDateToString(user.getBirthDay());
                        }


                        dmodel6.insertRow(dmodel6.getRowCount(), new Object[]{
                                    (i + 1),
                                    user.getStatus().toString(),
                                    user.getId(),
                                    user.getFullName().toString(),
                                    user.getCountry().toString(),
                                    user.getSex().toString(),
                                    birthDay,
                                    ""
                                });
//                        } else {
//
//                            JOptionPane.showMessageDialog(this, "User: " + list.get(i).trim() + " not exist", "Error", JOptionPane.ERROR_MESSAGE);
//                        }

                    } catch (SkypeException ex) {
//                        JOptionPane.showMessageDialog(this, "Error get user: " + list.get(i).trim(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }

            if (langVi) {
                JOptionPane.showMessageDialog(this, "Nhập tên skype xong");
            } else {
                JOptionPane.showMessageDialog(this, "Import skype id finish");
            }


        }




    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSms = new javax.swing.JTextArea();
        spinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        btSendSMS = new javax.swing.JButton();
        btPauseSendSMS = new javax.swing.JButton();
        btStopSendSMS = new javax.swing.JButton();
        btContinueSendSMS = new javax.swing.JButton();
        btImportSkypeIds = new javax.swing.JButton();
        btAddSkypeId = new javax.swing.JButton();
        txtAddSkypeId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        table6 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btSearch = new javax.swing.JButton();
        btSaveId = new javax.swing.JButton();
        comBoxCountry = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btImportListEmailSearch = new javax.swing.JButton();
        btSaveId2 = new javax.swing.JButton();
        btSearchIDOnEmailPhone = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        btStopSearchIDOnEmailPhone = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbLinkWeb = new javax.swing.JLabel();
        btStopSearchWeb = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        btSavePhone = new javax.swing.JButton();
        btSaveEmail = new javax.swing.JButton();
        btSaveNickSkype = new javax.swing.JButton();
        btSaveAll = new javax.swing.JButton();
        btSearchWeb = new javax.swing.JButton();
        txtWebsite = new javax.swing.JTextField();
        cbSkype = new javax.swing.JCheckBox();
        cbPhone = new javax.swing.JCheckBox();
        cbEmail = new javax.swing.JCheckBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        table4 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        table5 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        table7 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        radioVi = new javax.swing.JRadioButton();
        radioEn = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.GridLayout(2, 1));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Message :");
        jLabel2.setText(LbMessage);

        txtSms.setColumns(20);
        txtSms.setRows(5);
        txtSms.setText("Chào $user$, chúc bạn\n{ngày mới vui vẻ|cuối tuần chơi vui|sức khỏe và thành đạt}\n");
        jScrollPane2.setViewportView(txtSms);
        //txtSms.setText("Chào $user$, chúc bạn\n{ngày mới vui vẻ|cuối tuần chơi vui|sức khỏe và thành đạt}\n");

        jLabel4.setText("Seconds");
        jLabel4.setText(LbSeconds);

        btSendSMS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSendSMS.setForeground(new java.awt.Color(255, 255, 255));
        btSendSMS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btSendSMS.setText("Send SMS");
        btSendSMS.setText(BtSendSMS);
        btSendSMS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSendSMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSendSMSActionPerformed(evt);
            }
        });

        btPauseSendSMS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btPauseSendSMS.setForeground(new java.awt.Color(255, 255, 255));
        btPauseSendSMS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btPauseSendSMS.setText("Pause");
        btPauseSendSMS.setText(BtPauseSMS);
        btPauseSendSMS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPauseSendSMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPauseSendSMSActionPerformed(evt);
            }
        });
        btPauseSendSMS.setEnabled(false);

        btStopSendSMS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btStopSendSMS.setForeground(new java.awt.Color(255, 255, 255));
        btStopSendSMS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btStopSendSMS.setText("Stop");
        btStopSendSMS.setText(BtStopSMS);
        btStopSendSMS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btStopSendSMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStopSendSMSActionPerformed(evt);
            }
        });
        btStopSendSMS.setEnabled(false);

        btContinueSendSMS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btContinueSendSMS.setForeground(new java.awt.Color(255, 255, 255));
        btContinueSendSMS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btContinueSendSMS.setText("Continute");
        btContinueSendSMS.setText(BtContinueSMS);
        btContinueSendSMS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btContinueSendSMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btContinueSendSMSActionPerformed(evt);
            }
        });
        btContinueSendSMS.setEnabled(false);

        btImportSkypeIds.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btImportSkypeIds.setForeground(new java.awt.Color(255, 255, 255));
        btImportSkypeIds.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btImportSkypeIds.setText("Import SkypeIDs");
        btImportSkypeIds.setText(BtImportSkypeId);
        btImportSkypeIds.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btImportSkypeIds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImportSkypeIdsActionPerformed(evt);
            }
        });

        btAddSkypeId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btAddSkypeId.setForeground(new java.awt.Color(255, 255, 255));
        btAddSkypeId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btAddSkypeId.setText("Add skype ID");
        btAddSkypeId.setText(BtAddSkypeId);
        btAddSkypeId.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAddSkypeId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddSkypeIdActionPerformed(evt);
            }
        });

        txtAddSkypeId.setMaximumSize(new java.awt.Dimension(6, 20));

        jLabel5.setText("Skype Id:");
        jLabel5.setText(LbSkypeId);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(txtAddSkypeId, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btAddSkypeId, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(btPauseSendSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btStopSendSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(btSendSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btContinueSendSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btImportSkypeIds, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btContinueSendSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSendSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btPauseSendSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btStopSendSMS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(36, 36, 36)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAddSkypeId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btImportSkypeIds, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddSkypeId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        jPanel12.add(jPanel13);

        table6.setModel(dmodel6);
        jScrollPane7.setViewportView(table6);

        jPanel12.add(jScrollPane7);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        jButton3.setText("Save List");
        jButton3.setText(BtSaveListSkypeId);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("General", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(500, 400));
        jPanel2.setMinimumSize(new java.awt.Dimension(500, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 400));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setLayout(new java.awt.GridLayout(2, 1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(500, 100));
        jPanel3.setMinimumSize(new java.awt.Dimension(500, 100));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 100));

        jLabel1.setText("Search :");
        jLabel1.setText(LbSearchAllSkype);

        btSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSearch.setForeground(new java.awt.Color(255, 255, 255));
        btSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btSearch.setText("Search");
        btSearch.setText(BtSearchAllSkype);
        btSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchActionPerformed(evt);
            }
        });

        btSaveId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSaveId.setForeground(new java.awt.Color(255, 255, 255));
        btSaveId.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btSaveId.setText("Save IDs");
        btSaveId.setText(BtSaveIdSearchAllSkype);
        btSaveId.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSaveId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveIdActionPerformed(evt);
            }
        });

        jLabel3.setText("Country :");
        jLabel3.setText(LbCountry);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSaveId, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(402, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSaveId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel19.add(jPanel3);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(dmodel1);
        jScrollPane1.setViewportView(table1);

        jPanel19.add(jScrollPane1);

        jLabel7.setForeground(new java.awt.Color(0, 0, 204));
        jLabel7.setText("...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setLayout(new java.awt.GridLayout(2, 1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btImportListEmailSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btImportListEmailSearch.setForeground(new java.awt.Color(255, 255, 255));
        btImportListEmailSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btImportListEmailSearch.setText("Import Email or Phone");
        btImportListEmailSearch.setText(BtImportEmailOrPhone);
        btImportListEmailSearch.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btImportListEmailSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImportListEmailSearchActionPerformed(evt);
            }
        });

        btSaveId2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSaveId2.setForeground(new java.awt.Color(255, 255, 255));
        btSaveId2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btSaveId2.setText("Save IDs");
        btSaveId2.setText(BtSaveIdSearchAllSkype);
        btSaveId2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSaveId2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveId2ActionPerformed(evt);
            }
        });

        btSearchIDOnEmailPhone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSearchIDOnEmailPhone.setForeground(new java.awt.Color(255, 255, 255));
        btSearchIDOnEmailPhone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btSearchIDOnEmailPhone.setText("Search Skype IDs");
        btSearchIDOnEmailPhone.setText(BtSearchSkypeId);
        btSearchIDOnEmailPhone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSearchIDOnEmailPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchIDOnEmailPhoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSaveId2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearchIDOnEmailPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btImportListEmailSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btImportListEmailSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btSearchIDOnEmailPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btSaveId2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel8);

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        table3.setModel(dmodel3);
        jScrollPane4.setViewportView(table3);

        jPanel6.add(jScrollPane4);

        jPanel18.add(jPanel6);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        table2.setModel(dmodel2);
        jScrollPane3.setViewportView(table2);

        jPanel18.add(jScrollPane3);

        btStopSearchIDOnEmailPhone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btStopSearchIDOnEmailPhone.setForeground(new java.awt.Color(255, 255, 255));
        btStopSearchIDOnEmailPhone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btStopSearchIDOnEmailPhone.setText("Stop");
        btStopSearchIDOnEmailPhone.setText(BtStop);
        btStopSearchIDOnEmailPhone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btStopSearchIDOnEmailPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStopSearchIDOnEmailPhoneActionPerformed(evt);
            }
        });
        btStopSearchIDOnEmailPhone.setEnabled(false);

        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("...");

        jLabel14.setForeground(new java.awt.Color(0, 0, 204));
        jLabel14.setText("...");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btStopSearchIDOnEmailPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btStopSearchIDOnEmailPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel14))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Import Email or Phone", jPanel5);

        lbLinkWeb.setForeground(new java.awt.Color(0, 0, 204));
        lbLinkWeb.setText("URL....");

        btStopSearchWeb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btStopSearchWeb.setForeground(new java.awt.Color(255, 255, 255));
        btStopSearchWeb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btStopSearchWeb.setText("Stop");
        btStopSearchWeb.setText(BtStop);
        btStopSearchWeb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btStopSearchWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStopSearchWebActionPerformed(evt);
            }
        });
        btStopSearchWeb.setEnabled(false);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        jButton4.setText("Clear Skype Id");
        jButton4.setText(BtClearSkypeId);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new java.awt.GridLayout(2, 1));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        btSavePhone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSavePhone.setForeground(new java.awt.Color(255, 255, 255));
        btSavePhone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btSavePhone.setText("Save Phone");
        btSavePhone.setText(BtSavePhone);
        btSavePhone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSavePhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSavePhoneActionPerformed(evt);
            }
        });

        btSaveEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSaveEmail.setForeground(new java.awt.Color(255, 255, 255));
        btSaveEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btSaveEmail.setText("Save Email");
        btSaveEmail.setText(BtSaveEmail);
        btSaveEmail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSaveEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveEmailActionPerformed(evt);
            }
        });

        btSaveNickSkype.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSaveNickSkype.setForeground(new java.awt.Color(255, 255, 255));
        btSaveNickSkype.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btSaveNickSkype.setText("Save Skype ID");
        btSaveNickSkype.setText(BtSaveSkype);
        btSaveNickSkype.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSaveNickSkype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveNickSkypeActionPerformed(evt);
            }
        });

        btSaveAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSaveAll.setForeground(new java.awt.Color(255, 255, 255));
        btSaveAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btSaveAll.setText("Save All");
        btSaveAll.setText(BtSaveAll);
        btSaveAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSaveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveAllActionPerformed(evt);
            }
        });

        btSearchWeb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btSearchWeb.setForeground(new java.awt.Color(255, 255, 255));
        btSearchWeb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        btSearchWeb.setText("Search website");
        btSearchWeb.setText(BtSearchWeb);
        btSearchWeb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSearchWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchWebActionPerformed(evt);
            }
        });

        txtWebsite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWebsiteActionPerformed(evt);
            }
        });

        cbSkype.setBackground(new java.awt.Color(255, 255, 255));
        cbSkype.setText("Skype");
        cbSkype.setSelected(true);

        cbPhone.setBackground(new java.awt.Color(255, 255, 255));
        cbPhone.setText("Phone");
        cbPhone.setText(CbPhone);

        cbEmail.setBackground(new java.awt.Color(255, 255, 255));
        cbEmail.setText("Email");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btSavePhone, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSaveEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSaveNickSkype, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSaveAll, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btSearchWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(cbSkype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(cbPhone)
                                .addGap(18, 18, 18)
                                .addComponent(cbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSearchWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSkype)
                    .addComponent(cbPhone)
                    .addComponent(cbEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSavePhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSaveEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSaveNickSkype, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSaveAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jPanel16.add(jPanel17);

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        table4.setModel(dmodel4);
        table4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table4MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table4);

        jPanel16.add(jScrollPane5);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(btStopSearchWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbLinkWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btStopSearchWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLinkWeb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Search Website", jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new java.awt.GridLayout(2, 1));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        jButton1.setText("Save Skype ID");
        jButton1.setText(BtSaveIdSearchAllSkype);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        jButton2.setText("Search Friends");
        jButton2.setText(BtSearchFriend);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(403, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel15);

        table5.setModel(dmodel5);
        jScrollPane6.setViewportView(table5);

        jPanel14.add(jScrollPane6);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 707, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Friend List", jPanel11);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Email:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(java.awt.Color.blue);
        jLabel10.setText("vnsoft05@gmail.com");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/avatar_logo.png"))); // NOI18N
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel12.setText("Skype:");
        //jLabel12.setText(LbPhone);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(java.awt.Color.blue);
        jLabel13.setText("john.do.vn");

        jLabel16.setText("Website:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(java.awt.Color.blue);
        jLabel17.setText("www.botvn.net");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel18.setText("Address:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(java.awt.Color.blue);
        jLabel15.setText("<html>VietNam : <br/><br/>80/24, Hoang Hoa Tham Street, <br/> 7 Ward, Binh Thanh District, <br />Ho Chi Minh City ");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(java.awt.Color.blue);
        jLabel19.setText("<html>USA: <br/><br/>545 Irvine Center Dr <br/> Suite #200, <br />Irvine, CA 92618");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))))
                .addGap(96, 96, 96))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(171, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Contact", jPanel7);

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));

        table7.setModel(dmodel7);
        jScrollPane8.setViewportView(table7);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        jButton6.setText("Clear logs");
        jButton6.setText(BtClearLog);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bt.png"))); // NOI18N
        jButton7.setText("Export logs");
        jButton7.setText(BtExportLog);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(25, 182, 240));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Skype Ads");

        radioVi.setBackground(new java.awt.Color(25, 182, 240));
        buttonGroup1.add(radioVi);
        radioVi.setForeground(new java.awt.Color(255, 255, 255));
        radioVi.setText("Vietnamese");
        radioVi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioViActionPerformed(evt);
            }
        });

        radioEn.setBackground(new java.awt.Color(25, 182, 240));
        buttonGroup1.add(radioEn);
        radioEn.setForeground(new java.awt.Color(255, 255, 255));
        radioEn.setText("English");
        radioEn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioVi)
                .addGap(18, 18, 18)
                .addComponent(radioEn)
                .addGap(7, 7, 7))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(radioVi)
                    .addComponent(radioEn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane8)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchActionPerformed

        if (!txtSearch.getText().trim().equals("")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    searchAllTable1();
                }
            }).start();
        } else {
            if (langVi) {
                JOptionPane.showMessageDialog(this, "Điều kiện tìm kiếm không được rỗng", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Condition search is empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        }



    }//GEN-LAST:event_btSearchActionPerformed

    private void btSaveIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveIdActionPerformed

        List<String> allRowValue = TableUtil.getAllRowValue(table1, 2);

        if (allRowValue.size() > 0) {
            if (langVi) {
                FileChooserUtil.write(allRowValue, this, "Lưu tên Skype thành công!");
            } else {
                FileChooserUtil.write(allRowValue, this, "Save Skype Id success!");
            }
        } else {
            if (langVi) {
                JOptionPane.showMessageDialog(this, "Danh sách tên skype rỗng", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "List Skype IDs is empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        }




    }//GEN-LAST:event_btSaveIdActionPerformed

    private void btImportSkypeIdsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImportSkypeIdsActionPerformed

        new Thread(new Runnable() {
            @Override
            public void run() {
                searchAllTable6();
            }
        }).start();



        //        List<String> readList = FileChooserUtil.read(this);
//
//        if (readList != null) {
//            SendSmsAuto.send(readList, txtSms.getText());
//            JOptionPane.showMessageDialog(this, "Send success", "Sucess", JOptionPane.DEFAULT_OPTION);
//        }



    }//GEN-LAST:event_btImportSkypeIdsActionPerformed

    private void btImportListEmailSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImportListEmailSearchActionPerformed
        List<String> listEmailPhone = FileChooserUtil.read(this);

        if (listEmailPhone != null) {

            List<String> listChecked = new ArrayList<String>();

            for (String emailPhone : listEmailPhone) {

                if (Validate.validatePhone(emailPhone)) {
                    listChecked.add(emailPhone);
                } else if (Validate.validateEmail2(emailPhone)) {
                    listChecked.add(emailPhone);
                } else {
                    dmodel7.insertRow(dmodel7.getRowCount(), new Object[]{
                                DateUtil.getHoursToString(new Date()) + ": " + "Import Email or Phone error: " + emailPhone
                            });
                }
            }
            searchAllTable3(listChecked);


        }




    }//GEN-LAST:event_btImportListEmailSearchActionPerformed

    private void btSaveId2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveId2ActionPerformed

        if (table2.getRowCount() > 0) {
            List<String> allRowValue = TableUtil.getAllRowValue(table2, 0);

            if (langVi) {
                FileChooserUtil.write(allRowValue, this, "Lưu tên Skype thành công!");
            } else {
                FileChooserUtil.write(allRowValue, this, "Save Skype Id success!");
            }


        } else {

            if (langVi) {
                JOptionPane.showMessageDialog(this, "Danh sách tên skype rỗng", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "List Skype IDs is empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        }



    }//GEN-LAST:event_btSaveId2ActionPerformed

    private void btSearchWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchWebActionPerformed


        if (!txtWebsite.getText().trim().equals("")) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    btStopSearchWeb.setEnabled(true);

                    btSearchWeb.setEnabled(false);
                    cbSkype.setEnabled(false);
                    cbEmail.setEnabled(false);
                    cbPhone.setEnabled(false);

                    btSavePhone.setEnabled(false);
                    btSaveEmail.setEnabled(false);
                    btSaveNickSkype.setEnabled(false);
                    btSaveAll.setEnabled(false);

                    searchAllTable4();
                }
            }).start();


        }

    }//GEN-LAST:event_btSearchWebActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        new Thread(new Runnable() {
            @Override
            public void run() {
                searchAllTable5();
            }
        }).start();



    }//GEN-LAST:event_jButton2ActionPerformed

    private void table4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table4MouseClicked

        int c = table4.getSelectedColumn();
        int r = table4.getSelectedRow();
        if (c == 3) {
            if (r > -1) {
                String valueAt = (String) table4.getValueAt(r, c);

                Document doc = Jsoup.parse(valueAt);

                Elements links = doc.select("a[href]");

                String linkhref = links.attr("href");

                OpenBrowser.openWebpage(linkhref);

//                System.out.println(linkhref);
            }

        }



    }//GEN-LAST:event_table4MouseClicked

    private void btStopSearchWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStopSearchWebActionPerformed

        searchWeb.stop = true;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        lbLinkWeb.setText("Stop");

        btStopSearchWeb.setEnabled(false);

        btSearchWeb.setEnabled(true);
        cbSkype.setEnabled(true);
        cbEmail.setEnabled(true);
        cbPhone.setEnabled(true);

        btSavePhone.setEnabled(true);
        btSaveEmail.setEnabled(true);
        btSaveNickSkype.setEnabled(true);
        btSaveAll.setEnabled(true);

    }//GEN-LAST:event_btStopSearchWebActionPerformed

    private void txtWebsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWebsiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWebsiteActionPerformed

    private void btSavePhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSavePhoneActionPerformed
        List<String> listPhone = new ArrayList<String>();
        int rowCount = table4.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String val = (String) table4.getValueAt(i, 2);
            if (val.equals("Phone")) {
                String phone = (String) table4.getValueAt(i, 1);
                listPhone.add(phone);
            }

        }

        if (listPhone.size() > 0) {
            if (langVi) {
                FileChooserUtil.write(listPhone, this, "Lưu số điện thoại thành công!");
            } else {
                FileChooserUtil.write(listPhone, this, "Save phone success!");
            }
        } else {
            if (langVi) {
                JOptionPane.showMessageDialog(this, "Danh sách SĐT rỗng!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "List phone empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }


    }//GEN-LAST:event_btSavePhoneActionPerformed

    private void btSaveEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveEmailActionPerformed

        List<String> listEmail = new ArrayList<String>();
        int rowCount = table4.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String val = (String) table4.getValueAt(i, 2);
            if (val.equals("Email")) {
                String email = (String) table4.getValueAt(i, 1);
                listEmail.add(email);
            }

        }

        if (listEmail.size() > 0) {
            if (langVi) {
                FileChooserUtil.write(listEmail, this, "Lưu Email thành công!");
            } else {
                FileChooserUtil.write(listEmail, this, "Save Email success!");
            }


        } else {
            if (langVi) {
                JOptionPane.showMessageDialog(this, "Danh sách Email rỗng!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "List Email empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_btSaveEmailActionPerformed

    private void btSaveNickSkypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveNickSkypeActionPerformed


        List<String> listSkype = new ArrayList<String>();
        int rowCount = table4.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String val = (String) table4.getValueAt(i, 2);
            if (val.equals("Skype")) {
                String skype = (String) table4.getValueAt(i, 1);
                listSkype.add(skype);
            }

        }

        if (listSkype.size() > 0) {
            if (langVi) {
                FileChooserUtil.write(listSkype, this, "Lưu tên Skype thành công!");
            } else {
                FileChooserUtil.write(listSkype, this, "Save Skype Ids success!");
            }
        } else {
            if (langVi) {
                JOptionPane.showMessageDialog(this, "Danh sách tên Skype rỗng!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "List Skype Ids empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_btSaveNickSkypeActionPerformed

    private void btSaveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveAllActionPerformed

        List<String> listAll = new ArrayList<String>();
        int rowCount = table4.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String val = (String) table4.getValueAt(i, 1);
            listAll.add(val);
        }

        if (listAll.size() > 0) {

            if (langVi) {
                FileChooserUtil.write(listAll, this, "Lưu danh sách thành công!");
            } else {
                FileChooserUtil.write(listAll, this, "Save list success!");
            }

        } else {
            if (langVi) {
                JOptionPane.showMessageDialog(this, "Danh sách rỗng!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "List empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_btSaveAllActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        List<String> listAll = new ArrayList<String>();
        int rowCount = table5.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String val = (String) table5.getValueAt(i, 2);
            listAll.add(val);
        }

        if (listAll.size() > 0) {
            if (langVi) {
                FileChooserUtil.write(listAll, this, "Lưu tên Skype thành công!");
            } else {
                FileChooserUtil.write(listAll, this, "Save Skype Ids success!");
            }
        } else {
            if (langVi) {
                JOptionPane.showMessageDialog(this, "Danh sách tên Skype rỗng!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "List Skype Ids empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btSendSMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSendSMSActionPerformed


        if (txtSms.getText().trim().equals("")) {
            if (langVi) {
                JOptionPane.showMessageDialog(this, "SMS không được rỗng", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "SMS is empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            return;
        }

        final List<String> list = TableUtil.getAllRowValue(table6, 2);

        if (list.size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendSMS(list);
                }
            }).start();
        } else {

            if (langVi) {
                JOptionPane.showMessageDialog(this, "Danh sách tên Skype rỗng", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "List Skype Ids Empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_btSendSMSActionPerformed

    private void sendSMS(List<String> list) {
        btStopSendSMS.setEnabled(true);
        btPauseSendSMS.setEnabled(true);

        btSendSMS.setEnabled(false);
        btImportSkypeIds.setEnabled(false);
        btAddSkypeId.setEnabled(false);
        spinner.setEnabled(false);
        txtSms.setEnabled(false);

        //set value empty for column "Send"
        TableUtil.setValueColumn(table6, 6, "");

        Integer seconds = (Integer) spinner.getValue();
        sendSmsAuto.stop = false;
        sendSmsAuto.init();
        sendSmsAuto.send(list, txtSms.getText(), seconds, dmodel7, table6, langVi);

        if (sendSmsAuto.stop == false) {
            btStopSendSMS.setEnabled(false);
            btContinueSendSMS.setEnabled(false);
            btPauseSendSMS.setEnabled(false);

            btSendSMS.setEnabled(true);
            btAddSkypeId.setEnabled(true);
            spinner.setEnabled(true);
            btImportSkypeIds.setEnabled(true);
            txtSms.setEnabled(true);
        }

    }

    private void btSearchIDOnEmailPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchIDOnEmailPhoneActionPerformed


        int rowCount = table3.getRowCount();

        if (rowCount > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    btSearchIDOnEmailPhone.setEnabled(false);
                    btStopSearchIDOnEmailPhone.setEnabled(true);

                    SearchSkypeId.stopSearchEmailphone = false;
                    searchAllTable2();
                }
            }).start();
        }




    }//GEN-LAST:event_btSearchIDOnEmailPhoneActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        List<OUser> list = new ArrayList<OUser>();
        int rowCount = table6.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String skypeId = (String) table6.getValueAt(i, 2);
            String fullName = (String) table6.getValueAt(i, 3);
            String country = (String) table6.getValueAt(i, 4);
            String sex = (String) table6.getValueAt(i, 5);
            String birthDay = (String) table6.getValueAt(i, 6);

            OUser oUser = new OUser();
            oUser.setSkypeId(skypeId);
            oUser.setFullName(fullName);
            oUser.setCountry(country);
            oUser.setSex(sex);
            oUser.setBirthDay(birthDay);


            list.add(oUser);
        }

        if (list.size() > 0) {
            FileChooserUtil.writeList(list, this);
        } else {
            if (langVi) {
                JOptionPane.showMessageDialog(this, "Danh sách rỗng", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "List empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }



        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        TableUtil.clearTable(dmodel7);


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        List<String> allRowValue = TableUtil.getAllRowValue(table7, 0);

        if (allRowValue.size() > 0) {

            if (langVi) {
                FileChooserUtil.write(allRowValue, this, "Lưu lịch sử thành công!");
            } else {
                FileChooserUtil.write(allRowValue, this, "Save Logs success!");
            }


        } else {

            if (langVi) {
                JOptionPane.showMessageDialog(this, "Lịch sử rỗng", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Logs empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        }




    }//GEN-LAST:event_jButton7ActionPerformed

    private void btStopSendSMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStopSendSMSActionPerformed

        sendSmsAuto.stop = true;

        btStopSendSMS.setEnabled(false);
        btContinueSendSMS.setEnabled(false);
        btPauseSendSMS.setEnabled(false);

        btSendSMS.setEnabled(true);
        btAddSkypeId.setEnabled(true);
        spinner.setEnabled(true);
        btImportSkypeIds.setEnabled(true);
        txtSms.setEnabled(true);







    }//GEN-LAST:event_btStopSendSMSActionPerformed

    private void btContinueSendSMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btContinueSendSMSActionPerformed

        final List<String> list = TableUtil.getAllRowValue(table6, 2);

        if (list.size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    btStopSendSMS.setEnabled(true);
                    btPauseSendSMS.setEnabled(true);

                    btContinueSendSMS.setEnabled(false);
                    btAddSkypeId.setEnabled(false);
                    spinner.setEnabled(false);
                    txtSms.setEnabled(false);

                    Integer seconds = (Integer) spinner.getValue();
                    sendSmsAuto.stop = false;
                    sendSmsAuto.send(list, txtSms.getText(), seconds, dmodel7, table6, langVi);

                    if (sendSmsAuto.stop == false) {
                        btStopSendSMS.setEnabled(false);
                        btContinueSendSMS.setEnabled(false);
                        btPauseSendSMS.setEnabled(false);

                        btSendSMS.setEnabled(true);
                        btAddSkypeId.setEnabled(true);
                        spinner.setEnabled(true);
                        btImportSkypeIds.setEnabled(true);
                        txtSms.setEnabled(true);
                    }

                }
            }).start();
        } else {
            if (langVi) {
                JOptionPane.showMessageDialog(this, "Danh sách tên Skype rỗng", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "List Skype Ids Empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        }

    }//GEN-LAST:event_btContinueSendSMSActionPerformed

    private void btAddSkypeIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddSkypeIdActionPerformed
        String nickSkype = txtAddSkypeId.getText().trim();

        if (!nickSkype.equals("")) {

//            if (!CheckExistSkypeIdOnServer.checkExist(nickSkype)) {
//                JOptionPane.showMessageDialog(this, "User: " + nickSkype + " not exist", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }

            //check exist in table6
            int rowCount = table6.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                if (table6.getValueAt(i, 2).equals(nickSkype)) {


                    if (langVi) {
                        JOptionPane.showMessageDialog(this, "Tên Skype đã có trong danh sách", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Account exist in list", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                    return;

                }
            }

            //searach nick skype and insert to table6
            try {
                User user = Skype.getUser(nickSkype);
                String birthDay = "";
                if (user.getBirthDay() != null) {
                    birthDay = DateUtil.getDateToString(user.getBirthDay());
                }

                dmodel6.insertRow(dmodel6.getRowCount(), new Object[]{
                            (table6.getRowCount() + 1),
                            user.getStatus().toString(),
                            user.getId(),
                            user.getFullName().toString(),
                            user.getCountry().toString(),
                            user.getSex().toString(),
                            birthDay,
                            ""
                        });

                if (langVi) {
                    JOptionPane.showMessageDialog(this, "Thêm tên Skype thành công", "Success", JOptionPane.DEFAULT_OPTION);
                } else {
                    JOptionPane.showMessageDialog(this, "Add account success", "Success", JOptionPane.DEFAULT_OPTION);
                }


            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error get user: " + nickSkype, "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }



    }//GEN-LAST:event_btAddSkypeIdActionPerformed

    private void btPauseSendSMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPauseSendSMSActionPerformed

        sendSmsAuto.stop = true;
        btPauseSendSMS.setEnabled(false);

        btStopSendSMS.setEnabled(true);
        btContinueSendSMS.setEnabled(true);
        btAddSkypeId.setEnabled(true);
        spinner.setEnabled(true);
        txtSms.setEnabled(true);


    }//GEN-LAST:event_btPauseSendSMSActionPerformed

    private void btStopSearchIDOnEmailPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStopSearchIDOnEmailPhoneActionPerformed

        SearchSkypeId.stopSearchEmailphone = true;

        btSearchIDOnEmailPhone.setEnabled(true);
        btStopSearchIDOnEmailPhone.setEnabled(false);

//        jLabel14.setText("...");



    }//GEN-LAST:event_btStopSearchIDOnEmailPhoneActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int[] row = table4.getSelectedRows();

        if (row.length > 0) {
            for (int i = row.length - 1; i > -1; i--) {
                dmodel4.removeRow(row[i]);
            }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void radioViActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioViActionPerformed

        langVi = true;

        ReadWriteFile.write("lang.phat", "vi");

        initLanguage("lange_vi.properties");

        setTextAll();


    }//GEN-LAST:event_radioViActionPerformed

    private void radioEnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEnActionPerformed

        langVi = false;

        ReadWriteFile.write("lang.phat", "en");

        initLanguage("lange_en.properties");

        setTextAll();

    }//GEN-LAST:event_radioEnActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked

        OpenBrowser.openWebpage("http://" + jLabel17.getText());

    }//GEN-LAST:event_jLabel17MouseClicked

    private void setTextAll() {
        //tab1
        jLabel2.setText(LbMessage);
        jLabel4.setText(LbSeconds);
        btSendSMS.setText(BtSendSMS);
        btContinueSendSMS.setText(BtContinueSMS);
        btPauseSendSMS.setText(BtPauseSMS);
        btStopSendSMS.setText(BtStopSMS);
        jLabel5.setText(LbSkypeId);
        btAddSkypeId.setText(BtAddSkypeId);
        btImportSkypeIds.setText(BtImportSkypeId);
        jButton3.setText(BtSaveListSkypeId);
        //tab2
        jLabel1.setText(LbSearchAllSkype);
        jLabel3.setText(LbCountry);
        btSearch.setText(BtSearchAllSkype);
        btSaveId.setText(BtSaveIdSearchAllSkype);

        if (langVi) {
            if (jLabel7.getText().equals("Searching...")) {
                jLabel7.setText("Tìm...");
            } else {
                jLabel7.setText("Tìm kiếm xong!");
            }
        } else {
            if (jLabel7.getText().equals("Tìm...")) {
                jLabel7.setText("Searching...");
            } else {
                jLabel7.setText("Search finish!");
            }
        }

        //tab3
        btImportListEmailSearch.setText(BtImportEmailOrPhone);
        btSearchIDOnEmailPhone.setText(BtSearchSkypeId);
        btSaveId2.setText(BtSaveIdSearchAllSkype);
        btStopSearchIDOnEmailPhone.setText(BtStop);

        if (langVi) {
            if (jLabel6.getText().equals("Searching...")) {
                jLabel6.setText("Tìm...");
            } else {
                jLabel6.setText("Tìm kiếm xong!");
            }
        } else {
            if (jLabel6.getText().equals("Tìm...")) {
                jLabel6.setText("Searching...");
            } else {
                jLabel6.setText("Search finish!");
            }
        }


        //tab4
        btSearchWeb.setText(BtSearchWeb);
        cbPhone.setText(CbPhone);
        btSavePhone.setText(BtSavePhone);
        btSaveEmail.setText(BtSaveEmail);
        btSaveNickSkype.setText(BtSaveSkype);
        btSaveAll.setText(BtSaveAll);
        btStopSearchWeb.setText(BtStop);
        jButton4.setText(BtClearSkypeId);
        //tab5
        jButton2.setText(BtSearchFriend);
        jButton1.setText(BtSaveIdSearchAllSkype);
        //tab6
        //jLabel12.setText(LbPhone);
        //tab logs
        jButton6.setText(BtClearLog);
        jButton7.setText(BtExportLog);
        //header tabPanel
        initTabPanel();
        //table
        setTextHeaderTable();

        //compobox
        comBoxCountry.removeAllItems();
        initCompobox();


    }

    private void setTextHeaderTable() {
        JTableHeader th1 = table1.getTableHeader();
        th1.getColumnModel().getColumn(0).setHeaderValue(No);
        th1.getColumnModel().getColumn(1).setHeaderValue(Status);
        th1.getColumnModel().getColumn(2).setHeaderValue(SkypeID);
        th1.getColumnModel().getColumn(3).setHeaderValue(FullName);
        th1.getColumnModel().getColumn(4).setHeaderValue(Country);
        th1.getColumnModel().getColumn(5).setHeaderValue(Sex);
        th1.getColumnModel().getColumn(6).setHeaderValue(BirthDay);
        th1.repaint();

        JTableHeader th2 = table2.getTableHeader();
        th2.getColumnModel().getColumn(0).setHeaderValue(SkypeID);
        th2.getColumnModel().getColumn(1).setHeaderValue(EmailOrPhone);
        th2.repaint();

        JTableHeader th3 = table3.getTableHeader();
        th3.getColumnModel().getColumn(0).setHeaderValue(EmailOrPhone);
        th3.getColumnModel().getColumn(1).setHeaderValue(Search);
        th3.repaint();

        JTableHeader th4 = table4.getTableHeader();
        th4.getColumnModel().getColumn(0).setHeaderValue(No);
        th4.getColumnModel().getColumn(1).setHeaderValue(Account);
        th4.getColumnModel().getColumn(2).setHeaderValue(Result);
        th4.getColumnModel().getColumn(3).setHeaderValue(Text);
        th4.repaint();

        JTableHeader th5 = table5.getTableHeader();
        th5.getColumnModel().getColumn(0).setHeaderValue(No);
        th5.getColumnModel().getColumn(1).setHeaderValue(Status);
        th5.getColumnModel().getColumn(2).setHeaderValue(Account);
        th5.getColumnModel().getColumn(3).setHeaderValue(FullName);
        th5.getColumnModel().getColumn(4).setHeaderValue(Country);
        th5.getColumnModel().getColumn(5).setHeaderValue(Sex);
        th5.repaint();

        JTableHeader th6 = table6.getTableHeader();
        th6.getColumnModel().getColumn(0).setHeaderValue(No);
        th6.getColumnModel().getColumn(1).setHeaderValue(Status);
        th6.getColumnModel().getColumn(2).setHeaderValue(Account);
        th6.getColumnModel().getColumn(3).setHeaderValue(FullName);
        th6.getColumnModel().getColumn(4).setHeaderValue(Country);
        th6.getColumnModel().getColumn(5).setHeaderValue(Sex);
        th6.getColumnModel().getColumn(6).setHeaderValue(BirthDay);
        th6.getColumnModel().getColumn(7).setHeaderValue(Send);
        th6.repaint();

        JTableHeader th7 = table7.getTableHeader();
        th7.getColumnModel().getColumn(0).setHeaderValue(Logs);
        th7.repaint();





    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Demo1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAddSkypeId;
    public javax.swing.JButton btContinueSendSMS;
    private javax.swing.JButton btImportListEmailSearch;
    public javax.swing.JButton btImportSkypeIds;
    public javax.swing.JButton btPauseSendSMS;
    private javax.swing.JButton btSaveAll;
    private javax.swing.JButton btSaveEmail;
    private javax.swing.JButton btSaveId;
    private javax.swing.JButton btSaveId2;
    private javax.swing.JButton btSaveNickSkype;
    private javax.swing.JButton btSavePhone;
    private javax.swing.JButton btSearch;
    private javax.swing.JButton btSearchIDOnEmailPhone;
    private javax.swing.JButton btSearchWeb;
    public javax.swing.JButton btSendSMS;
    private javax.swing.JButton btStopSearchIDOnEmailPhone;
    private javax.swing.JButton btStopSearchWeb;
    public javax.swing.JButton btStopSendSMS;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbEmail;
    private javax.swing.JCheckBox cbPhone;
    private javax.swing.JCheckBox cbSkype;
    private javax.swing.JComboBox comBoxCountry;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel lbLinkWeb;
    private javax.swing.JRadioButton radioEn;
    private javax.swing.JRadioButton radioVi;
    public javax.swing.JSpinner spinner;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    public javax.swing.JTable table3;
    public javax.swing.JTable table4;
    private javax.swing.JTable table5;
    private javax.swing.JTable table6;
    private javax.swing.JTable table7;
    private javax.swing.JTextField txtAddSkypeId;
    private javax.swing.JTextField txtSearch;
    public javax.swing.JTextArea txtSms;
    private javax.swing.JTextField txtWebsite;
    // End of variables declaration//GEN-END:variables
}
