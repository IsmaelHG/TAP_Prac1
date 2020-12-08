package oop.MailStore;

import oop.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MailStoreMemory implements MailStore {
    private List<Message> mailList;

    /**
     * MailStore (Memory version) constructor. Every message will be stored in memory through an internal list.
     *
     */
    public MailStoreMemory() {
        mailList = new LinkedList<Message>();
    }

    @Override
    public void SendMail(Message mail) {
        // Add a new message to the list
        mailList.add(mail.clone());
    }

    @Override
    public List<Message> GetMail(String username) {
        // We use a predicate to identify the messages destined to the username
        Predicate<Message> filterUsername = message -> message.getReceiver().equals(username);

        // We filter the list of messages that accomplish the predicate
        return mailList.stream().filter(filterUsername).collect(Collectors.toList());
    }
}
