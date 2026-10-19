/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package chatapp;

import java.util.Scanner;

/**
 *
 * @author rahul
 */
public class ChatappPoe {
    
    public static void main(String[] args){
    
        Scanner input = new Scanner(System.in);
        Login login = new Login(); // Calling the login class 
         
        System.out.println("+== Chat App Registration ==+");
        System.out.println("Enter your first name: ");
        login.SetFristname(input.nextLine()); 
        System.out.println("Enter your Last name: ");
        login.SetLastname(input.nextLine());
        System.out.println("Enter a username (must contain '_' and be 5 Charaters or less): ");
        login.SetUsername(input.nextLine());
        System.out.println("Enter a password (8 + characters, a Capital letter, a Number, a Special Character): ");
        login.SetPassword(input.nextLine());
        System.out.println("Enter a SA cell number, e.g +27662431701");
        login.SetCellnumber(input.nextLine());
        System.out.println(login.registerUser()); // registratin of user successfull 
        
        System.out.println("\n ++= Chat App Login =++");
        System.out.println("Enter Username: ");
        String logininusername = input.nextLine(); // LiU is Log IN Username
        System.out.println("Enter Password: "); // LiP = Login Password
        String Loginpasword = input.nextLine();
//        login.loginUser(logininusername, Loginpasword );
//        System.out.println(login.returnloginstatus());
//       
//        input.close();
         boolean LoggedIn = login.loginUser(logininusername, Loginpasword);
          System.out.println(login.returnloginstatus());
          if (LoggedIn) {
            runQuickChat(input);
        }
          input.close();
    }
    public static void runQuickChat (Scanner input){
        System.out.println("\n Welcome to QuickChat !!");
        System.out.println("How many messages do you wish to enter?");
        int numMessages = Integer.parseInt(input.nextLine());
        int messagesEntered = 0;
        int choice = 0;
        while (choice != 3){
            System.out.println("\n1. Send messages. ");
            System.out.println("2. Show recently sent messages.");
            System.out.println("3. Quit");
            choice = Integer.parseInt(input.nextLine());
        
            if (choice == 1) {
                
            }
        }
    }
}
