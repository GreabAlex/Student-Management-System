package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MeniuAutentificare  {

    String cnpLogat=new String();
    String numeLogat=new String();
    String prenumeLogat=new String();
    JPanel principal;
    JButton submit;

    MeniuAutentificare(CardLayout cardLayout,JPanel cards,MeniuProfesor prof,MenuStudent stud,MeniuAdmin admin, MeniuSuperAdmin superAdmin) {
        ///Creare Panou principal
        this.principal = new JPanel();
        principal.setPreferredSize(new Dimension(1024,600));
        principal.setMaximumSize(new Dimension(1920,1080));
        principal.setBackground(Color.darkGray);

        ///Data meniu principal
        Date data = new Date();
        SimpleDateFormat formatdata = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dataOra = formatdata.format(data);

        JPanel panouMesajIntampinare=new JPanel();
        panouMesajIntampinare.setBackground(Color.darkGray);
        JPanel panouLogo=new JPanel();
        panouLogo.setBackground(Color.darkGray);

        GridLayout format= new GridLayout(5,1);
        principal.setLayout(format);


        JLabel mesajIntampinare=new JLabel("Bun venit la Scoala APA");
        mesajIntampinare.setForeground(Color.ORANGE);
        mesajIntampinare.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel timp=new JLabel(dataOra);
        timp.setForeground(Color.ORANGE);
        timp.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel mesajLogare=new JLabel("Va rugam sa va autentificati!");
        mesajLogare.setForeground(Color.ORANGE);
        mesajLogare.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon logoScoala=new ImageIcon("logoScoala.png");
        Image img= logoScoala.getImage();
        img=img.getScaledInstance(110,90, Image.SCALE_SMOOTH);
        logoScoala=new ImageIcon(img);
        JLabel logo=new JLabel();
        logo.setIcon(logoScoala);
        logo.setHorizontalAlignment(SwingConstants.CENTER);


        panouMesajIntampinare.setLayout(new GridLayout(9,1));
        panouMesajIntampinare.add(new JLabel());
        panouMesajIntampinare.add(new JLabel());
        panouMesajIntampinare.add(mesajIntampinare);
        panouMesajIntampinare.add(timp);
        panouMesajIntampinare.add(mesajLogare);
        panouLogo.add(logo);
        
        principal.add(new JLabel());
        principal.add(panouLogo);
        principal.add(panouMesajIntampinare);

        JPanel panel;
        JLabel user_label, password_label;
        JTextField userName_text;
        JPasswordField password_text;
        JButton submit;

        // User Label
        // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField(15);

        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField(15);

        // Submit


        submit = new JButton("Autentificare");

        panel = new JPanel(new GridLayout(6, 3));
        panel.setBackground(Color.darkGray);
        panel.setForeground(Color.ORANGE);
        panel.add(new JLabel());
        panel.add(user_label);
        panel.add(new JLabel());
        user_label.setForeground(Color.ORANGE);
        user_label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(new JLabel());
        panel.add(userName_text);
        panel.add(new JLabel());
        userName_text.setSize(new Dimension(300,100));
        panel.add(new JLabel());
        panel.add(password_label);
        panel.add(new JLabel());
        password_label.setForeground(Color.ORANGE);
        password_label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(new JLabel());
        panel.add(password_text);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());


        JPanel aux=new JPanel(new GridLayout(1,3));
        aux.setBackground(Color.darkGray);
        aux.add(new JLabel());
        aux.add(submit);
        aux.add(new JLabel());

        panel.add(aux);

        panel.setMaximumSize(new Dimension(300,100));
        principal.add(panel);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume=new String(userName_text.getText());
                String parola=new String(password_text.getPassword());
                //if (nume.equals("stud1") && parola.equals("stud1"))
                  //  cardLayout.next(cards);
                //else if (nume.equals("prof1") && parola.equals("prof1")) {
                  //  cardLayout.next(cards);
                    //cardLayout.next(cards);
                //}
                //else

                Statement selectStatement = null;
                try{
                    Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost/proiectbd?user=root&password=root");
                    ResultSet rs = null;
                    ResultSetMetaData rsmd = null;

                    Object[][] data=new Object[20][4];

                    selectStatement = connection1.createStatement();
                    selectStatement.execute("select nume, prenume , functia, cnp from persoane;");
                    rs = selectStatement.getResultSet();

                    int rowCount = 0;
                    int ok=0;
                    while(rs.next()){
                        data[rowCount][0]=rs.getString("nume");
                        data[rowCount][1]=rs.getString("prenume");
                        data[rowCount][2]=rs.getString("functia");
                        data[rowCount][3]=rs.getString("cnp");
                        if(nume.equals( ((String)data[rowCount][0])+((String)data[rowCount][1]) ))
                        {
                            ok=1;
                            break;
                        }
                        else
                           rowCount++;
                    }
                    String cnp=(String)data[rowCount][3];
                    String cnpAux=cnp.substring(0,3);


                    int pass=1;
                    if(ok==0 || !(cnpAux.equals(parola))) {
                        JOptionPane.showMessageDialog(panouMesajIntampinare, "Nume sau parola incorecte");
                        pass=0;
                    }
                    else if ( ((String)data[rowCount][2]).equals("student")) {
                        //System.out.println((String)data[rowCount][2]);
                        cardLayout.next(cards);
                        stud.cnp=(String)data[rowCount][3];
                        stud.numePrenume=(String)data[rowCount][2];
                    }
                    else if (((String)data[rowCount][2]).equals("profesor")){
                        cardLayout.next(cards);
                        cardLayout.next(cards);
                        prof.cnp=(String)data[rowCount][3];
                        prof.numePrenume=(String)data[rowCount][2];
                    }else if (((String)data[rowCount][2]).equals("administrator")){
                        cardLayout.next(cards);
                        cardLayout.next(cards);
                        cardLayout.next(cards);
                        admin.cnp=(String)data[rowCount][3];
                        admin.numePrenume=(String)data[rowCount][2];
                    }else if (((String)data[rowCount][2]).equals("super-administrator")) {
                        cardLayout.next(cards);
                        cardLayout.next(cards);
                        cardLayout.next(cards);
                        cardLayout.next(cards);
                        superAdmin.cnp=(String)data[rowCount][3];
                        superAdmin.numePrenume=(String)data[rowCount][2];
                    }
                    if(pass==1){
                        cnpLogat=cnp;
                        System.out.println(cnpLogat);
                        numeLogat=((String)data[rowCount][0]);
                        System.out.println(numeLogat);
                        prenumeLogat=((String)data[rowCount][1]);
                        System.out.println(prenumeLogat);
                    }

                }
                catch(SQLException sqlex) {
                    System.err.println("An SQL Exception occured. Details below:");
                    sqlex.printStackTrace(System.err);
                }
            }
        });

        principal.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

}
