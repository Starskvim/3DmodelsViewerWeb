package com.example.modelsviewerweb.controllers;

import com.example.modelsviewerweb.controllers.response.DBStatsResponse;
import com.example.modelsviewerweb.controllers.response.ProgressBarResponse;
import com.example.modelsviewerweb.dto.PrintModelWebDTO;
import com.example.modelsviewerweb.repositories.JdbcTemplateDBStatsDao;
import com.example.modelsviewerweb.services.JsProgressBarService;
import com.example.modelsviewerweb.services.PrintModelService;
import com.example.modelsviewerweb.services.SyncAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModelRestController {

    private final JsProgressBarService jsProgressBarService;

    private final JdbcTemplateDBStatsDao jdbcTemplateDBStatsDao;

    private final PrintModelService printModelService;

    @GetMapping(value = "/updateProgressBar")
    public ProgressBarResponse updateTestBar(){
        return new ProgressBarResponse(JsProgressBarService.getCurrentCount(), JsProgressBarService.getCurrentTask());
    }

    @GetMapping(value = "/stats")
    public DBStatsResponse getStats(){
        System.out.println("getStats");

        DBStatsResponse result = jdbcTemplateDBStatsDao.getStats();

        System.out.println(result.getTotalModels());
        System.out.println(result.getTotalTag());
        System.out.println(result.getTotalOTH());
        System.out.println(result.getTotalSize());

        return result;
    }

    @GetMapping(value = "/sync/getModels")
    public List<String> getSavedModels(){
        return printModelService.getAllSavedNameModel();
    }

    @RequestMapping(value = "/sync/addModel", method = RequestMethod.POST)
    public void process(@RequestBody PrintModelWebDTO inputModel) {
        System.out.println( "input model - " + inputModel.getModelName());
        printModelService.addNewModel(inputModel);
    }

}
