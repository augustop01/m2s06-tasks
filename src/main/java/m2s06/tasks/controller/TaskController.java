package m2s06.tasks.controller;
import jakarta.validation.Valid;
import m2s06.tasks.db.Database;
import m2s06.tasks.dto.TaskRequest;
import m2s06.tasks.dto.TaskResponse;
import m2s06.tasks.models.Task;
import m2s06.tasks.models.enums.Priority;
import m2s06.tasks.models.enums.Status;
import m2s06.tasks.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> consult(
            @RequestParam(name = "status", required = false) Status status,
            @RequestParam(name = "priority", required = false) Priority priority,
            @RequestParam(name = "inCharge", required = false) String inCharge){
        var tasks = taskService.listAll(status, priority, inCharge);
        var result = new ArrayList<TaskResponse>();
        for (Task task : tasks){
            var taskDTO = mapper.map(task, TaskResponse.class);
            result.add(taskDTO);
        }
        if (result.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest taskRequest){
        var task = taskService.create(taskRequest);
        var result = mapper.map(task, TaskResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable("id") Integer id, @RequestBody TaskRequest taskRequest){
        taskService.update(id, taskRequest);
        var tasks = taskService.listAll();
        var taskResp = tasks.stream().filter(task1 -> task1.getId().equals(id)).findFirst();
        if (taskResp.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var result = mapper.map(taskResp, TaskResponse.class);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> delete(@PathVariable("id") Integer id){
        var tasks = taskService.listAll();
        var task = tasks.stream().filter(task1 -> task1.getId().equals(id)).findFirst();
        if (task.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var result = mapper.map(task, TaskResponse.class);
        taskService.delete(id);
        return ResponseEntity.ok(result);
    }
}