/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;
import java.util.Random; // generates random message ID's
import java.util.regex.Pattern; 
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
    
}

