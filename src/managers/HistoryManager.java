package managers;

import tasks.Task;

import java.util.List;

public interface HistoryManager {

    List<Task> getHistoryStorage();

    /**
     * Добавляет задачи в коллекцию истории задач
     */
    void add(Task task);

    /**
     * Возвращает список истории задач
     */
    List<Task> getHistory();
}