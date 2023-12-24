package m2s06.tasks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2s06.tasks.models.Task;
import m2s06.tasks.models.enums.Priority;
import m2s06.tasks.models.enums.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private Integer id;
    private String description;
    private Status status;
    private Priority priority;
    private String inCharge;

    public TaskResponse(TaskRequest taskRequest) {
    }

    public TaskResponse(Task task) {
    }
}
