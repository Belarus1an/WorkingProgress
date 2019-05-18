package com.samson.workingProgress.models.Repos;

import com.samson.workingProgress.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, Integer> {


}
