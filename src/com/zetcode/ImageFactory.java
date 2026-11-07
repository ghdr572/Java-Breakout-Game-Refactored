package com.zetcode;

import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

// Flyweight Factory: loads each image only once and shares it
// between all game objects that use the same picture.
public class ImageFactory {

    // Cache of already loaded images (key = file path)
    private static final HashMap<String, Image> imageMap = new HashMap<>();

    // Returns the shared image for the given path.
    // If it is not loaded yet, load it once and store it in the cache.
    public static Image getImage(String path) {

        Image image = imageMap.get(path);

        if (image == null) {
            image = new ImageIcon(path).getImage();
            imageMap.put(path, image);
            System.out.println("Loading image: " + path);
        }

        return image;
    }
}
