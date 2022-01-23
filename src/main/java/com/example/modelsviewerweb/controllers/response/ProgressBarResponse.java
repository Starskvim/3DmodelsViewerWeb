package com.example.modelsviewerweb.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProgressBarResponse {

    private Integer currentCount;

    private String currentTask;

}
