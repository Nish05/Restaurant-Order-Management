/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Nisha Bhanushali
 */
public class Menu {
    Connection con;
    String host;
    String uName;
    String uPass;
    Statement stmt;
    
    public Menu() throws SQLException{
        this.host = "jdbc:mysql://localhost:3306/mysql";
        this.uName = "root";
        this.uPass = "admin";
        this.con = DriverManager.getConnection(this.host, this.uName, this.uPass);
        this.stmt = con.createStatement();
    }
    
    public String getOrderInfo(String orderName) throws SQLException{
        
        String SQL = "SELECT * FROM Menu WHERE Name_Dish = '"+orderName+"'";
//        String SQL = "SELECT * FROM Menu";
        String result ="Sorry! not available";
        ResultSet rs = stmt.executeQuery( SQL );
        
        while(rs.next()){
            int cost = rs.getInt("Cost");
            String available = rs.getString("Category");
            result = "Cost : "+cost+" "+"Category : "+available;
        }
        
        if(rs != null){
            return result;
        }
        
        return "Cannot find the dish you are looking for !";
    }
    
    public static void main(String[] args)throws IOException, SQLException {
        Menu menu = new Menu();
        String result = menu.getOrderInfo("Stuffed Cherry Peppers");
        System.out.println(result);
    }
    
}
