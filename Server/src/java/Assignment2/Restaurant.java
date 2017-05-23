/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Nish
 */
public class Restaurant {
    Connection con;
    String host;
    String uName;
    String uPass;
    Statement stmt;
    
    public Restaurant() throws SQLException{
        this.host = "jdbc:mysql://localhost:3306/mysql";
        this.uName = "root";
        this.uPass = "admin";
        this.con = DriverManager.getConnection(this.host, this.uName, this.uPass);
        this.stmt = con.createStatement();
    }
    
    public void getAllContent() throws SQLException{
        String SQL = "SELECT * FROM Menu";
        ResultSet rs = stmt.executeQuery( SQL );
        while(rs.next()){
            int id_col = rs.getInt("Id");
            String dishname = rs.getString("Name_Dish");
            String category = rs.getString("Category");
            int cost = rs.getInt("Cost");
            System.out.println( id_col + " " + dishname + " " + category + " " + cost );
        }
    }
    
    
    public boolean checkTable(int seats)throws SQLException{
        String SQL = "SELECT count(*) AS count FROM Restaurant_Tables WHERE Availability = 'yes' and NoSeats = "+seats;
        ResultSet rs = stmt.executeQuery( SQL );
        rs.next();
        int rowCount = rs.getInt("count");
        System.out.println(rowCount);
        return rowCount != 0;
    }        
    public HashMap<Integer, String> getContentByTable(int seats) throws SQLException{
        
        String SQL = "SELECT * FROM Restaurant_Tables WHERE NoSeats = "+seats;
        ResultSet rs = stmt.executeQuery( SQL );
        HashMap<Integer, String> map = new HashMap<>();
        while(rs.next()){
            String available = rs.getString("Availability");
            int tableNo = rs.getInt("TableNo");
            map.put(tableNo,available);
        }
        return map;
    }
//    public void getContentByTables(int tables){
//        
//    }
    public static void main(String[] args)throws IOException, SQLException{
        
        Restaurant menu = new Restaurant();
        System.out.println(menu.checkTable(10));
        
//        menu.getAllContent();
//        HashMap<Integer,String> result = menu.getContentByTable(4);
//        for(Integer key: result.keySet()){
//            int key1 = key;
//            String value1 = result.get(key1);
//            System.out.println(key1+"   "+value1);
//        }
    }

}