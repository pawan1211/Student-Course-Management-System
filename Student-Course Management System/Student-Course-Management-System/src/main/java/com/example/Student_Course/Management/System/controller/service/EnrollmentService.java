package com.example.Student_Course.Management.System.controller.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.Student_Course.Management.System.data.Course;
import com.example.Student_Course.Management.System.data.Enrollment;
import com.example.Student_Course.Management.System.data.Student;
import com.example.Student_Course.Management.System.exception.ResourceNotFoundException;
import com.example.Student_Course.Management.System.repo.CourseRepository;
import com.example.Student_Course.Management.System.repo.EnrollmentRepository;
import com.example.Student_Course.Management.System.repo.StudentRepository;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository repo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public EnrollmentService(EnrollmentRepository repo, StudentRepository studentRepo, CourseRepository courseRepo) {
        this.repo = repo; this.studentRepo = studentRepo; this.courseRepo = courseRepo;
    }

    public Enrollment enroll(Long studentId, Long courseId, String semester) {
        Student s = studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", studentId));
        Course c = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", courseId));
        Enrollment e = new Enrollment(s, c, semester);
        return repo.save(e);
    }

    public Page<Enrollment> list(Pageable p) { return repo.findAll(p); }
    public List<Enrollment> listBySemester(String semester) { return repo.findBySemester(semester); }
    public Page<Enrollment> listBySemesterPaged(String semester, Pageable p) { return repo.findBySemester(semester, p); }

    public List<Course> getCoursesByStudent(Long studentId) {
        var ens = repo.findByStudentId(studentId);
        return ens.stream().map(Enrollment::getCourse).toList();
    }

    public List<Student> getStudentsByCourse(Long courseId) {
        var ens = repo.findByCourseId(courseId);
        return ens.stream().map(Enrollment::getStudent).toList();
    }

    public void deleteEnrollment(Long studentId, Long courseId) {
        List<Enrollment> list = repo.findByStudentId(studentId);
        list.stream()
            .filter(e -> e.getCourse().getId().equals(courseId))
            .findFirst()
            .ifPresent(e -> repo.deleteById(e.getId()));
    }

    public Enrollment get(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enrollment", id)); }
}
