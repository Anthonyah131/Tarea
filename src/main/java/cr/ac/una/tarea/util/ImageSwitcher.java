/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea.util;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author ANTHONY
 */
public class ImageSwitcher {
    private List<Image> images;
    private ImageView imageView;
    private Timeline timeline;

    public ImageSwitcher(List images, ImageView imageView) { // Clase para cambiar imagenes con carrucel
        this.images = images;
        this.imageView = imageView;

        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            int index = (imageView.getImage() == null ? 0 : getIndex() + 1) % images.size();
            imageView.setImage((Image) images.get(index));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    
    public ImageSwitcher() {
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    private int getIndex() {
        for (int i = 0; i < images.size(); i++) {
            if (images.get(i) == imageView.getImage()) {
                return i;
            }
        }
        return -1;
    }
}

