package com.company;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.*;

public class MeniuProfesor {
    JPanel principal;
    String cnp;
    JPanel stanga;
    String numePrenume;

    MeniuProfesor(CardLayout cardLayout, JPanel cards) {

        JPanel dreapta=new JPanel();
        dreapta.setBackground(Color.darkGray);

        //BorderLayout
        this.principal = new JPanel(new BorderLayout());
        principal.setPreferredSize(new Dimension(1024, 600));
        principal.setBackground(Color.darkGray);

        stanga = new JPanel(new BorderLayout());
        JPanel auxStanga = new JPanel(new GridLayout(18, 1));
        stanga.setPreferredSize(new Dimension(120, 100));
        stanga.setBackground(Color.gray);


        JPanel sus = new JPanel(new GridLayout(5, 1));
        sus.setPreferredSize(new Dimension(100, 80));
        sus.setBackground(Color.DARK_GRAY);
        sus.setLayout(new BorderLayout());

        String auxLabel="Bine ai revenit! ";
        JLabel bunVenit = new JLabel(auxLabel);
        bunVenit.setForeground(Color.ORANGE);
        sus.add(bunVenit, BorderLayout.CENTER);


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
        JButton dateleMele = new JButton("Date Pers");
        dateleMele.setBackground(Color.orange);

        JButton activitati = new JButton("Activitati");
        activitati.setBackground(Color.orange);
        activitati.setFocusable(false);

        JButton cursuri = new JButton("Cursuri");
        cursuri.setBackground(Color.orange);

        JButton catalog = new JButton("Catalog");
        catalog.setBackground(Color.orange);

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
        auxStanga.add(catalog);
        auxStanga.add(new JLabel(""));
        auxStanga.add(notificari);
        auxStanga.add(new JLabel(""));
        auxStanga.add(logout);

        auxStanga.setBackground(Color.GRAY);
        stanga.add(auxStanga, BorderLayout.CENTER);


        JPanel aux2Stanga = new JPanel();
        stanga.add(aux2Stanga, BorderLayout.WEST);
        aux2Stanga.setPreferredSize(new Dimension(15, 10));
        aux2Stanga.setBackground(Color.GRAY);

        JPanel aux3Stanga = new JPanel();
        stanga.add(aux3Stanga, BorderLayout.EAST);
        aux3Stanga.setPreferredSize(new Dimension(15, 10));
        aux3Stanga.setBackground(Color.GRAY);


        bunVenit.setHorizontalAlignment(SwingConstants.CENTER);

        principal.add(stanga, BorderLayout.WEST);
        principal.add(dreapta,BorderLayout.CENTER);
        principal.add(sus, BorderLayout.NORTH);

        ////////////////////////////////////////////////////
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.first(cards);
            }
        });

        /////////////////////////////////////////////////////

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
                    dreapta.add(new JLabel(""));
                    dreapta.add(new JLabel(""));
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

        ///////////////////////////////////////////////

        activitati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement selectStatement = null;
                try{
                    Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                    ResultSet rs = null;
                    ResultSetMetaData rsmd = null;
                    String[] columnNames = {"Nume Curs", "Tipul Activitatii", "Ziua", "Ora inceput", "Ora final"};

                    Object[][] data=new Object[10][5];
                    data[0][0]=new String("NUME CURS"); data[0][1]=new String("TIPUL");
                    data[0][2]=new String("ZIUA");      data[0][3]=new String("ORA INCEPUT");
                    data[0][4]=new String("ORA FINAL");

                    selectStatement = connection1.createStatement();
                    selectStatement.execute("Call AFISARE_ACTIVITATI_PROFESORI("+cnp+")");
                    rs = selectStatement.getResultSet();

                    int rowCount = 1;
                        while(rs.next()){
                        data[rowCount][0]=rs.getString("nume_curs"); data[rowCount][1]=rs.getString("tip_activitate");
                        data[rowCount][2]=rs.getString("recurenta"); data[rowCount][3]=rs.getString("ora_inceput");
                        data[rowCount][4]=rs.getString("ora_final");
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

                    JPanel auxDownload=new JPanel();
                    auxDownload.setBackground(Color.darkGray);
                    JButton download=new JButton("Download");
                    download.setBackground(Color.orange);
                    auxDownload.add(download);

                    dreapta.add(auxDownload);
                    download.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                FileWriter fisier = new FileWriter("DownloadActivitatiProfesori");
                                int i=0;
                                while (data[i][0]!=null){
                                    fisier.write((String)data[i][0]+" "+(String)data[i][1]+" "+(String)data[i][2]+
                                            " "+(String)data[i][3]+" "+(String)data[i][4]+" \n");
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

                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details below:");
                    sqlex.printStackTrace(System.err);
                }


            }
        });

        ///////////////////////////////////////////////////

        cursuri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Statement selectStatement = null;
                    try {

                            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                            ResultSet rs = null;
                            ResultSetMetaData rsmd = null;
                            String[] columnNames = {"ID CURS", "NUME CURS", "PONDERE CURS", "PONDERE SEMINAR", "Pondere lab"};

                            Object[][] data = new Object[10][5];
                            data[0][0] = new String("ID CURS");
                            data[0][1] = new String("NUME CURS");
                            data[0][2] = new String("PONDERE CURS");
                            data[0][3] = new String("PONDERE SEMINAR");
                            data[0][4] = new String("PONDERE LABORATOR");

                            selectStatement = connection1.createStatement();
                            selectStatement.execute("select id_curs, nume_curs , pondere_curs , pondere_seminar, pondere_laborator from cursuri where profesori_persoane_CNP='" + cnp + "'");
                            rs = selectStatement.getResultSet();

                            int rowCount = 1;
                            while (rs.next()) {
                                System.out.println("aici");
                                data[rowCount][0] = rs.getString("id_curs");
                                data[rowCount][1] = rs.getString("nume_curs");
                                data[rowCount][2] = rs.getString("pondere_curs");
                                data[rowCount][3] = rs.getString("pondere_seminar");
                                data[rowCount][4] = rs.getString("pondere_laborator");
                                System.out.println(data[rowCount][0]);
                                rowCount++;
                            }
                            JTable tab = new JTable(data, columnNames);
                            JPanel aux = new JPanel();
                            aux.setBackground(Color.darkGray);
                            aux.add(tab);

                            dreapta.removeAll();

                            dreapta.setLayout(new GridLayout(7, 1));
                            dreapta.add(new JLabel(" "));
                            dreapta.add(aux);
                            tab.setBackground(Color.orange);
                            tab.setForeground(Color.black);
                            tab.setAlignmentX(Component.CENTER_ALIGNMENT);
                            tab.setGridColor(Color.black);

                            JPanel adaugare = new JPanel();

                            JLabel labelID = new JLabel("Id Curs");
                            labelID.setBackground(Color.darkGray);
                            labelID.setForeground(Color.ORANGE);
                            JTextField idCurs = new JTextField(10);
                            adaugare.add(labelID);
                            adaugare.add(idCurs);

                            JLabel labelCurs = new JLabel("Curs");
                            labelCurs.setBackground(Color.darkGray);
                            labelCurs.setForeground(Color.ORANGE);
                            JTextField procentCurs = new JTextField(10);
                            adaugare.add(labelCurs);
                            adaugare.add(procentCurs);

                            JLabel labelSeminar = new JLabel("Seminar");
                            labelSeminar.setBackground(Color.darkGray);
                            labelSeminar.setForeground(Color.ORANGE);
                            JTextField procentSeminar = new JTextField(10);
                            adaugare.add(labelSeminar);
                            adaugare.add(procentSeminar);

                            JLabel labelLaborator = new JLabel("Laborator");
                            labelLaborator.setBackground(Color.darkGray);
                            labelLaborator.setForeground(Color.ORANGE);
                            JTextField procentLaborator = new JTextField(10);
                            adaugare.add(labelLaborator);
                            adaugare.add(procentLaborator);

                            JButton submit = new JButton("Submit");
                            submit.setBackground(Color.ORANGE);
                            adaugare.setBackground(Color.darkGray);
                            adaugare.add(submit);

                        submit.addActionListener(new ActionListener() {
                            @Override

                            public void actionPerformed(ActionEvent e) {
                                Statement selectStatement10 = null;
                                try {
                                    Connection connection10 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                    selectStatement10 = connection10.createStatement();
                                    Integer procent1 = new Integer(procentCurs.getText());
                                    Integer procent2 = new Integer(procentLaborator.getText());
                                    Integer procent3 = new Integer(procentSeminar.getText());

                                    //if (procent1+procent2+procent3)==100) {
                                        selectStatement10.execute("update cursuri set pondere_seminar=" + procentSeminar.getText() + ", pondere_laborator=" + procentLaborator.getText() +
                                                ", pondere_curs=" + procentCurs.getText() + " where id_curs=" + idCurs.getText());
                                        cursuri.doClick();
                                  // }
                                  //else
                                    //   JOptionPane.showMessageDialog(procentSeminar,"Suma procentelor trebuie sa fie 100");


                                } catch (SQLException sqlex) {
                                    System.err.println("An SQL Exception occured. Details below:");
                                    sqlex.printStackTrace(System.err);
                                }

                            }
                        });

                        JLabel auxFill = new JLabel();
                        auxFill.setBackground(Color.darkGray);

                        dreapta.add(auxFill);
                        dreapta.add(adaugare);
                        dreapta.revalidate();
                        dreapta.repaint();
                        tab.setPreferredSize(new Dimension(600, 150));
                        dreapta.setAlignmentY(Component.CENTER_ALIGNMENT);

                    } catch (SQLException sqlex) {
                        System.err.println("An SQL Exception occured. Details below:");
                        sqlex.printStackTrace(System.err);
                    }
                }

        });

        /////////////////////////////////////////////////////

        catalog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement selectStatement = null;
                try{
                    Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                    ResultSet rs = null;
                    ResultSetMetaData rsmd = null;
                    String[] columnNames = {"nume_curs", "Nume Prenume","Nota Curs","Nota Seminar","Nota Laborator"};

                    Object[][] data=new Object[10][5];
                    Object[][] dataAux=new Object[10][1];

                    data[0][0]=new String("NUME CURS"); data[0][1]=new String("NUME PRENUME");
                    data[0][2]=new String("CURS");      data[0][3]=new String("SEMINAR");
                    data[0][4]=new String("LABORATOR");

                    selectStatement = connection1.createStatement();
                    selectStatement.execute("Call AFISARE_STUDENTI_PROFESOR("+cnp+")");
                    rs = selectStatement.getResultSet();

                    int rowCount = 1;
                    while(rs.next()){
                        data[rowCount][0]=rs.getString("nume_curs");
                        data[rowCount][1]=(rs.getString("nume")+" "+rs.getString("prenume"));
                        if (rs.getString("nota_curs").equals("0"))
                           data[rowCount][2]="Adauga";
                        else
                           data[rowCount][2]=rs.getString("nota_curs");

                        if (rs.getString("nota_seminar").equals("0"))
                            data[rowCount][3]="Adauga";
                        else
                            data[rowCount][3]=rs.getString("nota_seminar");

                        if (rs.getString("nota_lab").equals("0"))
                            data[rowCount][4]="Adauga";
                        else
                            data[rowCount][4]=rs.getString("nota_lab");

                        dataAux[rowCount][0]=rs.getString("cnp");
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


                    JPanel auxDownload=new JPanel();
                    auxDownload.setBackground(Color.darkGray);
                    JButton download=new JButton("Download");
                    download.setBackground(Color.orange);
                    auxDownload.add(download);

                    dreapta.add(auxDownload);
                    download.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                FileWriter fisier = new FileWriter("DownloadCatalog");
                                int i=0;
                                while (data[i][0]!=null){
                                    fisier.write((String)data[i][0]+" | "+(String)data[i][1]+" \n");
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

                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details below:");
                    sqlex.printStackTrace(System.err);
                }
            }
        });
    }
}