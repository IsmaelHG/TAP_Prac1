package part1.CLI;

import part1.MailStore.MailStore;
import part1.MailStore.MemMailStore;
import part1.MailSystem;

public class CLIMain {
    public static void main(String[] args) {
        CLI cliapplication;
        MailStore memmailstore = new MemMailStore();
        MailSystem mail = new MailSystem(memmailstore);

        cliapplication = new CLI(mail);

        cliapplication.CLISystem();

    }
}
