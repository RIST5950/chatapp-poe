package chatapp;

import java.util.regex.Pattern;

public class Login {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;
    private boolean lastLoginSuccessful; // remembers the result of the last login attempt

    public Login() {}

    // Setters: allow other classes to store values (names unchanged so ChatappPoe still works)
    public void SetFristname(String firstName) { this.firstName = firstName; }
    public void SetLastname(String lastName) { this.lastName = lastName; }
    public void SetUsername(String username) { this.username = username; }
    public void SetPassword(String password) { this.password = password; }
    public void SetCellnumber(String cellPhoneNumber) { this.cellPhoneNumber = cellPhoneNumber; }

    public String GetUsername() { return username; }

    // Username must contain an underscore and be no more than five characters
    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    }

    // Password: at least 8 characters, a capital letter, a number and a special character
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        return hasCapital && hasDigit && hasSpecial;
    }

    // Cell number: +27 followed by up to ten digits (regex)
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber == null) {
            return false;
        }
        return Pattern.matches("^\\+27[0-9]{1,10}$", cellPhoneNumber);
    }

    // Each of these returns the success or error message from the brief's tables
    public String usernameMessage(String username) {
        if (checkUserName(username)) {
            return "Username successfully captured.";
        }
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }

    public String passwordMessage(String password) {
        if (checkPasswordComplexity(password)) {
            return "Password successfully captured.";
        }
        return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    public String cellNumberMessage(String cellPhoneNumber) {
        if (checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell number successfully captured.";
        }
        return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    }

    // Checks username, then password, then cell number; the first failure is returned
    public String registerUser() {
        if (!checkUserName(username)) {
            return usernameMessage(username);
        }
        if (!checkPasswordComplexity(password)) {
            return passwordMessage(password);
        }
        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return cellNumberMessage(cellPhoneNumber);
        }
        return usernameMessage(username) + "\n"
                + passwordMessage(password) + "\n"
                + cellNumberMessage(cellPhoneNumber) + "\n"
                + "User has been registered successfully.";
    }

    // Compares the entered details with the registered details
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        lastLoginSuccessful = enteredUsername != null && enteredPassword != null
                && enteredUsername.equals(username) && enteredPassword.equals(password);
        return lastLoginSuccessful;
    }

    public String returnLoginStatus() {
        if (lastLoginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}