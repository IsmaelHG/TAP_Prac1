package patterns.Strategies;

import oop.MailStore.MailStore;

public class ReverseMessage extends Wrapper{
    public ReverseMessage(MailStore mail) {
        super(mail);
    }

    @Override
    public String MessageWrapper(String body) {
        return new StringBuilder().append(body).reverse().toString();
    }

    @Override
    public String MessageUnWrapper(String body) {
        return new StringBuilder().append(body).reverse().toString();
    }
}
