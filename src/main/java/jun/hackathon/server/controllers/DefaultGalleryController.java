package jun.hackathon.server.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/default/gallery")
public class DefaultGalleryController {
    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(@RequestParam("imageid") String imageId) throws IOException {
        String path = "images/" + imageId + ".png";
        byte[] imageData = Files.readAllBytes(Paths.get(path));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }
}
