package jun.hackathon.server.controllers;

import jun.hackathon.server.util.ImageSplitter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/split")
public class SplitController {

    @GetMapping("/default")
    public List<String> getDefaultTiles(@RequestParam("imageid") String imageId,
                                        @RequestParam("gridsize") int gridSize) throws Exception {
        // String path = "images/" + imageId + ".png";
        // BufferedImage originalImage = ImageIO.read(new File(path));
        return ImageSplitter.getTilesFromImage(gridSize, imageId);
    }
}
