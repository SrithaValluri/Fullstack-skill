package com.klu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Course;
import com.klu.repo.CourseRepo;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Course saveCourse(Course course) {
        return courseRepo.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepo.findById(id).orElse(null);
    }

    public Course updateCourse(Long id, Course course) {
        Course existing = courseRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(course.getTitle());
            existing.setDuration(course.getDuration());
            existing.setFee(course.getFee());
            return courseRepo.save(existing);
        }
        return null;
    }

    public String deleteCourse(Long id) {
        if(courseRepo.existsById(id)) {
            courseRepo.deleteById(id);
            return "Course deleted successfully";
        }
        return "Course not found";
    }

    public List<Course> searchCoursesByTitle(String title) {
        return courseRepo.findByTitleContainingIgnoreCase(title);
    }
}