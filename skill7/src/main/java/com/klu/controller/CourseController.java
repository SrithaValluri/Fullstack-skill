package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Course;
import com.klu.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/save")
    public ResponseEntity<Course> saveCourse(@RequestBody Course c) {
        return new ResponseEntity<>(courseService.saveCourse(c), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);

        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course c) {
        Course updated = courseService.updateCourse(id, c);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        String msg = courseService.deleteCourse(id);

        if (msg.equals("Course deleted successfully")) {
            return ResponseEntity.ok(msg);
        } else {
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> searchCourse(@PathVariable String title) {
        return ResponseEntity.ok(courseService.searchCoursesByTitle(title));
    }
}