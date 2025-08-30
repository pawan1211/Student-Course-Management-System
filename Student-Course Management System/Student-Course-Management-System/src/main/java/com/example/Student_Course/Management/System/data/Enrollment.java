package com.example.Student_Course.Management.System.data;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


@Table(name = "enrollments", indexes = @Index(columnList = "semester"))
public class Enrollment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Course course;

    @Column(nullable = false)
 
    private String semester; // e.g., "Spring2025"
    
    public Enrollment(Student student, Course course, String semester)
    {
    	this.semester=semester;
    	this.course=course;
    	this.student=student;
    }

  
}
