/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;
import java.util.Random; // generates random message ID's
import java.util.regex.Pattern;
import java.util.ArrayList;
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
            return "Cell number succesfully captureed.";
        }
        return "Cell phone number is inncorrectaly formatted or dose not contain an international code."
                + "Please enter a valid number and try again!!"; 
    }
    
    public String checkMessageLenght(){
        if (messageText.length() <= 250) {
            return "Message ready to send";
        }
        int extra = messageText.length() - 250;
        return "Message exeeds 250 characters by"+ extra + "pls reduce the size";
    } 
    public String createMessageHash(){
    String[]words = messageText.trim().split("\\s+");
    String first = words[0].replaceAll("[^a-zA-Z0-9]", "");
    String last = words[words.length-1].replaceAll("[^a-zA-Z0-9]", "");
    messageHash = messageID.substring(0,2) + ":" + messageNumber + ":" +
            (first + last).toUpperCase();
    return messageHash;
    
    }
    public String printMessages(){
    String output = "";
        for (Message m : sentMessages) {
            output = output + "Mesage ID: " + m.getMessageID() + 
                    "\n" + "Message Hash: " + m.getMessageHash() + 
                    "\n" + m.createMessageHash() + "\n" + 
                    "Recipient: " + m.getrecipient() + "\n" +
                    "Message: " + m.getMessageText() + "\n\n";
            
        }
        return output;
    }
    public int returnTotalMessages(){return sentMessages.size();}
}

