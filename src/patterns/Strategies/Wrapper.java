package patterns.Strategies;

import oop.MailStore.MailStore;
import oop.Message;

import java.util.List;
import java.util.ListIterator;

public abstract class Wrapper implements MailStore {
    protected MailStore mailst;

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

    public abstract String MessageWrapper(String body);

    public abstract String MessageUnWrapper(String body);

}
