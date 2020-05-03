package apbdoo.onlineLib.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void saveImageFile(Long bookId, MultipartFile file) throws IOException;
}