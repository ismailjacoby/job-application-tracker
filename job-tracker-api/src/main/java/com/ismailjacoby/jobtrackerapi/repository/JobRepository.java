package com.ismailjacoby.jobtrackerapi.repository;

import com.ismailjacoby.jobtrackerapi.model.entity.Job;
import com.ismailjacoby.jobtrackerapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByUser(User user);
    List<Job> findByUserIdOrderByDateAppliedDesc(Long userId);
}
