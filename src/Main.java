import Model.Constants.Status;
import Model.Epic;
import Model.SubTask;
import Model.Task;
import Service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
       Task task = new Task("First Model.Task", "Model.Task description", Status.NEW);
        Task task1 = new Task("Second Model.Task","SecondTaskDescription", Status.DONE );
        taskManager.createTask(task);
        taskManager.createTask(task1);





        Epic epic = new Epic("First epic", "Model.Epic description");
        Epic epic2 = new Epic("Second epic", "Model.Epic 2 description");
        Epic createdEpic = taskManager.createEpic(epic);
        Epic createdEpic2 = taskManager.createEpic(epic2);

        Integer epicId = createdEpic.getTaskId();
        Integer epic2Id = createdEpic2.getTaskId();

        SubTask subTask = new SubTask("New task", "Description", Status.NEW, epicId);
        taskManager.createSubTask(subTask);
        SubTask subTask1 = new SubTask("New task1", "Description", Status.NEW, epicId);
        SubTask subTask2 = new SubTask("New task2", "Description", Status.DONE, epic2Id);
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);


        taskManager.getTasks();
        taskManager.getEpics();
        taskManager.getSubTasks();

        taskManager.getTaskById(task.getTaskId());
        taskManager.getEpicById(epic.getTaskId());
        taskManager.getSubTaskById(subTask.getTaskId());

        taskManager.getSubTaskListByEpicId(epicId);

        taskManager.updateTask(task.getTaskId(), task);
        taskManager.updateEpic(epic.getTaskId(), epic);
        taskManager.updateSubTask(subTask.getTaskId(), subTask);

        taskManager.deleteTaskById(task.getTaskId());
        taskManager.deleteEpicById(epic.getTaskId());
        taskManager.deleteSubTaskById(subTask.getTaskId());

        taskManager.deleteAllTasks();
        taskManager.deleteAllEpics();
        taskManager.deleteAllSubTasks();

    }
}