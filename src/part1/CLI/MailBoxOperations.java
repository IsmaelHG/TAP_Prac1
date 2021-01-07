package part1.CLI;

import part1.MailBox;
import part1.Message;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Implementation via delegation of diverse mailbox operations.
 * Used in CLI login mode
 *
 */
public class MailBoxOperations {
    private final MailBox currentMailBox;

    /**
     *
     * MailBoxOperations constructor
     *
     *
     * @param currentMailBox MailBox to operate
     */
    public MailBoxOperations(MailBox currentMailBox) {
        this.currentMailBox = currentMailBox;
    }

    /**
     * Method for sending a message. Delegates to SendMail method from MailBox
     *
     * @param destination Destination of the message
     * @param subject Subject of the message
     * @param body Body of the message
     */
    public void SendMessage(String destination, String subject, String body) {
        this.currentMailBox.SendMail(destination, subject, body, LocalDateTime.now());
    }

    /***
     *
     * Method for updating the mailbox. Delegates to UpdateMail from MailBox
     *
     */
    public void UpdateMail() {
        this.currentMailBox.UpdateMail();
    }

    /***
     *
     * Method for returning a list of messages. Delegates to ListMail from MailBox
     *
     * @return List of messages
     */
    public List<Message> ListMail() {
        return this.currentMailBox.ListMail();
    }

    /***
     *
     * Method for sorting the mail given an specific comparator. Delegates to GetSortedMail from MailBox
     *
     * @param compare Comparator
     * @return Sorted list of messages from the mailbox
     */
    public List<Message> SortMail(Comparator<Message> compare) {
        return this.currentMailBox.GetSortedMail(compare);
    }

    /***
     *
     * Method for filtering the mail given an specific predicate. Delegates to FilterMail from MailBox
     *
     * @param condition Predicate
     * @return Filtered list of messages from the mailbox
     */
    public List<Message>  FilterMail(Predicate<Message> condition) {
        return this.currentMailBox.FilterMail(condition);
    }

}
