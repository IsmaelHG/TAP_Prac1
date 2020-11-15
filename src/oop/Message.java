package oop;

import oop.User;


/***
 *
 *
 *
 */
import java.time.LocalDateTime;

public class Message {
    private String subject;
    private String sender, receiver;
    private String body;
    private final LocalDateTime creationtime;

    /**
     *
     * @param subject
     * @param sender
     * @param receiver
     * @param body
     * @param creationtime
     */
    public Message(String subject, String sender, String receiver, String body, LocalDateTime creationtime) {
        this.subject = subject;
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
        this.creationtime = creationtime;
    }

    /**
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @return
     */
    public String getSender() {
        return sender;
    }

    /**
     *
     * @return
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     *
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getCreationtime() {
        return creationtime;
    }
}
