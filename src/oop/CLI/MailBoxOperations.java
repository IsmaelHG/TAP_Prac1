package oop.CLI;

import oop.MailBox;
import oop.Message;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MailBoxOperations {
    private String currentUser;
    private MailBox currentMailBox;

    public MailBoxOperations(String username, MailBox currentMailBox) {
        this.currentUser = username;
        this.currentMailBox = currentMailBox;
    }

    public void SendMessage(String destination, String subject, String body) {
        this.currentMailBox.SendMail(destination, subject, body, LocalDateTime.now());
    }

    public void UpdateMail() {
        this.currentMailBox.UpdateMail();
    }

    public List<Message> ListMail() {
        return this.currentMailBox.ListMail();
    }

    public List<Message> SortMail(Comparator<Message> compare) {
        return this.currentMailBox.GetSortedMail(compare);
    }

    public List<Message>  FilterMail(Predicate<Message> condition) {
        return this.currentMailBox.FilterMail(condition);
    }

}
