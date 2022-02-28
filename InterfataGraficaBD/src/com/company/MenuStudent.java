package com.company;
import com.mysql.cj.x.protobuf.MysqlxExpr;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class MenuStudent {
    JPanel principal;
    String cnp;
    JPanel stanga;
    String numePrenume;
    int butonApasat;
    String auxTransmitere;
    int control=0;
    int pozitie=0;
    Object[] butoane=new Object[10];

    MenuStudent(CardLayout cardLayout, JPanel cards){

        //BorderLayout
        this.principal=new JPanel(new BorderLayout());
        principal.setPreferredSize(new Dimension(1024,600));
        principal.setBackground(Color.darkGray);

        stanga= new JPanel (new BorderLayout());
        JPanel auxStanga=new JPanel(new GridLayout(18,1));
        stanga.setPreferredSize(new Dimension(120,100));
        stanga.setBackground(Color.gray);


        JPanel sus= new JPanel(new GridLayout(5,1));
        sus.setPreferredSize(new Dimension(100,80));
        sus.setBackground(Color.DARK_GRAY);
        sus.setLayout(new BorderLayout());

        JLabel bunVenit=new JLabel("Bine ai revenit !");
        bunVenit.setForeground(Color.ORANGE);
        sus.add(bunVenit,BorderLayout.CENTER);

        //Icon

            ImageIcon logoScoala = new ImageIcon("logoScoala.png");
            Image img = logoScoala.getImage();
            img = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logoScoala = new ImageIcon(img);
            JLabel auxSus = new JLabel();
            auxSus.setLayout(new BorderLayout());
            auxSus.setPreferredSize(new Dimension(120, 100));
            auxSus.setHorizontalAlignment(SwingConstants.CENTER);
            auxSus.setIcon(logoScoala);
            auxSus.setBackground(Color.GRAY);
            auxSus.setOpaque(true);
            sus.add(auxSus, BorderLayout.WEST);

        ///Butoane

            JButton activitati = new JButton("Activitati");
            activitati.setBackground(Color.orange);
            activitati.setFocusable(false);

           JButton dateleMele = new JButton("Date Pers");
           dateleMele.setBackground(Color.orange);

            JButton cursuri = new JButton("Cursuri");
            cursuri.setBackground(Color.orange);

            JButton note = new JButton("Note");
            note.setBackground(Color.orange);

            JButton grupuri = new JButton("Grupuri");
            grupuri.setBackground(Color.orange);

            JButton notificari = new JButton("Notificari");
            notificari.setBackground(Color.orange);

            JButton logout = new JButton("Log out");
            logout.setBackground(Color.orange);


        ///Meniul din stanga

            auxStanga.add(new JLabel(""));
            auxStanga.add(dateleMele);
            auxStanga.add(new JLabel(""));
            auxStanga.add(activitati);
            auxStanga.add(new JLabel(""));
            auxStanga.add(cursuri);
            auxStanga.add(new JLabel(""));
            auxStanga.add(note);
            auxStanga.add(new JLabel(""));
            auxStanga.add(grupuri);
            auxStanga.add(new JLabel(""));
            auxStanga.add(notificari);
            auxStanga.add(new JLabel(""));
            auxStanga.add(logout);


        auxStanga.setBackground(Color.GRAY);
        stanga.add(auxStanga,BorderLayout.CENTER);


        JPanel aux2Stanga=new JPanel();
        stanga.add(aux2Stanga,BorderLayout.WEST);
        aux2Stanga.setPreferredSize(new Dimension(15,10));
        aux2Stanga.setBackground(Color.GRAY);

        JPanel aux3Stanga=new JPanel();
        stanga.add(aux3Stanga,BorderLayout.EAST);
        aux3Stanga.setPreferredSize(new Dimension(15,10));
        aux3Stanga.setBackground(Color.GRAY);


        bunVenit.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel dreapta=new JPanel();
        dreapta.setBackground(Color.darkGray);

        principal.add(stanga,BorderLayout.WEST);
        principal.add(sus,BorderLayout.NORTH);
        principal.add(dreapta,BorderLayout.CENTER);

        ///////////////////////////////////////////////////////////////////////////

        activitati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement selectStatement = null;
                try{
                    Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                    ResultSet rs = null;
                    ResultSetMetaData rsmd = null;
                    String[] columnNames = {"Materia", "Desfasurare"};

                    Object[][] data=new Object[10][2];
                    data[0][0]=new String("Materia"); data[0][1]=new String("Desfasurare");

                    selectStatement = connection1.createStatement();
                    selectStatement.execute("Call AFISARE_ACTIVITATI_STUDENTI("+cnp+")");
                    rs = selectStatement.getResultSet();

                    int rowCount = 1;
                    while(rs.next()){
                        data[rowCount][0]=rs.getString("materia"); data[rowCount][1]=rs.getString("perioada_participare");
                        rowCount++;
                    }
                    JTable tab=new JTable(data,columnNames);
                    JPanel aux=new JPanel();
                    aux.setBackground(Color.darkGray);
                    aux.add(tab);

                    JPanel auxDownload=new JPanel();
                    auxDownload.setBackground(Color.darkGray);
                    JButton download=new JButton("Download");
                    download.setBackground(Color.orange);
                    auxDownload.add(download);


                    dreapta.removeAll();

                    dreapta.setLayout(new GridLayout(4,1));
                    dreapta.add(new JLabel(" "));
                    dreapta.add(aux);
                    tab.setBackground(Color.orange);
                    tab.setForeground(Color.black);
                    tab.setAlignmentX(Component.CENTER_ALIGNMENT);
                    tab.setGridColor(Color.black);

                    ImageIcon logoScoala=new ImageIcon("Activity.png");
                    Image img= logoScoala.getImage();
                    img=img.getScaledInstance(150,100, Image.SCALE_SMOOTH);
                    logoScoala=new ImageIcon(img);
                    JLabel logo=new JLabel();
                    logo.setIcon(logoScoala);
                    logo.setHorizontalAlignment(SwingConstants.CENTER);
                    dreapta.add(logo);
                    dreapta.add(auxDownload);
                    download.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                FileWriter fisier = new FileWriter("DownloadActivitatiStudent");
                                int i=0;
                                while (data[i][0]!=null){
                                    fisier.write((String)data[i][0]+" "+(String)data[i][1]+" \n");
                                    i++;
                                }

                                fisier.close();
                            }
                            catch (java.io.IOException e1) {
                                System.out.println("An error occurred.");
                                e1.printStackTrace();
                            }
                        }
                    });

                    dreapta.revalidate();
                    dreapta.repaint();
                    tab.setPreferredSize(new Dimension(600,250));
                    dreapta.setAlignmentY(Component.CENTER_ALIGNMENT);

                }  catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details below:");
                    sqlex.printStackTrace(System.err);
                }


            }
        });

        //////////////////////////////////////////////////////////////////////////////

        note.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement selectStatement = null;
                try{
                    Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                    ResultSet rs = null;
                    ResultSetMetaData rsmd = null;
                    String[] columnNames = {"Materia", "Desfasurare","vg","vg","x"};

                    Object[][] data=new Object[10][8];
                    data[0][0]=new String("Materia"); data[0][1]=new String("Nota Laborator");
                    data[0][2]=new String("Nota Curs"); data[0][3]=new String("Nota Seminar");
                    data[0][4]=new String("Nota finala");

                    selectStatement = connection1.createStatement();
                    selectStatement.execute("Call AFISARE_NOTE_STUDENT("+cnp+")");
                    rs = selectStatement.getResultSet();

                    int rowCount = 1;
                    while(rs.next()){
                        data[rowCount][0]=rs.getString("materie"); data[rowCount][1]=rs.getString("nota_lab");
                        data[rowCount][2]=rs.getString("nota_curs"); data[rowCount][3]=rs.getString("nota_seminar");
                        data[rowCount][5]=rs.getString("pondere_curs"); data[rowCount][6]=rs.getString("pondere_seminar");
                        data[rowCount][7]=rs.getString("pondere_laborator");
                        double notaCurs=Double.parseDouble((String)data[rowCount][2]);
                        double notaSeminar=Double.parseDouble((String)data[rowCount][3]);
                        double notaLaborator=Double.parseDouble((String)data[rowCount][1]);
                        double pondereCurs=Double.parseDouble((String)data[rowCount][5]);
                        double pondereSeminar=Double.parseDouble((String)data[rowCount][6]);
                        double pondereLaborator=Double.parseDouble((String)data[rowCount][7]);


                        System.out.println(notaCurs+" "+notaSeminar+" "+notaLaborator+" "+pondereCurs+" "+pondereSeminar+" "+pondereLaborator);
                        double notafinal=notaCurs*pondereCurs/100+notaLaborator*pondereLaborator/100+notaSeminar*pondereSeminar/100;
                        notafinal= Math.round(notafinal);
                        System.out.println(notafinal);
                        data[rowCount][4]=String.valueOf((int)notafinal);

                        rowCount++;
                    }
                    JTable tab=new JTable(data,columnNames);
                    JPanel aux=new JPanel();
                    aux.setBackground(Color.darkGray);
                    aux.add(tab);

                    dreapta.removeAll();

                    dreapta.setLayout(new GridLayout(4,1));
                    dreapta.add(new JLabel(" "));
                    dreapta.add(aux);
                    tab.setBackground(Color.orange);
                    tab.setForeground(Color.black);
                    tab.setAlignmentX(Component.CENTER_ALIGNMENT);
                    tab.setGridColor(Color.black);

                    ImageIcon logoScoala=new ImageIcon("Activity.png");
                    Image img= logoScoala.getImage();
                    img=img.getScaledInstance(150,100, Image.SCALE_SMOOTH);
                    logoScoala=new ImageIcon(img);
                    JLabel logo=new JLabel();
                    logo.setIcon(logoScoala);
                    logo.setHorizontalAlignment(SwingConstants.CENTER);
                    dreapta.add(logo);

                    dreapta.revalidate();
                    dreapta.repaint();
                    tab.setPreferredSize(new Dimension(600,250));
                    dreapta.setAlignmentY(Component.CENTER_ALIGNMENT);

                }  catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details below:");
                    sqlex.printStackTrace(System.err);
                }


            }
        });

        /////////////////////////////////////////////////////////////////////////////

        cursuri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement selectStatement = null;
                try{
                    Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                    ResultSet rs = null;
                    ResultSetMetaData rsmd = null;
                    String[] columnNames = {"Materia", "Desfasurare","vg",};

                    Object[][] data=new Object[10][4];
                    data[0][0]=new String("ID CURS"); data[0][1]=new String("Nume Curs");
                    data[0][2]=new String("Descriere");

                    selectStatement = connection1.createStatement();
                    selectStatement.execute("Select Id_curs,nume_curs,descriere from cursuri");
                    rs = selectStatement.getResultSet();

                    int rowCount = 1;
                    while(rs.next()){
                        data[rowCount][0]=rs.getString("Id_curs"); data[rowCount][1]=rs.getString("nume_curs");
                        data[rowCount][2]=rs.getString("descriere");
                        rowCount++;
                    }
                    JTable tab=new JTable(data,columnNames);
                    JPanel aux=new JPanel();
                    aux.setBackground(Color.darkGray);
                    aux.add(tab);

                    dreapta.removeAll();

                    dreapta.setLayout(new GridLayout(4,1));
                    dreapta.add(new JLabel(" "));
                    dreapta.add(aux);
                    tab.setBackground(Color.orange);
                    tab.setForeground(Color.black);
                    tab.setAlignmentX(Component.CENTER_ALIGNMENT);
                    tab.setGridColor(Color.black);

                    JPanel inscriere= new JPanel();
                    JLabel inscriere1=new JLabel("ID Curs: ");
                    inscriere1.setForeground(Color.ORANGE);
                    JTextField inscriere2=new JTextField(15);
                    JButton inscriere3 = new JButton("Submit");

                    inscriere3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String id = inscriere2.getText();
                            String numeCurs = null;
                            String cnpProf;
                            String numeMaterie;

                            try {
                                Connection connection5 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                ResultSet rs5 = null;
                                Statement selectStatement5 = null;
                                selectStatement5 = connection5.createStatement();
                                selectStatement5.execute("call ID_MATERIE(" + id + ");");
                                rs5 = selectStatement5.getResultSet();
                                rs5.next();
                                cnpProf = rs5.getString("profesori_persoane_CNP");
                                numeMaterie = rs5.getString("nume_curs");
                                System.out.println("INSERT INTO note (studenti_persoane_CNP, cursuri_profesori_persoane_CNP, cursuri_Id_curs, materie, nota_lab, nota_curs, nota_seminar)"
                                        + " VALUES ('"+cnp+"', '"+cnpProf+"', "+id+", '"+numeMaterie+"', 0, 0, 0)");
                                selectStatement5.execute("INSERT INTO note (studenti_persoane_CNP, cursuri_profesori_persoane_CNP, cursuri_Id_curs, materie, nota_lab, nota_curs, nota_seminar)"
                                        + " VALUES ('"+cnp+"', '"+cnpProf+"', "+id+", '"+numeMaterie+"', 0, 0, 0)");
                                //selectStatement5.execute("insert into note( studenti_persoane_CNP,cursuri_profesori_persoane_CNP,cursuri_Id_curs,materie,nota_lab,nota_curs,nota_seminar) values ('1991010234567','1991010234563',155,'PC2',7,7,7)");
                            }
                            catch(SQLException sqlex) {
                                System.err.println("An SQL Exception occured. Details below:");
                                sqlex.printStackTrace(System.err);
                                if(!id.equals("111"))
                                  JOptionPane.showMessageDialog(inscriere2,"Sunteti deja inscris la acest curs!");
                            }
                        }
                    });

                    inscriere3.setBackground(Color.orange);
                    inscriere.setBackground(Color.darkGray);
                    inscriere.add(inscriere1);
                    inscriere.add(inscriere2);
                    inscriere.add(inscriere3);

                    dreapta.add(inscriere);


                    dreapta.revalidate();
                    dreapta.repaint();
                    tab.setPreferredSize(new Dimension(600,250));
                    dreapta.setAlignmentY(Component.CENTER_ALIGNMENT);

                }  catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details below:");
                    sqlex.printStackTrace(System.err);
                }


            }
        });

        ///////////////////////////////////////////////////////////////////////////////

        grupuri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement selectStatement = null;
                try{
                    Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                    ResultSet rs = null;
                    ResultSetMetaData rsmd = null;

                    Object[][] data=new Object[10][2];
                    data[0][0]=new String("ID"); data[0][1]=new String("Materia");

                    selectStatement = connection1.createStatement();
                    selectStatement.execute("Call AFISARE_GRUPURI_STUDENT("+cnp+")");
                    rs = selectStatement.getResultSet();

                    int rowCount = 1;
                    while(rs.next()){
                        data[rowCount][0]=rs.getString("id_grup"); data[rowCount][1]=rs.getString("materia");
                        rowCount++;
                    }
                    dreapta.setLayout(new BorderLayout());

                    JPanel auxChat= new JPanel();
                    auxChat.setBackground(Color.DARK_GRAY);

                    JPanel auxParticipanti= new JPanel();
                    auxParticipanti.setBackground(Color.darkGray);
                    auxParticipanti.setPreferredSize(new Dimension(150,700));

                    JPanel auxGrupuri= new JPanel();
                    auxGrupuri.setBackground(Color.darkGray);
                    auxGrupuri.setPreferredSize(new Dimension(100,700));
                    auxGrupuri.setLayout(new GridLayout(14,1));
                    auxGrupuri.add(new JLabel(" "));


                    JLabel Toate=new JLabel("Toate");
                    Toate.setBackground(Color.ORANGE);
                    JLabel Cursurile=new JLabel("Cursurile");
                    Cursurile.setBackground(Color.ORANGE);
                    JButton toateCursurile=new JButton();
                    toateCursurile.setLayout(new GridLayout(2,1));
                    toateCursurile.add(Toate);
                    toateCursurile.add(Cursurile);
                    toateCursurile.setBackground(Color.ORANGE);
                    auxGrupuri.add(toateCursurile);
                    toateCursurile.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Statement selectStatement4 = null;
                            try{
                                Connection connection4 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                ResultSet rs4 = null;
                                ResultSetMetaData rsmd4 = null;
                                String[] columnNames = {"Materia", "Desfasurare"};

                                Object[][] data4=new Object[10][3];
                                data4[0][0]=new String("ID Grup"); data4[0][1]=new String("Nume Grup");

                                selectStatement4 = connection4.createStatement();
                                selectStatement4.execute("select distinct id_grup, materia from grupuri");
                                rs4 = selectStatement4.getResultSet();
                                int rowCount = 0;
                                while(rs4.next()){
                                    rowCount++;
                                    data4[rowCount][0]=rs4.getString("id_grup"); data4[rowCount][1]=rs4.getString("materia");
                                }

                                JTable tab4=new JTable(data4,columnNames);
                                JPanel aux4=new JPanel();
                                aux4.setBackground(Color.darkGray);
                                aux4.add(tab4);

                                dreapta.removeAll();

                                dreapta.setLayout(new GridLayout(4,1));
                                dreapta.add(new JLabel(" "));
                                dreapta.add(aux4);
                                tab4.setBackground(Color.orange);
                                tab4.setForeground(Color.black);
                                tab4.setAlignmentX(Component.CENTER_ALIGNMENT);
                                tab4.setGridColor(Color.black);

                                JPanel inscriere= new JPanel();
                                JLabel inscriere1=new JLabel("ID Grup: ");
                                inscriere1.setForeground(Color.ORANGE);
                                JTextField inscriere2=new JTextField(15);
                                JButton inscriere3 = new JButton("Submit");
                                inscriere3.setBackground(Color.orange);
                                inscriere.setBackground(Color.darkGray);
                                inscriere.add(inscriere1);
                                inscriere.add(inscriere2);
                                inscriere.add(inscriere3);

                                inscriere3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String id = inscriere2.getText();
                                        String numeCurs = null;
                                        String cnpProf;
                                        String numeMaterie;

                                        try {
                                            Connection connection5 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                            ResultSet rs5 = null;
                                            Statement selectStatement5 = null;
                                            selectStatement5 = connection5.createStatement();
                                            System.out.println("SELECT distinct materia FROM grupuri Where id_grup="+ id + ";");
                                            selectStatement5.execute("SELECT distinct materia FROM grupuri Where id_grup="+ id + ";");
                                            rs5 = selectStatement5.getResultSet();
                                            rs5.next();
                                            numeMaterie = rs5.getString("materia");

                                            Connection connection6= DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                            Statement selectStatement6 = null;
                                            selectStatement6 = connection6.createStatement();
                                            System.out.println("INSERT INTO grupuri (id_grup, studenti_persoane_CNP, materia) VALUES ('"+inscriere2.getText()+"', '"+cnp+"', '"+numeMaterie+"');");
                                            selectStatement6.execute("INSERT INTO grupuri (id_grup, studenti_persoane_CNP, materia) VALUES ('"+inscriere2.getText()+"', '"+cnp+"', '"+numeMaterie+"');");
                                            grupuri.doClick();
                                        }
                                        catch(SQLException sqlex) {
                                            System.err.println("An SQL Exception occured. Details below:");
                                            sqlex.printStackTrace(System.err);
                                            JOptionPane.showMessageDialog(inscriere2,"Sunteti deja inscris la acest grup!");
                                        }
                                    }
                                });


                                dreapta.add(inscriere);

                                dreapta.revalidate();
                                dreapta.repaint();
                                tab4.setPreferredSize(new Dimension(600,250));
                                dreapta.setAlignmentY(Component.CENTER_ALIGNMENT);

                            }  catch(SQLException sqlex) {
                                System.err.println("An SQL Exception occured. Details below:");
                                sqlex.printStackTrace(System.err);
                            }
                        }
                    });

                    for (int i=1;i<rowCount;i++){
                        butonApasat=i;
                        auxGrupuri.add(new JLabel(" "));
                         butoane[butonApasat]=new JButton((String)data[i][1]);
                        ((JButton)butoane[butonApasat]).setBackground(Color.orange);
                         Font font=new Font(Font.DIALOG,Font.BOLD,10);
                        ((JButton)butoane[butonApasat]).setFont(font);
                         auxGrupuri.add(((JButton)butoane[butonApasat]));
                         auxTransmitere=new String((String)data[butonApasat][0]);
                         System.out.println((String)data[butonApasat][0]);
                        ((JButton)butoane[butonApasat]).addActionListener(new ActionListener() {
                             @Override
                             public void actionPerformed(ActionEvent e) {
                                 System.out.println(((JButton) butoane[butonApasat]).getText());
                                 Statement selectStatement = null;
                                 JButton buttonActual=(JButton)butoane[butonApasat];
                                 pozitie=butonApasat;
                                 try{

                                     Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                     ResultSet rs2 = null;
                                     ResultSetMetaData rsmd = null;

                                     Object[][] data1=new Object[20][4];

                                     selectStatement = connection1.createStatement();
                                     selectStatement.execute("Call AFISARE_MESAJE_GRUP("+auxTransmitere+")");
                                     rs2 = selectStatement.getResultSet();

                                     JPanel chatStanga=new JPanel();
                                     chatStanga.setLayout(new GridLayout(14,1));
                                     chatStanga.setBackground(Color.darkGray);
                                     JPanel chatCentru=new JPanel();
                                     chatCentru.setLayout(new GridLayout(14,1));
                                     chatCentru.setBackground(Color.darkGray);

                                     int rowCount1 = 0;
                                     while(rs2.next()){
                                         data1[rowCount1][0]=rs2.getString("mesaj"); data1[rowCount1][1]=rs2.getString("data_mesaj");
                                         data1[rowCount1][2]=rs2.getString("nume"); data1[rowCount1][3]=rs2.getString("prenume");
                                         //chatStanga.add(new JLabel((String)data1[rowCount1][1]+" "+(String)data1[rowCount1][2]+" "+(String)data1[rowCount1][3]));
                                         JPanel auxX=new JPanel();
                                         auxX.setLayout(new GridLayout(2,1));
                                         JLabel auxX1=new JLabel((String)data1[rowCount1][2]+" "+(String)data1[rowCount1][3]);
                                         auxX1.setForeground(Color.ORANGE);
                                         auxX.add(auxX1);
                                         JLabel auxX2=new JLabel((String)data1[rowCount1][1]);
                                         auxX2.setForeground(Color.ORANGE);
                                         auxX.add(auxX2);
                                         Color auxCuloare=new Color(1,1,1);
                                         if (rowCount1%2==0)
                                            auxCuloare=Color.gray;
                                         else
                                             auxCuloare=Color.DARK_GRAY;
                                         auxX.setAlignmentX(Component.CENTER_ALIGNMENT);
                                         auxX.setBackground(auxCuloare);
                                         auxX.setForeground(Color.ORANGE);
                                         auxX.setPreferredSize(new Dimension(120,400));
                                         chatStanga.add(auxX);
                                         JPanel auxCC1=new JPanel();
                                         JLabel auxC1=new JLabel((String)data1[rowCount1][0]);
                                         auxC1.setForeground(Color.ORANGE);
                                         auxCC1.setBackground(auxCuloare);
                                         auxCC1.add(auxC1);
                                         chatCentru.add(auxCC1);
                                         rowCount1++;
                                     }

                                     Object[][] data2=new Object[14][4];
                                     selectStatement.execute("Call AFISARE_MEMBRII_GRUP("+auxTransmitere+")");
                                     rs2 = selectStatement.getResultSet();

                                     int rowCount2=0;
                                     auxParticipanti.removeAll();
                                     auxParticipanti.setLayout(new GridLayout(18,1));
                                     JLabel explicatieParticipanti=new JLabel("Participanti: ");
                                     explicatieParticipanti.setBackground(Color.darkGray);
                                     explicatieParticipanti.setForeground(Color.ORANGE);
                                     auxParticipanti.add(explicatieParticipanti);
                                     while(rs2.next()) {
                                         data2[rowCount2][0] = rs2.getString("nume");
                                         data2[rowCount2][1] = rs2.getString("prenume");
                                         JLabel auxLabel=new JLabel((String)data2[rowCount2][0]+" "+(String)data2[rowCount2][1]);
                                         auxLabel.setForeground(Color.ORANGE);
                                         auxParticipanti.add(auxLabel);

                                         rowCount2++;
                                     }

                                     JPanel down=new JPanel();
                                     down.setBackground(Color.darkGray);
                                     JTextField mesaj=new JTextField(50);
                                     JButton sendMesaj=new JButton("Trimite");

                                     sendMesaj.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             //System.out.println(auxTransmitere);
                                             Statement selectStatementM;
                                             try{
                                                 Connection connection4 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                                 ResultSet rsM = null;

                                                 Date auxTimp= new Date(System.currentTimeMillis());
                                                 String date = new SimpleDateFormat("yyyy-MM-dd").format(auxTimp);
                                                 selectStatementM = connection4.createStatement();
                                                 selectStatementM.execute("insert into mesaje_activitati(grupuri_id_grup,grupuri_studenti_persoane_CNP ,mesaj, data_mesaj) values " +
                                                         "("+auxTransmitere+",'"+cnp+"','"+mesaj.getText()+"','"+date+"');");
                                                 control=1;
                                                 grupuri.doClick();
                                                 System.out.println("Se apasa aici");


                                             }  catch(SQLException sqlex) {
                                                 System.err.println("An SQL Exception occured. Details below:");
                                                 sqlex.printStackTrace(System.err);
                                             }
                                         }
                                     });

                                     System.out.println(control+" ");

                                     sendMesaj.setBackground(Color.ORANGE);
                                     down.add(mesaj);
                                     down.add(sendMesaj);
                                     down.setPreferredSize(new Dimension(700,100));

                                     auxParticipanti.setBackground(Color.darkGray);
                                     auxParticipanti.setForeground(Color.ORANGE);

                                     auxChat.setLayout(new BorderLayout());
                                     chatStanga.setBackground(Color.DARK_GRAY);
                                     auxChat.add(chatStanga,BorderLayout.WEST);
                                     auxChat.add(chatCentru,BorderLayout.CENTER);
                                     auxChat.add(down,BorderLayout.SOUTH);

                                     //try {
                                       //  TimeUnit.SECONDS.sleep(5);}catch (InterruptedException ie) {
                                         //Thread.currentThread().interrupt();
                                     //}
                                     auxChat.revalidate();
                                     auxChat.repaint();
                                     auxChat.setAlignmentY(Component.CENTER_ALIGNMENT);

                                 }  catch(SQLException sqlex) {
                                     System.err.println("An SQL Exception occured. Details below:");
                                     sqlex.printStackTrace(System.err);
                                 }
                             }});
                         }


                    JLabel Paraseste=new JLabel("Paraseste");
                    Toate.setBackground(Color.ORANGE);
                    JLabel Grup=new JLabel("     Grup");
                    Cursurile.setBackground(Color.ORANGE);
                    JButton parasesteCurs=new JButton();
                    parasesteCurs.setLayout(new GridLayout(2,1));
                    parasesteCurs.add(Paraseste);
                    parasesteCurs.add(Grup);
                    parasesteCurs.setBackground(Color.ORANGE);
                    auxGrupuri.add(new JLabel());
                    auxGrupuri.add(parasesteCurs);
                    parasesteCurs.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Statement selectStatement4 = null;
                            try{
                                Connection connection4 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                ResultSet rs4 = null;
                                ResultSetMetaData rsmd4 = null;
                                String[] columnNames = {"Materia", "Desfasurare"};

                                JTable tab4=new JTable(data,columnNames);
                                JPanel aux4=new JPanel();
                                aux4.setBackground(Color.darkGray);
                                aux4.add(tab4);

                                dreapta.removeAll();

                                dreapta.setLayout(new GridLayout(4,1));
                                dreapta.add(new JLabel(" "));
                                dreapta.add(aux4);
                                tab4.setBackground(Color.orange);
                                tab4.setForeground(Color.black);
                                tab4.setAlignmentX(Component.CENTER_ALIGNMENT);
                                tab4.setGridColor(Color.black);

                                JPanel inscriere= new JPanel();
                                JLabel inscriere1=new JLabel("ID Grup: ");
                                inscriere1.setForeground(Color.ORANGE);
                                JTextField inscriere2=new JTextField(15);
                                JButton inscriere3 = new JButton("Submit");
                                inscriere3.setBackground(Color.orange);
                                inscriere.setBackground(Color.darkGray);
                                inscriere.add(inscriere1);
                                inscriere.add(inscriere2);
                                inscriere.add(inscriere3);

                                inscriere3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String id = inscriere2.getText();
                                        String numeCurs = null;
                                        String cnpProf;
                                        String numeMaterie;

                                        try {
                                            Connection connection5 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                            ResultSet rs5 = null;
                                            Statement selectStatement5 = null;
                                            selectStatement5 = connection5.createStatement();
                                            System.out.println("SELECT distinct materia FROM grupuri Where id_grup="+ id + ";");
                                            selectStatement5.execute("SELECT distinct materia FROM grupuri Where id_grup="+ id + ";");
                                            rs5 = selectStatement5.getResultSet();
                                            rs5.next();
                                            numeMaterie = rs5.getString("materia");

                                            Connection connection6= DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                            Statement selectStatement6 = null;
                                            selectStatement6 = connection6.createStatement();
                                            selectStatement6.execute("INSERT INTO grupuri (id_grup, studenti_persoane_CNP, materia) VALUES ('2', '1991010234567', 'Analiza Matematica');");
                                            grupuri.doClick();
                                        }
                                        catch(SQLException sqlex) {
                                            System.err.println("An SQL Exception occured. Details below:");
                                            sqlex.printStackTrace(System.err);
                                            JOptionPane.showMessageDialog(inscriere2,"Sunteti deja inscris la acest grup!");
                                        }
                                    }
                                });


                                dreapta.add(inscriere);

                                dreapta.revalidate();
                                dreapta.repaint();
                                tab4.setPreferredSize(new Dimension(600,250));
                                dreapta.setAlignmentY(Component.CENTER_ALIGNMENT);

                            }  catch(SQLException sqlex) {
                                System.err.println("An SQL Exception occured. Details below:");
                                sqlex.printStackTrace(System.err);
                            }
                        }
                    });


                    dreapta.removeAll();


                    dreapta.add(auxGrupuri,BorderLayout.WEST);
                    dreapta.add(auxChat,BorderLayout.CENTER);
                    dreapta.add(auxParticipanti,BorderLayout.EAST);

                    dreapta.revalidate();
                    dreapta.repaint();
                    dreapta.setAlignmentY(Component.CENTER_ALIGNMENT);

                    if (control==1) {
                        ((JButton) butoane[pozitie]).doClick();
                        control=0;
                    }

                }  catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details below:");
                    sqlex.printStackTrace(System.err);
                }
            }
        });

        ////////////////////////////////////////////////////////////////////////////////

        dateleMele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JPanel auxSelectare=new JPanel();
                auxSelectare.setBackground(Color.darkGray);


                JPanel auxSusSelectare=new JPanel();
                auxSusSelectare.setLayout(new GridLayout(3,1));
                auxSusSelectare.setBackground(Color.darkGray);
                auxSusSelectare.add(new JLabel(" "));
                auxSusSelectare.add(auxSelectare);

                        Statement selectStatement1 = null;
                        try{
                            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                            ResultSet rs = null;
                            ResultSetMetaData rsmd = null;


                            selectStatement1 = connection1.createStatement();
                            selectStatement1.execute("SELECT * FROM persoane where cnp='"+cnp+"'");
                            rs = selectStatement1.getResultSet();
                            rs.next();



                            JLabel cnpG=new JLabel("CNP"); cnpG.setForeground(Color.ORANGE);
                            JLabel cnpN=new JLabel("NUME"); cnpN.setForeground(Color.ORANGE);
                            JLabel cnpP=new JLabel("PRENUME");cnpP.setForeground(Color.ORANGE);
                            JLabel cnpA=new JLabel("ADRESA");cnpA.setForeground(Color.ORANGE);
                            JLabel cnpT=new JLabel("TELEFON");cnpT.setForeground(Color.ORANGE);
                            JLabel cnpE=new JLabel("EMAIL");cnpE.setForeground(Color.ORANGE);
                            JLabel cnpI=new JLabel("IBAN");cnpI.setForeground(Color.ORANGE);
                            JLabel cnpC=new JLabel("NR CONTRACT");cnpC.setForeground(Color.ORANGE);
                            JLabel cnpF=new JLabel("FUNCTIA");cnpF.setForeground(Color.ORANGE);

                            JTextField cnpGasit=new JTextField(rs.getString("CNP"),20);
                            cnpGasit.setEditable(false);
                            JTextField numeGasit=new JTextField(rs.getString("nume"),20);
                            numeGasit.setEditable(false);
                            JTextField prenumeGasit=new JTextField(rs.getString("prenume"),20);
                            prenumeGasit.setEditable(false);
                            JTextField adresaGasit=new JTextField(rs.getString("adresa"),20);
                            adresaGasit.setEditable(false);
                            JTextField telefonGasit=new JTextField("0"+rs.getString("nr_tel"),20);
                            telefonGasit.setEditable(false);
                            JTextField emailGasit=new JTextField(rs.getString("email"),20);
                            emailGasit.setEditable(false);
                            JTextField ibanGasit=new JTextField(rs.getString("IBAN"),20);
                            ibanGasit.setEditable(false);
                            JTextField nrcontractGasit=new JTextField(rs.getString("nr_contract"),20);
                            nrcontractGasit.setEditable(false);
                            JTextField functiaGasit=new JTextField(rs.getString("functia"),20);
                            functiaGasit.setEditable(false);


                            JPanel auxGasit=new JPanel();
                            auxGasit.setLayout(new GridLayout(10,4));
                            auxGasit.removeAll();
                            auxGasit.revalidate();
                            auxGasit.repaint();

                            JButton modifica=new JButton("Modifica");
                            modifica.setBackground(Color.ORANGE);

                            ///////////////////////

                            JPanel auxStergere=new JPanel();
                            auxGasit.removeAll();


                            auxGasit.add(new JLabel(""));auxGasit.add(cnpG);auxGasit.add(cnpGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpN);auxGasit.add(numeGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpP);auxGasit.add(prenumeGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpA);auxGasit.add(adresaGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpT);auxGasit.add(telefonGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpE);auxGasit.add(emailGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpI);auxGasit.add(ibanGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpC);auxGasit.add(nrcontractGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpF);auxGasit.add(functiaGasit); auxGasit.add(new JLabel(""));

                            auxGasit.setBackground(Color.darkGray);


                            auxStergere.setLayout(new GridLayout(1,1));
                            auxStergere.add(auxGasit);

                            dreapta.removeAll();
                            dreapta.add(auxStergere);
                            dreapta.revalidate();
                            dreapta.repaint();


                        }
                        catch(SQLException sqlex) {
                            System.err.println("An SQL Exception occured. Details below:");
                            sqlex.printStackTrace(System.err);

                        }
                    }
                });

        //////////////////////////////////////////////////////////////////////////////

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.first(cards);
            }
        });

    }
}
