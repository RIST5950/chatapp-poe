package chatapp;

import java.util.Random;
import java.util.regex.Pattern;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
        private static String storageFile = "messages.json";

    public static void setStorageFile(String fileName) {
        storageFile = fileName;
    }
        private Message() {
    }
    private String sender;
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();
    private static ArrayList<String> messageHashes = new ArrayList<>();
    private static ArrayList<String> messageIDs = new ArrayList<>();

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
    }

    // Getters and setter
    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    

    public String generateMessageID() {
        Random random = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id = id + random.nextInt(10);
        }
        return id;
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient != null && Pattern.matches("^\\+27[0-9]{1,10}$", recipient)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }
        int extra = messageText.length() - 250;
        return "Message exceeds 250 characters by " + extra + "; please reduce the size.";
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String last = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        messageHash = messageID.substring(0, 2) + ":" + messageNumber + ":"
                + (first + last).toUpperCase();
        return messageHash;
    }

    public static String printMessages() {
        String output = "";
        for (Message m : sentMessages) {
            output = output + "Message ID: " + m.getMessageID() + "\n"
                    + "Message Hash: " + m.getMessageHash() + "\n"
                    + "Recipient: " + m.getRecipient() + "\n"
                    + "Message: " + m.getMessageText() + "\n\n";
        }
        return output;
    }

    public static int returnTotalMessages() {
        return sentMessages.size();
    }

        public static void saveStoredMessages() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(storageFile)) {
            gson.toJson(storedMessages, writer);
        } catch (IOException e) {
            System.out.println("Couldn't save messages: " + e.getMessage());
        }
    }

    public void storeMessage() {
        storedMessages.add(this);
        saveStoredMessages();
    }

        public String SentMessage(int choice) {
        if (choice < 1 || choice > 3) {
            return "Invalid option.";
        }
        createMessageHash();
        messageHashes.add(messageHash);
        messageIDs.add(messageID);

        if (choice == 1) {
            sentMessages.add(this);
            return "Message successfully sent.";
        } else if (choice == 2) {
            disregardedMessages.add(this);
            return "Press 0 to delete the message.";
        }
        storeMessage();
        return "Message successfully stored.";
    }
            public String messageIDCreated() {
        return "Message ID generated: " + messageID;
    }

    public static void clearSentMessages() {
        sentMessages.clear();
    }

    // Empties every array so each unit test starts fresh
    public static void clearAll() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
    }
    
    
}