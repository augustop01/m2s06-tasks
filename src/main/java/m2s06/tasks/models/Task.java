package m2s06.tasks.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2s06.tasks.db.Database;
import m2s06.tasks.dto.TaskRequest;
import m2s06.tasks.models.enums.Priority;
import m2s06.tasks.models.enums.Status;

@Data
@Entity
@Table(name = "TAREFAS")
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;
    @Column(nullable = false)
    private String inCharge;

    public Task(TaskRequest taskDTO){
        this.id = Database.addId();
        this.description = taskDTO.getDescription();
        this.status = taskDTO.getStatus();
        this.priority = taskDTO.getPriority();
        this.inCharge = taskDTO.getInCharge();
    }
}
