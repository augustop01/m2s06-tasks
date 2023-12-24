package m2s06.tasks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import m2s06.tasks.models.enums.Priority;
import m2s06.tasks.models.enums.Status;

@Data
public class TaskRequest {
    private Integer id;
    @NotBlank(message = "The 'description' field cannot be blank.")
    private String description;
    @NotNull(message = "The 'status' field cannot be null.")
    private Status status;
    @NotNull(message = "The 'priority' field cannot be null.")
    private Priority priority;
    @NotBlank(message = "The 'inCharge' field cannot be blank.")
    private String inCharge;
}
