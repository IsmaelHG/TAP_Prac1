package part2.UnitTest;

import org.junit.Assert;
import org.junit.Test;
import part1.Message;
import part1.User;

import java.time.LocalDateTime;
import java.time.Month;

/***
 *
 * Unit test for all the classes implemented in part2
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
        }

        @Test
        public void testUser() {
            User usertest = new User("Pablo2000","Pablo",2000);
            System.out.println("-> TESTING USER CREATION...");
            Assert.assertTrue(usertest.getUsername().equals("Pablo2000"));
            Assert.assertTrue(usertest.getYearofbirth()==2000);
        }

        @Test
        public void testMailBox() {

        }

        @Test
        public void testMailSystem() {

        }

        @Test
        public void testMailStore() {

        }

    }
}
