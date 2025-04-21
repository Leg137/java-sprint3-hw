/**
 * Класс для создания обычных задач, Task задач
 */
public class Task {
    private final int id; // Уникальный идентификационный номер задачи, по которому её можно будет найти
    private final String name; // Название, кратко описывающее суть задачи
    private final String description; // Описание, в котором раскрываются детали
    private String status; // Статус, отображающий её прогресс

    /**
     * Конструктор для создания Task задач
     */
    Task(String nameTask, String descriptionTask, String statusTask) {
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
    Task(Task task) {
        this(task.name, task.description, task.status);
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID задачи Task=\"" + id + "\", Название задачи=\"" + name + "\", Описание=\"" + description
                + "\", Статус=\"" + status + "\"";
    }
}