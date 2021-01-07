package part2;

import part1.MailBox;
import part1.MailStore.MailStore;
import part1.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/***
 *
 * MailBox with observable pattern
 *
 */
public class MailBoxObservable extends MailBox {
    private PropertyChangeSupport support;
    private List<Message> spamList;
    /**
     * MailBox constructor.
     *
     * @param username  Unique identifier
     * @param mailstore MailStore used to retrieve and send the messages
     */
    public MailBoxObservable(String username, MailStore mailstore) {
        super(username, mailstore);
        spamList = new LinkedList<>();
        support = new PropertyChangeSupport(this);
    }

    /**
     *
     * Add filter listener to the mailbox
     *
     * @param pcl PropertyChangeListener
     */
    public void addFilter(PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }

    /**
     *
     * Remove filter listener to the mailbox
     *
     * @param pcl PropertyChangeListener
     */
    public void removeFilter(PropertyChangeListener pcl) {
        this.support.removePropertyChangeListener(pcl);
    }

    @Override
    public void UpdateMail () {
        List<Message> oldList = new LinkedList<>();
        List<Message> newList = super.mailstore.GetMail(super.username).stream().sorted((x, y)->x.getCreationtime().compareTo(y.getCreationtime())).collect(Collectors.toList());
        if (support.hasListeners(null)) {
            support.firePropertyChange(null, oldList, newList);
            spamList = oldList;
        }
        super.messages = newList;
    }

    /**
     *
     * Method to get the spamlist
     *
     * @return spamList
     */
    public List<Message> getSpamList(){
        List<Message> newList = new LinkedList<>();
        for (Message m: this.spamList) {
            newList.add(m);
        }

        return newList;
    }
}
