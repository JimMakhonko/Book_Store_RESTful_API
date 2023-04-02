package service;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 *  This class will implement business logic related to our
 *  Book entities, such as creating new books, retrieving all books,
 *  and updating books. Here's an example implementation:
 */
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public List<Book> getAllBooks(){
        return (List<Book>) bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBook(Long Id){
        bookRepository.deleteById(Id);
    }
}
