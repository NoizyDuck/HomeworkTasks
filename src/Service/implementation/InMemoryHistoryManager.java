package service.implementation;
import java.util.LinkedList;
import model.Task;
import java.util.List;
import service.HistoryManager;

public class InMemoryHistoryManager implements HistoryManager {
    private LinkedList<Task> history = new LinkedList<>();

    @Override
    public void addTask(Task task){
        history.addFirst(task);
        if (history.size() > 10) {
            history.removeLast();
        }
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        return "InMemoryHistoryManager{" +
            "history=" + history +
            '}';
    }
}
