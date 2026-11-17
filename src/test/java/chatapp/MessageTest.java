package chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {

    // Runs before every test: uses a separate test file so your real
    // messages.json is never overwritten, and empties all the arrays
    @BeforeEach
    public void setUp() {
        Message.setStorageFile("test-messages.json");
        Message.clearAll();
    }

    // ----- Message length -----

    // Success (Msg 1 from the Part 2 brief)
    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    // Failure (260 letters is 10 over the limit)
    @Test
    public void testMessageLengthFailure() {
        String longText = "a".repeat(260);
        Message msg = new Message(0, "+27718693002", longText);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                msg.checkMessageLength());
    }

    // ----- Recipient number -----

    // Success
    @Test
    public void testRecipientSuccess() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.",
                msg.checkRecipientCell());
    }

    // Failure (no international code)
    @Test
    public void testRecipientFailure() {
        Message msg = new Message(1, "08575975889",
                "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell());
    }

    // ----- Message hash -----

    // Msg 1 (ID forced to start with 00)
    @Test
    public void testMessageHashMsg1() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        msg.setMessageID("0012345678");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    // Msg 2 (message number 1)
    @Test
    public void testMessageHashMsg2() {
        Message msg = new Message(1, "08575975889",
                "Hi Keegan, did you receive the payment?");
        msg.setMessageID("0012345678");
        assertEquals("00:1:HIPAYMENT", msg.createMessageHash());
    }

    // Hash loop: runs the same test once for each row of data
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
        "0|Hi Mike, can you join us for dinner tonight?|00:0:HITONIGHT",
        "1|Hi Keegan, did you receive the payment?|00:1:HIPAYMENT"
    })
    public void testMessageHashLoop(int number, String text, String expectedHash) {
        Message msg = new Message(number, "+27718693002", text);
        msg.setMessageID("0012345678");
        assertEquals(expectedHash, msg.createMessageHash());
    }

    // ----- Message ID -----

    // Created, 10 digits, and the output text is correct
    @Test
    public void testMessageIDCreated() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
        assertEquals("Message ID generated: " + msg.getMessageID(),
                msg.messageIDCreated());
    }

    // ----- Send / Disregard / Store -----

    @Test
    public void testSentMessageSend() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message(1, "08575975889",
                "Hi Keegan, did you receive the payment?");
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }

    // ----- Total messages -----

    // Sending adds one to the count (arrays start empty in setUp)
    @Test
    public void testTotalMessagesIncreasesOnSend() {
        Message msg = new Message(0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        msg.SentMessage(1);
        assertEquals(1, Message.returnTotalMessages());
    }

    // Disregarding does not change the count
    @Test
    public void testTotalMessagesUnchangedOnDisregard() {
        Message msg = new Message(1, "08575975889",
                "Hi Keegan, did you receive the payment?");
        msg.SentMessage(2);
        assertEquals(0, Message.returnTotalMessages());
    }
}