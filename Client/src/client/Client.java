/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.soap.SOAPFaultException;

/**
 *
 * @author Nish
 */
public class Client {
    private static String name;
    private static String opNum;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Restaurant Order System");
        
        System.out.println("Enter your name");
        Client.setName(sc.nextLine());
        boolean flag = true;
        
        while(flag){
            System.out.println();
            System.out.println("1. Book a Table");
            System.out.println("2. Place your order");
            System.out.println("3. Exit");
            int choice = sc.nextInt();

            Client.setOpNum(Integer.toString(choice));
            
            switch(choice) {
                case 1: 
                        System.out.println("You want table for how many people ? ");
                        int people = sc.nextInt();
                        try{
                            String info = Client.getTableInfo(Integer.toString(people));
                            System.out.println(info);
                            
                        } catch(SOAPFaultException e){
                            System.out.println(e.getFault().getFaultString());
                        }
                        
                        break;

                case 2:
                        sc.nextLine();
                        String orderInfo;
                        System.out.println("Which dish would you like to order ?");
                        String dishName = sc.nextLine();
                        try{
                            
                            orderInfo= Client.getOrderInfo(dishName);
                            System.out.println(orderInfo);
                            
                        } catch(SOAPFaultException e){
                            System.out.println(e.getFault().getFaultString());
                        }
                       
                        break;
                    
                case 3:
                    flag = false;
                    break;
                    
            }
        }
    }

    private static String getOrderInfo(java.lang.String dish) {
        assignment2.Asgnmnt2_Service service = new assignment2.Asgnmnt2_Service();
        assignment2.Asgnmnt2 port = service.getAsgnmnt2Port();
        return port.getOrderInfo(dish);
    }

    private static String getTableInfo(java.lang.String seats) {
        assignment2.Asgnmnt2_Service service = new assignment2.Asgnmnt2_Service();
        assignment2.Asgnmnt2 port = service.getAsgnmnt2Port();
        return port.getTableInfo(seats);
    }

    
    static String getName(){
        return name;
    }
    
    static String getopNum(){
        return opNum;
    }
    
    static void setName(String name1){
        name = name1;
    }
    
    static void setOpNum(String number){
        opNum = number;
    }
}
