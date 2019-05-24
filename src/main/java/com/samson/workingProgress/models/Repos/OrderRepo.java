package com.samson.workingProgress.models.Repos;

import com.samson.workingProgress.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

}
