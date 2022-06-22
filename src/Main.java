import java.util.List;
import model.constants.Status;
import model.Epic;
import model.SubTask;
import model.Task;
import service.HistoryManager;
import service.TaskManager;
import utils.Managers;


public class Main {
    public static void main(String[] args) {
        TaskManager inMemoryTaskManager = Managers.getDefault();
        Task task = new Task("First Model.Task", "Model.Task description", Status.NEW);
        Task task1 = new Task("Second Model.Task","SecondTaskDescription", Status.DONE );
        inMemoryTaskManager.createTask(task);
        inMemoryTaskManager.createTask(task1);





        Epic epic = new Epic("First epic", "Model.Epic description");
        Epic epic2 = new Epic("Second epic", "Model.Epic 2 description");
        Epic createdEpic = inMemoryTaskManager.createEpic(epic);
        Epic createdEpic2 = inMemoryTaskManager.createEpic(epic2);

        Integer epicId = createdEpic.getTaskId();
        Integer epic2Id = createdEpic2.getTaskId();

        SubTask subTask = new SubTask("New task", "Description", Status.NEW, epicId);
        inMemoryTaskManager.createSubTask(subTask);
        SubTask subTask1 = new SubTask("New task1", "Description", Status.NEW, epicId);
        SubTask subTask2 = new SubTask("New task2", "Description", Status.DONE, epic2Id);
        inMemoryTaskManager.createSubTask(subTask1);
        inMemoryTaskManager.createSubTask(subTask2);


        inMemoryTaskManager.getTasks();
        inMemoryTaskManager.getEpics();
        inMemoryTaskManager.getSubTasks();

        inMemoryTaskManager.getTaskById(task.getTaskId());
        inMemoryTaskManager.getEpicById(epic.getTaskId());
        inMemoryTaskManager.getSubTaskById(subTask.getTaskId());
     inMemoryTaskManager.getSubTaskById(subTask.getTaskId());
     inMemoryTaskManager.getSubTaskById(subTask.getTaskId());
     inMemoryTaskManager.getSubTaskById(subTask.getTaskId());
     inMemoryTaskManager.getTaskById(task.getTaskId());
     inMemoryTaskManager.getEpicById(epic.getTaskId());
     inMemoryTaskManager.getTaskById(task.getTaskId());
     inMemoryTaskManager.getEpicById(epic.getTaskId());
     inMemoryTaskManager.getSubTaskById(subTask.getTaskId());
     inMemoryTaskManager.getSubTaskById(subTask.getTaskId());

        inMemoryTaskManager.getSubTaskListByEpicId(epicId);

        inMemoryTaskManager.updateTask(task.getTaskId(), task);
        inMemoryTaskManager.updateEpic(epic.getTaskId(), epic);
        inMemoryTaskManager.updateSubTask(subTask.getTaskId(), subTask);

        inMemoryTaskManager.deleteTaskById(task.getTaskId());
        inMemoryTaskManager.deleteEpicById(epic.getTaskId());
        inMemoryTaskManager.deleteSubTaskById(subTask.getTaskId());

        inMemoryTaskManager.deleteAllTasks();
        inMemoryTaskManager.deleteAllEpics();
        inMemoryTaskManager.deleteAllSubTasks();
        HistoryManager defaultHistory = Managers.getDefaultHistory();
        List<Task> history = defaultHistory.getHistory();
        System.out.println(history.toString());
    }
}