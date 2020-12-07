package oop.MailStore;

import oop.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MailStoreMemory implements MailStore {
    private List<Message> mailList;

    public void MailStore() {
        mailList = new LinkedList<Message>();
    }

    /**
     * @param mail
     */
    @Override
    public void SendMail(Message mail) {
        mailList.add(mail);
    }

    /**
     * @param username
     * @return
     */
    @Override
    public List<Message> GetMail(String username) {
        Predicate<Message> filterUsername = message -> message.getReceiver().equals(username);
        return mailList.stream().filter(filterUsername).collect(Collectors.toList());
    }
}
