package oop.MailStore;

import oop.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.FileHandler;

public class MailStoreFile implements MailStore {
    String filename;

    public void MailStore(String filename) {
        this.filename=filename;
    }


    /**
     * @param mail
     */
    @Override
    public void SendMail(Message mail) {

    }

    /**
     * @param username
     * @return
     */
    @Override
    public List<Message> GetMail(String username) {
        List<Message> messages = new LinkedList<>();
        File mailFile = new File(this.filename);
        Scanner mailFileReader = null;
        try {
            mailFileReader = new Scanner(mailFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        while (mailFileReader.hasNextLine()) {
            String message = mailFileReader.nextLine();
            StringTokenizer tk = new StringTokenizer(message,";");
            String subject = tk.nextToken();
            String sender = tk.nextToken();
            String receiver = tk.nextToken();
            String body = tk.nextToken();
            String date = tk.nextToken();
            if (receiver.equals(username)) {
                messages.add(new Message(subject, sender, receiver,body, LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            }
        }


        return messages;
    }
}
