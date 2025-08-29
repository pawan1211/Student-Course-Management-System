package com.example.Student_Course.Management.System.controller;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.Student_Course.Management.System.controller.service.StudentService;
import com.example.Student_Course.Management.System.data.Student;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Validated
public class StudentController {
    private final StudentService svc;
    public StudentController(StudentService svc){ this.svc = svc; }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Validated Student s){
        return ResponseEntity.ok(svc.create(s));
    }

    @GetMapping
    public Page<Student> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort){
        String[] parts = sort.split(",");
        Sort.Direction dir = parts.length>1 && parts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable p = PageRequest.of(page, size, Sort.by(dir, parts[0]));
        return svc.list(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id){
        return ResponseEntity.ok(svc.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody @Validated Student s){
        return ResponseEntity.ok(svc.update(id, s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort){
        String[] parts = sort.split(",");
        Sort.Direction dir = parts.length>1 && parts[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable p = PageRequest.of(page, size, Sort.by(dir, parts[0]));
        return ResponseEntity.ok(svc.searchByNamePaged(q, p));
    }
}
