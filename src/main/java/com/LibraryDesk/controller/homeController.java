
package com.LibraryDesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.LibraryDesk.repository.BookRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import com.LibraryDesk.repository.StudentRepository;
import com.LibraryDesk.repository.BorrowRecordRepository;
import org.springframework.web.bind.annotation.PostMapping;
import com.LibraryDesk.model.Book;
import com.LibraryDesk.model.Student;
import com.LibraryDesk.model.BorrowRecord;
import java.time.LocalDate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.temporal.ChronoUnit;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class homeController {

    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public homeController(BookRepository bookRepository, StudentRepository studentRepository , BorrowRecordRepository borrowRecordRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/books")
    public String books(@RequestParam(required = false) String search, Model model) {

        if (search == null || search.isBlank()) {
            model.addAttribute("books", bookRepository.findAll());
        } else {
            model.addAttribute("books",
                    bookRepository.findByTitleContainingIgnoreCase(search));
        }

        return "books";
    }

    @GetMapping("/issue")
    public String issueBook(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());

        return "issuebook";
    }


    @PostMapping("/issue")
    public String issueBook(
            @RequestParam Long book,
            @RequestParam Integer student,
            RedirectAttributes redirectAttributes) {

        Book selectedBook = bookRepository.findById(book).orElseThrow();
        Student selectedStudent = studentRepository.findById(student).orElseThrow();

        selectedBook.issueCopy();
        bookRepository.save(selectedBook);

        BorrowRecord borrowRecord = new BorrowRecord(
                selectedBook,
                selectedStudent,
                LocalDate.now(),
                LocalDate.now().plusDays(14)
        );

        borrowRecordRepository.save(borrowRecord);

        redirectAttributes.addFlashAttribute(
                "success",
                "Book issued successfully!"
        );
        return "redirect:/issue";
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }


    @PostMapping("/students/add")
    public String addStudent(
            @RequestParam Integer studentId,
            @RequestParam String studentName,
            RedirectAttributes redirectAttributes) {

        Student student = new Student(studentId, studentName);
        studentRepository.save(student);

        redirectAttributes.addFlashAttribute(
                "success",
                "Student added successfully!"
        );
        return "redirect:/students";
    }

    @GetMapping("/records")
    public String records(Model model) {

        model.addAttribute("records", borrowRecordRepository.findAll());

        return "records";
    }
    @GetMapping("/return")
    public String returnBook(Model model) {

        model.addAttribute(
                "records",
                borrowRecordRepository.findByReturnDateIsNull()
        );

        return "returnbook";
    }

    @PostMapping("/return")
    public String returnbook(
            @RequestParam long recordId,
            RedirectAttributes redirectAttributes){
        BorrowRecord record = borrowRecordRepository.findById(recordId).orElseThrow();

        LocalDate today = LocalDate.now();

        record.setReturnDate(today);

        long lateDays = 0;

        if(today.isAfter(record.getDueDate())){
            lateDays = ChronoUnit.DAYS.between(
                    record.getDueDate(),
                    today
            );
        }

        double lateFees = lateDays*100;

        record.setLateFees(lateFees);
        record.getBook().returnCopy();

        bookRepository.save(record.getBook());
        borrowRecordRepository.save(record);

        redirectAttributes.addFlashAttribute(
                "success",
                "Book Returned Successfully"
        );
        return "redirect:/return";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/books/add")
    public String addBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String isbn,
            @RequestParam int copies,
            RedirectAttributes redirectAttributes) {

        Book book = new Book(title, author, isbn, copies);

        bookRepository.save(book);

        redirectAttributes.addFlashAttribute(
                "success",
                "Book added successfully!"
        );

        return "redirect:/books";
    }
    @GetMapping("/books/edit/{id}")
    public String editBook(
            @PathVariable Long id,
            Model model) {

        Book book = bookRepository.findById(id).orElseThrow();

        model.addAttribute("book", book);

        return "editbook";
    }
    @PostMapping("/books/edit/{id}")
    public String updateBook(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String isbn,
            @RequestParam int copies,
            RedirectAttributes redirectAttributes) {

        Book book = bookRepository.findById(id).orElseThrow();

        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setCopies(copies);

        bookRepository.save(book);

        redirectAttributes.addFlashAttribute(
                "success",
                "Book updated successfully!"
        );

        return "redirect:/books";
    }
    @PostMapping("/books/delete/{id}")
    public String deleteBook(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        bookRepository.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "success",
                "Book deleted successfully!"
        );

        return "redirect:/books";
    }
}
