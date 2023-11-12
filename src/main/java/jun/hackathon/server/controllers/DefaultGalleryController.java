package jun.hackathon.server.controllers;

import org.apache.commons.lang3.ArrayUtils;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/default/gallery")
public class DefaultGalleryController {
    @GetMapping("/image")
    public ResponseEntity<Byte[]> getImage(@RequestParam("imageid") String imageId) throws IOException {
        String path = "images/" + imageId + ".png";
        byte[] imageData = Files.readAllBytes(Paths.get(path));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(ArrayUtils.toObject(imageData));
    }

    @GetMapping
    public List<Byte[]> getAllImages() throws IOException {
        List<Byte[]> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String path = "images/" + i + ".png";
            byte[] imageData = Files.readAllBytes(Paths.get(path));
            list.add(ArrayUtils.toObject(imageData));
        }
        return list;
    }
}
