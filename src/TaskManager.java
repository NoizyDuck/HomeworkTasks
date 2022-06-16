import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private Integer taskId = 0;
    private HashMap<Integer, Task> taskHashMap = new HashMap<>();
    private HashMap<Integer, Epic> epicTaskHashMap = new HashMap<>();
    private HashMap<Integer, SubTask> subTaskHashMap = new HashMap<>();

    public Task createTask(Task task) {
        taskId++;
        task.setTaskId(taskId);
        taskHashMap.put(taskId, task);

        return task;
    }

    public Epic createEpic(Epic epic) {
        taskId++;
        epic.setTaskId(taskId);
        epicTaskHashMap.put(taskId, epic);

        return epic;
    }

    public SubTask createSubTask(SubTask subTask) {
        taskId++;
        subTask.setTaskId(taskId);
        subTaskHashMap.put(taskId, subTask);
        linkSubtaskToEpic(subTask);

        return subTask;
    }

    private void linkSubtaskToEpic(SubTask subTask) {
        Integer epicId = subTask.getEpicId();
        Epic epic = epicTaskHashMap.get(epicId);
        epic.linkSubtask(subTask);
        calculateEpicStatus(epic);
    }

    public String getTasks() {
        String result = null;
        for (Integer id : taskHashMap.keySet()) {
            result += "\nTask Number " + id + ": " + taskHashMap.get(id).toString();
        }
        return result;
    }

    public String getEpics() {
        String result = null;
        for (Integer id : epicTaskHashMap.keySet()) {
            result += "\nEpic Number " + id + ": " + epicTaskHashMap.get(id).toString();
        }
        return result;
    }

    public String getSubTasks() {
        String result = null;
        for (Integer id : subTaskHashMap.keySet()) {
            result += "\nSubTask Number " + id + ": " + subTaskHashMap.get(id).toString();
        }
        return result;
    }

    public void deleteAllTasks() {
        taskHashMap.clear();
    }

    public void deleteAllEpics() {
        epicTaskHashMap.clear();
    }

    public void deleteAllSubTasks() {
        subTaskHashMap.clear();
    }

    public String getTaskById(int id) {
        String result;
        if (taskHashMap.containsKey(id)) {
            result = "\nTask Number " + id + ": " + taskHashMap.get(id).toString();
            return result;
        } else {
            Console.noTaskId();
            return null;
        }
    }

    public String getEpicById(int id) {
        String result;
        if (epicTaskHashMap.containsKey(id)) {
            result = "\nEpic Number " + id + ": " + epicTaskHashMap.get(id).toString();
            return result;
        } else {
            Console.noEpicId();
            return null;
        }
    }

    public String getSubTaskById(int id) {
        String result = null;
        if (subTaskHashMap.containsKey(id)) {
            result = "\nSubTask Number " + id + ": " + subTaskHashMap.get(id).toString();
            return result;
        } else {
            Console.noSubId();
            return null;
        }
    }

    public void updateTask(int Id, Task task) {
        if (taskHashMap.containsKey(Id)) {
            taskHashMap.put(Id, task);
        } else {
            Console.noTaskId();
        }
    }

    public void updateEpic(int id, Epic epic) {
        if (epicTaskHashMap.containsKey(id)) {
            epicTaskHashMap.put(id, epic);
        } else {
            Console.noEpicId();
        }
    }

    public void updateSubTask(int id, SubTask subTask) {
        if (subTaskHashMap.containsKey(id)) {
            subTaskHashMap.put(id, subTask);
            Epic epicToRecalculate = epicTaskHashMap.get(subTask.getEpicId());
            calculateEpicStatus(epicToRecalculate);
        } else {
            Console.noSubId();
        }
    }

    public void deleteTaskById(int Id) {
        if (taskHashMap.containsKey(Id)) {
            taskHashMap.remove(Id);
        } else {
            Console.noTaskId();
        }
    }

    public void deleteEpicById(int Id) {
        if (epicTaskHashMap.containsKey(Id)) {
            epicTaskHashMap.remove(Id);
        } else {
            Console.noEpicId();
        }
    }

    public void deleteSubTaskById(int Id) {
        if (subTaskHashMap.containsKey(Id)) {
            subTaskHashMap.remove(Id);
        } else {
            Console.noSubId();
        }
    }

    public String getSubTaskListByEpicId(int id) {
        String result = null;
        if (epicTaskHashMap.containsKey(id)) {
            result = "\nEpic Number " + id + ": " + epicTaskHashMap.get(id).toString();
            for (Epic epic : epicTaskHashMap.values()) {
                for (Integer subId : epic.getSubTasksIds()) {
                    result += "\nSubTask Number " + subId + ": " + subTaskHashMap.get(subId).toString();
                }
            }
            return result;
        } else {
            Console.noEpicId();
            return null;
        }
    }

    private void calculateEpicStatus(Epic epic) {
        Integer statusNew = 0;
        Integer statusDone = 0;
        List<Integer> subTasksIds = epic.getSubTasksIds();
        int size = subTasksIds.size();
        for (Integer subTaskId : subTasksIds) {
            SubTask subTask = subTaskHashMap.get(subTaskId);
            switch (subTask.getStatus()) {
                case NEW:
                    statusNew++;
                    break;
                case DONE:
                    statusDone++;
                    break;
            }
        }
        if (statusNew == size) {
            epic.setStatus(Status.NEW);
        } else if (statusDone == size) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}
