package com.curd.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.curd.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
