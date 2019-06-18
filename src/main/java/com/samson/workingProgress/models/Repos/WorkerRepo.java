package com.samson.workingProgress.models.Repos;

import com.samson.workingProgress.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, Integer> {

   default boolean checkWorkerPesel(String pesel, List<Worker> workerList){

       if (pesel.length() != 11){
           return false;
       } else {
           for (Worker workerValue: workerList){
               if (workerValue.getPesel().equals(pesel)){
                   return false;
               }
           }
       }
       return true;
   }
}
