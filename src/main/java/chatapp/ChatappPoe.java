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
        Login login = new Login();
         
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
        System.out.println(login.registerUser());
        
        System.out.println("\n ++= Chat App Login =++");
        System.out.println("Enter Username: ");
        String LiU = input.nextLine(); // LiU is Log IN Username
        System.out.println("Enter Password: "); // LiP = Login Password
        String LiP = input.nextLine();
        login.loginUser(LiU, LiP);
        System.out.println(login.returnloginstatus());
        
        input.close();
         
    }
}
