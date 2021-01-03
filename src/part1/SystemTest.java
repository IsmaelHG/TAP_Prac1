package part1;

import part1.MailStore.MailStore;
import part1.MailStore.FileMailStore;
import part1.MailStore.MemMailStore;
import part1.exceptions.AlreadyTakenUsernameException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SystemTest {

    public static void main (String argv[]) throws AlreadyTakenUsernameException {
        
        MailStore mailstore = new MemMailStore();
        MailSystem mail = new MailSystem(mailstore);

        MailBox Pepitobox = mail.CreateUser("Pepito420", "Pepe", 2000);
        MailBox pepebox = mail.CreateUser("XxX_Pepe_XxX", "Pepe", 2000);
        MailBox franbox =mail.CreateUser("fran1980", "Francisco", 1980);

        Pepitobox.SendMail("Pepito420","Hola","Muy buenas", LocalDateTime.now());
        Pepitobox.SendMail("fran1980","adfs","Muy buenas", LocalDateTime.now());
        pepebox.SendMail("Pepito420","ADFS","Muy buenas", LocalDateTime.now());
        pepebox.SendMail("fran1980","adfs","Muy buenas", LocalDateTime.now());
        franbox.SendMail("XxX_Pepe_XxX","jajajaja","Muy buenas", LocalDateTime.now());
        franbox.SendMail("fran1980","Hola","Muy buenas", LocalDateTime.now());
        Pepitobox.SendMail("XxX_Pepe_XxX","Hola","Muy buenas", LocalDateTime.now());
        franbox.SendMail("Pepito420","Hola","Muy buenas", LocalDateTime.now());

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
            System.out.println(s+"\n\n");
        }

        System.out.println("\n\n");


        System.out.println(mail.countWordsFromName("Pepe")+"\n\n");

        System.out.println("\n\n");

        for (Message me : mail.getMessagesToUsersBornBeforeYear(2000) ) {
            System.out.println(me+"\n\n");
        }

        mailstore = new FileMailStore("mensajes.txt");

    }
}
