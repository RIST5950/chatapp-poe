package chatapp;

import java.util.Scanner;

public class ChatappPoe {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();

        System.out.println("+== Chat App Registration ==+");
        System.out.println("Enter your first name: ");
        login.SetFristname(input.nextLine());
        System.out.println("Enter your last name: ");
        login.SetLastname(input.nextLine());
        System.out.println("Enter a username (must contain '_' and be 5 characters or less): ");
        login.SetUsername(input.nextLine());
        System.out.println("Enter a password (8+ characters, a capital letter, a number, a special character): ");
        login.SetPassword(input.nextLine());
        System.out.println("Enter a SA cell number, e.g. +27662431701");
        login.SetCellnumber(input.nextLine());
        System.out.println(login.registerUser());

        System.out.println("\n++= Chat App Login =++");
        System.out.println("Enter username: ");
        String loginUsername = input.nextLine();
        System.out.println("Enter password: ");
        String loginPassword = input.nextLine();

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnloginstatus());

        if (loggedIn) {
            runQuickChat(input);
        }
        input.close();
    }

    // Reads a whole number and keeps asking until the user types a valid one
    public static int readNumber(Scanner input) {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number:");
            }
        }
    }

    public static void runQuickChat(Scanner input) {
        System.out.println();
        System.out.println("Welcome to QuickChat.");
        System.out.println("How many messages do you wish to enter?");
        int numMessages = readNumber(input);
        int messagesEntered = 0;
        int choice = 0;

        while (choice != 3) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            choice = readNumber(input);

            if (choice == 1) {
                if (messagesEntered >= numMessages) {
                    System.out.println("You have already entered all " + numMessages + " messages.");
                } else {
                    sendMessages(input, messagesEntered, numMessages);
                    messagesEntered = numMessages;
                    System.out.println("\nTotal number of messages sent: " + Message.returnTotalMessages());
                }
            } else if (choice == 2) {
                System.out.println("Coming Soon.");
            } else if (choice == 3) {
                System.out.println("Goodbye.");
            } else {
                System.out.println("Invalid option, please choose 1, 2 or 3.");
            }
        }
    }

    public static void sendMessages(Scanner input, int startIndex, int numMessages) {
        for (int i = startIndex; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");

            Message msg = null;
            String recipientResult = "";
            while (!recipientResult.equals("Cell phone number successfully captured.")) {
                System.out.println("Enter the recipient cell number (e.g. +27718693002):");
                msg = new Message(i, input.nextLine(), "");
                recipientResult = msg.checkRecipientCell();
                System.out.println(recipientResult);
            }

            String lengthResult = "";
            while (!lengthResult.equals("Message ready to send.")) {
                System.out.println("Enter your message (max 250 characters):");
                msg = new Message(i, msg.getRecipient(), input.nextLine());
                lengthResult = msg.checkMessageLength();
                System.out.println(lengthResult);
            }

            System.out.println("Message ID generated: " + msg.getMessageID());
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            int option = readNumber(input);

            System.out.println(msg.SentMessage(option));

            if (option == 1) {
                System.out.println("Message ID: " + msg.getMessageID());
                System.out.println("Message Hash: " + msg.getMessageHash());
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getMessageText());
            } else if (option == 2) {
                if (input.nextLine().trim().equals("0")) {
                    System.out.println("Message deleted.");
                }
            }
        }
    }
}