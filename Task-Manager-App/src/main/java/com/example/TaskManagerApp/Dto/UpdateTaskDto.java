package com.example.TaskManagerApp.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateTaskDto {

    private String description;
    private String deadline;
    private Boolean completed;
}
