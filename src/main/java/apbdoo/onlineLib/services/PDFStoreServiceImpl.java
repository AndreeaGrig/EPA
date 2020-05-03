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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class PDFStoreServiceImpl implements PDFStoreService{

    private static final String UPLOADED_FOLDER = "C://Users//andreea//Documents//OnlineLibrary//src//main//resources//static//pdfs//";

    private BookRepository bookRepository;

    public PDFStoreServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @TrackExecutionTime
    @Override
    public void savePdfFile(Long bookId, MultipartFile file) {
        try {
            String extension = file.getOriginalFilename().split("\\.")[1];
            if (!"pdf".equals(extension)) {
                throw new FileFormatException("The uploaded file is not pdf");
            }

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + bookId + ".pdf");
            Files.write(path, bytes);
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new EntityNoFoundException(String.format("Could not find book by id: %s", bookId)));
            book.setUrlPdf(bookId + ".pdf");
            bookRepository.save(book);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
