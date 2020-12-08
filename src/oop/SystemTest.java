package oop;

import oop.MailStore.MailStore;
import oop.MailStore.MailStoreMemory;

public class SystemTest {

    public static void main (String argv[]) {
        MailStore mailstore = new MailStoreMemory();
        MailSystem mail = new MailSystem(mailstore);

        MailBox Pepitobox = mail.CreateUser("Pepito420", "Pepe", 2000);
        MailBox pepebox = mail.CreateUser("XxX_Pepe_XxX", "Pepe", 2000);
        MailBox franbox =mail.CreateUser("fran1980", "Francisco", 1980);

        Pepitobox.SendMail("Pepito420","Hola","Muy buenas");
        Pepitobox.SendMail("fran1980","Hola","Muy buenas");
        pepebox.SendMail("Pepito420","Hola","Muy buenas");
        pepebox.SendMail("fran1980","Hola","Muy buenas");
        franbox.SendMail("XxX_Pepe_XxX","Hola","Muy buenas");
        franbox.SendMail("fran1980","Hola","Muy buenas");
        Pepitobox.SendMail("XxX_Pepe_XxX","Hola","Muy buenas");
        franbox.SendMail("Pepito420","Hola","Muy buenas");

        mail.RetrieveMailBox("Pepito420").UpdateMail();


        for (Message m : Pepitobox.ListMail()) {
            System.out.println(m+"\n\n");
        }

        System.out.println("\n\n");

        for (Message m : Pepitobox.FilterMail(message -> message.getSender().equals("XxX_Pepe_XxX"))) {
            System.out.println(m+"\n\n");
        }


    }
}
