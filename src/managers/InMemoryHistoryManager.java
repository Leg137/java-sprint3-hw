package managers;

import tasks.Task;

import java.util.*;

/**
 * Класс для истории просмотра задач
 */
public class InMemoryHistoryManager implements HistoryManager {

    @Override
    public List<Task> getHistoryStorage() {
        return historyStorage;
    }

    private final List<Task> historyStorage = new LinkedList<>();

    /**
     * Добавляет задачи в коллекцию истории задач
     */
    @Override
    public void add(Task task) {
        getHistoryStorage().add(task);
    }

    /**
     * Возвращает список истории задач
     */
    @Override
    public List<Task> getHistory() {
        List<Task> tasks = new ArrayList<>();
        if (!getHistoryStorage().isEmpty()) {
            tasks.addAll(getHistoryStorage());
        }
        return tasks;
    }

    /**
     * Возвращает ограниченный список истории задач
     */
    public List<Task> getLimitedHistory(int limit) {
        List<Task> tasks = new ArrayList<>();
        if (!getHistoryStorage().isEmpty()) {
            tasks.addAll(getHistoryStorage());
            if (tasks.size() > limit) {
                return tasks.subList(tasks.size() - limit, tasks.size());
            } else {
                return tasks;
            }
        }
        return tasks;
    }
}