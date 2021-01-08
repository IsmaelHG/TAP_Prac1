package part1.CLI;

import part1.MailStore.MailStore;
import part1.MailStore.MemMailStore;
import part1.MailSystem;
import part1.SystemTest;
import part1.exceptions.AlreadyTakenUsernameException;

public class CLIMain {
    public static void main(String[] args) throws AlreadyTakenUsernameException {
        CLI cliapplication;
        MailStore memmailstore = new MemMailStore();
        MailSystem mail = new MailSystem(memmailstore);

        cliapplication = new CLI(mail);
        SystemTest.mailsystempopulate(mail);

        cliapplication.CLISystem();

    }
}
