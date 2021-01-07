package part3;

import part1.MailStore.MailStore;
import part1.MailSystem;
import part1.Message;
import part1.SystemTest;
import part1.exceptions.AlreadyTakenUsernameException;
import part3.factories.MailStoreFactory;
import part3.factories.MailStoreMemoryFactory;
import part3.factories.MailStoreRedisFactory;
import part3.factories.MailStoreWrappedFactory;

import java.util.Scanner;

/**
 *
 * Main program to create a specific factory, populate with diverse messages and print all of them
 *
 */
public class FactoriesTest {
    public static void main(String[] args) throws AlreadyTakenUsernameException {
        MailStoreFactory redisfactory = new MailStoreRedisFactory();
        MailStoreFactory memoryfactory = new MailStoreMemoryFactory();
        MailStoreFactory wrappedfactory = new MailStoreWrappedFactory();

        int op=0;
        MailStore mailstore = null;
        MailSystem mail;
        Scanner keyboard = new Scanner(System.in);

        while ( (op < 1) || (op > 3) ) {
            System.out.println("Please select a factory:");
            System.out.println("1. Memory factory");
            System.out.println("2. Wrapped factory");
            System.out.println("3. Redis factory (make sure redis server is running at 127.0.0.1:6379)");
            op = keyboard.nextInt();
            switch (op) {
                case 1:
                    mailstore = memoryfactory.createMailStore();
                    break;
                case 2:
                    mailstore = wrappedfactory.createMailStore();
                    break;
                case 3:
                    mailstore = redisfactory.createMailStore();
                    break;
                default:

            }
        }

        mail = new MailSystem(mailstore);
        SystemTest.mailsystempopulate(mail);

        for (Message m : mail.GetAllMessages()) {
            System.out.println(m+"\n\n");
        }


    }

}
