package jun.hackathon.server.controllers;

import jun.hackathon.server.services.GalleryService;
import jun.hackathon.server.util.exceptions.ImageNotFoundException;
import jun.hackathon.server.util.exceptions.UnsupportedFileTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            String imageId = galleryService.uploadImage(file);
            Map<String, String> response = new HashMap<>();
            response.put("imageid", imageId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UnsupportedFileTypeException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("reason", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } catch (IOException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("reason", e.getMessage());
            map.put("stack", e.getStackTrace());
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

@GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam("imageid") String imageId) throws Exception {
            return galleryService.getImage(imageId);
    }

    @GetMapping()
    public List<String> getAllImages() throws Exception {
        return galleryService.getAllImages();
    }
}
