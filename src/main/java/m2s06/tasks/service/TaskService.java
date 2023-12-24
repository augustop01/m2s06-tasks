package m2s06.tasks.service;

import m2s06.tasks.db.Database;
import m2s06.tasks.dto.TaskRequest;
import m2s06.tasks.dto.TaskResponse;
import m2s06.tasks.models.Task;
import m2s06.tasks.models.enums.Priority;
import m2s06.tasks.models.enums.Status;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    public Task create(TaskRequest taskRequest){
        Task task = new Task(taskRequest);
        Database.create(task);
        return task;
    }

    public List<Task> listAll(){
        return Database.consult();
    }
    public List<Task> listAll(Status status, Priority priority, String inCharge){
        List<Task> tasks = Database.consult();
        if (status != null){
            return tasks.stream()
                    .filter(task -> task.getStatus().equals(status)).collect(Collectors.toList());
        }
        if (priority != null){
            return tasks.stream()
                    .filter(task -> task.getPriority().equals(priority)).collect(Collectors.toList());
        }
        if (inCharge != null){
            return tasks.stream()
                    .filter(task -> task.getInCharge().equals(inCharge)).collect(Collectors.toList());
        }
        return tasks;
    }

    public TaskResponse update(Integer id, TaskRequest taskRequest) throws ResponseStatusException{
        Task task = Database.get(id);
        task.setInCharge(taskRequest.getInCharge() != null && !taskRequest.getInCharge().isBlank() ? taskRequest.getInCharge() : task.getInCharge());
        task.setDescription(taskRequest.getDescription() != null && !taskRequest.getDescription().isBlank() ? taskRequest.getDescription() : task.getDescription());
        task.setPriority(taskRequest.getPriority() != null ? taskRequest.getPriority() : task.getPriority());
        task.setStatus(taskRequest.getStatus() != null ? taskRequest.getStatus() : task.getStatus());

        return new TaskResponse(taskRequest);
    }

    public void delete(Integer id){
        Database.delete(id);
    }
}
