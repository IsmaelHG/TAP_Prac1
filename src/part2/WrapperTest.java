package part2;

import part1.MailBox;
import part1.MailStore.FileMailStore;
import part1.MailStore.MailStore;
import part1.MailSystem;
import part1.Message;
import part1.SystemTest;
import part1.exceptions.AlreadyTakenUsernameException;
import part2.Strategies.CipherMessage;
import part2.Strategies.ReverseMessage;

import java.io.File;
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

        SystemTest.mailsystempopulate(mail);

        //

        System.out.println("REVERSED MAILSTORE TEST:");
        MailStore mailstorereversed = new ReverseMessage(new FileMailStore("reverse.txt"));
        for (Message m : mail.GetAllMessages()) {
            mailstorereversed.SendMail(m);
        }
        for (Message m : mailstorereversed.GetMail("Pepito420")) {
            System.out.println(m+"\n\n");
        }

        //

        System.out.println("CIPHERED MAILSTORE TEST:");
        MailStore mailstorecipher =  new CipherMessage(new FileMailStore("cipher.txt"));
        for (Message me : mail.GetAllMessages()) {
            mailstorecipher.SendMail(me);
        }
        for (Message m : mailstorecipher.GetMail("Pepito420")) {
            System.out.println(m+"\n\n");
        }

        //

        System.out.println("REVERSED CIPHER MAILSTORE:");
        MailStore mailstorereversedcipher = new ReverseMessage (new CipherMessage(new FileMailStore("cipherreverse.txt")));
        for (Message me : mail.GetAllMessages()) {
            mailstorereversedcipher.SendMail(me);
        }
        for (Message m : mailstorereversedcipher.GetMail("Pepito420")) {
            System.out.println(m+"\n\n");
        }


    }

}
