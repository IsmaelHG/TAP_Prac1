package part1.UnitTest;

import part1.MailBox;
import part1.MailStore.FileMailStore;
import part1.MailStore.MailStore;
import part1.MailStore.MemMailStore;
import part1.Message;
import part1.User;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;

/***
 *
 * Unit test for all the classes implemented in part1
 *
 */
public class MailTest {

    public class main {

        @Test
        public void testMessage() {
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
            Message messagetest = new Message("Hola", "UsuarioA", "UsuarioB", "Muy buenas", timetest);
            System.out.println("-> TESTING MESSAGE CREATION...");
            Assert.assertTrue(messagetest.getSubject().equals("Hola"));
            Assert.assertTrue(messagetest.getSubject().equals("UsuarioA"));
            Assert.assertTrue(messagetest.getSubject().equals("UsuarioB"));
            Assert.assertTrue(messagetest.getSubject().equals("Muy buenas"));
            Assert.assertTrue(messagetest.getCreationtime().equals(timetest));
            // Message should conserve all the parameters
        }

        @Test
        public void testUser() {
            User usertest = new User("Pablo2000","Pablo",2000);
            System.out.println("-> TESTING USER CREATION...");
            Assert.assertTrue(usertest.getUsername().equals("Pablo2000"));
            Assert.assertTrue(usertest.getName().equals("Pablo"));
            Assert.assertTrue(usertest.getYearofbirth()==2000);
            // User should conserve all the parameters
        }

        @Test
        public void testMailBoxSendUpdateListMail() {
            MailStore mailstoretest = new MemMailStore();
            MailBox mailboxtest = new MailBox("User1",mailstoretest);
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);

            mailboxtest.SendMail("User1","Subject","Body",timetest);
            mailboxtest.UpdateMail();
            List<Message> messagelist = mailboxtest.ListMail();

            Assert.assertTrue(messagelist.size()==1); // Should be only one message
            Assert.assertTrue(messagelist.get(0).getBody().equals("Body")); // Message content should be the same
            Assert.assertTrue(messagelist.get(0).getSender().equals("User1"));
            Assert.assertTrue(messagelist.get(0).getSubject().equals("Subject"));
            Assert.assertTrue(messagelist.get(0).getCreationtime().equals(timetest));
        }

        @Test
        public void testMailBoxSortedMail() {
            MailStore mailstoretest = new MemMailStore();
            MailBox mailboxtest = new MailBox("User1",mailstoretest);
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
            Comparator<Message> comparator = (x,y)->x.getBody().compareTo(y.getBody());

            mailboxtest.SendMail("User1","Subject","BBBB",timetest);
            mailboxtest.SendMail("User1","Subject","DDDD",timetest);
            mailboxtest.SendMail("User1","Subject","AAAA",timetest);
            mailboxtest.SendMail("User1","Subject","CCCC",timetest);
            List<Message> messagelist = mailboxtest.GetSortedMail(comparator);

            Assert.assertTrue(messagelist.size()==4); // Should be only four messages
            Assert.assertTrue(messagelist.get(0).getBody().equals("AAAA")); // Should be sorted by body content
            Assert.assertTrue(messagelist.get(1).getBody().equals("BBBB")); // Should be sorted by body content
            Assert.assertTrue(messagelist.get(2).getBody().equals("CCCC")); // Should be sorted by body content
            Assert.assertTrue(messagelist.get(3).getBody().equals("DDDD")); // Should be sorted by body content
        }

        @Test
        public void testMemMailStore() {
            MailStore mailstoretest = new MemMailStore();
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);

            mailstoretest.SendMail(new Message("Subject","User1","User1","Body",timetest));
            List<Message> messagelist = mailstoretest.GetMail("User1");

            Assert.assertTrue(messagelist.size()==1); // Should be only one message
            Assert.assertTrue(messagelist.get(0).getBody().equals("Body")); // Message content should be the same
            Assert.assertTrue(messagelist.get(0).getSender().equals("User1"));
            Assert.assertTrue(messagelist.get(0).getSubject().equals("Subject"));
            Assert.assertTrue(messagelist.get(0).getCreationtime().equals(timetest));

        }

        @Test
        public void testFileMailStore() {
            MailStore mailstoretest = new FileMailStore("filetest.txt");
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);

            mailstoretest.SendMail(new Message("Subject","User1","User1","Body",timetest));
            List<Message> messagelist = mailstoretest.GetMail("User1");

            Assert.assertTrue(messagelist.size()==1); // Should be only one message
            Assert.assertTrue(messagelist.get(0).getBody().equals("Body")); // Message content should be the same
            Assert.assertTrue(messagelist.get(0).getSender().equals("User1"));
            Assert.assertTrue(messagelist.get(0).getSubject().equals("Subject"));
            Assert.assertTrue(messagelist.get(0).getCreationtime().equals(timetest));
        }

        @Test
        public void testMailSystem() {

        }

    }
}
