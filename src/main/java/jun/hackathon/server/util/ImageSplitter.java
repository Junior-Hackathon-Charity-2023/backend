package jun.hackathon.server.util;

import jun.hackathon.server.util.storage.ImageStorage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageSplitter {
    public static List<String> getTilesFromImage(int gridSize, String imageId) throws IOException {
        String grid     = gridSize + "x" + gridSize;
        String imageDir = "images/";
        String cropsDir = imageDir + "crops/";
        String cropDir  = cropsDir + imageId + "_" + grid + "/";

        File directory = new File(cropDir);
        if (!directory.exists()) {
            directory.mkdirs();

            String originalPath = imageDir + imageId + ".png";
            String cmd = "magick " + originalPath + " -crop " + grid + " " + cropDir + "%d.png";

            Runtime.getRuntime().exec(cmd);
        }

        List<String> urls = new ArrayList<>();

        int nImages = gridSize * gridSize;
        for (int i = 0; i < nImages; ++i) {
            urls.add(cropDir + i + ".png");
        }
        return urls;
    }
}

