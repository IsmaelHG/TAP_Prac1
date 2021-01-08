package part1.CLI;
import part1.MailBox;
import part1.MailSystem;
import part1.Message;
import part1.exceptions.AlreadyTakenUsernameException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

/***
 * CLI Application
 *
 *
 */
public class CLI {
    private final MailSystem mail;

    private void ShowSystemMenu() {
        System.out.println("Choose a command option:");
        System.out.println("1. createuser <username> <name> <year of birth>");
        System.out.println("2. filter (contains <word>) or (lessthan <n>)");
        System.out.println("3. logas <username>");
        System.out.println("4. exit");
    }

    private void ShowUserMenu() {
        System.out.println("Choose a command option:");
        System.out.println("1. send <username of destination> <subject> <body>");
        System.out.println("2. update");
        System.out.println("3. list");
        System.out.println("4. sort");
        System.out.println("5. filter (contains <word>) or (lessthan <n>)");
        System.out.println("6. logout");
    }

    /**
     * CLI Constructor
     *
     * @param mailsys MailSystem used for this CLI
     */
    public CLI(MailSystem mailsys) {
        this.mail = mailsys;
    }

    /**
     *
     * Main CLI Method
     *
     */
    public void CLISystem() {
        Scanner keyboard = new Scanner(System.in);
        String inp = "";

        while (!inp.equals("4")) {
            ShowSystemMenu();
            inp = keyboard.nextLine();
            switch (inp) {
                case "1" -> {
                    System.out.println("Write the new username:");
                    String user = keyboard.nextLine();
                    System.out.println("Write the personal name:");
                    String name = keyboard.nextLine();
                    System.out.println("Write the year of birth:");
                    try {
                        int year = Integer.parseInt(keyboard.nextLine());
                        mail.CreateUser(user, name, year);
                    } catch (AlreadyTakenUsernameException e) {
                        e.printStackTrace();
                    }
                }
                case "2" -> FilterMailSystem();
                case "3" -> {
                    System.out.println("Write the username to logas:");
                    String username = keyboard.nextLine();
                    try {
                        this.CLIUser(username);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "4" -> System.out.println("Exiting the application. Bye!");
                default -> System.out.println("Incorrect option, try again.");
            }
        }


    }

    private void FilterMailSystem() {
        Scanner keyboard = new Scanner(System.in);
        String inp = "";

        while (!inp.equals("3")) {
            System.out.println("Choose an option for filtering the mail:");
            System.out.println("1. contains <word> : filters all messages that contain the word in the body or subject.");
            System.out.println("2. lessthan <n> :filters messages that contain less than n words in the body.");
            System.out.println("3. Exit filter menu");
            inp = keyboard.nextLine();
            switch (inp) {
                case "1" -> {
                    System.out.println("contains <word>: Write the <word>");
                    String wordt = keyboard.nextLine();
                    mail.FilterAllMessages(message -> message.getSubject().contains(wordt) || message.getBody().contains(wordt)).forEach(System.out::println);
                }
                case "2" -> {
                    System.out.println("lessthan <n>: Write the <n> word");
                    int nt;
                    try {
                        nt = Integer.parseInt(keyboard.nextLine());
                    } catch (Exception e) {
                        System.out.println("You must write a number!");
                        break;
                    }
                    int finalNt = nt;
                    mail.FilterAllMessages(message -> new StringTokenizer(message.getSubject()).countTokens() == finalNt).forEach(System.out::println);
                }
                case "3" -> System.out.println("Exiting filter menu");
                default -> System.out.println("Incorrect option, try again.");
            }
        }
    }

    private void CLIUser(String Username) {
        MailBox usermail = mail.RetrieveMailBox(Username);

        Scanner keyboard = new Scanner(System.in);
        String inp = "";

        while (!inp.equals("6")) {
            ShowUserMenu();
            inp = keyboard.nextLine();
            switch (inp) {
                case "1" -> {
                    System.out.println("Write the destination username: ");
                    String destination = keyboard.nextLine();
                    System.out.println("Write the subject: ");
                    String subject = keyboard.nextLine();
                    System.out.println("Write the body (no ';' or line jump allowed): ");
                    String body = keyboard.nextLine();
                    usermail.SendMail(destination, subject, body, LocalDateTime.now());
                    System.out.println("Message sent!");
                }
                case "2" -> {
                    usermail.UpdateMail();
                    System.out.println("Updated!");
                }
                case "3" -> usermail.ListMail().forEach(System.out::println);
                case "4" -> SortMailBox(usermail);
                case "5" -> FilterMailBox(usermail);
                case "6" -> System.out.println("Logging out from user "+Username+". Bye!");
                default -> System.out.println("Incorrect option, try again.");
            }
        }

    }

    private void SortMailBox(MailBox usermail) {

        Scanner keyboard = new Scanner(System.in);
        String inp = "";

        while (!inp.equals("6")) {
            System.out.println("Choose an option for sorting the mail:");
            System.out.println("1. By sent time");
            System.out.println("2. By sender username");
            System.out.println("3. By receiver username");
            System.out.println("4. By subject text");
            System.out.println("5. By body text");
            System.out.println("6. Exit sort menu");
            inp = keyboard.nextLine();
            switch (inp) {
                case "1" -> usermail.GetSortedMail(Comparator.comparing(Message::getCreationtime)).forEach(System.out::println);
                case "2" -> usermail.GetSortedMail(Comparator.comparing(Message::getSender)).forEach(System.out::println);
                case "3" -> usermail.GetSortedMail(Comparator.comparing(Message::getReceiver)).forEach(System.out::println);
                case "4" -> usermail.GetSortedMail(Comparator.comparing(Message::getSubject)).forEach(System.out::println);
                case "5" -> usermail.GetSortedMail(Comparator.comparing(Message::getBody)).forEach(System.out::println);
                case "6" -> System.out.println("Exiting sorting menu");
                default -> System.out.println("Incorrect option, try again.");
            }
        }
    }

    private void FilterMailBox(MailBox usermail) {
        Scanner keyboard = new Scanner(System.in);
        String inp = "";

        while (!inp.equals("3")) {
            System.out.println("Choose an option for filtering the mail:");
            System.out.println("1. contains <word> : filters all messages that contain the word in the body or subject.");
            System.out.println("2. lessthan <n> :filters messages that contain less than n words in the body.");
            System.out.println("3. Exit filter menu");
            inp = keyboard.nextLine();
            switch (inp) {
                case "1" -> {
                    System.out.println("contains <word>: Write the <word>");
                    String wordt = keyboard.nextLine();
                    usermail.FilterMail(message -> message.getSubject().contains(wordt) || message.getBody().contains(wordt)).forEach(System.out::println);
                }
                case "2" -> {
                    System.out.println("lessthan <n>: Write the <n>");
                    int nt;
                    try {
                        nt = Integer.parseInt(keyboard.nextLine());
                    } catch (Exception e) {
                        System.out.println("You must write a number!");
                        break;
                    }
                    int finalNt = nt;
                    usermail.FilterMail(message -> new StringTokenizer(message.getSubject()).countTokens()+1 < finalNt).forEach(System.out::println);
                }
                case "3" -> System.out.println("Exiting filter menu");
                default -> System.out.println("Incorrect option, try again.");
            }
        }
    }

}
