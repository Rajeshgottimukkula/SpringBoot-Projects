package org.example.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.example.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepo extends JpaRepository<Task,Integer>{
    @Transactional
	Optional<Task> findByTaskName(String name);

}
