package com.example.modelsviewerweb.controllers;

import com.example.modelsviewerweb.controllers.response.DBStatsResponse;
import com.example.modelsviewerweb.controllers.response.ProgressBarResponse;
import com.example.modelsviewerweb.repositories.JdbcTemplateDBStatsDao;
import com.example.modelsviewerweb.services.JsProgressBarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ModelRestController {

    private final JsProgressBarService jsProgressBarService;

    private final JdbcTemplateDBStatsDao jdbcTemplateDBStatsDao;

    @GetMapping(value = "/updateProgressBar")
    public ProgressBarResponse updateTestBar(){
        return new ProgressBarResponse(JsProgressBarService.getCurrentCount(), JsProgressBarService.getCurrentTask());
    }

    @GetMapping(value = "/stats")
    public DBStatsResponse getStats(){
        return jdbcTemplateDBStatsDao.getStats();
    }
}
