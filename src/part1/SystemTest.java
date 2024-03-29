package part1;

import part1.MailStore.MailStore;
import part1.MailStore.FileMailStore;
import part1.MailStore.MemMailStore;
import part1.exceptions.AlreadyTakenUsernameException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/***
 *
 * Main program for testing the mailsystem.
 *
 *
 */
public class SystemTest {
    public static void mailsystempopulate(MailSystem mail) throws AlreadyTakenUsernameException {
        MailBox Pepitobox = mail.CreateUser("Pepito420", "Pepe", 2000);
        MailBox pepebox = mail.CreateUser("XxX_Pepe_XxX", "Pepe", 2000);
        MailBox franbox =mail.CreateUser("fran1980", "Francisco", 1980);

        Pepitobox.SendMail("Pepito420","Hola","Muy buenas", LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40));
        Pepitobox.SendMail("fran1980","adfs","Muy buenas", LocalDateTime.of(2020, Month.MAY, 29, 19, 30, 40));
        pepebox.SendMail("Pepito420","ADFS","Muy buenas", LocalDateTime.of(2021, Month.JANUARY, 8, 13, 25, 33));
        pepebox.SendMail("fran1980","adfs","Muy buenas", LocalDateTime.of(2018, Month.DECEMBER, 29, 19, 30, 40));
        franbox.SendMail("XxX_Pepe_XxX","Prueba","Muy buenas", LocalDateTime.of(2015, Month.JULY, 5, 19, 30, 40));
        franbox.SendMail("fran1980","TAP","Muy buenas", LocalDateTime.of(2017, Month.MAY, 21, 13, 30, 30));
        Pepitobox.SendMail("XxX_Pepe_XxX","Hola","Muy buenas", LocalDateTime.of(2013, Month.JULY, 29, 19, 30, 40));
        franbox.SendMail("Pepito420","Hola","Muy buenas", LocalDateTime.of(2019, Month.AUGUST, 17, 19, 30, 40));

    }

    public static void main (String[] argv) throws AlreadyTakenUsernameException {

        // Initialize the mail system with an in-memorymail store
        MailStore mailstore = new MemMailStore();
        MailSystem mail = new MailSystem(mailstore);

        // Create at least 3 users, two have the same name but different username
        // Then, use the mailboxes to send a few emails between them.Make some of them share the same subject and make enough so that the following tests have results.
        mailsystempopulate(mail);

        MailBox Pepitobox = mail.RetrieveMailBox("Pepito420");

        // Get one of the mailboxes and update its mail.
        mail.RetrieveMailBox("Pepito420").UpdateMail();


        // List the mailbox messages in the console. (Sorted by newer first.) Use the iterable capabilities of the mailbox!
        for (Message m : Pepitobox) {
            System.out.println(m+"\n\n");
        }

        // Now list the messages by sender username using the mailbox feature.
        for (Message m : Pepitobox.FilterMail(message -> message.getSender().equals("XxX_Pepe_XxX"))) {
            System.out.println(m+"\n\n");
        }

        // Filter the messages with the following conditions:
        // -The message subject contains a certain word.
        // -The message sender is a certain user
        for (Message m : mail.FilterAllMessages(message -> message.getSender().equals("XxX_Pepe_XxX") && message.getBody().contains("buenas"))) {
            System.out.println(m+"\n\n");
        }

        // Use the mail system object to retrieve all messages and print them.
        for (Message m : mail.GetAllMessages()) {
            System.out.println(m+"\n\n");
        }

        //Filter messages globally that fulfill the following conditions:
        // -The message subject is a single word.
        // -The sender was born after year 2000.
        for (Message m : mail.FilterAllMessages(message -> new StringTokenizer(message.getSubject() ).countTokens() == 1 && mail.RetrieveUser(message.getSender()).getYearofbirth() > 2000 )) {
            System.out.println(m+"\n\n");
        }

        // Get the count of messages in the system and print it.
        System.out.println("Total number of messages is: "+mail.CountAllMesssages()+"\n");

        // Get the average number of messages received per user and print it.
        System.out.println("Average number of messages per user is: "+mail.avgMessagesPerUser()+"\n");

        // Group the messages per subject in a Map<String, List<Message>>and print it.
        Map<String, List<Message>> m = mail.groupMessagesPerSubject();
        for (String s : m.keySet()) {
            System.out.println("Subject group: "+s+"\n");
            m.get(s).forEach(System.out::println);
            System.out.println("\n");
        }

        // Count the words of all messages sent by users with a certain real name.
        // Use the name that you used on two users. Print the result.
        System.out.println("Number of words sent by users with the name Pepe is: "+mail.countWordsFromName("Pepe")+"\n\n");

        // Print the messages destinated to users born before the year 2000
        for (Message me : mail.getMessagesToUsersBornBeforeYear(2000) ) {
            System.out.println(me+"\n\n");
        }

        // Create a mailstore (file version) and copy all the messages
        MailStore mailstorefile = new FileMailStore("mailstore.txt");

        for (Message me : mail.GetAllMessages()) {
            mailstorefile.SendMail(me);
        }

    }
}
