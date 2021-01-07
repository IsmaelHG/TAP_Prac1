package part3.factories;

import part1.MailStore.MailStore;

/***
 *
 * MailStoreFactory
 *
 */
public abstract class MailStoreFactory {

    /***
     *
     * @return MailStore created by the specific factory
     */
    public abstract MailStore createMailStore();

}
