/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;  
import java.sql.ResultSet;




    

/**
 *
 * @author oabdo
 */


public class main {           

    static Connection c; 
    static Statement ss; 
    static String query; 
    PreparedStatement  ps;
    ResultSet  rs;
    public static void main (String [] args) throws SQLException{

        DBconnect c1 = new DBconnect();
       
        
        c= c1.LogIn();
       /* ss=c.createStatement();
        query ="insert into slots (slot_status) values ('Emtpy')";
        ss.execute(query);}
       catch(SQLException ee){
           System.out.println(ee.getMessage());*/
       }
        /*finally {
            try{
                c.close();
            }catch(SQLException ee){
                System.out.println(ee.getMessage());
                }
        }*/
    }
   
    

