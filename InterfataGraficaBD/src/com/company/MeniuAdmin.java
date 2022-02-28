package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.*;

public class MeniuAdmin {
    JPanel principal;
    String cnp;
    JPanel stanga;
    String numePrenume;

    MeniuAdmin(CardLayout cardLayout, JPanel cards) {

        JPanel dreapta = new JPanel();
        dreapta.setBackground(Color.darkGray);

        //BorderLayout
        this.principal = new JPanel(new BorderLayout());
        principal.setPreferredSize(new Dimension(1024, 600));
        principal.setBackground(Color.darkGray);

        stanga = new JPanel(new BorderLayout());
        JPanel auxStanga = new JPanel(new GridLayout(14, 1));
        stanga.setPreferredSize(new Dimension(120, 100));
        stanga.setBackground(Color.gray);


        JPanel sus = new JPanel(new GridLayout(5, 1));
        sus.setPreferredSize(new Dimension(100, 80));
        sus.setBackground(Color.DARK_GRAY);
        sus.setLayout(new BorderLayout());

        String auxLabel = "Bine ai revenit! ";
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

        JButton dateleMele = new JButton("Date Pers");
        dateleMele.setBackground(Color.orange);

        ///Butoane
        JButton cautaPersoana = new JButton("Utilizatori");
        cautaPersoana.setBackground(Color.orange);
        cautaPersoana.setFocusable(false);

        JButton cautaCursuri = new JButton("Cursuri");
        cautaCursuri.setBackground(Color.orange);


        JButton logout = new JButton("Log out");
        logout.setBackground(Color.orange);


        ///Meniul din stanga
        auxStanga.add(new JLabel(""));
        auxStanga.add(dateleMele);
        auxStanga.add(new JLabel(""));
        auxStanga.add(cautaPersoana);
        auxStanga.add(new JLabel(""));
        auxStanga.add(cautaCursuri);
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
        principal.add(dreapta, BorderLayout.CENTER);
        principal.add(sus, BorderLayout.NORTH);

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
        ////////////////////////////////////////////
        cautaPersoana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JLabel numeD=new JLabel("Nume");
                numeD.setForeground(Color.ORANGE);
                JLabel prenumeD=new JLabel("Prenume");
                prenumeD.setForeground(Color.ORANGE);
                JTextField numeDat=new JTextField(10);
                JTextField prenumeDat=new JTextField(10);
                JButton submit=new JButton("Submit");
                submit.setBackground(Color.ORANGE);

                JPanel auxSelectare=new JPanel();
                auxSelectare.setBackground(Color.darkGray);

                auxSelectare.add(numeD);
                auxSelectare.add(numeDat);
                auxSelectare.add(prenumeD);
                auxSelectare.add(prenumeDat);
                auxSelectare.add(submit);

                JPanel auxSusSelectare=new JPanel();
                auxSusSelectare.setLayout(new GridLayout(3,1));
                auxSusSelectare.setBackground(Color.darkGray);
                auxSusSelectare.add(new JLabel(" "));
                auxSusSelectare.add(auxSelectare);

                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Statement selectStatement1 = null;
                        try{
                            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                            ResultSet rs = null;
                            ResultSetMetaData rsmd = null;


                            selectStatement1 = connection1.createStatement();
                            selectStatement1.execute("call CAUTARE_UTILIZATOR ('"+numeDat.getText()+"','"+prenumeDat.getText()+"')");
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
                            JTextField prenumeGasit=new JTextField(rs.getString("prenume"),20);
                            JTextField adresaGasit=new JTextField(rs.getString("adresa"),20);
                            JTextField telefonGasit=new JTextField("0"+rs.getString("nr_tel"),20);
                            JTextField emailGasit=new JTextField(rs.getString("email"),20);
                            JTextField ibanGasit=new JTextField(rs.getString("IBAN"),20);
                            JTextField nrcontractGasit=new JTextField(rs.getString("nr_contract"),20);
                            JTextField functiaGasit=new JTextField(rs.getString("functia"),20);
                            String functia= new String((String)rs.getString("functia"));


                            JPanel auxGasit=new JPanel();
                            auxGasit.setLayout(new GridLayout(10,4));
                            auxGasit.removeAll();
                            auxGasit.revalidate();
                            auxGasit.repaint();

                            JButton modifica=new JButton("Modifica");
                            modifica.setBackground(Color.ORANGE);


                            //Mi//////////////

                            modifica.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    Statement selectStatement2 = null;
                                    try {
                                        Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                        selectStatement2 = connection2.createStatement();

                                        if(functia.equals("administrator") || functia.equals("super-administrator"))
                                            JOptionPane.showMessageDialog(modifica,"Doar un super-administrator poate edita un administrator");
                                        else {
                                            selectStatement2.execute("update persoane set nume='" + numeGasit.getText() +
                                                    "', prenume='" + prenumeGasit.getText() + "', adresa='" + adresaGasit.getText() + "', nr_tel='" + telefonGasit.getText() +
                                                    "', email='" + emailGasit.getText() + "', IBAN='" + ibanGasit.getText() + "', nr_contract='" + nrcontractGasit.getText() +
                                                    "', functia='" + functiaGasit.getText() + "' where CNP='" + cnpGasit.getText() + "'");
                                            submit.doClick();
                                        }



                                    } catch (SQLException sqlex) {
                                        System.err.println("An SQL Exception occured. Details below:");
                                        sqlex.printStackTrace(System.err);
                                    }
                                }
                            });


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
                            dreapta.add(auxSusSelectare);
                            dreapta.add(auxStergere);
                            dreapta.revalidate();
                            dreapta.repaint();



                            auxGasit.add(new JLabel(" "));auxGasit.add(new JLabel(" "));auxGasit.add(modifica);

                        }
                        catch(SQLException sqlex) {
                            System.err.println("An SQL Exception occured. Details below:");
                            sqlex.printStackTrace(System.err);

                        }
                    }
                });


                dreapta.removeAll();


                dreapta.setLayout(new GridLayout(3,1));
                dreapta.add(auxSusSelectare);


                dreapta.revalidate();
                dreapta.repaint();
                dreapta.setAlignmentY(Component.CENTER_ALIGNMENT);

            }
        });
        ////////////////////////////////////////////////////

        cautaCursuri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JLabel numeCurs=new JLabel("Nume Curs");
                numeCurs.setForeground(Color.ORANGE);
                JTextField numeCursDat=new JTextField(10);
                JButton submit=new JButton("Submit");
                submit.setBackground(Color.ORANGE);

                JPanel auxSelectare=new JPanel();
                auxSelectare.setBackground(Color.darkGray);

                auxSelectare.add(numeCurs);
                auxSelectare.add(numeCursDat);
                auxSelectare.add(submit);

                JPanel auxSusSelectare=new JPanel();
                auxSusSelectare.setLayout(new GridLayout(3,1));
                auxSusSelectare.setBackground(Color.darkGray);
                auxSusSelectare.add(new JLabel(" "));
                auxSusSelectare.add(auxSelectare);

                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Statement selectStatement1 = null;
                        try{
                            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                            ResultSet rs = null;
                            ResultSetMetaData rsmd = null;


                            selectStatement1 = connection1.createStatement();
                            selectStatement1.execute("call CAUTARE_CURS ('"+numeCursDat.getText()+"')");
                            rs = selectStatement1.getResultSet();
                            rs.next();



                            JLabel cnpG=new JLabel("ID CURS"); cnpG.setForeground(Color.ORANGE);
                            JLabel cnpN=new JLabel("NUME PROFESOR"); cnpN.setForeground(Color.ORANGE);
                            JLabel cnpP=new JLabel("PRENUME PROFESOR");cnpP.setForeground(Color.ORANGE);
                            JLabel cnpC=new JLabel("NUME CURS");cnpC.setForeground(Color.ORANGE);
                            JLabel cnpD=new JLabel("DESCRIERE");cnpD.setForeground(Color.ORANGE);
                            JLabel cnpPC=new JLabel("PONDERE CURS");cnpPC.setForeground(Color.ORANGE);
                            JLabel cnpPS=new JLabel("PONDERE SEMINAR");cnpPS.setForeground(Color.ORANGE);
                            JLabel cnpPL=new JLabel("PONDERE LABORATOR");cnpPL.setForeground(Color.ORANGE);


                            JTextField idGasit=new JTextField(rs.getString("id_curs"),20);
                            idGasit.setEditable(false);

                            String cnpProf=rs.getString("profesori_persoane_cnp");
                            ////////////////////////////////////////////////////////////////////////////////// cnp si id
                            int idCurs = Integer.parseInt(idGasit.getText());

                            JTextField numeGasit=new JTextField(rs.getString("nume_curs"),20);
                            JTextField descriereGasit=new JTextField(rs.getString("descriere"),20);
                            JTextField pcGasit=new JTextField(rs.getString("pondere_curs"),20);
                            JTextField psGasit=new JTextField(rs.getString("pondere_seminar"),20);
                            JTextField plGasit=new JTextField(rs.getString("pondere_laborator"),20);

                            selectStatement1.execute("call CAUTARE_Profesor_CURS ('"+cnpProf+"')");
                            rs = selectStatement1.getResultSet();
                            rs.next();
                            JTextField numePofGasit=new JTextField(rs.getString("nume"),20);
                            JTextField prenumeProfeGasit=new JTextField(rs.getString("prenume"),20);



                            JPanel auxGasit=new JPanel();
                            auxGasit.setLayout(new GridLayout(10,4));
                            auxGasit.removeAll();
                            auxGasit.revalidate();
                            auxGasit.repaint();

                            JButton modifica=new JButton("Modifica");
                            modifica.setBackground(Color.ORANGE);


                            //Mi/////////////

                            modifica.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    Statement selectStatement2 = null;
                                    try {
                                        Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                        selectStatement2 = connection2.createStatement();
                                        selectStatement2.execute("update cursuri set nume_curs='" + numeGasit.getText() +
                                                "', descriere='" + descriereGasit.getText() + "', pondere_curs='" + pcGasit.getText() + "', pondere_seminar='" + psGasit.getText() +
                                                "', pondere_laborator='" + plGasit.getText()  + "' where id_curs='" + idGasit.getText() +"'");
                                        submit.doClick();



                                    } catch (SQLException sqlex) {
                                        System.err.println("An SQL Exception occured. Details below:");
                                        sqlex.printStackTrace(System.err);
                                    }
                                }
                            });


                            //////////////////////

                            JPanel auxStergere=new JPanel();
                            auxGasit.removeAll();


                            auxGasit.add(new JLabel(""));auxGasit.add(cnpG);auxGasit.add(idGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpN);auxGasit.add(numePofGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpP);auxGasit.add(prenumeProfeGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpC);auxGasit.add(numeGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpD);auxGasit.add(descriereGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpPC);auxGasit.add(pcGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpPS);auxGasit.add(psGasit); auxGasit.add(new JLabel(""));
                            auxGasit.add(new JLabel(""));auxGasit.add(cnpPL);auxGasit.add(plGasit); auxGasit.add(new JLabel(""));


                            auxGasit.setBackground(Color.darkGray);


                            auxStergere.setLayout(new GridLayout(1,1));
                            auxStergere.add(auxGasit);

                            dreapta.removeAll();
                            dreapta.add(auxSusSelectare);
                            dreapta.add(auxStergere);
                            dreapta.revalidate();
                            dreapta.repaint();

                            JButton listaStudenti=new JButton("Lista Studenti");
                            listaStudenti.setBackground(Color.ORANGE);

                            auxGasit.add(new JLabel(" "));auxGasit.add(listaStudenti);auxGasit.add(modifica);




                            listaStudenti.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    dreapta.removeAll();
                                    dreapta.revalidate();
                                    dreapta.repaint();


                                    Statement selectStatement2 = null;
                                    try {
                                        Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                                        selectStatement2 = connection2.createStatement();
                                        submit.doClick();
                                        //////////////////////////

                                        ResultSet rs = null;
                                        ResultSetMetaData rsmd = null;
                                        String[] columnNames = {"NUME", "PRENUME"};

                                        Object[][] data = new Object[10][2];
                                        data[0][0] = new String("NUME");
                                        data[0][1] = new String("PRENUME");

                                        selectStatement2 = connection2.createStatement();
                                        selectStatement2.execute("call CAUTARE_studenti_CURS('" + cnpProf + "','"+idCurs+"')");
                                        rs = selectStatement2.getResultSet();

                                        int rowCount = 1;
                                        while (rs.next()) {
                                            System.out.println("aici");
                                            data[rowCount][0] = rs.getString("nume");
                                            data[rowCount][1] = rs.getString("prenume");

                                            System.out.println(data[rowCount][0]);
                                            rowCount++;
                                        }
                                        JTable tab1 = new JTable(data, columnNames);
                                        JPanel aux1 = new JPanel();
                                        aux1.setBackground(Color.darkGray);
                                        aux1.add(tab1);
                                        dreapta.removeAll();
                                        dreapta.add(aux1);
                                        dreapta.revalidate();
                                        dreapta.repaint();

                                        /////////////////////////////









                                    } catch (SQLException sqlex) {
                                        System.err.println("An SQL Exception occured. Details below:");
                                        sqlex.printStackTrace(System.err);
                                    }


                                }
                            });



                        }
                        catch(SQLException sqlex) {
                            System.err.println("An SQL Exception occured. Details below:");
                            sqlex.printStackTrace(System.err);
                            JOptionPane.showMessageDialog(dreapta, "Cursul nu exista :(");

                        }
                    }
                });


                dreapta.removeAll();


                dreapta.setLayout(new GridLayout(3,1));
                dreapta.add(auxSusSelectare);


                dreapta.revalidate();
                dreapta.repaint();
                dreapta.setAlignmentY(Component.CENTER_ALIGNMENT);

            }
        });


        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.first(cards);
            }
        });


    }
}