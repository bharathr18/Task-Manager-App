package com.example.TaskManagerApp.Dto;

import com.example.TaskManagerApp.Model.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateNoteResponseDto {

    private Integer taskId;
    private NoteEntity note;
}
