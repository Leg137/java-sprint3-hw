package managers;

import tasks.EpicTask;
import tasks.Task;

import java.util.*;

/**
 * Менеджер хранит всю информацию в оперативной памяти
 */
public class InMemoryTaskManager implements TaskManager {
    protected HistoryManager historyManager = Managers.getDefaultHistory();

    private static int Id = 0; // У каждого типа задач есть идентификатор, целое, уникальное для всех типов задач число
    // Возможность хранить задачи всех типов


    private final TreeMap<Integer, Task> taskStorage = new TreeMap<>();

    private final TreeMap<Integer, EpicTask> epicTaskStorage = new TreeMap<>();

    private final TreeMap<Integer, EpicTask.SubTask> subTaskStorage = new TreeMap<>();

    public static int getId() {
        return Id;
    }

    public static void setId(int id) {
        Id = id;
    }

    @Override
    public TreeMap<Integer, Task> getTaskStorage() {
        return taskStorage;
    }

    @Override
    public TreeMap<Integer, EpicTask> getEpicTaskStorage() {
        return epicTaskStorage;
    }

    @Override
    public TreeMap<Integer, EpicTask.SubTask> getSubTaskStorage() {
        return subTaskStorage;
    }
    // Методы для каждого из типа задач(Задача/Эпик/Подзадача):

    /**
     * Метод для сохранения задач всех типов.
     */
    @Override
    public void saveToStorage(Object object) {
        switch (object.getClass().toString()) {
            case "class tasks.Task": {
                taskStorage.put(((Task) object).getId(), (Task) object);
                break;
            }
            case "class tasks.EpicTask": {
                epicTaskStorage.put(((EpicTask) object).getId(), (EpicTask) object);
                break;
            }
            case "class tasks.EpicTask$SubTask": {
                subTaskStorage.put(((EpicTask.SubTask) object).getId(), (EpicTask.SubTask) object);
                break;
            }
        }
    }

    /**
     * Получение списка всех задач;
     */
    @Override
    public ArrayList<Object> getCompleteListOfAnyTasks(TreeMap<Integer, ? extends Task> treeMap) {
        ArrayList<Object> completeListOfAnyTasks = new ArrayList<>();

        for (Integer key : treeMap.keySet()) {
            completeListOfAnyTasks.add(treeMap.get(key));
        }
        return completeListOfAnyTasks;
    }

    /**
     * Удаление всех задач;
     */
    @Override
    public void deleteAllTasksOfAnyType(TreeMap<Integer, ? extends Task> treeMap) {
        treeMap.clear();
    }

    /**
     * Получение по идентификатору;
     */
    @Override
    public Object getTaskOfAnyTypeById(int id) {
        Task taskOfAnyKind = null;

        if (taskStorage.get(id) != null) {
            taskOfAnyKind = taskStorage.get(id);
            historyManager.add(taskOfAnyKind);
        } else if (epicTaskStorage.get(id) != null) {
            taskOfAnyKind = epicTaskStorage.get(id);
            historyManager.add(taskOfAnyKind);
        } else if (subTaskStorage.get(id) != null) {
            taskOfAnyKind = subTaskStorage.get(id);
            historyManager.add(taskOfAnyKind);
        }
        return taskOfAnyKind;
    }

    /**
     * Создание. Сам объект должен передаваться в качестве параметра;
     */
    @Override
    public Object createCopyOfTaskOfAnyType(Object object) {
        switch (object.getClass().toString()) {
            case "class tasks.Task": {
                return new Task((Task) object);
            }
            case "class tasks.EpicTask$SubTask": {
                return new EpicTask.SubTask((EpicTask.SubTask) object);
            }
            case "class tasks.EpicTask": {
                return new EpicTask((EpicTask) object);
            }
            default:
                return null;
        }
    }

    /**
     * Обновление. Новая версия объекта с верным идентификатором передаются в виде параметра;
     */
    @Override
    public void updateTaskOfAnyType(int id, Object object) {
        switch (object.getClass().toString()) {
            case "class tasks.Task": {
                taskStorage.put(id, (Task) object);
                break;
            }
            case "class tasks.EpicTask": {
                epicTaskStorage.put(id, (EpicTask) object);
                break;
            }
            case "class tasks.EpicTask$SubTask": {
                subTaskStorage.put(id, (EpicTask.SubTask) object);
                break;
            }
        }
    }

    /**
     * Удаление по идентификатору.
     */
    @Override
    public void removeTaskOfAnyTypeById(int id) {
        for (Integer task : taskStorage.keySet()) {
            if (id == task) {
                taskStorage.remove(id);
                break;
            }
        }
        for (Integer epicTask : epicTaskStorage.keySet()) {
            if (id == epicTask) {
                epicTaskStorage.remove(id);
                break;
            }
        }
        for (Integer subTask : subTaskStorage.keySet()) {
            if (id == subTask) {
                subTaskStorage.remove(id);
                break;
            }
        }
    }

    /**
     * Дополнительные методы:
     * Получение списка всех подзадач определённого эпика.
     */
    @Override
    public ArrayList<EpicTask.SubTask> getCompleteListOfSubTaskByEpicTask(EpicTask epicTask) {
        return epicTask.getSubTasks();
    }

    /**
     * Метод для управления статусом для Epic задач.
     * Если у эпика нет подзадач или все они имеют статус NEW | DONE, то статус должен быть NEW | DONE.
     * Во всех остальных случаях статус должен быть IN_PROGRESS.
     */
    public static Task.Status getEpicTaskStatus(ArrayList<EpicTask.SubTask> subTasks) {
        Task.Status statusEpicTask;
        int countNew = 0;
        int countDone = 0;

        for (EpicTask.SubTask subTask : subTasks) {
            if (subTask.getStatus().equals(Task.Status.NEW)) {
                countNew++;
            }
            if (!subTask.getStatus().equals(Task.Status.DONE)) {
                countDone++;
            }
        }

        if ((subTasks.isEmpty()) || (countNew == subTasks.size())) {
            statusEpicTask = Task.Status.NEW; // если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW
        } else if (countDone == subTasks.size()) {
            statusEpicTask = Task.Status.DONE; // если все подзадачи имеют статус DONE, эпик считается завершённым, статус DONE
        } else {
            statusEpicTask = Task.Status.IN_PROGRESS;
        }
        return statusEpicTask;
    }
}