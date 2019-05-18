package com.samson.workingProgress.models.Repos;

import com.samson.workingProgress.models.Worker;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class Workers {

    List<Worker> ALL_WORKER = Arrays.asList(
            new Worker("Pavel", 1111222292),
            new Worker("Piotr", 1111222288),
            new Worker("Mateusz", 1111222282),
            new Worker("Tadeusz", 1111222293),
            new Worker("Mariusz", 1111222290)
    );

    public List<Worker> getALL_WORKER() {
        return ALL_WORKER;
    }
}
