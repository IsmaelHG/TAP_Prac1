package part2.Strategies;

import part1.MailStore.MailStore;
import part1.Message;

import java.util.List;
import java.util.ListIterator;

/**
 *
 * Abstract Wrapped implemented with decorator pattern to wrap the body of
 * every message
 *
 */
public abstract class Wrapper implements MailStore {
    protected MailStore mailst;

    /**
     * Wrapper constructor
     *
     * @param mailstr MailStore to decorate
     */
    public Wrapper(MailStore mailstr) {
        this.mailst = mailstr;
    }

    @Override
    public void SendMail(Message mail) {
        String WrappedBody = MessageWrapper(mail.getBody());
        Message WrappedMessage = new Message(mail.getSubject(), mail.getSender(), mail.getReceiver(), WrappedBody, mail.getCreationtime());

        mailst.SendMail(WrappedMessage);
    }

    @Override
    public List<Message> GetMail(String username) {
        String UnWrappedBody;
        Message WrappedMessage;

        List<Message> WrappedMessages = mailst.GetMail(username);

        for (ListIterator<Message> i = WrappedMessages.listIterator(); i.hasNext();) {
            WrappedMessage = i.next();
            UnWrappedBody = MessageUnWrapper(WrappedMessage.getBody());
            i.set(new Message(WrappedMessage.getSubject(), WrappedMessage.getSender(), WrappedMessage.getReceiver(), UnWrappedBody, WrappedMessage.getCreationtime()));
        }

        return WrappedMessages;
    }

    /***
     *
     * Method to encode the body of a message
     *
     * @param body String of the message body
     * @return Encoded string
     */
    public abstract String MessageWrapper(String body);

    /***
     *
     * Method to decode the body of a message
     *
     * @param body String of the encoded message body
     * @return Decoded string
     */
    public abstract String MessageUnWrapper(String body);

}
