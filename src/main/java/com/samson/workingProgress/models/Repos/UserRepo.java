package com.samson.workingProgress.models.Repos;

import com.samson.workingProgress.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
