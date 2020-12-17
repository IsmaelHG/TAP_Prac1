package patterns;

import oop.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SpamUserFilter implements PropertyChangeListener {

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Predicate<Message> filterSpam = message -> message.getSender().contains("spam");
        List<Message> lis = (List<Message>) evt.getNewValue();

        ((List<Message>) evt.getOldValue()).clear();
        ((List<Message>) evt.getOldValue()).addAll(lis.stream().filter(filterSpam).collect(Collectors.toList()));

        lis.removeIf(filterSpam);
    }
}
