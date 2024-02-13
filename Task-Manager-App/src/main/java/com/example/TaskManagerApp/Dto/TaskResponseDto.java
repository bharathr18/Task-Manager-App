package com.example.TaskManagerApp.Dto;

import com.example.TaskManagerApp.Model.NoteEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TaskResponseDto {

    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;
    private List<NoteEntity> notes;
}
