package com.qa.repo;

import com.qa.domain.Lift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiftsRepository extends JpaRepository<Lift, Long> {

}
