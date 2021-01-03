package part3.factories;

import part1.MailStore.MailStore;
import part1.MailStore.FileMailStore;
import part2.Strategies.CipherMessage;
import part2.Strategies.ReverseMessage;

public class MailStoreWrappedFactory extends MailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new CipherMessage(new ReverseMessage(new FileMailStore("messages.txt")));
    }
}
