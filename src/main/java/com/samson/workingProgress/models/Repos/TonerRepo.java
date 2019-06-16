package com.samson.workingProgress.models.Repos;

import com.samson.workingProgress.models.Toner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TonerRepo extends JpaRepository<Toner, Integer> {

    default boolean checkTonerName(String tonerName, List<Toner> all){

        for (Toner toner: all){
            if (toner.getTonerName().equals(tonerName)){
                return false;
            }
        }
        return true;
    }
}
