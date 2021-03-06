package nl.rws.books;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class BooksController {

    private BooksRepository repository;

    public BooksController(BooksRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public ResponseEntity<BookListResponse> getListOfBooks() {
        List<Book> books = this.repository.findAll();
        return ResponseEntity.ok(new BookListResponse(books));
    }

    @PostMapping("/books/{id}/borrow")
    public ResponseEntity<Book> borrow(
            @PathVariable String id,
            @RequestParam String memberId) {

        Book book = repository.findOne(id);
        book.setStatus("unavailable");
        book.setBorrowedBy(new Member(memberId));

        repository.save(book);

        return ResponseEntity.ok(book);
    }

}

