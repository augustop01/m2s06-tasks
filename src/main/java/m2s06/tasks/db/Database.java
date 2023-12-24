package m2s06.tasks.db;

import m2s06.tasks.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Integer idSequence = 0;

    public static Integer addId(){
        Database.idSequence = Database.idSequence +1;
        return Database.idSequence;
    }
    private static final List<Task> tasks = new ArrayList<>();
    public static void create(Task task){
        Database.tasks.add(task);
    }
    public static void delete(Integer id){
        Database.tasks.removeIf(task -> task.getId().equals(id));
    }
    public static List<Task> consult(){
        return Database.tasks;
    }

    public static Task get(Integer id) throws ResponseStatusException{
        return Database.tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhuma tarefa foi encontrada."));
    }

}
