package part2;

import part1.MailBox;
import part1.MailStore.MailStore;
import part1.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    }

    public void addFilter(PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }

    public void removeFilter(PropertyChangeListener pcl) {
        this.support.removePropertyChangeListener(pcl);
    }

    @Override
    public void UpdateMail () {
        List<Message> newList = super.mailstore.GetMail(super.username).stream().sorted((x, y)->x.getCreationtime().compareTo(y.getCreationtime())).collect(Collectors.toList());
        support.firePropertyChange("messages", super.messages, newList);
        spamList = super.messages;
        super.messages = newList;
    }
}
