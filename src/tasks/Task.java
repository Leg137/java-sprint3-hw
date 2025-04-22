package tasks;

import managers.InMemoryTaskManager;

/**
 * Класс для создания обычных задач, Task задач
 */
public class Task {
    private final int id; // Уникальный идентификационный номер задачи, по которому её можно будет найти
    private final String name; // Название, кратко описывающее суть задачи
    private final String description; // Описание, в котором раскрываются детали
    private Status status; // Статус, отображающий её прогресс

    /**
     * Конструктор для создания Task задач
     */
    public Task(String nameTask, String descriptionTask, Status statusTask) {
        this.id = InMemoryTaskManager.getId() + 1; // При создании задачи менеджер присваивает ей новый идентификатор
        InMemoryTaskManager.setId(this.id);
        this.name = nameTask;
        this.description = descriptionTask;
        this.status = statusTask;
    }

    /**
     * Конструктор для создания задач наследников Epic задач и SubTask подзадач
     */
    Task(String nameTask, String descriptionTask) {
        this.id = InMemoryTaskManager.getId() + 1; // При создании задачи менеджер присваивает ей новый идентификатор
        InMemoryTaskManager.setId(this.id);
        this.name = nameTask;
        this.description = descriptionTask;
    }

    /**
     * Конструктор для копирования Task задач
     */
    public Task(Task task) {
        this(task.name, task.description, task.status);
    }

    public int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID задачи tasks.Task=\"" + id + "\", Название задачи=\"" + name + "\", Описание=\"" + description
                + "\", Статус=\"" + status + "\"";
    }

    public enum Status {
        NEW,
        DONE,
        IN_PROGRESS
    }
}