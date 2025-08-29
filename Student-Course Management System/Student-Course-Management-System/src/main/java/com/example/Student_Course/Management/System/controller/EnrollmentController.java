package com.example.Student_Course.Management.System.controller;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Student_Course.Management.System.controller.service.EnrollmentService;
import com.example.Student_Course.Management.System.data.Enrollment;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService svc;
    public EnrollmentController(EnrollmentService svc){ this.svc = svc; }

    @PostMapping("/{studentId}/{courseId}")
    public ResponseEntity<Enrollment> enroll(@PathVariable Long studentId, @PathVariable Long courseId,
                                             @RequestParam(defaultValue = "Spring2025") String semester){
        Enrollment e = svc.enroll(studentId, courseId, semester);
        return ResponseEntity.ok(e);
    }

    @GetMapping
    public Page<Enrollment> list(@RequestParam(defaultValue="0") int page,
                                 @RequestParam(defaultValue="10") int size,
                                 @RequestParam(defaultValue="id,asc") String sort){
        String[] parts = sort.split(",");
        Sort.Direction dir = parts.length>1 && parts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable p = PageRequest.of(page,size,Sort.by(dir,parts[0]));
        return svc.list(p);
    }

    @GetMapping("/semester/{sem}")
    public ResponseEntity<?> bySemester(@PathVariable String sem,
                                        @RequestParam(defaultValue="0") int page,
                                        @RequestParam(defaultValue="10") int size,
                                        @RequestParam(defaultValue="id,asc") String sort){
        String[] parts = sort.split(",");
        Sort.Direction dir = parts.length>1 && parts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable p = PageRequest.of(page,size,Sort.by(dir,parts[0]));
        return ResponseEntity.ok(svc.listBySemesterPaged(sem, p));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<?>> coursesByStudent(@PathVariable Long studentId){
        return ResponseEntity.ok(svc.getCoursesByStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<?>> studentsByCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(svc.getStudentsByCourse(courseId));
    }

    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long studentId, @PathVariable Long courseId){
        svc.deleteEnrollment(studentId, courseId);
        return ResponseEntity.noContent().build();
    }
}
