/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 *
 * @author rahul
 */
public class LoginTest {
    private Login login;
    @BeforeEach 
    public void setUP(){
        
    login = new Login();
    login.SetFristname("Rahul");
    login.SetLastname("Desai");
    
    
    }
   @Test
   public void TestCheckUsername_corretlyFormatted(){   // checks if user name meets the requrements 
    assertTrue(login.CheckUsername("kyl_1"));
   }
   @Test
   public void TestCheckUsername_incorrectlyFormatted (){ // tests is a incorrectly formatted username is added 
    assertFalse(login.CheckUsername("kyle!!!!"));
   }
   @Test
   public void Testpasswordcomplexity_meetsRequirements(){  // tests password strength lodgic is successful 
     assertTrue(login.CheckPasswordcomplexity("Ch&&sec@ke99!"));
   }
   @Test
    public void Testpasswordcomplexity_doesNotMeetRequirements() { // test password strength, en enterd incorrectly 
        assertFalse(login.CheckPasswordcomplexity("password"));
    }
   @Test
    public void testCheckCellphonenumber_correctlyFormatted() { // tests if the phone number is valid 
        assertTrue(login.CheckCellPhoneNumber("+27838968976"));
    }

   @Test
    public void testCheckCellphonenumber_incorrectlyFormatted() { // tests an incorrect phone number 
        assertFalse(login.CheckCellPhoneNumber("08966553"));
    }
       @Test
    public void testRegisterUser_usernameIncorrectlyFormatted() { // checks the whole username 
        login.SetUsername("kyle!!!!!!");
        login.SetPassword("Ch&&sec@ke99!");
        login.SetCellnumber("+27838968976");
        assertEquals(
                "Your username isn't correctly formmated; please ensure that your username contains an underscore and isn't more than 5Characters in lenght",
                login.registerUser()
        );
    }

    @Test
    public void testRegisterUser_passwordDoesNotMeetRequirements() { // testes the whole password 
        login.SetUsername("kyl_1");
        login.SetPassword("password");
        login.SetCellnumber("+27838968976");
        assertEquals(
                "Your Password isn't correctly formmated; please ensure that your password contains at least 8 charactersA Capital letter, A number and a special Character",
                login.registerUser()
        );
    }

    @Test
    public void testRegisterUser_cellPhoneIncorrectlyFormatted() { // tests if code will runwith incorrect formatted phone numbers 
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        login.SetCellnumber("08966553");
        assertEquals(
                "Cell Number is incorrectly formatted or dosen't cointain an internationalcode, please correct the number and try again",
                login.registerUser()
        );
    }

    @Test
    public void testRegisterUser_successful() {
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        login.SetCellnumber("+27838968976");
        assertEquals(
                "Username Successfully captured. \nPassword  Successfully captured. \nCell Number is Sucessfully captured. \n Registration sucessfull, RahulDesai!",
                login.registerUser()
        );
    }
        @Test
    public void testLoginUser_successful() {// tests a successfull username
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_failed() { // tests a unsccessfull username 
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        assertFalse(login.loginUser("kyl_1", "WrongPass1!"));
    }
        @Test
    public void testReturnLoginStatus_successful() { 
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Rahul Desai Its great to see you.", login.returnloginstatus());
    }

    @Test
    public void testReturnLoginStatus_failed() {
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        login.loginUser("kyl_1", "WrongPass1!");
        assertEquals("Username or Password Incorrect, please try again.", login.returnloginstatus());
    }

   
}
 