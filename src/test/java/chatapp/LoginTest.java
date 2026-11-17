package chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private static final String USERNAME_OK = "Username successfully captured.";
    private static final String USERNAME_ERROR = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    private static final String PASSWORD_OK = "Password successfully captured.";
    private static final String PASSWORD_ERROR = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    private static final String CELL_OK = "Cell number successfully captured.";
    private static final String CELL_ERROR = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";

    private Login login;

    // Runs before every test so each one starts with a fresh Login object
    @BeforeEach
    public void setUp() {
        login = new Login();
        login.SetFristname("Rahul");
        login.SetLastname("Desai");
    }

    // ----- Messages (assertEquals) -----

    @Test
    public void testUsernameMessageCorrect() {
        assertEquals(USERNAME_OK, login.usernameMessage("kyl_1"));
    }

    @Test
    public void testUsernameMessageIncorrect() {
        assertEquals(USERNAME_ERROR, login.usernameMessage("kyle!!!!!!"));
    }

    @Test
    public void testPasswordMessageCorrect() {
        assertEquals(PASSWORD_OK, login.passwordMessage("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordMessageIncorrect() {
        assertEquals(PASSWORD_ERROR, login.passwordMessage("password"));
    }

    @Test
    public void testCellMessageCorrect() {
        assertEquals(CELL_OK, login.cellNumberMessage("+27838968976"));
    }

    @Test
    public void testCellMessageIncorrect() {
        assertEquals(CELL_ERROR, login.cellNumberMessage("08966553"));
    }

    // ----- True / false checks (assertTrue / assertFalse) -----

    @Test
    public void testCheckUserNameTrue() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameFalse() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityTrue() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityFalse() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumberTrue() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberFalse() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // ----- registerUser: one field is wrong each time -----

    @Test
    public void testRegisterUsernameWrong() {
        login.SetUsername("kyle!!!!!!");
        login.SetPassword("Ch&&sec@ke99!");
        login.SetCellnumber("+27838968976");
        assertEquals(USERNAME_ERROR, login.registerUser());
    }

    @Test
    public void testRegisterPasswordWrong() {
        login.SetUsername("kyl_1");
        login.SetPassword("password");
        login.SetCellnumber("+27838968976");
        assertEquals(PASSWORD_ERROR, login.registerUser());
    }

    @Test
    public void testRegisterCellWrong() {
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        login.SetCellnumber("08966553");
        assertEquals(CELL_ERROR, login.registerUser());
    }

    @Test
    public void testRegisterSuccess() {
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        login.SetCellnumber("+27838968976");
        assertEquals(USERNAME_OK + "\n" + PASSWORD_OK + "\n" + CELL_OK
                + "\nUser has been registered successfully.", login.registerUser());
    }

    // ----- Login (assertTrue / assertFalse) -----

    @Test
    public void testLoginSuccessful() {
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        assertFalse(login.loginUser("kyl_1", "WrongPass1!"));
    }

    // ----- Login messages -----
    // loginUser() must run first, because returnLoginStatus() reads the stored result

    @Test
    public void testLoginStatusSuccess() {
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Rahul, Desai it is great to see you again.", login.returnLoginStatus());
    }

    @Test
    public void testLoginStatusFailure() {
        login.SetUsername("kyl_1");
        login.SetPassword("Ch&&sec@ke99!");
        login.loginUser("kyl_1", "WrongPass1!");
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus());
    }
}