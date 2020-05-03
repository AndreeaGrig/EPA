package apbdoo.onlineLib.services;

import apbdoo.onlineLib.aspects.executiontime.TrackExecutionTime;
import apbdoo.onlineLib.domain.Book;
import apbdoo.onlineLib.exception.EntityNoFoundException;
import apbdoo.onlineLib.exception.FileFormatException;
import apbdoo.onlineLib.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    public static final List<String> IMAGE_EXTENSIONS = Collections.unmodifiableList(Arrays.asList("jpg", "jpeg", "gif", "png"));

    private BookRepository bookRepository;

    public ImageServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @TrackExecutionTime
    @Override
    public void saveImageFile(Long bookId, MultipartFile file) throws IOException {
        String extension = file.getOriginalFilename().split("\\.")[1];
        if (!IMAGE_EXTENSIONS.contains(extension)) {
            throw new FileFormatException("The uploaded file is not jpg, jpeg, gif or png");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNoFoundException(String.format("Could not find book by id: %s", bookId)));
        Byte[] byteObjects = new Byte[file.getBytes().length];
        int i = 0;
        for (byte b : file.getBytes()) {
            byteObjects[i++] = b;
        }
        book.setCover(byteObjects);
        bookRepository.save(book);
    }
}
