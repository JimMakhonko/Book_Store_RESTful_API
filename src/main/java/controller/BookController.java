package controller;

import java.util.List;
import java.util.Optional;

import model.Book;
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
import service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Void> createBook(@RequestBody Book book){
        bookService.saveBook(book);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable("id")Long id, @RequestBody Book book){
        Optional<Book> existingBook = bookService.getBookById(id);
        if(existingBook.isPresent()){
            book.setId(id);
            bookService.saveBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id){
        Optional<Book> existingBook = bookService.getBookById(id);
        if(existingBook.isPresent()){
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
