package com.example.TaskManagerApp.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateNoteDto {

    private String title;
    private String body;
}
