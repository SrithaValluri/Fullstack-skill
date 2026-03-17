package com.klu.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klu.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    // Custom search method
    List<Course> findByTitleContainingIgnoreCase(String title);
}