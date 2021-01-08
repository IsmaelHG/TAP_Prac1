package part4;

import part1.MailStore.FileMailStore;
import part1.MailStore.MailStore;
import part1.MailStore.MemMailStore;
import part1.MailSystem;
import part1.SystemTest;
import part1.exceptions.AlreadyTakenUsernameException;

import java.lang.reflect.InvocationTargetException;

public class LogTest {

    public static void main (String[] argv) throws AlreadyTakenUsernameException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        MailSystemConfig mail = new MailSystemConfig();
        SystemTest.mailsystempopulate(mail);
        mail.GetAllMessages();

    }

}
