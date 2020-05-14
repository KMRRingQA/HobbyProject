package com.qa.repo;

import com.qa.domain.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WindowsRepository extends JpaRepository<Door, Long> {
}
