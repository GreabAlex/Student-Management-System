package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {


    public static void main(String[] args) {


    //deschide fereastra

        JFrame framePrincipal=new JFrame("ScoalaAPA");
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.setSize(1024, 600);

    //logoFereastra
        ImageIcon logoScoala=new ImageIcon("logoScoala.png");
        Image img= logoScoala.getImage();
        framePrincipal.setIconImage(img);

        CardLayout layout=new CardLayout();
        JPanel cards=new JPanel(layout);


        Conectare test= new Conectare();


        MeniuAdmin primaPaginaAdmin=new MeniuAdmin(layout,cards);

        MenuStudent primaPaginaStudent = new MenuStudent(layout, cards);

        MeniuProfesor primaPaginaProfesor = new MeniuProfesor(layout, cards);

        MeniuSuperAdmin primaPaginaSuperAdmin=new MeniuSuperAdmin(layout, cards);

        MeniuAutentificare paginaLogIn=new MeniuAutentificare(layout, cards,primaPaginaProfesor,primaPaginaStudent,primaPaginaAdmin,primaPaginaSuperAdmin);

        framePrincipal.getContentPane().setBackground(Color.darkGray);

        JPanel cards1= new JPanel();
        JPanel cards2= new JPanel();
        JPanel cards3= new JPanel();
        JPanel cards4= new JPanel();
        JPanel cards5= new JPanel();

        cards1.add(paginaLogIn.principal);
        cards2.add(primaPaginaStudent.principal);
        cards3.add(primaPaginaProfesor.principal);
        cards4.add(primaPaginaAdmin.principal);
        cards5.add(primaPaginaSuperAdmin.principal);

        cards.add(cards1);
        cards.add(cards2);
        cards.add(cards3);
        cards.add(cards4);
        cards.add(cards5);

        layout.next(cards);
        layout.next(cards);
        layout.next(cards);
        layout.next(cards);
        layout.next(cards);

        framePrincipal.add(cards);

        framePrincipal.setVisible(true);

    }
}
