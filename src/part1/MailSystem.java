package part1;

import part1.MailStore.MailStore;
import part1.exceptions.AlreadyTakenUsernameException;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * MailSystem is an administrative component responsible of managing
 * an entire mail system (mail store and the lists of users and mailboxes)
 *
 */
public class MailSystem {
    // Users and mailboxes will be stored in hashtables for quick access
    private Hashtable<String,User> users;
    private Hashtable<String,MailBox> mailboxes;

    private final MailStore mailstore; // Reference of the MailStore

    /**
     *
     * MailSystem constructor.
     *
     * @param mailstore MailStore to be managed
     */
    public MailSystem (MailStore mailstore) {
        users = new Hashtable<>();
        mailboxes = new Hashtable<>();
        this.mailstore = mailstore;
    }

    /**
     *
     * This operation will create a new User and their personal mailbox
     *
     * @param username Unique identifier for the entire mailsystem.
     * @param name Personal name of the user
     * @param yearofbirth Year of birth of the user
     * @return Mailbox of the new user.
     */
    public MailBox CreateUser (String username, String name, int yearofbirth) throws AlreadyTakenUsernameException {
        if (users.containsKey(username)) {
            throw new AlreadyTakenUsernameException("Username: "+username+" is already taken!");
        }
        users.put(username, new User(username, name, yearofbirth));
        mailboxes.put(users.get(username).getUsername(), new MailBox(users.get(username).getUsername(), this.mailstore));
        return mailboxes.get(users.get(username).getUsername());
    }

    /**
     *
     * This operation will retrieve a previously created mailbox
     *
     * @param username Unique identifier of the already created user
     * @return Mailbox of the user
     */
    public MailBox RetrieveMailBox (String username) {
        return mailboxes.get(users.get(username).getUsername());
    }

    public User RetrieveUser (String username) {
        return users.get(username);
    }
    /**
     *
     * This operation will get a list of every message sent on this system
     *
     * @return LinkedList of messages
     */
    public List<Message> GetAllMessages () {
        List<Message> messages = new LinkedList<Message>();

        //
        for(String user: users.keySet()){
            messages.addAll(mailstore.GetMail(user));
        }

        return messages;
    }

    /**
     *
     * Operation for getting a list of every user registered on this system
     *
     * @return LinkedList of users
     */
    public List<User> GetAllUsers () {
        return new LinkedList<>(users.values());
    }

    /**
     *
     * This operation will get all messages in the system that fulfill a given condition
     *
     * @param condition Condition to fulfill
     * @return LinkedList of messages that accomplish the condition
     */
    public List<Message> FilterAllMessages (Predicate<Message> condition) {
        List<Message> messages = new LinkedList<Message>();

        for(String user: mailboxes.keySet()){
            messages.addAll(mailboxes.get(user).FilterMail(condition));
        }

        return messages;
    }

    /**
     *
     * Operation for counting the total number of messages.
     *
     * @return number of messages in the system
     */
    public int CountAllMesssages () {
        Set<String> usermailboxes = mailboxes.keySet();
        int totalCount = 0;
        for(String user: usermailboxes){
            totalCount += mailstore.GetMail(user).size();
        }

        return totalCount;
    }

    /**
     *
     * Return the average of sent messages per user
     *
     * @return average of sent messages per user
     */
    public double avgMessagesPerUser () {
        return (double) this.CountAllMesssages() / (double) users.size();
    }

    /**
     *
     * This operation will group a list of messages with an specific subject
     *
     * @return LinkedList of messages with this subject
     */
    public Map<String, List<Message> > groupMessagesPerSubject () {
        return GetAllMessages().stream().collect( Collectors.groupingBy(Message::getSubject) );
    }

    /**
     *
     * Counts the words of all messages from users with a particular name.
     *
     * @param name personal name of the users
     * @return number of words written by users with a particular name
     */
    public int countWordsFromName (String name) {
        int total = 0;
        Predicate<Message> filterSenderName = message -> RetrieveUser(message.getSender()).getName().equals(name);

        for (Message mail : FilterAllMessages(filterSenderName)) {
            StringTokenizer tokens = new StringTokenizer(mail.getBody());
            total += tokens.countTokens();
        }

        return total;
    }

    /**
     *
     * Get a list of messages destinated to users born before a certain year.
     *
     * @param yearofbirth Year of birth of the receiver
     * @return LinkedList of messages
     */
    public List<Message> getMessagesToUsersBornBeforeYear (int yearofbirth) {
        Predicate<Message> filterReceiverYear = message -> users.get(message.getReceiver()).getYearofbirth() < yearofbirth;
        return FilterAllMessages(filterReceiverYear);
    }


}
