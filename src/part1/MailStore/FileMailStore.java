package part1.MailStore;

import part1.Message;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileMailStore implements MailStore {
    String filename;

    /**
     * MailStore (File version) constructor.
     *
     * @param filename name of the file that will store all the messages (in plain text)
     */
    public FileMailStore(String filename) {
        this.filename=filename;
    }

    @Override
    public void SendMail(Message mail) {
        // We open the file and prepare it to be written in append mode
        File mailFile = new File(this.filename);
        FileWriter fr = null;
        try {
            fr = new FileWriter(mailFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter br = new BufferedWriter(fr);

        // We establish the format to represent the message send date as a text
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            // We write the message, every field is delimited with a ";" and the message will end with a newline marker
            br.write(mail.getSubject()+";"+mail.getSender()+";"+mail.getReceiver()+";"+mail.getBody()+";"+mail.getCreationtime().format(formatter)+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // We finished writing the file
        try {
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Message> GetMail(String username) {
        // We open the file and prepare it to be read from the beginning
        List<Message> messages = new LinkedList<>();
        File mailFile = new File(this.filename);
        Scanner mailFileReader = null;
        try {
            mailFileReader = new Scanner(mailFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        // Read every line of the text file and treat it as an individual message
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

        // We finished reading the file
        mailFileReader.close();


        return messages;
    }
}
