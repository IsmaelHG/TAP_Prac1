package oop.MailStore;

import oop.Message;
import java.util.List;

/**
 *
 * MailStore is a backend Service that will Store all messages from every user. Can perform the operations of retrieving messages of an
 * specific user and sending a new message.
 *
 */
public interface MailStore {


    /**
     * Send mail operation. Stores the message
     *
     * @param mail Message to send
     */
    void SendMail(Message mail);

    /**
     * Get mail operation. Searches for every message destined to an specific username
     *
     * @param username Receiver of the messages
     * @return LinkedList of Message. Will return null in case of error or no messages avaible for that username
     */
    List<Message> GetMail(String username);


}
