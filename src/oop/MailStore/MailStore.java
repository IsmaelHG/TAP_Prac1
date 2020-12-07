package oop.MailStore;

import oop.Message;
import oop.User;

import java.util.List;

/**
 *
 *
 *
 */
public interface MailStore {


    /**
     *
     */
    public abstract void SendMail (Message mail);

    /**
     *
     * @return
     */
    public abstract List<Message> GetMail (String username);


}
