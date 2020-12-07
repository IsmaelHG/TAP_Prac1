package oop;

import oop.MailStore.MailStore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 *
 *
 */
public class MailBox implements Iterable<Message> {
    private List<Message> messages;
    private final String username;
    private MailStore mailstore;

    public MailBox(String username, MailStore mailstore) {
        this.username = username;
        this.mailstore = mailstore;
    }

    public void UpdateMail () {
        this.messages = mailstore.GetMail(this.username).stream().sorted((x,y)->x.getCreationtime().compareTo(y.getCreationtime())).collect(Collectors.toList());
    }

    public void SendMail (String destination, String subject, String body) {
        this.mailstore.SendMail(new Message(subject, this.getUsername(), destination, body, LocalDateTime.now()));
    }

    public List<Message> ListMail () {
        List<Message> clonedList = new LinkedList<Message>();

        for (Message mail : messages) {
            clonedList.add(mail);
        }

        return clonedList;
    }

    public List<Message> FilterMail (Predicate<Message> f) {
        return mailstore.GetMail(this.username).stream().filter(f).collect(Collectors.toList());
    }

    public String getUsername() {
        return username;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}
