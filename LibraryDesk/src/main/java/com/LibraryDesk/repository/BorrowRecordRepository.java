package com.LibraryDesk.repository;
import com.LibraryDesk.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord , Long > {
    List<BorrowRecord> findByReturnDateIsNull();
}
