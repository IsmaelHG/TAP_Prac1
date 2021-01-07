package part3.factories;

import part1.MailStore.MailStore;
import part1.MailStore.MemMailStore;

/***
 *
 * MailStoreFactory with memory implementation
 *
 */
public class MailStoreMemoryFactory extends MailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new MemMailStore();
    }
}
