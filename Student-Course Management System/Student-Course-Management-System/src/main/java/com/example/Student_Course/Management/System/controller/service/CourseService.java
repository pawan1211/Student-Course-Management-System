package com.example.Student_Course.Management.System.controller.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.Student_Course.Management.System.data.Course;
import com.example.Student_Course.Management.System.exception.ResourceNotFoundException;
import com.example.Student_Course.Management.System.repo.CourseRepository;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repo;
    public CourseService(CourseRepository repo) { this.repo = repo; }

    public Course create(Course c) { return repo.save(c); }
    public Page<Course> list(Pageable p) { return repo.findAll(p); }
    public Course get(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", id)); }
    public Course update(Long id, Course updated) {
        Course c = get(id);
        c.setTitle(updated.getTitle());
        c.setCategory(updated.getCategory());
        return repo.save(c);
    }
    public void delete(Long id) { repo.deleteById(id); }
    public List<Course> findByCategory(String category) { return repo.findByCategoryIgnoreCase(category); }
    public Page<Course> findByCategoryPaged(String category, Pageable p) { return repo.findByCategoryIgnoreCase(category, p); }
}
