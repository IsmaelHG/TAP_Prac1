package part1.UnitTest;

import part1.MailBox;
import part1.MailStore.FileMailStore;
import part1.MailStore.MailStore;
import part1.MailStore.MemMailStore;
import part1.MailSystem;
import part1.Message;
import part1.User;
import org.junit.Assert;
import org.junit.Test;
import part1.exceptions.AlreadyTakenUsernameException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/***
 *
 * Unit test for all the classes implemented in part1
 *
 */
public class MailTest {

    public static class main {

        @Test
        public void testMessage() {
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
            Message messagetest = new Message("Hola", "UsuarioA", "UsuarioB", "Muy buenas", timetest);
            System.out.println("-> TESTING MESSAGE CREATION...");
            Assert.assertEquals("Hola", messagetest.getSubject());
            Assert.assertEquals("UsuarioA", messagetest.getSender());
            Assert.assertEquals("UsuarioB", messagetest.getReceiver());
            Assert.assertEquals("Muy buenas", messagetest.getBody());
            Assert.assertEquals(messagetest.getCreationtime(), timetest);
            // Message should conserve all the parameters
        }

        @Test
        public void testUser() {
            User usertest = new User("Pablo2000","Pablo",2000);
            System.out.println("-> TESTING USER CREATION...");
            Assert.assertEquals("Pablo2000", usertest.getUsername());
            Assert.assertEquals("Pablo", usertest.getName());
            Assert.assertEquals(2000, usertest.getYearofbirth());
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

            Assert.assertEquals(1, messagelist.size()); // Should be only one message
            Assert.assertEquals("Body", messagelist.get(0).getBody()); // Message content should be the same
            Assert.assertEquals("User1", messagelist.get(0).getSender());
            Assert.assertEquals("Subject", messagelist.get(0).getSubject());
            Assert.assertEquals(messagelist.get(0).getCreationtime(), timetest);
        }

        @Test
        public void testMailBoxSortedMail() {
            MailStore mailstoretest = new MemMailStore();
            MailBox mailboxtest = new MailBox("User1",mailstoretest);
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
            Comparator<Message> comparator = Comparator.comparing(Message::getBody);

            mailboxtest.SendMail("User1","Subject","BBBB",timetest);
            mailboxtest.SendMail("User1","Subject","DDDD",timetest);
            mailboxtest.SendMail("User1","Subject","AAAA",timetest);
            mailboxtest.SendMail("User1","Subject","CCCC",timetest);
            List<Message> messagelist = mailboxtest.GetSortedMail(comparator);

            Assert.assertEquals(4, messagelist.size()); // Should be only four messages
            Assert.assertEquals("AAAA", messagelist.get(0).getBody()); // Should be sorted by body content
            Assert.assertEquals("BBBB", messagelist.get(1).getBody()); // Should be sorted by body content
            Assert.assertEquals("CCCC", messagelist.get(2).getBody()); // Should be sorted by body content
            Assert.assertEquals("DDDD", messagelist.get(3).getBody()); // Should be sorted by body content
        }

        @Test
        public void testMailBoxFilterMail() {
            MailStore mailstoretest = new MemMailStore();
            MailBox mailboxtest = new MailBox("User1",mailstoretest);
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);
            Predicate<Message> filter = message -> message.getBody().equals("AAAA");

            mailboxtest.SendMail("User1","Subject","BBBB",timetest);
            mailboxtest.SendMail("User1","Subject","DDDD",timetest);
            mailboxtest.SendMail("User1","Subject","AAAA",timetest);
            mailboxtest.SendMail("User1","Subject","CCCC",timetest);
            List<Message> messagelist = mailboxtest.FilterMail(filter);

            Assert.assertEquals(1, messagelist.size()); // Should be only one message
        }

        @Test
        public void testMemMailStore() {
            MailStore mailstoretest = new MemMailStore();
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);

            mailstoretest.SendMail(new Message("Subject","User1","User1","Body",timetest));
            List<Message> messagelist = mailstoretest.GetMail("User1");

            Assert.assertEquals(1, messagelist.size()); // Should be only one message
            Assert.assertEquals("Body", messagelist.get(0).getBody()); // Message content should be the same
            Assert.assertEquals("User1", messagelist.get(0).getSender());
            Assert.assertEquals("Subject", messagelist.get(0).getSubject());
            Assert.assertEquals(messagelist.get(0).getCreationtime(), timetest);

        }

        @Test
        public void testFileMailStore() throws IOException {
            File file = new File("filetest.txt");
            if(file.exists()){
                file.delete();
            }
            MailStore mailstoretest = new FileMailStore("filetest.txt");
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);

            mailstoretest.SendMail(new Message("Subject","User1","User1","Body",timetest));
            List<Message> messagelist = mailstoretest.GetMail("User1");

            Assert.assertEquals(1, messagelist.size()); // Should be only one message
            Assert.assertEquals("Body", messagelist.get(0).getBody()); // Message content should be the same
            Assert.assertEquals("User1", messagelist.get(0).getSender());
            Assert.assertEquals("Subject", messagelist.get(0).getSubject());
            Assert.assertEquals(messagelist.get(0).getCreationtime(), timetest);
        }

        @Test
        public void testMailSystemUsersMailbox() throws AlreadyTakenUsernameException {
            MailStore mailstoretest = new MemMailStore();
            MailSystem mail = new MailSystem(mailstoretest);

            MailBox mailboxtest = mail.CreateUser("User1","User",2000);
            Assert.assertEquals(mailboxtest, mail.RetrieveMailBox("User1"));
            Assert.assertEquals("User1", mail.RetrieveUser("User1").getUsername());
        }

        @Test
        public void testMailSystemMessages() throws AlreadyTakenUsernameException {
            MailStore mailstoretest = new MemMailStore();
            MailSystem mail = new MailSystem(mailstoretest);

            MailBox mailboxtest1 = mail.CreateUser("User1","User",2000);
            MailBox mailboxtest2 = mail.CreateUser("User2","User",1999);
            LocalDateTime timetest = LocalDateTime.of(2018, Month.JULY, 29, 19, 30, 40);

            mailboxtest1.SendMail("User2","Subject","BBBB",timetest);
            mailboxtest2.SendMail("User1","Subject","DDDD",timetest);
            mailboxtest1.SendMail("User2","Subject","AAAA",timetest);
            mailboxtest2.SendMail("User1","Subject","CCCC",timetest);

            List<Message> messageList = mail.GetAllMessages();
            Assert.assertEquals(messageList.size(),4);
            Assert.assertEquals(messageList.size(),mail.CountAllMesssages());
            Assert.assertEquals(mail.GetAllUsers().size(),2);


        }

    }
}
