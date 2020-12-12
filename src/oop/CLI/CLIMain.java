package oop.CLI;

import oop.MailStore.MailStore;
import oop.MailStore.MailStoreMemory;
import oop.MailSystem;

public class CLIMain {
    public void ShowSystemMenu() {
        System.out.println("Choose a command option:");
        System.out.println("1. createuser <username> <name> <year of birth>");
        System.out.println("2. filter (contains <word>) or (lessthan <n>)");
        System.out.println("3. logas <username>");
        System.out.println("4. exit");
    }

    public void ShowUserMenu() {
        System.out.println("Choose a command option:");
        System.out.println("1. send <username of destination> <subject> <body>");
        System.out.println("2. update");
        System.out.println("3. list");
        System.out.println("4. sort");
        System.out.println("5. filter (contains <word>) or (lessthan <n>)");
        System.out.println("6. logout");
    }

    public static void main(String args[]) {
        MailStore mailstore = new MailStoreMemory();
        MailSystem mail = new MailSystem(mailstore);

        SystemOperations operations = new SystemOperations(mail);



    }
}
