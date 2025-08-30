package com.example.Student_Course.Management.System.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Student_Course.Management.System.data.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCategoryIgnoreCase(String category);
    Page<Course> findByCategoryIgnoreCase(String category, Pageable pageable);
}
