package apbdoo.onlineLib.services;

import apbdoo.onlineLib.aspects.executiontime.TrackExecutionTime;
import apbdoo.onlineLib.domain.Book;
import apbdoo.onlineLib.domain.Category;
import apbdoo.onlineLib.exception.EntityNoFoundException;
import apbdoo.onlineLib.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @TrackExecutionTime
    @Override
    public Set<Book> getBooks() {
        Set<Book> books = new HashSet<Book>();
        bookRepository.findAll(Sort.by("addDate").descending()).iterator().forEachRemaining(books::add);
        return books;
    }

    @TrackExecutionTime
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNoFoundException(String.format("Could not find book by id: %s", id)));
    }

    @TrackExecutionTime
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @TrackExecutionTime
    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @TrackExecutionTime
    @Override
    public Page<Book> getPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @TrackExecutionTime
    @Override
    public Page<Book> getCategoryPage(String category, Pageable pageable) {
        return bookRepository.findAllByCategory(Category.valueOf(category), pageable);
    }
}

