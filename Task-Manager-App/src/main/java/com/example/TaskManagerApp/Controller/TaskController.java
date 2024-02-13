package com.example.TaskManagerApp.Controller;

import com.example.TaskManagerApp.Dto.CreateTaskDto;
import com.example.TaskManagerApp.Dto.ErrorResponseDto;
import com.example.TaskManagerApp.Dto.TaskResponseDto;
import com.example.TaskManagerApp.Dto.UpdateTaskDto;
import com.example.TaskManagerApp.Model.TaskEntity;
import com.example.TaskManagerApp.Service.NotesService;
import com.example.TaskManagerApp.Service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final NotesService notesService;
    private final ModelMapper modelMapper = new ModelMapper();

    public TaskController(TaskService taskService, NotesService notesService) {
        this.taskService = taskService;
        this.notesService = notesService;
    }



    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable("id") Integer id) {
        var task = taskService.getTaskById(id);
        var notes = notesService.getNotesForTask(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        var taskResponse = modelMapper.map(task, TaskResponseDto.class);
        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDto body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDto body) throws ParseException {
        var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleErrors(Exception e) {
        if (e instanceof ParseException) {
            return ResponseEntity.badRequest().body(new ErrorResponseDto("Invalid date format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDto("Internal Server Error"));
    }
}
