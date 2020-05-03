package apbdoo.onlineLib.repositories;

import apbdoo.onlineLib.domain.Book;
import apbdoo.onlineLib.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<Book> findAllByCategory(Category category, Pageable pageable);
}
