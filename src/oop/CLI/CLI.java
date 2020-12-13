package oop.CLI;

import oop.MailStore.MailStore;
import oop.MailStore.MailStoreMemory;
import oop.MailSystem;

import java.util.Scanner;

public class CLI {
    private MailSystem mail;
    private SystemOperations operations;

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

    public CLI(MailSystem mailsys) {
        operations = new SystemOperations(mailsys);
    }

    public void CLISystem() {
        ShowSystemMenu();
        Scanner keyboard = new Scanner(System.in);
        String inp = keyboard.nextLine();

        while (!inp.equals("4")) {
            switch (inp) {
                case "1":
                    break;

                case "2":
                    break;

                case "3":
                    break;

                default:
                    System.out.println("Incorrect option, try again.");
                    break;
            }
            inp = keyboard.nextLine();
        }

        System.out.println("Exiting the application. Bye!");


    }

    private void CLIUser(String Username) {
        ShowUserMenu();
        Scanner keyboard = new Scanner(System.in);
        String inp = keyboard.nextLine();

        while (!inp.equals("6")) {
            switch (inp) {
                case "1":
                    break;

                case "2":
                    break;

                case "3":
                    break;

                case "4":
                    break;

                case "5":
                    break;

                default:
                    System.out.println("Incorrect option, try again.");
                    break;
            }
            inp = keyboard.nextLine();
        }

        System.out.println("Logging out from user "+Username+". Bye!");

    }
}
