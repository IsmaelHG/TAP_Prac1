package part2;

import part1.MailBox;
import part1.MailStore.MailStore;
import part1.MailStore.MemMailStore;
import part1.MailSystem;
import part1.Message;
import part1.exceptions.AlreadyTakenUsernameException;

import java.time.LocalDateTime;

/***
 *
 * Main program for testing the filters.
 *
 *
 */
public class FilterTest {
    public static void main (String[] argv) throws AlreadyTakenUsernameException {
        MailStore mailstore = new MemMailStore();
        MailSystem mail = new MailSystem(mailstore);

        MailBox Pepitobox = mail.CreateUser("Pepito420", "Pepe", 2000);
        MailBox ismaelbox = mail.CreateUser("Ismael2000", "Ismael", 2000);
        MailBox spambox = mail.CreateUser("spammer", "Spam", 1980);

        Pepitobox.SendMail("Pepito420", "Hola", "Student marks", LocalDateTime.now());
        ismaelbox.SendMail("Pepito420", "ADFS", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", LocalDateTime.now());
        spambox.SendMail("Pepito420", "Hola", "Muy buenas", LocalDateTime.now());

        MailBoxObservable Pepitoboxfilter = new MailBoxObservable("Pepito420", mailstore);

        System.out.println("Mailbox messages lists without any filter:\n---Normal message list---");
        Pepitoboxfilter.UpdateMail();
        for (Message m : Pepitoboxfilter) {
            System.out.println(m+"\n\n");
        }
        System.out.println("---Spam list---");
        for (Message m : Pepitoboxfilter.getSpamList()) {
            System.out.println(m+"\n\n");
        }

        SpamUserFilter spam = new SpamUserFilter();

        Pepitoboxfilter.addFilter(spam);

        System.out.println("Mailbox messages lists with spam filter:\n---Normal message list---");
        Pepitoboxfilter.UpdateMail();
        for (Message m : Pepitoboxfilter) {
            System.out.println(m+"\n\n");
        }
        System.out.println("---Spam list---");
        for (Message m : Pepitoboxfilter.getSpamList()) {
            System.out.println(m+"\n\n");
        }

        TooLongFilter toolong = new TooLongFilter();

        Pepitoboxfilter.removeFilter(spam);
        Pepitoboxfilter.addFilter(toolong);

        System.out.println("Mailbox messages lists with too long filter:\n---Normal message list---");
        Pepitoboxfilter.UpdateMail();
        for (Message m : Pepitoboxfilter) {
            System.out.println(m+"\n\n");
        }
        System.out.println("---Spam list---");
        for (Message m : Pepitoboxfilter.getSpamList()) {
            System.out.println(m+"\n\n");
        }

        Pepitoboxfilter.addFilter(spam);

        System.out.println("Mailbox messages lists with both filters:\n---Normal message list---");
        Pepitoboxfilter.UpdateMail();
        for (Message m : Pepitoboxfilter) {
            System.out.println(m+"\n\n");
        }
        System.out.println("---Spam list---");
        for (Message m : Pepitoboxfilter.getSpamList()) {
            System.out.println(m+"\n\n");
        }

    }
}
