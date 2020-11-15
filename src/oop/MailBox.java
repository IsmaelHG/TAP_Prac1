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

        return null;
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Actions are performed in the order of iteration, if that
     * order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     * <p>
     * The behavior of this method is unspecified if the action performs
     * side-effects that modify the underlying source of elements, unless an
     * overriding class has specified a concurrent modification policy.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    public void forEach(Consumer<? super Message> action) {

    }
}
