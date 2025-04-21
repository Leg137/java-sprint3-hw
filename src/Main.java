import java.util.ArrayList;
import java.util.Arrays;

/**
 * «Трекер задач»
 */
public class Main {
    public static void main(String[] args) {
        // Тестирование
        TaskManager inMemoryTaskManager = new InMemoryTaskManager();

        // Создали 2‑е Task задачи
        Task taskFirst = new Task("Поесть", "Принять пищу", "NEW");
        Task taskSecond = new Task("Поспать", "Хорошенько выспаться", "DONE");

        // Создали 1у EpicTask задачу с 2мя SubTask подзадачами
        ArrayList<EpicTask.SubTask> subTasksEpicTaskFirst = new ArrayList<>();

        EpicTask.SubTask subtaskFirstEpicTaskFirst = new EpicTask.SubTask("Закончить учебу",
                "Сдать все спринты", "Вовремя выполнить ТЗ", "NEW");
        EpicTask.SubTask subtaskSecondEpicTaskFirst = new EpicTask.SubTask("Закончить учебу",
                "Сдать дипломный проект", "Сделать дипломный проект", "DONE");

        subTasksEpicTaskFirst.add(subtaskFirstEpicTaskFirst);
        subTasksEpicTaskFirst.add(subtaskSecondEpicTaskFirst);

        EpicTask epicTaskFirst = new EpicTask("Закончить учебу",
                "Получить сертификат обучения", subTasksEpicTaskFirst);

        // Создали 2ю EpicTask задачу с 1‑й SubTask подзадачей
        EpicTask.SubTask subtaskFirstEpicTaskSecond = new EpicTask.SubTask("Сменить работу",
                "Закончить курс по Java", "Научиться программировать на языке Java",
                "NEW");
        ArrayList<EpicTask.SubTask> subTasksEpicTaskSecond = new ArrayList<>();

        subTasksEpicTaskSecond.add(subtaskFirstEpicTaskSecond);

        EpicTask epicTaskSecond = new EpicTask("Сменить работу"
                , "Начать работать Java разработчиком", subTasksEpicTaskSecond);

        // Возможность хранить задачи всех типов
        inMemoryTaskManager.saveToStorage(taskFirst);
        inMemoryTaskManager.saveToStorage(taskSecond);
        inMemoryTaskManager.saveToStorage(subtaskFirstEpicTaskFirst);
        inMemoryTaskManager.saveToStorage(subtaskSecondEpicTaskFirst);
        inMemoryTaskManager.saveToStorage(epicTaskFirst);
        inMemoryTaskManager.saveToStorage(subtaskFirstEpicTaskSecond);
        inMemoryTaskManager.saveToStorage(epicTaskSecond);

        /*
          Методы для каждого из типа задач(Task/EpicTask/SubTask):
          Получение списка всех задач;
         */
        System.out.println("\n    Получение списка всех задач:");
        System.out.println(Arrays.toString(inMemoryTaskManager.getCompleteListOfAnyTasks(inMemoryTaskManager.getTaskStorage()).toArray()));
        System.out.println(Arrays.toString(inMemoryTaskManager.getCompleteListOfAnyTasks(inMemoryTaskManager.getEpicTaskStorage()).toArray()));
        System.out.println(Arrays.toString(inMemoryTaskManager.getCompleteListOfAnyTasks(inMemoryTaskManager.getSubTaskStorage()).toArray()));

        //  Удаление всех задач;
        inMemoryTaskManager.deleteAllTasksOfAnyType(inMemoryTaskManager.getEpicTaskStorage());

        System.out.println("\n     Удаление всех задач:");
        System.out.println(Arrays.toString(inMemoryTaskManager.getCompleteListOfAnyTasks(inMemoryTaskManager.getTaskStorage()).toArray()));
        System.out.println(Arrays.toString(inMemoryTaskManager.getCompleteListOfAnyTasks(inMemoryTaskManager.getEpicTaskStorage()).toArray()));
        System.out.println(Arrays.toString(inMemoryTaskManager.getCompleteListOfAnyTasks(inMemoryTaskManager.getSubTaskStorage()).toArray()));

        //  Получение по идентификатору;
        System.out.println("\n     Получение по идентификатору:");
        System.out.println(inMemoryTaskManager.getTaskOfAnyTypeById(0));
        System.out.println(inMemoryTaskManager.getTaskOfAnyTypeById(1));
        System.out.println(inMemoryTaskManager.getTaskOfAnyTypeById(2));
        System.out.println(inMemoryTaskManager.getTaskOfAnyTypeById(3));
        System.out.println(inMemoryTaskManager.getTaskOfAnyTypeById(4));
        System.out.println(inMemoryTaskManager.getTaskOfAnyTypeById(5));
        System.out.println(inMemoryTaskManager.getTaskOfAnyTypeById(6));
        System.out.println(inMemoryTaskManager.getTaskOfAnyTypeById(7));
        System.out.println(inMemoryTaskManager.getTaskOfAnyTypeById(8));

        //  Создание. Сам объект должен передаваться в качестве параметра;
        System.out.println("\n     Создание. Сам объект должен передаваться в качестве параметра:");
        System.out.println(inMemoryTaskManager.createCopyOfTaskOfAnyType(taskFirst));
        System.out.println(inMemoryTaskManager.createCopyOfTaskOfAnyType(epicTaskFirst));
        System.out.println(inMemoryTaskManager.createCopyOfTaskOfAnyType(subtaskFirstEpicTaskFirst));

        //  Обновление. Новая версия объекта с верным идентификатором передаются в виде параметра;
        inMemoryTaskManager.updateTaskOfAnyType(5, epicTaskFirst);
        inMemoryTaskManager.updateTaskOfAnyType(7, epicTaskSecond);

        System.out.println("\n     Обновление. Новая версия объекта с верным идентификатором передаются в виде"
                + " параметра:");
        System.out.println(Arrays.toString(inMemoryTaskManager.getCompleteListOfAnyTasks(inMemoryTaskManager.getEpicTaskStorage()).toArray()));

        //  Удаление по идентификатору.
        inMemoryTaskManager.removeTaskOfAnyTypeById(1);
        inMemoryTaskManager.removeTaskOfAnyTypeById(2);

        System.out.println("\n     Удаление по идентификатору:");
        System.out.println(Arrays.toString(inMemoryTaskManager.getCompleteListOfAnyTasks(inMemoryTaskManager.getTaskStorage()).toArray()));

        //  Дополнительные методы:
        //   Получение списка всех подзадач определённого эпика.
        System.out.println("\n     Получение списка всех подзадач определённого эпика:");
        System.out.println(inMemoryTaskManager.getCompleteListOfSubTaskByEpicTask(epicTaskFirst));
        System.out.println(inMemoryTaskManager.getCompleteListOfSubTaskByEpicTask(epicTaskSecond));
    }
}