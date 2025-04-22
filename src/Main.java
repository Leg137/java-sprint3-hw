import managers.HistoryManager;
import managers.Managers;
import managers.TaskManager;
import tasks.EpicTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * «Трекер задач»
 */
public class Main {
    public static void main(String[] args) {
        // Тестирование
        TaskManager manager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();

        // Создали 2‑е Task задачи
        Task taskFirst = new Task("Поесть", "Принять пищу", Task.Status.NEW);
        Task taskSecond = new Task("Поспать", "Хорошенько выспаться", Task.Status.DONE);

        // Создали 1у tasks.EpicTask задачу с 2мя SubTask подзадачами
        ArrayList<EpicTask.SubTask> subTasksEpicTaskFirst = new ArrayList<>();

        EpicTask.SubTask subtaskFirstEpicTaskFirst = new EpicTask.SubTask("Закончить учебу",
                "Сдать все спринты", "Вовремя выполнить ТЗ", Task.Status.NEW);
        EpicTask.SubTask subtaskSecondEpicTaskFirst = new EpicTask.SubTask("Закончить учебу",
                "Сдать дипломный проект", "Сделать дипломный проект", Task.Status.DONE);

        subTasksEpicTaskFirst.add(subtaskFirstEpicTaskFirst);
        subTasksEpicTaskFirst.add(subtaskSecondEpicTaskFirst);

        EpicTask epicTaskFirst = new EpicTask("Закончить учебу",
                "Получить сертификат обучения", subTasksEpicTaskFirst);

        // Создали 2ю tasks.EpicTask задачу с 1‑й SubTask подзадачей
        EpicTask.SubTask subtaskFirstEpicTaskSecond = new EpicTask.SubTask("Сменить работу",
                "Закончить курс по Java", "Научиться программировать на языке Java",
                Task.Status.NEW);
        ArrayList<EpicTask.SubTask> subTasksEpicTaskSecond = new ArrayList<>();

        subTasksEpicTaskSecond.add(subtaskFirstEpicTaskSecond);

        EpicTask epicTaskSecond = new EpicTask("Сменить работу"
                , "Начать работать Java разработчиком", subTasksEpicTaskSecond);

        // Возможность хранить задачи всех типов
        manager.saveToStorage(taskFirst);
        manager.saveToStorage(taskSecond);
        manager.saveToStorage(subtaskFirstEpicTaskFirst);
        manager.saveToStorage(subtaskSecondEpicTaskFirst);
        manager.saveToStorage(epicTaskFirst);
        manager.saveToStorage(subtaskFirstEpicTaskSecond);
        manager.saveToStorage(epicTaskSecond);

        /*
          Методы для каждого из типа задач(Task/EpicTask/SubTask):
          Получение списка всех задач;
         */
        System.out.println("\n    Получение списка всех задач:");
        System.out.println(Arrays.toString(manager.getCompleteListOfAnyTasks(manager.getTaskStorage()).toArray()));
        System.out.println(Arrays.toString(manager.getCompleteListOfAnyTasks(manager.getEpicTaskStorage()).toArray()));
        System.out.println(Arrays.toString(manager.getCompleteListOfAnyTasks(manager.getSubTaskStorage()).toArray()));

        //  Удаление всех задач;
        manager.deleteAllTasksOfAnyType(manager.getEpicTaskStorage());

        System.out.println("\n     Удаление всех задач:");
        System.out.println(Arrays.toString(manager.getCompleteListOfAnyTasks(manager.getTaskStorage()).toArray()));
        System.out.println(Arrays.toString(manager.getCompleteListOfAnyTasks(manager.getEpicTaskStorage()).toArray()));
        System.out.println(Arrays.toString(manager.getCompleteListOfAnyTasks(manager.getSubTaskStorage()).toArray()));

        //  Получение по идентификатору;
        System.out.println("\n     Получение по идентификатору:");
        System.out.println("Получаем задачу с id=1 " + manager.getTaskOfAnyTypeById(1));
        System.out.println("Получаем задачу с id=2 " + manager.getTaskOfAnyTypeById(2));
        System.out.println("Получаем задачу с id=3 " + manager.getTaskOfAnyTypeById(3));
        System.out.println("Получаем задачу с id=4 " + manager.getTaskOfAnyTypeById(4));
        System.out.println("Получаем задачу с id=6 " + manager.getTaskOfAnyTypeById(6));
        System.out.println("Получаем задачу с id=1 " + manager.getTaskOfAnyTypeById(1));
        System.out.println("Получаем задачу с id=2 " + manager.getTaskOfAnyTypeById(2));
        System.out.println("Получаем задачу с id=3 " + manager.getTaskOfAnyTypeById(3));
        System.out.println("Получаем задачу с id=4 " + manager.getTaskOfAnyTypeById(4));
        System.out.println("Получаем задачу с id=6 " + manager.getTaskOfAnyTypeById(6));
        System.out.println("Получаем задачу с id=1 " + manager.getTaskOfAnyTypeById(1));
        System.out.println("Получаем задачу с id=2 " + manager.getTaskOfAnyTypeById(2));
        System.out.println("Получаем задачу с id=3 " + manager.getTaskOfAnyTypeById(3));
        System.out.println("Получаем задачу с id=4 " + manager.getTaskOfAnyTypeById(4));
        System.out.println("Получаем задачу с id=6 " + manager.getTaskOfAnyTypeById(6));
        System.out.println("Смотрим историю задач: " + historyManager.getHistory());

        //  Создание. Сам объект должен передаваться в качестве параметра;
        System.out.println("\n     Создание. Сам объект должен передаваться в качестве параметра:");
        System.out.println(manager.createCopyOfTaskOfAnyType(taskFirst));
        System.out.println(manager.createCopyOfTaskOfAnyType(epicTaskFirst));
        System.out.println(manager.createCopyOfTaskOfAnyType(subtaskFirstEpicTaskFirst));

        //  Обновление. Новая версия объекта с верным идентификатором передаются в виде параметра;
        manager.updateTaskOfAnyType(5, epicTaskFirst);
        manager.updateTaskOfAnyType(7, epicTaskSecond);

        System.out.println("\n     Обновление. Новая версия объекта с верным идентификатором передаются в виде"
                + " параметра:");
        System.out.println(Arrays.toString(manager.getCompleteListOfAnyTasks(manager.getEpicTaskStorage()).toArray()));

        //  Удаление по идентификатору.
        manager.removeTaskOfAnyTypeById(1);
        manager.removeTaskOfAnyTypeById(2);

        System.out.println("\n     Удаление по идентификатору:");
        System.out.println(Arrays.toString(manager.getCompleteListOfAnyTasks(manager.getTaskStorage()).toArray()));

        //  Дополнительные методы:
        //   Получение списка всех подзадач определённого эпика.
        System.out.println("\n     Получение списка всех подзадач определённого эпика:");
        System.out.println(manager.getCompleteListOfSubTaskByEpicTask(epicTaskFirst));
        System.out.println(manager.getCompleteListOfSubTaskByEpicTask(epicTaskSecond));
    }
}