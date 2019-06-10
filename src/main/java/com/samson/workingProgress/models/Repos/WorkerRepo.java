package com.samson.workingProgress.models.Repos;

import com.samson.workingProgress.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, Integer> {

   default boolean checkWorkerPesel(String pesel, List<Worker> oldWorkerList){

       if (pesel.length() != 12){
           return false;
       } else {
           for (Worker workerValue: oldWorkerList){
               if (workerValue.getPesel().equals(pesel)){
                   return false;
               }
           }
       }
       return true;
   }
}
