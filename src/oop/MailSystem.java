package oop;

import oop.MailStore.MailStore;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 *
 *
 */
public class MailSystem {
    private Hashtable<String,User> users;
    private Hashtable<String,MailBox> mailboxes;
    private MailStore mailstore;

    public MailBox CreateUser (String username, String name, int yearofbirth) {
        users.put(username, new User(username, name, yearofbirth));
        return mailboxes.put(users.get(username).getUsername(), new MailBox(users.get(username).getUsername(), this.mailstore));
    }

    public MailBox RetrieveMailBox (String username) {
        return mailboxes.get(users.get(username).getUsername());
    }

    public List<Message> GetAllMessages () {
        List<Message> messages = new LinkedList<Message>();

        Set<String> usermailboxes = mailboxes.keySet();
        for(String user: usermailboxes){
            messages.addAll(mailstore.GetMail(user));
        }

        return messages;
    }

    public List<User> GetAllUsers () {
        return new LinkedList<>(users.values());
    }

    public List<Message> FilterAllMessages (Predicate<Message> f) {
        List<Message> messages = new LinkedList<Message>();

        Set<String> usermailboxes = mailboxes.keySet();
        for(String user: usermailboxes){
            messages.addAll(mailboxes.get(user).FilterMail(f));
        }

        return messages;
    }

    public int CountAllMesssages () {
        Set<String> usermailboxes = mailboxes.keySet();
        int totalCount = 0;
        for(String user: usermailboxes){
            totalCount += mailstore.GetMail(user).size();
        }

        return totalCount;
    }

    public double avgMessagesPerUser () {
        return (double) this.CountAllMesssages() / (double) users.size();
    }

    public List<Message> groupMessagesPerSubject (String subject) {
        Predicate<Message> filterSubject = message -> message.getSubject().equals(subject);

        return FilterAllMessages(filterSubject);
    }

    public int countWordsFromName (String name) {
        int total = 0;
        Predicate<Message> filterSenderName = message -> message.getSender().equals(name);

        List<Message> filteredMessages = FilterAllMessages(filterSenderName);

        for (Message mail : filteredMessages) {
            StringTokenizer tokens = new StringTokenizer(mail.getBody());
            total += tokens.countTokens();
        }

        return total;
    }

    public List<Message> getMessagesToUsersBornBeforeYear (int yearofbirth) {
        Predicate<Message> filterReceiverYear = message -> users.get(message.getReceiver()).getYearofbirth() < yearofbirth;
        return FilterAllMessages(filterReceiverYear);
    }


}
