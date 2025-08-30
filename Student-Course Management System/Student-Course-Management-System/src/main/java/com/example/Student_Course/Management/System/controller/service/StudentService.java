package com.example.Student_Course.Management.System.controller.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.Student_Course.Management.System.data.Student;
import com.example.Student_Course.Management.System.exception.ResourceNotFoundException;
import com.example.Student_Course.Management.System.repo.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;
    public StudentService(StudentRepository repo) { this.repo = repo; }

    public Student create(Student s) { return repo.save(s); }
    public Page<Student> list(Pageable p) { return repo.findAll(p); }
    public Student get(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", id)); }
    public Student update(Long id, Student updated){
        Student s = get(id);
        s.setName(updated.getName());
        s.setAge(updated.getAge());
        return repo.save(s);
    }
    public void delete(Long id) { repo.deleteById(id); }
    public List<Student> searchByName(String name) { return repo.findByNameContainingIgnoreCase(name); }
    public Page<Student> searchByNamePaged(String name, Pageable p) { return repo.findByNameContainingIgnoreCase(name, p); }
}
