package com.example.TaskManagerApp.Controller;

import com.example.TaskManagerApp.Dto.CreateNoteDto;
import com.example.TaskManagerApp.Dto.CreateNoteResponseDto;
import com.example.TaskManagerApp.Model.NoteEntity;
import com.example.TaskManagerApp.Service.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    private NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId) {
        var notes = notesService.getNotesForTask(taskId);

        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDto> addNote(
            @PathVariable("taskId") Integer taskId,
            @RequestBody CreateNoteDto body
    ) {
        var note = notesService.addNoteForTask(taskId, body.getTitle(), body.getBody());

        return ResponseEntity.ok(new CreateNoteResponseDto(taskId, note));
    }
}
