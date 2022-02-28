package com.company;

import java.sql.*;

public class Conectare {

    public Connection connection = null;
   private Statement selectStatement = null, insertStatement = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;

    Conectare(){
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            }
        catch (Exception ex) {
            System.err.println("An Exception occured during JDBC Driver loading."
                    +
                    " Details are provided below:");
            ex.printStackTrace(System.err);}

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/proiect?user=root&password=root");

           /* selectStatement = connection.createStatement();
            selectStatement.execute("Call AFISARE_ACTIVITATI_PROFESORI(1991010234565)");
            rs = selectStatement.getResultSet();
            rsmd = rs.getMetaData();

            System.out.println("There are " + rsmd.getColumnCount() + " columns in the result set:");
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
                System.out.println("\t Column " + (i) + " is " + rsmd.getColumnName(i)); int rowCount = 0; while(rs.next()){
                System.out.println("Displaying information on row: " +
                        (++rowCount));
                System.out.println("\tnume_curs: " + rs.getString("nume_curs"));
                System.out.println("\ttip_activitate: " + rs.getString("tip_activitate"));
                System.out.println("\trecurenta: " + rs.getString("recurenta"));
                System.out.println("\tora_inceput: " + rs.getString("ora_inceput"));
                System.out.println("\tora_final: " + rs.getString("ora_final"));

        }*/

        }


        catch(SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details below:");
            sqlex.printStackTrace(System.err);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch(SQLException e) {
                }
                rs = null;
            }
            if (selectStatement != null) {
                try {
                    selectStatement.close();
                }
                catch(SQLException e) {}
                selectStatement = null;
            }
            if (insertStatement != null) {
                try {
                    insertStatement.close();
                }
                catch(SQLException e) {}
                insertStatement = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                }
                catch(SQLException e) {}
                connection = null; }}
    }

}
