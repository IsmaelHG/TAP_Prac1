package oop.MailStore;

import oop.Message;

import java.io.*;
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
        File mailFile = new File(this.filename);
        FileWriter fr = null;
        try {
            fr = new FileWriter(mailFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter br = new BufferedWriter(fr);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            br.write(mail.getSubject()+";"+mail.getSender()+";"+mail.getReceiver()+";"+mail.getBody()+";"+mail.getCreationtime().format(formatter)+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }


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
        mailFileReader.close();


        return messages;
    }
}
