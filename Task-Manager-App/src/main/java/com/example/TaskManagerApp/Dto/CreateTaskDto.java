package com.example.TaskManagerApp.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTaskDto {

    private String title;
    private String description;
    private String deadline;
}
