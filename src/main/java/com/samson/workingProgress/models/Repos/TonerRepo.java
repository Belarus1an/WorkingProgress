package com.samson.workingProgress.models.Repos;

import com.samson.workingProgress.models.Toner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TonerRepo extends JpaRepository<Toner, Integer> {
}
