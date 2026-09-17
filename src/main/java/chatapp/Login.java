/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;
import java.util.regex.Pattern;
/**
 *
 * @author rahul
 */
public class Login {
    private String firstName; 
    private String lastName;
    private String username;
    private String password;
    private String CellPhoneNumber; 
    private boolean LastLoginSucessful; 
    
    public Login(){}
    public void SetFristname (String firstName){ 
    this.firstName = firstName;
    }
    public void SetLastname (String lastName){  
    this.lastName = lastName; 
    }
    public void SetUsername (String username){ 
    this.username = username; 
    }
    public void SetPassword (String password){ 
    this.password = password; 
    }
    public void SetCellnumber (String SetPhoneNumber){ 
    this.CellPhoneNumber = SetPhoneNumber; 
    }
    public String GetUsername (){ 
    return username; 
    }
    public boolean CheckUsername (String username){
    
        if (username == null){
          return false;
        }
        return username.contains("_")&& username.length() <=5;
    }
    public boolean CheckPasswordcomplexity (String password) { 
     if(password == null || password.length() < 8) {
     return false; 
     }
     boolean hasCapital = password.matches(".*[A-Z].*"); 
     boolean hasDigital = password.matches(".*[0-9].*"); 
     boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*"); 
     return hasCapital && hasDigital && hasSpecial;
    }
    public boolean CheckCellPhoneNumber (String CellPhoneNumber){ 
     if (CellPhoneNumber== null) {
         return false; 
     }
     return Pattern.matches("^\\+27[0-9]{1,10}$", CellPhoneNumber);
    }
    public String registerUser () 
    {
           if(!CheckUsername(username)){
           return "Your username isn't correctly formmated; please ensure that your " + 
                   "username contains an underscore and isn't more than 5" +
                   "Characters in lenght"; 
           }
           if (!CheckPasswordcomplexity(password)){
           return "Your Password isn't correctly formmated; please ensure that your " + 
                   "password contains at least 8 characters" +
                   "A Capital letter, A number and a special Character";
           }
           if (!CheckCellPhoneNumber(CellPhoneNumber)) {
           return "Cell Number is incorrectly formatted or dosen't cointain an international" +
                    "code, please correct the number and try again";
           }
           return  "Username Successfully captured. \n" +
                   "Password  Successfully captured. \n" + 
                   "Cell Number is Sucessfully captured. \n " + 
                   "Registration sucessfull, " + firstName + "" + lastName + "!";
    }
    public boolean loginUser(String EnteredUsername, String EnteredPassword){ 
         
        LastLoginSucessful = EnteredUsername != null && EnteredPassword != null 
                && EnteredUsername.equals(username) && EnteredPassword.equals(password);
        return LastLoginSucessful;
    }
    public String returnloginstatus (){ 
    
        if(LastLoginSucessful){
          return "Welcome " + firstName + " " + lastName + " Its great to see you.";
        }
        return "Username or Password Incorrect, please try again.";
    }
}   
