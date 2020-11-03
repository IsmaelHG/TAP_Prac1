package oop;

import oop.MailStore.MailStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 *
 *
 *
 */
public class MailBox implements Iterable<Message> {
    private ArrayList<Message> messages;
    private String username;

    public void UpdateMail (MailStore mailstore) {

    }

    public void SendMail (MailStore mailstore) {

    }

    public Message[] ListMail () {

        return null;
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
