/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;

/**
 *
 * @author rahul
 */
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageArraysTest {

    private Message msg1;
    private Message msg2;
    private Message msg3;
    private Message msg4;
    private Message msg5;

    @BeforeEach
    public void setUp() {
        Message.setStorageFile("test-messages.json");
        Message.clearAll();

        msg1 = new Message(0, "+27834557896", "Did you get the cake?");
        msg2 = new Message(1, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg3 = new Message(2, "+27834484567", "Yohoooo, I am at your gate.");
        msg4 = new Message(3, "+27830000000", "It is dinner time!");
        msg5 = new Message(4, "+27838884567", "Ok, I am leaving without you.");

        msg4.setMessageID("0838884567");

        msg1.SentMessage(1);
        msg2.SentMessage(3);
        msg3.SentMessage(2);
        msg4.SentMessage(1);
        msg5.SentMessage(3);
    }
        @Test
    public void testSentMessagesArrayPopulated() {
        ArrayList<Message> sent = Message.getSentMessages();
        assertEquals(2, sent.size());
        assertEquals("Did you get the cake?", sent.get(0).getMessageText());
        assertEquals("It is dinner time!", sent.get(1).getMessageText());
    }

    @Test
    public void testLongestMessage() {
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                Message.findLongestStoredMessage().getMessageText());
    }

    @Test
    public void testSearchByMessageID() {
        assertEquals("It is dinner time!",
                Message.findByMessageID("0838884567").getMessageText());
    }

    @Test
    public void testSearchByRecipient() {
        ArrayList<Message> found = Message.findByRecipient("+27838884567");
        assertEquals(2, found.size());
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                found.get(0).getMessageText());
        assertEquals("Ok, I am leaving without you.", found.get(1).getMessageText());
    }
        @Test
    public void testDeleteByHash() {
        String result = Message.deleteByHash(msg2.getMessageHash());
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", result);
        assertNull(Message.findByMessageID(msg2.getMessageID()));
    }

    @Test
    public void testDisplayReport() {
        String report = Message.displayReport(Message.getSentMessages());
        assertTrue(report.contains("Message Hash: " + msg1.getMessageHash()));
        assertTrue(report.contains("Recipient: +27834557896"));
        assertTrue(report.contains("Message: Did you get the cake?"));
        assertTrue(report.contains("Message Hash: " + msg4.getMessageHash()));
        assertTrue(report.contains("Message: It is dinner time!"));
    }
}