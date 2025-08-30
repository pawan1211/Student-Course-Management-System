package com.example.Student_Course.Management.System.controller;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.Student_Course.Management.System.controller.service.CourseService;
import com.example.Student_Course.Management.System.controller.service.EnrollmentService;
import com.example.Student_Course.Management.System.data.Course;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Validated
public class CourseController {
    private final CourseService svc;
    private final EnrollmentService enrollmentService;
    public CourseController(CourseService svc, EnrollmentService enrollmentService){ this.svc = svc; this.enrollmentService = enrollmentService; }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody @Validated Course c){ return ResponseEntity.ok(svc.create(c)); }

    @GetMapping
    public Page<Course> list(@RequestParam(defaultValue="0") int page,
                             @RequestParam(defaultValue="10") int size,
                             @RequestParam(defaultValue="id,asc") String sort){
        String[] parts = sort.split(",");
        Sort.Direction dir = parts.length>1 && parts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable p = PageRequest.of(page, size, Sort.by(dir, parts[0]));
        return svc.list(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable Long id){ return ResponseEntity.ok(svc.get(id)); }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody @Validated Course c){ return ResponseEntity.ok(svc.update(id, c)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ svc.delete(id); return ResponseEntity.noContent().build(); }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> byCategory(@PathVariable String category,
                                        @RequestParam(defaultValue="0") int page,
                                        @RequestParam(defaultValue="10") int size,
                                        @RequestParam(defaultValue="id,asc") String sort){
        String[] parts = sort.split(",");
        Sort.Direction dir = parts.length>1 && parts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable p = PageRequest.of(page, size, Sort.by(dir, parts[0]));
        return ResponseEntity.ok(svc.findByCategoryPaged(category, p));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<?>> studentsInCourse(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getStudentsByCourse(id));
    }
}

