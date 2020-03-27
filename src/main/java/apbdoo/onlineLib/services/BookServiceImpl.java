package apbdoo.onlineLib.services;

import apbdoo.onlineLib.aspects.TrackExecutionTime;
import apbdoo.onlineLib.domain.Book;
import apbdoo.onlineLib.domain.Category;
import apbdoo.onlineLib.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
            books.forEach(book -> log.info(book.getTitle()));
            return books;
        }

        @TrackExecutionTime
        @Override
        public Book findBookById(Long l) {

            Optional<Book> bookOptional = bookRepository.findById(l);

            if (!bookOptional.isPresent()) {
                throw new RuntimeException("Book not found!");
            }

            return bookOptional.get();
        }

        @TrackExecutionTime
        @Override
        public Book saveBook(Book book) {
            Book savedBook = bookRepository.save(book);
            return savedBook;
        }

        @TrackExecutionTime
        @Override
        public void deleteBookById(Long id) {
            bookRepository.deleteById(id);
        }

    @TrackExecutionTime
    @Override
    public Page<Book> getPage(Pageable pageable){
        Page<Book> bookPage = bookRepository.findAll(pageable);
        return bookPage;
    }

    @TrackExecutionTime
    @Override
    public Page<Book> getCategoryPage(String category, Pageable pageable){
        List<Book> books = bookRepository.findAllByCategory(Category.valueOf(category), pageable);
        Page<Book> bookPage =  new PageImpl<>(books, pageable, books.size());
        return bookPage;
    }
}

