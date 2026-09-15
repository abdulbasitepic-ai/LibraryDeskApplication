package com.LibraryDesk.repository;
import com.LibraryDesk.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentRepository extends JpaRepository<Student , Integer> {

}
