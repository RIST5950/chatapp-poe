/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;
import java.util.Random; // generates random message ID's
import java.util.regex.Pattern;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author rahul
 */
public class Message {
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>(); 
    
    public String getMessageID(){return messageID;}
    public void setMessageID (String messageID){this.messageID = messageID;}
    public String getMessageHash(){return messageHash;}
    public String getrecipient (){return recipient;}
    public String getMessageText(){return messageText;}
    public Message (int messageNumber, String recipient, String messageText){
    this.messageNumber = messageNumber;
    this.recipient = recipient;
    this.messageText = messageText;
    this.messageID = generateMessageID();
}
    public String generateMessageID(){
     Random random = new Random();
     String id = "";
        for (int i = 0; i < 10; i++) {
            id = id + random.nextInt(10);  
        }
        return id; 
    }
    public boolean checkMessageID(){
       return messageID != null && messageID.length() <= 10; 
    }
   public String checkrecipientCell(){
        if (recipient != null && Pattern.matches("^\\+27[0-9]{1,10}$", recipient)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    
   public String checkMessageLenght(){
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }
        int extra = messageText.length() - 250;
        return "Message exceeds 250 characters by " + extra + "; please reduce the size.";
    }
    public String createMessageHash(){
    String[]words = messageText.trim().split("\\s+");
    String first = words[0].replaceAll("[^a-zA-Z0-9]", "");
    String last = words[words.length-1].replaceAll("[^a-zA-Z0-9]", "");
    messageHash = messageID.substring(0,2) + ":" + messageNumber + ":" +
            (first + last).toUpperCase();
    return messageHash;
    
    }
    public static String printMessages(){
    String output = "";
        for (Message m : sentMessages) {
            output = output + "Message ID: " + m.getMessageID() + 
                    "\n" + "Message Hash: " + m.getMessageHash() + "\n" + 
                    "Recipient: " + m.getrecipient() + "\n" +
                    "Message: " + m.getMessageText() + "\n\n";
            
        }
        return output;
    }
    public static int returnTotalMessages(){return sentMessages.size();}
    public void storedMessage (){
     storedMessages.add(this);
     Gson gson = new GsonBuilder().setPrettyPrinting().create();
     try (FileWriter  writer = new FileWriter("messages.json")){
     gson.toJson(storedMessages, writer);
     }
     catch( IOException e){
         System.out.println("Could'nt store message: " + e.getMessage());
     }
    }
     public String SentMessage(int choice){
        createMessageHash();
        if (choice == 1) {
            sentMessages.add(this);
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storedMessage();
            return "Message successfully stored.";
        }
        return "Invalid option.";
    }
}

