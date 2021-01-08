package part1.MailStore;

import part1.Message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/***
 *
 * MailStore implementation via memory. Messages will be lost when the application exits
 *
 */
public class MemMailStore implements MailStore {
    private final HashMap<String,List<Message>> mailList;

    /**
     * MailStore (Memory version) constructor. Every message will be stored in memory through an internal list.
     *
     */
    public MemMailStore() {
        mailList = new HashMap<>();
    }

    @Override
    public void SendMail(Message mail) {
        // Add a new message to the list
        if (!mailList.containsKey(mail.getReceiver())) {
            mailList.put(mail.getReceiver(),new LinkedList<>());
        }
        mailList.get(mail.getReceiver()).add(mail.clone());
    }

    @Override
    public List<Message> GetMail(String username) {
        return mailList.containsKey(username) ? mailList.get(username) : new LinkedList<>();
    }
}
