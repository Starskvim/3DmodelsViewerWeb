package com.example.modelsviewerweb.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class JsProgressBarService {
    private static volatile AtomicInteger currentCount = new AtomicInteger(0);

    private static volatile Integer totalCount = 0;

    private static volatile String currentTask = "Start Progress Bar";

    public static void setCurrentCount(AtomicInteger count){
        double count2 = count.get();
        double totalCount2 = (double) totalCount;
        currentCount.set((int) Math.round(count2 / totalCount2 * 100.0));
    }

    public static void setTotalCount(int total){
        totalCount = total;
    }

    public static synchronized void setCurrentTask (String task){
        currentTask = task;
    }

    public static Integer getCurrentCount(){
        return currentCount.get();
    }

    public static String getCurrentTask(){
        return currentTask;
    }

}
