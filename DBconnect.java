/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;
    
import java.sql.Statement;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
//import static javaapplication7.main.c;
import static javaapplication7.main.ss;
//import javax.swing.JOptionPane;

//import javax.swing.*;    



/**
 *
 * @author oabdo
 */
public class DBconnect {
    PreparedStatement  ps;
    ResultSet  rs;

    final private String userd="admin2";
    final private String passd="admin";
    private String add="jdbc:sqlserver://localhost:1433;databaseName=admindb";
    Scanner entry = new Scanner(System.in);

    
    public Connection Connect() throws SQLException{
        Connection r=DriverManager.getConnection(add, userd, passd);
        System.out.println(" successful");
        return r; 
    }
    
    public Connection LogIn() throws SQLException{
        int c=0; 
         System.out.println("Enter the username: ");
        String username2 =entry.nextLine();
        System.out.println("Enter the password: ");
        String password2 =entry.nextLine();
        Connection r=DriverManager.getConnection(add, userd, passd);
        Statement ss=r.createStatement();
        ArrayList <Admins> acc = new ArrayList();
        rs= ss.executeQuery("select users_name, users_password from users where user_role='Admin'");

         while(rs.next()){
            acc.add(new Admins(rs.getString("users_name"),rs.getString("users_password")));
         }
         String User= null;
         String Pass= null;
        for ( int j =0; j<acc.size()||acc.size()<=1; j++){
        if (!(acc.size()<1)){
             User= acc.get(j).user; 
         Pass= acc.get(j).passw;}
        
        
        if ((username2.equals(User)&&password2.equals(Pass))||(username2.equals("admin2")&&password2.equals("admin"))){
            c++;
        System.out.println("connection successful");
        System.out.println("Enter the opertation ID: \nAdd slots: 1\nview slots: 2\nAdd/del/Update users: 3\nView Parked cars: 4\nView Shift report: 5");
        int op =entry.nextInt();
        switch (op){
            case 1: 
            ss.execute("insert into slots (slot_status) values ('Empty')");
            break;
            case 2:     
                ArrayList <slotsTable> list = new ArrayList();
                rs= ss.executeQuery("select*from slots");
                while(rs.next()){
                    list.add(new slotsTable(rs.getInt("slot_id"),rs.getString("slot_status")));
                }
                System.out.println("ID \t STATUS");
                for (int i=0; i < list.size(); i++)
                System.out.println(list.get(i).id+" \t "+list.get(i).status);
                break;     
            case 3:
                System.out.println("Enter the role ID: \nAdmin: 1\nOperator: 2\nCostumer: 3 ");
                int Role_id = entry.nextInt();
                String Role = roleC(Role_id);
                System.out.println("Enter the role operation ID: \nAdd: 1\nDelete: 2\nUpdate: 3");
                int role_op = entry.nextInt();
                switch (role_op){
                    case 1:
                        System.out.println("Enter The username: ");
                        String usr = entry.next(); 
                        System.out.println("Enter The password: ");
                        String pswrd = entry.next(); 
                        ss.execute("insert into users (users_name, users_password, user_role) values ('"+usr+"','"+pswrd+"','"+Role+"')");
                        break;
                    case 2: 
                        System.out.println("Enter the id of user to be deleted");
                        int usrId = entry.nextInt(); 
                        /*
                         ArrayList <slotsTable> list = new ArrayList();
                rs= ss.executeQuery("select*from slots");
                while(rs.next()){
                    list.add(new slotsTable(rs.getInt("slot_id"),rs.getString("slot_status")));
                }
                System.out.println("ID \t STATUS");
                for (int i=0; i < list.size(); i++)
                System.out.println(list.get(i).id+" \t "+list.get(i).status);
                        */
                        ArrayList <usersTable> delUser = new ArrayList();
                        rs= ss.executeQuery("select*from users");
                        while(rs.next()){
                        delUser.add(new usersTable(rs.getString("users_name"),rs.getString("users_password"),rs.getString("user_role")));
                        }
                        delUser.remove(usrId-1);
                        ss.execute("DBCC CHECKIDENT ('users', RESEED, 0)");
                        ss.execute("delete from users");
                        for (int i=0; i < delUser.size(); i++){
               // System.out.println(CarsL.get(i).car_no+" \t "+CarsL.get(i).slot_id);
                //break;
                                //System.out.println(delUser.get(i).user+"','"+delUser.get(i).pass+"', '"+delUser.get(i).roleId);

                        ss.execute(" set identity_insert users off insert into users (users_name, users_password, user_role)	values ('"+delUser.get(i).user+"','"+delUser.get(i).pass+"', '"+delUser.get(i).roleId+"')");
                        }break;
                    case 3: 
                        System.out.println("Enter the id of user to be updated");
                        int userID = entry.nextInt();
                        System.out.println("Enter the new username: ");
                        String userN = entry.next();
                        System.out.println("Enter the new password: ");
                        String userP = entry.next();
                        String roleC = roleC(Role_id);
                        ss.execute("update users "
                                + " SET users_name='"+userN+"',"
                                + " users_password ='"+userP+"',"
                                + " user_role='"+roleC+"'"
                                + " where users_id='"+userID+"'  ");
                }
                
                break; 
 
            case 4 : 
                 ArrayList <Parked_cars> CarsL = new ArrayList();
                rs= ss.executeQuery("select*from parked_cars");
                while(rs.next()){
                    CarsL.add(new Parked_cars(rs.getInt("car_no"),rs.getInt("slot_id")));
                }
                System.out.println("ID \t STATUS");
                for (int i=0; i < CarsL.size(); i++)
                System.out.println(CarsL.get(i).car_no+" \t "+CarsL.get(i).slot_id);
                break;    
            case 5:
                ArrayList <shifts> Shifts = new ArrayList();
                rs= ss.executeQuery("select*from parked_cars");
                while(rs.next()){
                    Shifts.add(new shifts(rs.getInt("slot_id"),rs.getInt("book_hours"),rs.getInt("book_mins"),rs.getInt("car_payent")));
                }
                System.out.println("ID \t STATUS");
                for (int i=0; i < Shifts.size(); i++)
                System.out.println(Shifts.get(i).slot_id+" \t "+Shifts.get(i).book_hours+" \t "+Shifts.get(i).book_mins+" \t "+Shifts.get(i).payment);
                break;   
                
                
        }
        break;
         
        }
        }
        
        if(c==0) {
            System.out.println("Username or password is incorrect");
        }
        
    return r;
    }

    
    private void add_slots (Statement c) throws SQLException{
        ss.execute("insert into slots (slot_status) values ('Empty')");
    }
    
    private void view_slots (Connection c){
        
    }
    
    private void free_slots (Connection c, int id) throws SQLException{
         ss=c.createStatement();
        ss.execute("insert into slots (slot_status) values ('Free') where slot_id = "+id);
    } 
     private void busy_slots (Statement c, int id) throws SQLException{
        ss.execute("insert into slots (slot_status) values ('busy') where slot_id = "+id);
    } 
     
     public String roleC (int Role_id){
         
                String Role = null;
                switch (Role_id){
                    case 1:
                        Role = "Admin";
                       return Role; 
                    case 2 : 
                        Role = "Operator"; 
                       return Role; 
                    case 3: 
                        Role ="Costumer";
                       return Role; 
                }
                return Role;
     }
}
     /*public void distable(){
        try{
             
            String s="SELECT * FROM slots";
            ps=c.prepareStatement(s);
            rs=ps.executeQuery();
            
            jtable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }*/
   


 
/**

 *

 * @author Raz

 */


   /* public static Connection connect() throws ClassNotFoundException{ 

    Connection con =null; 

    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    
    String pass ="admin";

    String connectionURL=("jdbc:sqlserver://localhost:1433;databaseName=admindb;user=admin2;password="+pass); 

    try{

    con=DriverManager.getConnection(connectionURL);
    System.out.println("Connection is successfull");

    }

    catch(SQLException e){

     System.out.println(e);

    }
        return con;
    }*/

