package oop;
import java.time.LocalDateTime;

/**
 *
 * This class will store the representation of a single message
 *
 */
public class Message {
    private String subject;
    private String sender, receiver;
    private String body;
    private final LocalDateTime creationtime;

    /**
     *
     * Message constructor
     *
     * @param subject Subject of the message
     * @param sender Sender of the message
     * @param receiver Receiver of the message
     * @param body Body of the message
     * @param creationtime Date of message creation
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
     * Getter
     *
     * @return Subject of the message
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * Getter
     *
     * @return Sender of the message
     */
    public String getSender() {
        return sender;
    }

    /**
     *
     * Getter
     *
     * @return Receiver of the message
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     *
     * Getter
     *
     * @return Body of the message
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * Getter
     *
     * @return Date of message creation
     */
    public LocalDateTime getCreationtime() {
        return creationtime;
    }

    /**
     *
     * Method for safely cloning the message
     *
     * @return cloned messsage
     */
    public Message clone() {
        return new Message(this.subject,this.sender,this.receiver,this.body,this.creationtime);
    }
}
