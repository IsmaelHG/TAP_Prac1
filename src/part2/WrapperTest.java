package part2;

import part1.MailBox;
import part1.MailStore.FileMailStore;
import part1.MailStore.MailStore;
import part1.MailSystem;
import part1.Message;
import part1.exceptions.AlreadyTakenUsernameException;
import part2.Strategies.CipherMessage;
import part2.Strategies.ReverseMessage;

import java.time.LocalDateTime;

/***
 *
 * Main program for testing the wrappers.
 *
 *
 */
public class WrapperTest {

    public static void main (String[] argv) throws AlreadyTakenUsernameException {
        MailStore mailstore = new FileMailStore("notwrapped.txt");
        MailSystem mail = new MailSystem(mailstore);

        MailBox Pepitobox = mail.CreateUser("Pepito420", "Pepe", 2000);
        MailBox pepebox = mail.CreateUser("XxX_Pepe_XxX", "Pepe", 2000);
        MailBox franbox =mail.CreateUser("fran1980", "Francisco", 1980);

        Pepitobox.SendMail("Pepito420","Hola","Student marks", LocalDateTime.now());
        Pepitobox.SendMail("fran1980","adfs","Muy buenas", LocalDateTime.now());
        pepebox.SendMail("Pepito420","ADFS","Muy buenas", LocalDateTime.now());
        pepebox.SendMail("fran1980","adfs","Muy buenas", LocalDateTime.now());
        franbox.SendMail("XxX_Pepe_XxX","jajajaja","Muy buenas", LocalDateTime.now());
        franbox.SendMail("fran1980","Hola","Muy buenas", LocalDateTime.now());
        Pepitobox.SendMail("XxX_Pepe_XxX","Hola","Muy buenas", LocalDateTime.now());
        franbox.SendMail("Pepito420","Hola","Muy buenas", LocalDateTime.now());

        System.out.println("Normal mailstore:");

        for (Message m : Pepitobox) {
            System.out.println(m+"\n\n");
        }

        MailStore mailstorereversed = new ReverseMessage(new FileMailStore("reverse.txt"));

        for (Message me : mail.GetAllMessages()) {
            mailstorereversed.SendMail(me);
        }

        System.out.println("Reversed mailstore:");

        for (Message m : mailstorereversed.GetMail("Pepito420")) {
            System.out.println(m+"\n\n");
        }


        //


        MailStore mailstorecipher =  new CipherMessage(new FileMailStore("cipher.txt"));

        for (Message me : mail.GetAllMessages()) {
            mailstorecipher.SendMail(me);
        }

        System.out.println("Ciphered mailstore:");

        for (Message m : mailstorecipher.GetMail("Pepito420")) {
            System.out.println(m+"\n\n");
        }


        //


        MailStore mailstorereversedcipher = new ReverseMessage (new CipherMessage(new FileMailStore("cipherreverse.txt")));

        for (Message me : mail.GetAllMessages()) {
            mailstorereversedcipher.SendMail(me);
        }

        System.out.println("Reversed cipher mailstore:");

        for (Message m : mailstorereversedcipher.GetMail("Pepito420")) {
            System.out.println(m+"\n\n");
        }


    }

}
