package part3;

import part1.MailBox;
import part1.MailStore.MailStore;
import part1.MailSystem;
import part1.Message;
import part1.SystemTest;
import part1.exceptions.AlreadyTakenUsernameException;
import part3.factories.MailStoreFactory;
import part3.factories.MailStoreRedisFactory;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/***
 *
 * Main program for testing the Redis mailstore by using a MailStoreRedisFactory.
 * The server must be 127.0.0.1:6379
 *
 */
public class RedisTest {

    public static void main(String[] args) throws AlreadyTakenUsernameException {

        MailStoreFactory mailstorefact = new MailStoreRedisFactory();
        MailStore mailstore = mailstorefact.createMailStore();
        MailSystem mail = new MailSystem(mailstore);

        SystemTest.mailsystempopulate(mail);

        MailBox Pepitobox = mail.RetrieveMailBox("Pepito420");

        mail.RetrieveMailBox("Pepito420").UpdateMail();

        for (Message m : Pepitobox) {
            System.out.println(m+"\n\n");
        }

        System.out.println("\n\n");

        for (Message m : Pepitobox.FilterMail(message -> message.getSender().equals("XxX_Pepe_XxX"))) {
            System.out.println(m+"\n\n");
        }

        System.out.println("\n\n");

        for (Message m : mail.FilterAllMessages(message -> message.getSender().equals("XxX_Pepe_XxX") && message.getBody().contains("buenas"))) {
            System.out.println(m+"\n\n");
        }

        System.out.println("\n\n");

        for (Message m : mail.GetAllMessages()) {
            System.out.println(m+"\n\n");
        }

        System.out.println("\n\n");

        for (Message m : mail.FilterAllMessages(message -> new StringTokenizer(message.getSubject() ).countTokens() == 1 && mail.RetrieveUser(message.getSender()).getYearofbirth() > 2000 )) {
            System.out.println(m+"\n\n");
        }

        System.out.println("\n\n");

        System.out.println(mail.CountAllMesssages()+"\n\n");

        System.out.println("\n\n");

        System.out.println(mail.avgMessagesPerUser()+"\n\n");

        System.out.println("\n\n");

        Map<String, List<Message>> m = mail.groupMessagesPerSubject();
        for (String s : m.keySet()) {
            System.out.println(s+"\n");
        }

        System.out.println("\n\n");


        System.out.println(mail.countWordsFromName("Pepe")+"\n\n");

        System.out.println("\n\n");

        for (Message me : mail.getMessagesToUsersBornBeforeYear(2000) ) {
            System.out.println(me+"\n\n");
        }
    }

}
