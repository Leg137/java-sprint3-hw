import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public interface TaskManager {
    TreeMap<Integer, Task> getTaskStorage();

    TreeMap<Integer, EpicTask> getEpicTaskStorage();

    TreeMap<Integer, EpicTask.SubTask> getSubTaskStorage();

    void saveToStorage(Object object);

    ArrayList<Object> getCompleteListOfAnyTasks(TreeMap<Integer, ? extends Task> treeMap);

    void deleteAllTasksOfAnyType(TreeMap<Integer, ? extends Task> treeMap);

    Object getTaskOfAnyTypeById(int id);

    Object createCopyOfTaskOfAnyType(Object object);

    void updateTaskOfAnyType(int id, Object object);

    void removeTaskOfAnyTypeById(int id);

    ArrayList<EpicTask.SubTask> getCompleteListOfSubTaskByEpicTask(EpicTask epicTask);

    List<Task> getHistory();
}