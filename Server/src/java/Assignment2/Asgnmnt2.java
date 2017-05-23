/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
/**
 *
 * @author Nish
 */
@WebService(serviceName = "Asgnmnt2")
@HandlerChain(file = "Asgnmnt2_handler.xml")
public class Asgnmnt2 {

    /**
     * This is a operation to get the table information
     * @param seats
     * @return 
     */
    @WebMethod(operationName = "getTableInfo")
    public String getTableInfo(@WebParam(name="Seats")String seats){
        //TODO write your implementation code here:
        Restaurant rest; 
        try {
            rest = new Restaurant();
            boolean absent = rest.checkTable(Integer.valueOf(seats));
//            System.out.println("Present : "+absent);
            if(absent){
                return "You can place your order";
            }
//            System.out.println("You cannot place your order");
            
        } catch (SQLException ex) {
            Logger.getLogger(Asgnmnt2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "You cannot place your order";
    }
    
    /**
     * This is an operation to get the order name 
     * @param dish
     * @return
     */
    
    @WebMethod(operationName = "getOrderInfo")
    public String getOrderInfo(@WebParam(name="Dish")String dish) {
        
        String orderName="Sorry ! not available";
//        System.out.println(dish);
        try {
            Menu menu = new Menu();
            orderName = menu.getOrderInfo(dish);
        } catch (SQLException ex) {
            Logger.getLogger(Asgnmnt2.class.getName()).log(Level.SEVERE, null, ex);
        }

//        System.out.println(orderName);
        return orderName;
    }
    
//    @WebMethod(operationName = "getInfo")
//    public String getOrderDetails() throws SQLException {
//        //TODO write your implementation code here:
//        Restaurant rest= new Restaurant(); 
//        boolean present = rest.checkTable(4);
//        if(present){
//            return "You can place your order";
//        }
//        return "You cannot place your order";
//    }
    
}
