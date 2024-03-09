package com.curd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curd.entity.Student;
import com.curd.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class HomeController {

	@Autowired
	private StudentRepository studentrepo;

	@GetMapping("/all")
	public List<Student> getStudent() {
		return studentrepo.findAll();
	}

	@GetMapping("/id/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentrepo.findById(id).orElse(null);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	@PostMapping("/add")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student savedStudent = studentrepo.save(student);
		return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("id")long id){
		Optional<Student> s = studentrepo.findById(id);
		if(s != null) {
			studentrepo.deleteById((long)id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Student> update(@PathVariable("id") long id, @RequestBody Student updateStudent) {
		Student existingStudent = studentrepo.findById(id).orElse(null);
        if (existingStudent != null) {
            updateStudent.setId(id);
            Student savedStudent = studentrepo.save(updateStudent);
            return new ResponseEntity<>(savedStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}


	
}