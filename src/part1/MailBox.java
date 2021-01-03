package part1;

import part1.MailStore.MailStore;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * MailBox is a username-personal message box where user mailing operations are implemented.
 *
 */
public class MailBox implements Iterable<Message> {
    protected List<Message> messages; // Internal list of messages retrieved from the MailStore
    protected final String username; // Unique username used for identification
    protected final MailStore mailstore; // Reference of the MailStore where the box will retrieve messages


    /**
     *
     *  MailBox constructor.
     *
     * @param username Unique identifier
     * @param mailstore MailStore used to retrieve and send the messages
     */
    public MailBox(String username, MailStore mailstore) {
        this.username = username;
        this.mailstore = mailstore;
        messages = new LinkedList<>();
    }

    /**
     *
     *  MailBox operation used for updating the internal message list. Messages will be retrieved from the MailStore and stored
     *  in sorted order (by creation time)
     *
     */
    public void UpdateMail () {
        // The message list will be retrieved from the MailStore and sorted by creation time through streams.
        this.messages = mailstore.GetMail(this.username).stream().sorted((x,y)->x.getCreationtime().compareTo(y.getCreationtime())).collect(Collectors.toList());
    }

    /**
     *
     * MailBox operation used for sending a message. The message will indicate the sender as the current mailbox username
     *
     * @param destination Username of destination
     * @param subject Subject of the message
     * @param body Body of the message
     */
    public void SendMail (String destination, String subject, String body, LocalDateTime sendDate) {
        this.mailstore.SendMail(new Message(subject, this.getUsername(), destination, body, sendDate));
    }

    /**
     *
     * MailBox operation to obtain a list of previously retrieved messages with UpdateMail()
     *
     * @return A list of every message retrieved from the mail store
     */
    public List<Message> ListMail () {
        List<Message> clonedList = new LinkedList<Message>();

        // The return list will be a clone of the internal list for integrity purposes.
        for (Message mail : messages) {
            clonedList.add(mail.clone());
        }

        return clonedList;
    }

    /**
     *
     * MailBox operation for getting a sorted list of messages by a given order condition.
     *
     * @param compare Comparator used of establishing the list order
     * @return
     */
    public List<Message> GetSortedMail (Comparator<Message> compare) {
        return mailstore.GetMail(this.username).stream().sorted(compare).collect(Collectors.toList());
    }

    /**
     *
     * MailBox operation for getting a list of messages that fulfill a given condition
     *
     * @param condition Predicate used for filtering the messages
     * @return
     */
    public List<Message> FilterMail (Predicate<Message> condition) {
        return mailstore.GetMail(this.username).stream().filter(condition).collect(Collectors.toList());
    }

    /**
     *
     * Getter
     *
     * @return Username of the mailbox
     */
    public String getUsername() {
        return username;
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}
